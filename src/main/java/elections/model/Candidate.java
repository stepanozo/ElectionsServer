/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.*;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author чтепоноза
 */
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id = 0;
    
    @Getter
    private String name= "";
    
    @Getter
    private  int yearOfBirth = 0;
    
    @Getter
    private String placeOfLiving = "";
    
    @Getter
    private String party = "";
    
    @Getter
    private String information = "";
    
    @Getter //Чтобы был и геттер, и сеттер
    @Setter
    private int votes;
    
    public Candidate(){
        
    }
    
    public Candidate(String name, int yearOfBirth, String placeOfLiving, String party, String information, int votes){
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.placeOfLiving = placeOfLiving;
        this.party = party;
        this.information = information;
        this.votes = votes;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Candidate candidate = (Candidate)obj;
        
        return 
            Objects.equals(candidate.name, this.name) && 
            candidate.yearOfBirth == this.yearOfBirth && 
            Objects.equals(candidate.placeOfLiving, this.placeOfLiving) &&
            Objects.equals(candidate.party, this.party) &&
            Objects.equals(candidate.information, this.information) && 
            candidate.votes == this.votes;
    }

    @Override
    public int hashCode() {
        return 
            name.hashCode() + 
            ((Integer)yearOfBirth).hashCode() +
            placeOfLiving.hashCode() +
            party.hashCode() +
            information.hashCode() +
            ((Integer)votes).hashCode();
    }
    
    @Override
    public String toString(){
        return String.format(
                "Имя: '%s' \n" +
                "Год рождения: %d \n" +
                "Место проживания: '%s' \n" +
                "Партия: '%s' \n" +
                "Информация: '%s' \n" +
                "Количество голосов: %d \n",
                name, yearOfBirth, placeOfLiving, party, information, votes
        );
    }
}
