package com.magneto.utils;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class ValidationsTest {

    @Inject
    Validations validations;

    @Test
    public void notContainsValidNBTest(){
        char[][] matrix = new char[][]{{'z','A','z','z'},{'x','C','x','x'},{'z','x','G','x'},{'x','z','x','T'}};
        Assertions.assertFalse(validations.containsValidNitrogenBases(matrix));
    }

    @Test
    public void containsValidNBTest(){
        char[][] matrix = new char[][]{{'A','T','G','C'},{'A','T','G','C'},{'A','T','G','C'},{'A','T','G','C'}};
        Assertions.assertTrue(validations.containsValidNitrogenBases(matrix));
    }
}
