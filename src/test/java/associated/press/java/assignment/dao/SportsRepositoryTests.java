package associated.press.java.assignment.dao;

import associated.press.java.assignment.model.Sport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Collections;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SportsRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SportsRepository sportsRepository;

    @Test
    public void testFindSportsWithNoPlayers() {
        Sport tennis = new Sport("Tennis", Collections.emptySet());
        entityManager.persist(tennis);
        entityManager.flush();

        List<Sport> sports = sportsRepository.findSportsWithNoPlayers();
        assertThat(sports).containsExactly(tennis);
    }
}