/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.model.Candidate;
import elections.model.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author чтепоноза
 */
@Service
public interface CandidateService {
    public Candidate create(Candidate candidate);
}
