package associated.press.java.assignment.dao;

import associated.press.java.assignment.enums.Gender;
import associated.press.java.assignment.model.Player;
import associated.press.java.assignment.model.Sport;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.data.domain.Page;

@DataJpaTest
public class PlayersRepositoryTests {

    @Autowired
    private PlayersRepository playersRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindPlayersWithNoSports() {
        Player playerWithNoSports = new Player("player@example.com", 5, 25, Gender.MALE, Collections.emptySet());
        Player playerWithSports = new Player("sporty@example.com", 7, 30, Gender.FEMALE, Collections.emptySet());
        Sport soccer = new Sport("Soccer", Collections.emptySet());
        playerWithSports.getSports().add(soccer);

        entityManager.persist(soccer);
        entityManager.persist(playerWithNoSports);
        entityManager.persist(playerWithSports);
        entityManager.flush();

        List<Player> players = playersRepository.findPlayersWithNoSports();
        
        assertThat(players).hasSize(1);
        assertThat(players).containsExactly(playerWithNoSports);
    }

    @Test
    public void testFindBySportsName() {
        Sport soccer = new Sport("Soccer", Collections.emptySet());
        Sport basketball = new Sport("Basketball", Collections.emptySet());
        Player player1 = new Player("player1@example.com", 5, 20, Gender.MALE, Collections.emptySet());
        Player player2 = new Player("player2@example.com", 6, 22, Gender.FEMALE, Collections.emptySet());
        player1.getSports().add(soccer);
        player2.getSports().add(soccer);
        player2.getSports().add(basketball);

        entityManager.persist(soccer);
        entityManager.persist(basketball);
        entityManager.persist(player1);
        entityManager.persist(player2);
        entityManager.flush();

        Page<Player> players = playersRepository.findBySportsName("Soccer", PageRequest.of(0, 10));

        assertThat(players.getTotalElements()).isEqualTo(2);
        assertThat(players.getContent()).containsExactlyInAnyOrder(player1, player2);
    }
}