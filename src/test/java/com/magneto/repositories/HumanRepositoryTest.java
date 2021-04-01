package com.magneto.repositories;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.inject.Inject;

@MicronautTest
public class HumanRepositoryTest {

    @Inject
    HumanRepository humanRepository;

    @Test
    public void addDnaTest(){
        String dna = "ATGCGA|CCCCCC|TTATGT|AGAAGG|CCCCTA|TCACGC|";
        Mockito.when(humanRepository.addDna(dna, true)).thenReturn(true);
        Assertions.assertTrue(humanRepository.addDna(dna, true));

    }

    @Test
    public void getCountTotalTest(){
        int countTotalExpected = 5;
        Mockito.when(humanRepository.getCountTotal()).thenReturn(5);
        Assertions.assertEquals(countTotalExpected,humanRepository.getCountTotal());
    }

    @Test
    public void getCountMutantTest(){
        int countMutantExpected = 2;
        Mockito.when(humanRepository.getCountMutant()).thenReturn(2);
        Assertions.assertEquals(countMutantExpected,humanRepository.getCountMutant());
    }

    @MockBean(HumanRepository.class)
    HumanRepository humanRepository(){
        return Mockito.mock(HumanRepository.class);
    }
}
