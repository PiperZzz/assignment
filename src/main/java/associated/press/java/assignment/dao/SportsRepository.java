package associated.press.java.assignment.dao;

import associated.press.java.assignment.model.Sport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SportsRepository extends JpaRepository<Sport, String>{
    
    @Query("SELECT s FROM Sport s WHERE size(s.players) >= 2")
    List<Sport> findSportsWithMultiplePlayers();

    @Query("SELECT s FROM Sport s WHERE s.players IS EMPTY")
    List<Sport> findSportsWithNoPlayers();
}
