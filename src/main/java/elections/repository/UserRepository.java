/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package elections.repository;

import elections.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author чтепоноза
 */

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, String> {

    @Modifying
    @Query("UPDATE User u set u.voted = TRUE WHERE u.login = :login") //Это язык JPQL для запросов к сущностям из базы данных
    void markAsVoted(@Param("login") String login);
}