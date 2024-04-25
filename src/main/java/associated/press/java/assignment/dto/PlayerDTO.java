package associated.press.java.assignment.dto;

import associated.press.java.assignment.enums.Gender;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {
    private String email;
    private int level;
    private int age;
    private Gender gender;
    private Set<String> sportNames;
}