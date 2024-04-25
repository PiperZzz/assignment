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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {

    @Autowired
    private PlayersRepository  playersRepository;

    @Autowired
    private SportsRepository sportsRepository;

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

    public Page<PlayerDTO> getPlayersFilteredBySport(int page, int size, String sportName) {
        Pageable pageable = PageRequest.of(page, size);
        
        Page<Player> players;

        if (sportName != null) {
            players = playersRepository.findBySportsName(sportName, pageable);
        } else {
            players = playersRepository.findAll(pageable);
        }
        return players.map(ModelMapper::mapPlayerToPlayerDTO);
    }
}
