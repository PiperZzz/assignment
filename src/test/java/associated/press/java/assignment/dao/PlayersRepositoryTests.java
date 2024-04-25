package associated.press.java.assignment.dao;

import associated.press.java.assignment.enums.Gender;
import associated.press.java.assignment.model.Player;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PlayersRepositoryTests {

    @Autowired
    private PlayersRepository playersRepository;

    @Test
    public void testFindByEmail() {
        Player player = new Player("test@example.com", 5, 30, Gender.MALE, new HashSet<> ());
        playersRepository.save(player);
        Optional<Player> foundPlayer = playersRepository.findByEmail("test@example.com");
        assertTrue(foundPlayer.isPresent());
        assertEquals("test@example.com", foundPlayer.get().getEmail());
    }
}

