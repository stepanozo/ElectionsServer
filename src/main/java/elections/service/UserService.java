/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.Exceptions.NoSuchUserException;
import elections.model.User;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User create(User user);
    
    public Iterable<User> findAll();
    
    public Optional<User> findByLogin(String login);
    
    public void deleteByLogin(String login);
    
    public void markAsVoted(String login);
    
    public boolean existsByLogin(String login);
    
    public boolean checkAdminByLogin(String login) throws NoSuchUserException;
    
    public void forgetAllVotes(boolean voted);
}
