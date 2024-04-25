package associated.press.java.assignment.dto;

import java.util.Set;

import lombok.Data;

@Data
public class SportDTO {
    private String name;
    private Set<String> playerEmails;
}