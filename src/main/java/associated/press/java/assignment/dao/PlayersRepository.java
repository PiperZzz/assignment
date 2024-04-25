package associated.press.java.assignment.dao;

import associated.press.java.assignment.model.Player;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayersRepository extends JpaRepository<Player, String>{

    Optional<Player> findByEmail(String email);
    
    List<Player> findByGenderAndLevelAndAge(String gender, int level, int age);

    @Query("SELECT p FROM Player p WHERE p.sports IS EMPTY")
    List<Player> findPlayersWithNoSports();
}
