package associated.press.java.assignment.controller;

import associated.press.java.assignment.dto.PlayerDTO;
import associated.press.java.assignment.service.PlayerService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/player")
public class PlayerRestController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/no-sports")
    public ResponseEntity<List<PlayerDTO>> getPlayersWithNoSports() {
        List<PlayerDTO> players = playerService.getPlayersWithNoSports();
        
        if (players.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(players);
    }
}
