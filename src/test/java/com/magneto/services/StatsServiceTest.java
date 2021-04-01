package com.magneto.services;


import com.magneto.Entities.Stats;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.inject.Inject;

@MicronautTest
public class StatsServiceTest {

    @Inject
    private StatsService statsService;

    @Test
    public void getStatsTest(){
        Stats expectedStats = new Stats();
        Stats stats = new Stats();
        expectedStats.setCount_mutant_dna(2);
        expectedStats.setCount_human_dna(5);
        expectedStats.setRatio(0.2);
        Mockito.when(statsService.getStats()).thenReturn(expectedStats);
        stats = statsService.getStats();
        Assertions.assertEquals(expectedStats, stats);
    }

    @MockBean(StatsService.class)
    StatsService statsService(){
        return Mockito.mock(StatsService.class);
    }

}
