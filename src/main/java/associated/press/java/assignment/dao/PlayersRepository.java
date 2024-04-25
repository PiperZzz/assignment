package associated.press.java.assignment.dao;

import associated.press.java.assignment.model.Player;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepository extends JpaRepository<Player, String>{
    List<Player> findByGenderAndLevelAndAge(String gender, int level, int age);
}
