package associated.press.java.assignment.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportDTO {
    private String name;
    private Set<String> playerEmails;
}