/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.Exceptions.NoSuchUserException;
import elections.NewExceptions.AlreadyAdminException;
import elections.NewExceptions.InvalidAdminMarkingException;
import elections.NewExceptions.InvalidUserDeleteException;
import elections.NewExceptions.InvalidUserVoteException;
import elections.NewExceptions.NotAdminException;
import elections.model.User;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    public User create(User user);
    
    public Iterable<User> findAll();
    
    public Optional<User> findByLogin(String login);
    
    public boolean deleteByLogin(String login) throws NoSuchUserException, InvalidUserDeleteException;
    
    public void markAsVoted(String login) throws NoSuchUserException, InvalidUserVoteException;
    
    public boolean existsByLogin(String login);
    
    public boolean checkAdminByLogin(String login) throws NoSuchUserException;
    
    public void forgetAllVotes();
    
    public int countWhoVoted();
    
    public void markAsAdmin(String login, boolean admin) throws NoSuchUserException, AlreadyAdminException, NotAdminException, InvalidAdminMarkingException;
}
