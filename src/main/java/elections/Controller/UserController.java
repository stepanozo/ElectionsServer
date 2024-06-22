/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.Controller;

import elections.service.UserService;
import elections.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    //Пока что это демонстрационные методы
    
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
