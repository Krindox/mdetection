package com.magneto.services;


import com.amazonaws.services.dynamodbv2.xspec.S;
import com.magneto.Entities.Human;
import com.magneto.repositories.HumanRepository;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@MicronautTest
public class HumanServiceTest {


    @Inject
    private HumanService humanService;

    @Test
    public void isNotANxNDnaMatrix(){
        Human human = new Human();
        List<String> listDnaMutant = new LinkedList<>();
        listDnaMutant.add("ATGCGGGGA");
        listDnaMutant.add("CAGTGC");
        listDnaMutant.add("TTATGT");
        listDnaMutant.add("AGAAGG");
        human.setDna(listDnaMutant);
        Assertions.assertThrows(HttpStatusException.class, () -> humanService.isMutant(human), "expected throw");

    }

    @Test
    public void dnaContainsNonValidNitrogenBases(){
        Human human = new Human();
        List<String> listDnaMutant = new LinkedList<>();
        listDnaMutant.add("ATQCGA");
        listDnaMutant.add("CAGTGC");
        listDnaMutant.add("PTAVGT");
        listDnaMutant.add("AGAAGJ");
        listDnaMutant.add("XCCYTA");
        listDnaMutant.add("TCAZTL");
        human.setDna(listDnaMutant);
        Assertions.assertThrows(HttpStatusException.class, () -> humanService.isMutant(human), "throw expected");
    }

    @Test
    public void isMutant(){
        Human human = new Human();
        List<String> listDnaMutant = new LinkedList<>();
        listDnaMutant.add("AAAAAA");
        listDnaMutant.add("TTTTTT");
        listDnaMutant.add("TTATGT");
        listDnaMutant.add("AGAAGG");
        listDnaMutant.add("CCCCTA");
        listDnaMutant.add("TCACTG");
        human.setDna(listDnaMutant);
        boolean isMutant = humanService.isMutant(human);
        Assertions.assertEquals(true, isMutant);
    }

    @Test
    public void isNotMutant(){
        Human human = new Human();
        List<String> listDnaMutant = new LinkedList<>();
        listDnaMutant.add("AGAGAG");
        listDnaMutant.add("CTCTCT");
        listDnaMutant.add("TTATGT");
        listDnaMutant.add("AGAAGG");
        listDnaMutant.add("CCCCTA");
        listDnaMutant.add("TCACTG");
        human.setDna(listDnaMutant);
        Assertions.assertFalse(humanService.isMutant(human));
    }

    @Test
    public void concatDnaTest(){
        String expectedDna = new String();
        expectedDna = "AGAGAG|CTCTCT|TTATGT|AGAAGG|CCCCTA|TCACTG|";
        List<String> dnaSequences = new LinkedList<>();
        dnaSequences.add("AGAGAG");
        dnaSequences.add("CTCTCT");
        dnaSequences.add("TTATGT");
        dnaSequences.add("AGAAGG");
        dnaSequences.add("CCCCTA");
        dnaSequences.add("TCACTG");
        Assertions.assertEquals(expectedDna, humanService.concatDna(dnaSequences));
    }


}
