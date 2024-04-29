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
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/player")
public class PlayerRestController {

    private final PlayerService playerService;

    public PlayerRestController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/no-sports")
    public ResponseEntity<List<PlayerDTO>> getPlayersWithNoSports() {
        List<PlayerDTO> players = playerService.getPlayersWithNoSports();
        
        if (players.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(players);
    }

    @PutMapping("/sports/{email}")
    public ResponseEntity<?> updatePlayerSports(@PathVariable String email, @RequestBody List<String> sportsNames) {
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid Path");
        }

        if (sportsNames == null || sportsNames.isEmpty() || sportsNames.stream().anyMatch(sportsName -> sportsName == null || sportsName.trim().isEmpty())) {
            return ResponseEntity.badRequest().body("Invalid RequestBody");
        }        

        try {
            PlayerDTO updatedPlayer = playerService.updatePlayerSports(email, sportsNames);
            return ResponseEntity.ok(updatedPlayer);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error processing request: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sportName) {
        
        if (page < 0 || size <= 0) {
            return ResponseEntity.badRequest().body("Invalid Page or Size");
        }        
        
        Page<PlayerDTO> players = playerService.getPlayersFilteredBySport(page, size, sportName);
        
        return ResponseEntity.ok(players);
    }
}
