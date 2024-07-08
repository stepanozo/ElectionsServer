/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.Controller;

import elections.NewExceptions.NoSuchUserException;
import elections.MD5Hashing.MD5Hashing;
import elections.NewExceptions.AlreadyAdminException;
import elections.NewExceptions.InvalidUserDeleteException;
import elections.NewExceptions.InvalidUserVoteException;
import elections.NewExceptions.NotAdminException;
import elections.service.UserService;
import elections.model.User;
import elections.security.LoginData;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author чтепоноза
 */

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Transactional
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        userService.create(user);
        
        if(userService.existsByLogin(user.getLogin())){
            return new ResponseEntity<>(user, HttpStatus.CREATED); //Вернули статус ОК, т.е. смогли авторизоваться. Позднее сюда надо добавить ТОКЕН.
        }
        else return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginData loginData){
        try{
            String login = loginData.getLogin();
            User user = userService.findByLogin(login).orElseThrow(() -> new NoSuchUserException("Не удалось найти пользователя " + login, login));
            System.out.print(MD5Hashing.hashPassword(loginData.getPassword()));
            if(user.getPasswordHash().equals(MD5Hashing.hashPassword(loginData.getPassword())))
                return new ResponseEntity<>(user, HttpStatus.OK); //Вернули статус ОК, т.е. смогли авторизоваться. Позднее сюда надо добавить ТОКЕН.
            else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login or password");
        } catch (NoSuchUserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login or password");
        }
    }
    
    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginData loginData){
        
        String login = loginData.getLogin();
        if(userService.existsByLogin(login))            //Вместо .build() можно использовать .body() чтобы вернуть тело
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); //Если такой юзер уже существует
        else if(userService.create(new User(loginData.getLogin(), MD5Hashing.hashPassword(loginData.getPassword()), false, false))!=null)
            return ResponseEntity.status(HttpStatus.CREATED).build(); //Вернули статус CREATED, т.е. смогли пользователя создать.
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    @GetMapping
    public ResponseEntity<Iterable<User>> getAll(){
        
        Iterable<User> users = userService.findAll();
       
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @GetMapping("/{login}") //В данном случае login выступает как id
    public ResponseEntity<User> getByLogin(@PathVariable String login){

        User user = userService.findByLogin(login).orElse(null);
        if(user == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return new ResponseEntity<>(user, HttpStatus.OK);
 
    }
    
    @GetMapping("/{login}:check-if-admin") //В данном случае login выступает как id
    public ResponseEntity<Boolean> checkAdminByLogin(@PathVariable String login){
        try{
            return new ResponseEntity<>(userService.checkAdminByLogin(login), HttpStatus.OK);
        } catch (NoSuchUserException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        
    }
   
    @DeleteMapping("/{login}") //В данном случае login выступает как id
    public ResponseEntity<?> deleteById(@PathVariable String login) { 
        try{
            boolean success = userService.deleteByLogin(login);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchUserException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (InvalidUserDeleteException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    @PatchMapping("/{login}/mark-as-voted")
    public ResponseEntity<?> markAsVoted(@PathVariable String login) {
        try{
            userService.markAsVoted(login);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchUserException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(InvalidUserVoteException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    @PatchMapping("/{login}/mark-as-admin/{admin}")
    public ResponseEntity<?> markAsAdmin(@PathVariable String login, @PathVariable boolean admin) {
        try{
            userService.markAsAdmin(login, admin);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchUserException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(AlreadyAdminException e){
            return new ResponseEntity<>("Пользователь уже является админом", HttpStatus.CONFLICT);
        } catch(NotAdminException e){
            return new ResponseEntity<>("Пользователь уже не является админом", HttpStatus.CONFLICT);
        }
    }
    
    @Transactional
    @PatchMapping("/forgetVotes")
    public ResponseEntity<?> forgetAllVotes(){
        userService.forgetAllVotes();
        if(userService.countWhoVoted() > 0)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        else return ResponseEntity.status(HttpStatus.OK).build();
            
    }
    
    
}
