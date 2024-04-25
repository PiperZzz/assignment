package associated.press.java.assignment.model;

import associated.press.java.assignment.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;

import lombok.Data;

@Entity
@Table(name = "players") //JPA can infer the name, here is just for good practices.
@Data
public class Player {
    
    @Id
    private String eamil;
    
    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
}
