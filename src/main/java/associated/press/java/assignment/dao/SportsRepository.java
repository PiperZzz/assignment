package associated.press.java.assignment.dao;

import associated.press.java.assignment.model.Sport;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SportsRepository extends JpaRepository<Sport, String>{

    Optional<Sport> findByName(String name);
    
    @Query("SELECT s FROM Sport s WHERE size(s.players) >= 2")
    List<Sport> findSportsWithMultiplePlayers();

    @Query("SELECT s FROM Sport s WHERE s.players IS EMPTY")
    List<Sport> findSportsWithNoPlayers();

    @Query("SELECT s FROM Sport s JOIN FETCH s.players WHERE s.name IN :names")
    List<Sport> findSportsWithPlayersByName(List<String> names);
}
