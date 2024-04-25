package associated.press.java.assignment.services;

import associated.press.java.assignment.dao.PlayersRepository;
import associated.press.java.assignment.model.Player;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PlayerService {

    @Autowired
    private PlayersRepository  playersRepository;

    public List<Player> getPlayersByGenderLevelAndAge(String gender, int level, int age) {
        return playersRepository.findByGenderAndLevelAndAge(gender, level, age);
    }
}
