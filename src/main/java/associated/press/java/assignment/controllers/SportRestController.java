package associated.press.java.assignment.controllers;

import associated.press.java.assignment.dto.SportDTO;

import java.util.List;

import associated.press.java.assignment.services.SportService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/sport")
public class SportRestController {
    
    @Autowired
    private SportService sportService;

    @GetMapping("/search")
    public ResponseEntity<?> getSportsWithPlayersByNames(@RequestParam List<String> names) {
        try {
            List<SportDTO> sports = sportService.getSportsWithPlayersByNames(names);
            if (sports.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(sports);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing request: " + e.getMessage());
        }
    }
}
