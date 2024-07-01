/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.NewExceptions;

/**
 *
 * @author чтепоноза
 */
public class InvalidDeleteException extends Exception {
   
     private String login;
    
    public InvalidDeleteException(String message, String login){
        super(message);
        this.login = login;
    }
}
