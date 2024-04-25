package associated.press.java.assignment.services;

import associated.press.java.assignment.dao.PlayersRepository;
import associated.press.java.assignment.dto.PlayerDTO;
import associated.press.java.assignment.model.Player;
import associated.press.java.assignment.util.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PlayerService {

    @Autowired
    private PlayersRepository  playersRepository;

    public List<Player> getPlayersByGenderLevelAndAge(String gender, int level, int age) {
        return playersRepository.findByGenderAndLevelAndAge(gender, level, age);
    }

    public List<PlayerDTO> getPlayersWithNoSports() {
        List<Player> players = playersRepository.findPlayersWithNoSports();

        return players.stream()
                      .map(ModelMapper::mapPlayerToPlayerDTO)
                      .collect(Collectors.toList());
    }
}
