/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.NewExceptions.NoSuchCandidateException;
import elections.NewExceptions.InvalidCandidateVoteException;
import elections.NewExceptions.InvalidDeleteException;
import elections.model.Candidate;
import elections.repository.CandidateRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author чтепоноза
 */


@Service //Без этой аннотации не сработает почему-то Autowired
public class CandidateServiceImpl implements CandidateService{
    
    @Autowired
    CandidateRepository candidateRepository;

    @Override
    public Candidate create(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
    
    @Override
    public Iterable<Candidate> findAll(){
        return candidateRepository.findAll();
    }
    
    @Override
    public boolean deleteAll() throws InvalidDeleteException{

        candidateRepository.deleteAll();
        if(candidateRepository.count() > 0)
            throw new InvalidDeleteException("Таблица кандидатов не очищена ");
        return true;
    }
    
    @Override
    public Optional<Candidate> findById(Long id){
        return candidateRepository.findById(id); 
    }

    @Override
    public void voteById(Long id) throws NoSuchCandidateException, InvalidCandidateVoteException{
        
        if(!candidateRepository.existsById(id))
            throw new NoSuchCandidateException("Не найден кандидат " + id, id);
        
        Candidate candidate = findById(id).orElseThrow(() -> new NoSuchCandidateException("Не найден кандидат " + id, id));
        candidateRepository.voteForCandidateById(id);
    }
    
    @Override
    public boolean existsById(Long id){
        return candidateRepository.existsById(id);
    }
    
    @Override
    public boolean existsByName(String name){
        Candidate c = candidateRepository.findByName(name).orElse(null);
        return c != null;
    }
}
