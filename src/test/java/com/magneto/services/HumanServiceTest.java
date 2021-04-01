package com.magneto.services;


import com.amazonaws.services.dynamodbv2.xspec.S;
import com.magneto.Entities.Human;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
        char[][] matrix = new char[listDnaMutant.size()][listDnaMutant.size()];
        for (int i = 0; i < listDnaMutant.size(); i++) {
            for (int j = 0; j < listDnaMutant.size(); j++) {
                matrix[i][j] = listDnaMutant.get(i).charAt(j);
            }
        }
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
        char[][] matrix = new char[listDnaMutant.size()][listDnaMutant.size()];
        for (int i = 0; i < listDnaMutant.size(); i++) {
            for (int j = 0; j < listDnaMutant.size(); j++) {
                matrix[i][j] = listDnaMutant.get(i).charAt(j);
            }
        }
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
        char[][] matrix = new char[listDnaMutant.size()][listDnaMutant.size()];
        for (int i = 0; i < listDnaMutant.size(); i++) {
            for (int j = 0; j < listDnaMutant.size(); j++) {
                matrix[i][j] = listDnaMutant.get(i).charAt(j);
            }
        }
        Assertions.assertFalse(humanService.isMutant(human));
    }

    @Test
    public void concatDnaTest(){
        String dna = new String();
        List<String> dnaSequences = new LinkedList<>();
        dnaSequences.add("AGAGAG");
        dnaSequences.add("CTCTCT");
        dnaSequences.add("TTATGT");
        dnaSequences.add("AGAAGG");
        dnaSequences.add("CCCCTA");
        dnaSequences.add("TCACTG");
        dna = humanService.concatDna(dnaSequences);
        System.out.println(dna);
    }
}
