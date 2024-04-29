package associated.press.java.assignment.util;

import java.util.stream.Collectors;

import associated.press.java.assignment.dto.PlayerDTO;
import associated.press.java.assignment.dto.SportDTO;
import associated.press.java.assignment.model.Player;
import associated.press.java.assignment.model.Sport;

public class ModelMapper {

    public static SportDTO mapSportToSportDTO(Sport sport) {
        SportDTO dto = new SportDTO();
        
        dto.setName(sport.getName());
        
        dto.setPlayerEmails(sport.getPlayers().stream()
            .map(Player::getEmail)
            .collect(Collectors.toSet()));
        
        return dto;
    }

    public static PlayerDTO mapPlayerToPlayerDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        
        dto.setEmail(player.getEmail());
        dto.setLevel(player.getLevel());
        dto.setAge(player.getAge());
        dto.setGender(player.getGender());
        
        dto.setSportNames(player.getSports().stream()
            .map(Sport::getName)
            .collect(Collectors.toSet()));
        
        return dto;
    }
}
