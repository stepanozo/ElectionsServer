/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.Exceptions.NoSuchCandidateException;
import elections.NewExceptions.CandidateAlreadyExistsException;
import elections.NewExceptions.InvalidCandidateVoteException;
import elections.NewExceptions.InvalidDeleteException;
import elections.model.Candidate;
import elections.model.User;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author чтепоноза
 */


@Service
public interface CandidateService {
    public Candidate create(Candidate candidate);
    
    public Iterable<Candidate> findAll();
    
    public boolean deleteAll() throws InvalidDeleteException;
    
    public void voteById(Long id) throws NoSuchCandidateException, InvalidCandidateVoteException;
    
    public boolean existsById(Long id);
    
    public boolean existsByName(String name);
   
    public Optional<Candidate> findById(Long id);
    
    
}
