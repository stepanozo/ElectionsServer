/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.repository;

import elections.model.Candidate;
import elections.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author чтепоноза
 */
@Repository
@Transactional
public interface CandidateRepository extends CrudRepository<Candidate, Long>{
    
}
