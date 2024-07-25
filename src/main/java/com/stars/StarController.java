package com.stars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/stars")
public class StarController {

    @Autowired
    private StarService starService;

    @GetMapping("/{id}")
    public ResponseEntity<Star> getStarById(@PathVariable Long id) {
        Optional<Star> star = starService.getStarById(id);
        return star.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Star addStar(@RequestBody Star star) {
        return starService.addStar(star);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Star> updateStar(@PathVariable Long id, @RequestBody Star updatedStar) {
        Star star = starService.updateStar(id, updatedStar);
        return star != null ? ResponseEntity.ok(star) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStar(@PathVariable Long id) {
        boolean deleted = starService.deleteStar(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/closest")
    public List<Star> findClosestStars(@RequestParam int size) {
        return starService.findClosestStars(size);
    }

    @GetMapping("/distances")
    public Map<Long, Integer> getNumberOfStarsByDistances() {
        return starService.getNumberOfStarsByDistances();
    }

    @GetMapping("/unique")
    public Collection<Star> getUniqueStars() {
        return starService.getUniqueStars();
    }
}