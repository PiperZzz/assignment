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
        Sport soccer = createSportWithPlayers("Soccer", 2);
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
        Sport basketball = createSportWithPlayers("Basketball", 1);
        List<String> names = Arrays.asList("Basketball");
        List<Sport> sports = sportsRepository.findSportsWithPlayersByName(names);
        assertThat(sports).hasSize(1);
        assertThat(sports.get(0)).isEqualTo(basketball);
    }

    @Test
    public void testFindSportsWithMultiplePlayers_NoSports() {
        List<Sport> sports = sportsRepository.findSportsWithMultiplePlayers();
        assertThat(sports).isEmpty();
    }

    @Test
    public void testFindSportsWithNoPlayers_NoSports() {
        List<Sport> sports = sportsRepository.findSportsWithNoPlayers();
        assertThat(sports).isEmpty();
    }

    @Test
    public void testFindSportsWithPlayersByName_NoSports() {
        List<String> names = Arrays.asList("NonexistentSport");
        List<Sport> sports = sportsRepository.findSportsWithPlayersByName(names);
        assertThat(sports).isEmpty();
    }

    private Sport createSportWithPlayers(String sportName, int playerCount) {
        Sport sport = new Sport(sportName, new HashSet<>());
        entityManager.persist(sport);
        entityManager.flush();
        for (int i = 0; i < playerCount; i++) {
            Player player = new Player("player" + (i + 1) + "@example.com", 5, 20, Gender.MALE, new HashSet<>());
            player.getSports().add(sport);
            entityManager.persist(player);
        }
        return sport;
    }
    
}