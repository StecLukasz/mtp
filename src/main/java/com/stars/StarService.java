package com.stars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StarService {

    @Autowired
    private StarRepository starRepository;

    public List<Star> findClosestStars(int size) {
        List<Star> stars = starRepository.findAll();
        return stars.stream()
                .sorted(Comparator.comparingLong(Star::getDistance))
                .limit(size)
                .collect(Collectors.toList());
    }

    public Map<Long, Integer> getNumberOfStarsByDistances() {
        List<Star> stars = starRepository.findAll();
        return stars.stream()
                .collect(Collectors.groupingBy(Star::getDistance, TreeMap::new, Collectors.reducing(0, e -> 1, Integer::sum)));
    }

    public Collection<Star> getUniqueStars() {
        List<Star> stars = starRepository.findAll();
        return stars.stream()
                .collect(Collectors.toMap(Star::getName, star -> star, (existing, replacement) -> existing))
                .values();
    }

    public Star addStar(Star star) {
        return starRepository.save(star);
    }

    public Optional<Star> getStarById(Long id) {
        return starRepository.findById(id);
    }

    public boolean deleteStar(Long id) {
        if (starRepository.existsById(id)) {
            starRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Star updateStar(Long id, Star updatedStar) {
        return starRepository.findById(id)
                .map(star -> {
                    star.setName(updatedStar.getName());
                    star.setDistance(updatedStar.getDistance());
                    return starRepository.save(star);
                })
                .orElse(null);
    }
}
