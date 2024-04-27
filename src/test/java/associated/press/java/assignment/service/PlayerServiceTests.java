package associated.press.java.assignment.service;

import associated.press.java.assignment.dao.PlayersRepository;
import associated.press.java.assignment.dao.SportsRepository;
import associated.press.java.assignment.dto.PlayerDTO;
import associated.press.java.assignment.model.Player;
import associated.press.java.assignment.model.Sport;
import associated.press.java.assignment.enums.Gender;
import associated.press.java.assignment.exception.ResourceNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class PlayerServiceTests {

    @MockBean
    private PlayersRepository playersRepository;

    @MockBean
    private SportsRepository sportsRepository;

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

    @Test
    public void testUpdatePlayerSports() {
        Player player = new Player("email@example.com", 5, 25, Gender.MALE, Collections.emptySet());
        when(playersRepository.findById("email@example.com")).thenReturn(Optional.of(player));

        Sport sport = new Sport("Soccer", Collections.emptySet());
        when(sportsRepository.findById("Soccer")).thenReturn(Optional.of(sport));

        List<String> sportsNames = Collections.singletonList("Soccer");
        PlayerDTO updatedPlayer = playerService.updatePlayerSports("email@example.com", sportsNames);

        assertEquals(1, updatedPlayer.getSportNames().size());
        assertTrue(updatedPlayer.getSportNames().contains("Soccer"));
    }

    @Test
    public void testUpdatePlayerSportsPlayerNotFound() {
        when(playersRepository.findById("email@example.com")).thenReturn(Optional.empty());

        List<String> sportsNames = Collections.singletonList("Soccer");
        assertThrows(ResourceNotFoundException.class, () -> playerService.updatePlayerSports("email@example.com", sportsNames));
    }

    @Test
    public void testUpdatePlayerSportsSportNotFound() {
        Player player = new Player("email@example.com", 5, 25, Gender.MALE, Collections.emptySet());
        when(playersRepository.findById("email@example.com")).thenReturn(Optional.of(player));
        
        when(sportsRepository.findById("Soccer")).thenReturn(Optional.empty());

        List<String> sportsNames = Collections.singletonList("Soccer");
        assertThrows(ResourceNotFoundException.class, () -> playerService.updatePlayerSports("email@example.com", sportsNames));
    }

    @Test
    public void testGetPlayersFilteredBySport() {
        Sport soccer = new Sport("Soccer", Collections.emptySet());
        Player player1 = new Player("email1@example.com", 5, 25, Gender.MALE, Collections.singleton(soccer));
        Player player2 = new Player("email2@example.com", 6, 30, Gender.FEMALE, Collections.singleton(soccer));
        List<Player> players = Arrays.asList(player1, player2);

        when(playersRepository.findBySportsName("Soccer", PageRequest.of(0, 10))).thenReturn(new PageImpl<>(players));

        Page<PlayerDTO> playerPage = playerService.getPlayersFilteredBySport(0, 10, "Soccer");

        assertEquals(2, playerPage.getTotalElements());
    }

    @Test
    public void testGetPlayersFilteredBySportNoSportName() {
        Sport soccer = new Sport("Soccer", Collections.emptySet());
        Player player = new Player("email@example.com", 5, 25, Gender.MALE, Collections.singleton(soccer));
        List<Player> players = Collections.singletonList(player);

        when(playersRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(players));

        Page<PlayerDTO> playerPage = playerService.getPlayersFilteredBySport(0, 10, null);

        assertEquals(1, playerPage.getTotalElements());
    }

    @Test
    public void testGetPlayersFilteredBySportEmptySportName() {
        Sport soccer = new Sport("Soccer", Collections.emptySet());
        Player player = new Player("email@example.com", 5, 25, Gender.MALE, Collections.singleton(soccer));
        List<Player> players = Collections.singletonList(player);

        when(playersRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(players));

        Page<PlayerDTO> playerPage = playerService.getPlayersFilteredBySport(0, 10, "");

        assertEquals(1, playerPage.getTotalElements());
    }
}