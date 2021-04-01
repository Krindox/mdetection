package com.magneto.Entities;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Introspected
public class Human {
    @NotBlank
    private List<String> dna;

    public Human() {
    }

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }
}
