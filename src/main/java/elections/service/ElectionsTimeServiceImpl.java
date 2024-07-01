/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.service;

import elections.model.ElectionsTime;
import elections.repository.ElectionsTimeRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author чтепоноза
 */


@Service
public class ElectionsTimeServiceImpl implements ElectionsTimeService{
    
    @Autowired 
    ElectionsTimeRepository electionsTimeRepository;
    
    @Override
    public ElectionsTime create(ElectionsTime electionsTime){
        return electionsTimeRepository.save(electionsTime);
    }

    @Override
    public Iterable<ElectionsTime> findAll() {
        return electionsTimeRepository.findAll();
    }

    @Override
    public Optional<ElectionsTime> findLatest() {
        return electionsTimeRepository.findLatest();
    }
    
    @Override
    public boolean hasRecords(){
        return electionsTimeRepository.hasRecords();
    }
    
    @Override
    public boolean existsById(Long id){
        return electionsTimeRepository.existsById(id);
    }
    

}
