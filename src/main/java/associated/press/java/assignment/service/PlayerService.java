package associated.press.java.assignment.service;

import associated.press.java.assignment.dao.PlayersRepository;
import associated.press.java.assignment.dao.SportsRepository;
import associated.press.java.assignment.dto.PlayerDTO;
import associated.press.java.assignment.model.Player;
import associated.press.java.assignment.model.Sport;
import associated.press.java.assignment.util.ModelMapper;
import associated.press.java.assignment.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {

    @Autowired
    private PlayersRepository  playersRepository;

    @Autowired
    private SportsRepository sportsRepository;

    public List<Player> getPlayersByGenderLevelAndAge(String gender, int level, int age) {
        return playersRepository.findByGenderAndLevelAndAge(gender, level, age);
    }

    public List<PlayerDTO> getPlayersWithNoSports() {
        List<Player> players = playersRepository.findPlayersWithNoSports();

        return players.stream()
                      .map(ModelMapper::mapPlayerToPlayerDTO)
                      .collect(Collectors.toList());
    }

    @Transactional
    public PlayerDTO updatePlayerSports(String email, List<String> sportsNames) {
        Player player = playersRepository.findById(email)
            .orElseThrow(() -> new ResourceNotFoundException("Player not found with email: " + email));
        
        Set<Sport> sports = sportsNames.stream()
            .map(name -> sportsRepository.findById(name)
                .orElseThrow(() -> new ResourceNotFoundException("Sport not found with name: " + name)))
            .collect(Collectors.toSet());
        
        player.setSports(sports);
        playersRepository.save(player);
        
        return ModelMapper.mapPlayerToPlayerDTO(player);
    }
}
