package com.magneto.services;

import com.magneto.Entities.Stats;
import com.magneto.repositories.HumanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StatsService {

    private int countMutant;
    private int countHuman;
    private double ratio;
    private Stats stats = new Stats();
    private static final Logger log = LoggerFactory.getLogger(StatsService.class);

    @Inject
    HumanRepository humanRepository;

    public Stats getStats(){
        log.info("Searching total count");
        countHuman  = humanRepository.getCountTotal();
        log.info("Searching mutant count");
        countMutant = humanRepository.getCountMutant();

        if(countHuman == 0){
            stats.setCount_human_dna(0);
            stats.setCount_mutant_dna(0);
            stats.setRatio(0.0);
        }else if(countMutant == 0){
            stats.setCount_human_dna(countHuman);
            stats.setCount_mutant_dna(0);
            stats.setRatio(0.0);
        }else{
            stats.setCount_human_dna(countHuman);
            stats.setCount_mutant_dna(countMutant);
            stats.setRatio((double) countMutant / countHuman);
        }

        return stats;
    }
}
