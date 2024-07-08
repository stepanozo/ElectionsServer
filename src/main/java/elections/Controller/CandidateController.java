/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.Controller;

import elections.Exceptions.NoSuchCandidateException;
import elections.NewExceptions.InvalidCandidateVoteException;
import elections.NewExceptions.InvalidDeleteException;
import elections.model.Candidate;
import elections.service.CandidateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/candidates")
public class CandidateController {
    
    @Autowired
    private CandidateService candidateService;
    
    @Transactional
    @PostMapping
    public ResponseEntity<Candidate> create(@RequestBody Candidate candidate){
        
        if(candidateService.existsByName(candidate.getName()))            
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); //Если такой кандидат уже существует
        else if(candidateService.create(candidate)!=null)
            return ResponseEntity.status(HttpStatus.CREATED).build();
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();   
    }
    
    @GetMapping
    public ResponseEntity<Iterable<Candidate>> getAll(){
        
        Iterable<Candidate> candidates = candidateService.findAll();
       
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }
    
    @DeleteMapping
    public ResponseEntity<?> deleteAll() { 
        try{
            boolean success = candidateService.deleteAll();
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (InvalidDeleteException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    //Хорошо бы здесь переделать так, чтоб отправлять статус, чтоб клиент понял, получилось у него вообще проголосовать или нет
    @PatchMapping("/{id}:vote")
    public ResponseEntity<?> voteById(@PathVariable Long id) {
        try{
            candidateService.voteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(NoSuchCandidateException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(InvalidCandidateVoteException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
