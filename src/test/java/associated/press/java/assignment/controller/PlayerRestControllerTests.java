package associated.press.java.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import associated.press.java.assignment.dto.PlayerDTO;
import associated.press.java.assignment.enums.Gender;
import associated.press.java.assignment.service.PlayerService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

@WebMvcTest(PlayerRestController.class)
public class PlayerRestControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    public void testGetPlayersWithNoSportsReturnsEmpty() throws Exception {
        mockMvc.perform(get("/player/no-sports"))
            .andExpect(status().isNoContent());
    }

    @Test
    public void testGetPlayersWithNoSportsReturnsNonEmpty() throws Exception {
        when(playerService.getPlayersWithNoSports()).thenReturn(Arrays.asList(new PlayerDTO("email@example.com", 5, 25, Gender.MALE, Collections.emptySet())));
        mockMvc.perform(get("/player/no-sports"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].email").value("email@example.com"));
    }
}
