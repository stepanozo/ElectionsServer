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
public class InvalidAdminMarkingException extends Exception {
    
    @Getter
    String login;
    
    @Getter
    boolean supposedNewMark;
    
    public InvalidAdminMarkingException(String message, String login, boolean supposedNewMark){
        super(message);
        this.login = login;
        this.supposedNewMark = supposedNewMark;
    }
    
}
