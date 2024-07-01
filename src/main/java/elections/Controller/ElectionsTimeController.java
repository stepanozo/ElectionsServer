/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.Controller;

import elections.Exceptions.NoElectionsException;
import elections.model.ElectionsTime;
import elections.model.User;
import elections.repository.ElectionsTimeRepository;
import elections.service.ElectionsTimeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author чтепоноза
 */

@RestController
@RequestMapping("/electionsTime")
public class ElectionsTimeController {
    
    @Autowired
    private ElectionsTimeService electionsTimeService;
    
    @GetMapping("/check-if-exist")
    public ResponseEntity<Boolean> hasRecords(){
        return new ResponseEntity<>(electionsTimeService.hasRecords(), HttpStatus.OK);
    }
    
    @GetMapping("/findLatest")
    public ResponseEntity<ElectionsTime> fingLatest(){
        try{
            ElectionsTime electionsTime = electionsTimeService.findLatest().orElseThrow(() -> new NoElectionsException("Выборы не проводятся"));
            return new ResponseEntity<>(electionsTime, HttpStatus.OK);
        } catch (NoElectionsException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
 
    }
    
    @Transactional
    @PostMapping
    public ResponseEntity<ElectionsTime> create(@RequestBody ElectionsTime electionsTime){
        electionsTimeService.create(electionsTime);
        
        if(electionsTimeService.existsById(electionsTime.getId())){
            return ResponseEntity.status(HttpStatus.CREATED).build(); 
        }
        else return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    

}
