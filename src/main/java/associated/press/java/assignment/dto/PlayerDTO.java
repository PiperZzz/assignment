package associated.press.java.assignment.dto;

import associated.press.java.assignment.enums.Gender;

import java.util.Set;

import lombok.Data;

@Data
public class PlayerDTO {
    private String email;
    private int level;
    private int age;
    private Gender gender;
    private Set<String> sportNames;
}