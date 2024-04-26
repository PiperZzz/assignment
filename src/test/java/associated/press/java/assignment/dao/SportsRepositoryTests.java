package associated.press.java.assignment.dao;

import associated.press.java.assignment.enums.Gender;
import associated.press.java.assignment.model.Player;
import associated.press.java.assignment.model.Sport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SportsRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SportsRepository sportsRepository;

    @Test
    public void testFindSportsWithMultiplePlayers() {
        Sport soccer = new Sport("Soccer", Collections.emptySet());
        Player player1 = new Player("player1@example.com", 5, 20, Gender.MALE, new HashSet<> ());
        Player player2 = new Player("player2@example.com", 6, 22, Gender.FEMALE, new HashSet<> ());
        soccer.getPlayers().add(player1);
        soccer.getPlayers().add(player2);
        entityManager.persist(soccer);
        entityManager.persist(player1);
        entityManager.persist(player2);
        entityManager.flush();

        List<Sport> sports = sportsRepository.findSportsWithMultiplePlayers();
        assertThat(sports).containsExactly(soccer);
    }

    @Test
    public void testFindSportsWithNoPlayers() {
        Sport tennis = new Sport("Tennis", Collections.emptySet());
        entityManager.persist(tennis);
        entityManager.flush();

        List<Sport> sports = sportsRepository.findSportsWithNoPlayers();
        assertThat(sports).containsExactly(tennis);
    }

    @Test
    public void testFindSportsWithPlayersByName() {
        Sport basketball = new Sport("Basketball", Collections.emptySet());
        Player player = new Player("player3@example.com", 7, 30, Gender.MALE, new HashSet<> ());
        basketball.getPlayers().add(player);
        entityManager.persist(basketball);
        entityManager.persist(player);
        entityManager.flush();

        List<String> names = Arrays.asList("Basketball");
        List<Sport> sports = sportsRepository.findSportsWithPlayersByName(names);
        assertThat(sports).hasSize(1);
        assertThat(sports.get(0)).isEqualTo(basketball);
    }
}