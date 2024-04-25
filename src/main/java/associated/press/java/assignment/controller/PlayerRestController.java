package associated.press.java.assignment.controller;

import associated.press.java.assignment.dto.PlayerDTO;
import associated.press.java.assignment.exception.ResourceNotFoundException;
import associated.press.java.assignment.service.PlayerService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PutMapping("/{email}/sports")
    public ResponseEntity<?> updatePlayerSports(@PathVariable String email, @RequestBody List<String> sportsNames) {
        try {
            PlayerDTO updatedPlayer = playerService.updatePlayerSports(email, sportsNames);
            return ResponseEntity.ok(updatedPlayer);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing request: " + e.getMessage());
        }
    }
}
