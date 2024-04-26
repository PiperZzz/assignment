package associated.press.java.assignment.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SportEndToEndTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getSportsWithPlayersByNameShouldReturnSports() throws Exception {
        mockMvc.perform(get("/sport/search")
                .param("names", "Soccer", "Basketball")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Soccer"))
                .andExpect(jsonPath("$[0].playerEmails").isArray());
    }

    @Test
    public void deleteSportShouldReturnOkWhenSportExists() throws Exception {
        // Assuming 'Soccer' exists in the database
        mockMvc.perform(delete("/sport/Soccer"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSportShouldReturnNotFoundWhenSportDoesNotExist() throws Exception {
        mockMvc.perform(delete("/sport/Football"))
                .andExpect(status().isNotFound());
    }

}
