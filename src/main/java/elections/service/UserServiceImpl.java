/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.Exceptions.NoSuchUserException;
import elections.model.User;
import elections.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author чтепоноза
 */

@Service //Без этой аннотации не сработает почему-то Autowired
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }
    
    @Override
    public Optional<User> findByLogin(String login){
        return userRepository.findById(login); //login здесь выступает в качестве id
    }
    
    @Override
    public void deleteByLogin(String login){
        userRepository.deleteById(login); //login здесь выступает в качестве id
    }
    
    @Override
    public void markAsVoted(String login){
        userRepository.markAsVoted(login);
    }
    
    @Override
    public boolean existsByLogin(String login){
        return userRepository.existsById(login);
    }
    
    @Override 
    public boolean checkAdminByLogin(String login) throws NoSuchUserException{
        User user = userRepository.findById(login).orElseThrow(() -> new NoSuchUserException("Не найден пользователь " + login, login));
        return user.isAdmin();
    }
    
    @Override
    public void forgetAllVotes(boolean voted){
        userRepository.forgetAllVotes(voted);
    }
}
