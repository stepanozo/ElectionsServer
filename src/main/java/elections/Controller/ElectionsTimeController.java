/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.Controller;

import elections.model.ElectionsTime;
import elections.service.ElectionsTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    boolean checkIfExist(){
        return electionsTimeService.hasRecords();
    }
    
    @GetMapping("/findLatest")
    ElectionsTime getLatest(){
        return electionsTimeService.findLatest().orElse(null);
    }
    
    @PostMapping
    public ElectionsTime create(@RequestBody ElectionsTime electionsTime){
        return electionsTimeService.create(electionsTime);
    }
    

}
