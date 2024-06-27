/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.Controller;

import elections.model.Candidate;
import elections.model.User;
import elections.service.CandidateService;
import elections.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @PostMapping
    public Candidate create(@RequestBody Candidate candidate){
        return candidateService.create(candidate);
    }
    
    @GetMapping
    public Iterable<Candidate> getAll(){
        return candidateService.findAll();
    }
    
    @DeleteMapping
    public void deleteAll(){
        candidateService.deleteAll();
    }
    
    //Хорошо бы здесь переделать так, чтоб отправлять статус, чтоб клиент понял, получилось у него вообще проголосовать или нет
    @PatchMapping("/{id}:vote")
    public void voteForCandidate(@PathVariable Long id){
        candidateService.voteById(id);
    }
}
