package associated.press.java.assignment.e2e;

import java.util.List;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerEndToEndTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUpdatePlayerSports() throws Exception {
    String email = "user@example.com";
    List<String> sportsNames = Arrays.asList("Tennis", "Soccer");

    mockMvc.perform(put("/player/{email}/sports", email)
    .contentType(MediaType.APPLICATION_JSON)
    .content(new ObjectMapper().writeValueAsString(sportsNames)))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.sports").isArray())
    .andExpect(jsonPath("$.sports[0]").value("Tennis"));

    }
}
