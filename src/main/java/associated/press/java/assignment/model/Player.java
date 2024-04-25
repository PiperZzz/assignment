package associated.press.java.assignment.model;

import associated.press.java.assignment.enums.Gender;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

import lombok.Data;

@Entity
@Table(name = "players") //JPA can infer the name, here is just for good practices.
@Data
public class Player {
    
    @Id
    private String email;
    
    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @ManyToMany
    @JoinTable(
        name = "players_sports",
        joinColumns = @JoinColumn(name = "player_email", referencedColumnName = "email"),
        inverseJoinColumns = @JoinColumn(name = "sport_name", referencedColumnName = "name")
    )
    private Set<Sport> sports = new HashSet<>();
}
