/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.model.ElectionsTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author чтепоноза
 */


@Service
public interface ElectionsTimeService {
    public ElectionsTime create(ElectionsTime electionsTime);
    
    public Iterable<ElectionsTime> findAll();
    
    public Optional<ElectionsTime> findLatest();
   
    public boolean hasRecords();
    
    public boolean existsById(Long id);

}
