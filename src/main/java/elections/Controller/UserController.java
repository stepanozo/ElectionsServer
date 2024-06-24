/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.Controller;

import elections.Exceptions.NoSuchUserException;
import elections.MD5Hashing.MD5Hashing;
import elections.service.UserService;
import elections.model.User;
import elections.security.LoginData;
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
    
    @PostMapping
    public User create(@RequestBody User user){
        return userService.create(user);
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
    public Iterable<User> getAll(){
        return userService.findAll();
    }
    
    @GetMapping("/{login}") //В данном случае login выступает как id
    public User getByLogin(@PathVariable String login){
        return userService.findByLogin(login).orElse(null);
    }
    
    @DeleteMapping("/{login}") //В данном случае login выступает как id
    public void deleteById(@PathVariable String login){
        userService.deleteByLogin(login);
    }
    
    @PatchMapping("/{login}:mark-as-voted")
    public void markAsVoted(@PathVariable String login){
        userService.markAsVoted(login);
    }
    
    
}
