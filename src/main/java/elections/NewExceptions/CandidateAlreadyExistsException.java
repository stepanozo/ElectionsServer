/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.NewExceptions;

import elections.model.Candidate;
import lombok.Getter;

/**
 *
 * @author чтепоноза
 */
public class CandidateAlreadyExistsException extends Exception{
    
    @Getter
    private Candidate candidate;
    
    public CandidateAlreadyExistsException(String message, Candidate candidate){
        super(message);
        this.candidate = candidate;
    }
    
}
