/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.Exceptions.NoSuchUserException;
import elections.NewExceptions.InvalidDeleteException;
import elections.NewExceptions.InvalidVoteException;
import elections.model.User;
import elections.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author чтепоноза
 */
@Transactional
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
    public boolean deleteByLogin(String login) throws NoSuchUserException, InvalidDeleteException{
        if(!userRepository.existsById(login))
            throw new NoSuchUserException("Удаляемый пользователь не существует " + login, login);
        userRepository.deleteById(login);
        if(userRepository.existsById(login))
            throw new InvalidDeleteException("Пользователь существует после удаления " + login, login);
        return true;
    }
    
    @Override
    public void markAsVoted(String login) throws NoSuchUserException, InvalidVoteException{
        
        if(!userRepository.existsById(login))
            throw new NoSuchUserException("Не найден пользователь " + login, login);
        userRepository.markAsVoted(login);
        User user = findByLogin(login).orElseThrow(() -> new NoSuchUserException("Не найден пользователь " + login, login));
        if (!user.isVoted())
            throw new InvalidVoteException("Не зафиксирован голос пользователя " + login, login);
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
    public void forgetAllVotes(){
        userRepository.forgetAllVotes();
    }
    
    @Override
    public int countWhoVoted(){
        return userRepository.countAllWhoVoted();
    }
}
