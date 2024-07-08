/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.NewExceptions;

import lombok.Getter;

/**
 *
 * @author чтепоноза
 */
public class NoSuchCandidateException extends Exception{
    
    @Getter
    private final long id;
    
    public NoSuchCandidateException(String message, long id){
        super(message);
        this.id = id;
    }
}
