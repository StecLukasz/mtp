package com.stars;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class StarServiceTest {

    @Mock
    private StarRepository starRepository;

    @InjectMocks
    private StarService starService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findClosestStarsTest() {
        List<Star> stars = Arrays.asList(
                new Star(1L, "Alpha", 4),
                new Star(2L, "Beta", 3),
                new Star(3L, "Gamma", 1),
                new Star(4L, "Delta", 2)
        );
        when(starRepository.findAll()).thenReturn(stars);

        List<Star> closestStars = starService.findClosestStars(3);
        assertEquals(3, closestStars.size());
        assertEquals("Gamma", closestStars.get(0).getName());
        assertEquals("Delta", closestStars.get(1).getName());
        assertEquals("Beta", closestStars.get(2).getName());
    }

    @Test
    public void getNumberOfStarsByDistancesTest() {
        List<Star> stars = Arrays.asList(
                new Star(1L, "Alpha", 4),
                new Star(2L, "Beta", 4),
                new Star(3L, "Gamma", 1),
                new Star(4L, "Delta", 2)
        );
        when(starRepository.findAll()).thenReturn(stars);

        Map<Long, Integer> result = starService.getNumberOfStarsByDistances();
        assertEquals(3, result.size());
        assertEquals(1, result.get(1L));
        assertEquals(1, result.get(2L));
        assertEquals(2, result.get(4L));
    }

    @Test
    public void getUniqueStarsTest() {
        List<Star> stars = Arrays.asList(
                new Star(1L, "Alpha", 4),
                new Star(2L, "Alpha", 3),
                new Star(3L, "Gamma", 1),
                new Star(4L, "Delta", 2)
        );
        when(starRepository.findAll()).thenReturn(stars);

        Collection<Star> uniqueStars = starService.getUniqueStars();
        assertEquals(3, uniqueStars.size());
        assertTrue(uniqueStars.stream().anyMatch(star -> star.getName().equals("Alpha")));
        assertTrue(uniqueStars.stream().anyMatch(star -> star.getName().equals("Gamma")));
        assertTrue(uniqueStars.stream().anyMatch(star -> star.getName().equals("Delta")));
    }
}