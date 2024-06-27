/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.repository;

import elections.model.Candidate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author чтепоноза
 */
@Repository
@Transactional
public interface CandidateRepository extends CrudRepository<Candidate, Long>{
    
    @Modifying
    @Query("UPDATE Candidate c set c.votes = c.votes + 1 WHERE c.id = :id")
    void voteForCandidateById(Long id);
}
