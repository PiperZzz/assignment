package associated.press.java.assignment.service;

import associated.press.java.assignment.dto.SportDTO;
import associated.press.java.assignment.enums.Gender;
import associated.press.java.assignment.exception.ResourceNotFoundException;
import associated.press.java.assignment.model.Player;
import associated.press.java.assignment.model.Sport;
import associated.press.java.assignment.dao.SportsRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
public class SportServiceTests {

    @Autowired
    @InjectMocks
    private SportService sportService;

    @MockBean
    private SportsRepository sportsRepository;

    @Test
    public void testGetSportsWithPlayersByNames() {
        Sport sport = new Sport("Soccer", Collections.emptySet());
        sport.setPlayers(new HashSet<> (Arrays.asList(new Player("email@example.com", 5, 25, Gender.MALE, Collections.emptySet()))));
        when(sportsRepository.findSportsWithPlayersByName(any())).thenReturn(Arrays.asList(sport));

        List<SportDTO> results = sportService.getSportsWithPlayersByNames(Arrays.asList("Soccer"));

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals("Soccer", results.get(0).getName());

        verify(sportsRepository).findSportsWithPlayersByName(any());
    }

    @Test
    public void testDeleteSport() {
        Sport sport = new Sport("Soccer", new HashSet<> ());
        when(sportsRepository.findByName("Soccer")).thenReturn(Optional.of(sport));

        assertDoesNotThrow(() -> sportService.deleteSport("Soccer"));

        verify(sportsRepository).delete(sport);
        verify(sportsRepository).findByName("Soccer");
    }

    @Test
    public void testDeleteSportNotFound() {
        when(sportsRepository.findByName("Unknown")).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            sportService.deleteSport("Unknown");
        });

        assertEquals("Sport not found with name: Unknown", exception.getMessage());
    }
}
