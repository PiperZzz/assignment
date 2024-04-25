package associated.press.java.assignment.service;

import associated.press.java.assignment.dao.SportsRepository;
import associated.press.java.assignment.model.Sport;
import associated.press.java.assignment.util.ModelMapper;
import associated.press.java.assignment.dto.SportDTO;
import associated.press.java.assignment.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SportService {
    
    @Autowired
    private SportsRepository sportsRepository;

    public List<Sport> getSportsWithMultiplePlayers() {
        return sportsRepository.findSportsWithMultiplePlayers();
    }

    public List<Sport> getSportsWithNoPlayers() {
        return sportsRepository.findSportsWithNoPlayers();
    }


    public List<SportDTO> getSportsWithPlayersByNames(List<String> names) {
        List<Sport> sports = sportsRepository.findSportsWithPlayersByName(names);

        return sports.stream()
                     .map(ModelMapper::mapSportToSportDTO)
                     .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSport(String name) {
        Sport sport = sportsRepository.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Sport not found with name: " + name));
        sportsRepository.delete(sport);
    }
}
