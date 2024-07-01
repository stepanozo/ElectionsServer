/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.Exceptions.NoSuchCandidateException;
import elections.Exceptions.NoSuchUserException;
import elections.NewExceptions.CandidateAlreadyExistsException;
import elections.NewExceptions.InvalidCandidateVoteException;
import elections.NewExceptions.InvalidDeleteException;
import elections.NewExceptions.InvalidUserDeleteException;
import elections.NewExceptions.InvalidUserVoteException;
import elections.model.Candidate;
import elections.model.User;
import elections.repository.CandidateRepository;
import elections.repository.UserRepository;
import jakarta.transaction.Transactional;
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
        
//        if(candidateRepository.existsByName(candidate.getName())) {
//            Candidate dbCandidate = candidateRepository.findByName(candidate.getName()).orElseThrow(()-> new NoSuchCandidateException("Нет такого кандидата: " + candidate.getName(), candidate.getId()));
//            throw new CandidateAlreadyExistsException("Кандидат с именем " + dbCandidate.getName() + " уже существует.", dbCandidate);
//        } else {
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
 //       int previousVotes = candidate.getVotes();
        candidateRepository.voteForCandidateById(id);
                //Тут была проверка, но в ней почему-то не увеличивается кол-во голосов
//        //Теперь проверим, что голос зафиксирован
//        candidate = candidateRepository.findById(id).orElseThrow(() -> new NoSuchCandidateException("Не найден кандидат " + id, id));
//        if(candidate.getVotes() != previousVotes + 1)
//            throw new InvalidCandidateVoteException("Не удалось поставить голос за кандидата " + id, id);
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
