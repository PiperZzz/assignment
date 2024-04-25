package associated.press.java.assignment.controller;

import associated.press.java.assignment.dto.SportDTO;
import associated.press.java.assignment.exception.ResourceNotFoundException;
import associated.press.java.assignment.service.SportService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteSport(@PathVariable String name) {
        try {
            sportService.deleteSport(name);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error processing request: " + e.getMessage());
        }
    }
}
