/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.model.Candidate;
import elections.model.User;
import elections.repository.CandidateRepository;
import elections.repository.UserRepository;
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
    public void deleteAll(){
        candidateRepository.deleteAll();
    }
}
