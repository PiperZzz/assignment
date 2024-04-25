package associated.press.java.assignment.services;

import associated.press.java.assignment.dao.SportsRepository;
import associated.press.java.assignment.model.Sport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
