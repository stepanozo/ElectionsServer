/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.Exceptions.NoSuchUserException;
import elections.NewExceptions.InvalidDeleteException;
import elections.NewExceptions.InvalidVoteException;
import elections.model.User;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public interface UserService {
    public User create(User user);
    
    public Iterable<User> findAll();
    
    public Optional<User> findByLogin(String login);
    
    public boolean deleteByLogin(String login) throws NoSuchUserException, InvalidDeleteException;
    
    public void markAsVoted(String login) throws NoSuchUserException, InvalidVoteException;
    
    public boolean existsByLogin(String login);
    
    public boolean checkAdminByLogin(String login) throws NoSuchUserException;
    
    public void forgetAllVotes();
    
    public int countWhoVoted();
}
