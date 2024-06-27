/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elections.repository;

import elections.model.Candidate;
import elections.model.ElectionsTime;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author чтепоноза
 */

@Repository
@Transactional
public interface ElectionsTimeRepository extends CrudRepository<ElectionsTime, Long>{
    
    //Тут имя сущности, а не таблицы
    @Query("SELECT e FROM ElectionsTime e WHERE e.id = (SELECT MAX(id) FROM ElectionsTime)") //Это язык JPQL для запросов к сущностям из базы данных
    Optional<ElectionsTime> findLatest();
    
    @Query("SELECT COUNT(et) > 0 FROM ElectionsTime et") //Вернуть true, если записей больше 0
    boolean hasRecords();
}

