package associated.press.java.assignment.controller;

import associated.press.java.assignment.dto.SportDTO;
import associated.press.java.assignment.exception.ResourceNotFoundException;
import associated.press.java.assignment.service.SportService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebMvcTest(SportRestController.class)
public class SportRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SportService sportService;

    @Test
    public void testGetSportsWithPlayersByNamesValidRequest() throws Exception {
        List<SportDTO> sports = Arrays.asList(new SportDTO("Soccer", Collections.emptySet()));
        given(sportService.getSportsWithPlayersByNames(anyList())).willReturn(sports);

        mockMvc.perform(get("/sport/search")
                .param("names", "Soccer", "Basketball"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("Soccer"));
    }

    @Test
    public void testGetSportsWithPlayersByNamesEmptyList() throws Exception {
        given(sportService.getSportsWithPlayersByNames(anyList())).willReturn(Collections.emptyList());

        mockMvc.perform(get("/sport/search")
                .param("names", "Soccer"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testGetSportsWithPlayersByNamesBadRequest() throws Exception {
        mockMvc.perform(get("/sport/search"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteSportValidRequest() throws Exception {
        doNothing().when(sportService).deleteSport(anyString());

        mockMvc.perform(delete("/sport/Soccer"))
            .andExpect(status().isOk());
    }

    @Test
    public void testDeleteSportNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Sport not found with name: Soccer")).when(sportService).deleteSport("Soccer");

        mockMvc.perform(delete("/sport/Soccer"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testMethodNotAllowed() throws Exception {
    mockMvc.perform(post("/sport/search")
            .param("names", "Soccer"))
        .andExpect(status().isMethodNotAllowed());
    }

}
