package associated.press.java.assignment.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;

import lombok.Data;

@Entity
@Table(name = "sports")
@Data
public class Sport {

    @Id
    private String name;

    @ManyToMany(mappedBy = "sports", cascade = CascadeType.REMOVE)
    private Set<Player> players = new HashSet<>();
}
