/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package elections.repository;

import elections.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
    
    @Modifying
    @Query("UPDATE User u set u.voted = FALSE WHERE u.voted = TRUE") //Это язык JPQL для запросов к сущностям из базы данных
    void forgetAllVotes();
    
    @Query("SELECT Count(u) FROM User u WHERE u.voted = TRUE")
    int countAllWhoVoted();
    
    @Modifying
    @Query("UPDATE User u set u.isAdmin = :admin WHERE u.login = :login") //Это язык JPQL для запросов к сущностям из базы данных
    void markAsAdmin(@Param("login") String login, @Param("admin") boolean admin);
    
}