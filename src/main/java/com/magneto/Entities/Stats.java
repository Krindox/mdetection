package com.magneto.Entities;

import io.micronaut.core.annotation.Introspected;

import java.util.Objects;

@Introspected
public class Stats {
    private int count_mutant_dna;
    private int count_human_dna;
    private double ratio;

    public int getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(int count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public int getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(int count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return count_mutant_dna == stats.count_mutant_dna &&
                count_human_dna == stats.count_human_dna &&
                Double.compare(stats.ratio, ratio) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count_mutant_dna, count_human_dna, ratio);
    }
}
