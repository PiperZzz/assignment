package associated.press.java.assignment.service;

import associated.press.java.assignment.dao.PlayersRepository;
import associated.press.java.assignment.model.Player;
import associated.press.java.assignment.enums.Gender;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PlayerServiceTests {

    @MockBean
    private PlayersRepository playersRepository;

    @Autowired
    @InjectMocks
    private PlayerService playerService;

    @Test
    public void testGetPlayersWithNoSportsReturnsEmptyList() {
        when(playersRepository.findPlayersWithNoSports()).thenReturn(new ArrayList<>());
        assertTrue(playerService.getPlayersWithNoSports().isEmpty());
    }

    @Test
    public void testGetPlayersWithNoSportsReturnsNonEmptyList() {
        List<Player> players = Arrays.asList(new Player("email@example.com", 5, 25, Gender.MALE, Collections.emptySet()));
        when(playersRepository.findPlayersWithNoSports()).thenReturn(players);
        assertFalse(playerService.getPlayersWithNoSports().isEmpty());
    }
}