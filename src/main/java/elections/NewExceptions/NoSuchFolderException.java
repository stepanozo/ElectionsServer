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
public class NoSuchFolderException extends Exception {
    
    @Getter
    private final String path;
    public NoSuchFolderException(String message, String path){
        super(message);
        this.path = path;
    }
}
