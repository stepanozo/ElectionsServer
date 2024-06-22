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
}
