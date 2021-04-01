package com.magneto.services;

import com.magneto.Entities.Human;
import com.magneto.utils.MatrixUtils;
import com.magneto.utils.Validations;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.exceptions.HttpStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HumanService {
    private static final Logger log = LoggerFactory.getLogger(HumanService.class);
    @Inject
    private MatrixUtils matrixUtils;
    @Inject
    private Validations validations;


    /**
     * Given a human who has a dna list, this method identify if the human is a mutant
     */
    public boolean isMutant(Human human){
        char[][] matrix;
        char[][] auxMatrix;
        int      countMutantSequence = 0;
        boolean  firstIteration = true;

        log.info("list size is {}", human.getDna().size());

        //Validate if it is a square matrix
        if(!matrixUtils.isSquareMatrix(human.getDna())){
            log.info("invalid DNA, must be an NxN matrix");
            throw new HttpStatusException(HttpStatus.FORBIDDEN, "invalid DNA, must be an NxN matrix");
        }

        //Transform the list in a char-Matrix
        matrix = matrixUtils.toMatrix(human.getDna());

        //Validate if a matrix contains correct Nitrogen bases
        if(!validations.containsValidNitrogenBases(matrix)){
            log.info("invalid Nitrogen Bases, must be (A,T,C,G)");
            throw new HttpStatusException(HttpStatus.FORBIDDEN, "invalid Nitrogen Bases, must be (A,T,C,G)");
        }

        //detect mutant sequences in a horizontal way
        countMutantSequence += matrixUtils.detectMutantSequence(matrix, matrix.length);

        //detect mutant sequences in a vertical way
        auxMatrix = matrixUtils.toTranspose(matrix);
        countMutantSequence += matrixUtils.detectMutantSequence(auxMatrix, matrix.length);

        //detect mutant sequences in a negative-diagonal way
        for (int i = 0; i <= matrix.length - validations.NUMBER_SAME_BASE_TO_BE_MUTANT; i++) {
            if(firstIteration) {
                firstIteration = false;
                auxMatrix = matrixUtils.identifyNegativeDiagonalMatrix(matrix, i);
                auxMatrix = matrixUtils.toTranspose(auxMatrix);
                countMutantSequence += matrixUtils.detectMutantSequence(auxMatrix, matrix.length);
            }else{
                auxMatrix = matrixUtils.identifyNegativeDiagonalMatrix(matrix, i);
                auxMatrix = matrixUtils.toTranspose(auxMatrix);
                countMutantSequence += matrixUtils.detectMutantSequence(auxMatrix, 1);
            }

        }

        //detect mutant sequences in a positive-diagonal way
        firstIteration       = true;
        for (int i = matrix.length - 1; i >= validations.NUMBER_SAME_BASE_TO_BE_MUTANT - 1 ; i--) {
            if(firstIteration) {
                firstIteration = false;
                auxMatrix = matrixUtils.identifyPositiveDiagonalMatrix(matrix, i);
                auxMatrix = matrixUtils.toTranspose(auxMatrix);
                countMutantSequence += matrixUtils.detectMutantSequence(auxMatrix, matrix.length);

            }else{
                auxMatrix = matrixUtils.identifyPositiveDiagonalMatrix(matrix, i);
                auxMatrix = matrixUtils.toTranspose(auxMatrix);
                countMutantSequence += matrixUtils.detectMutantSequence(auxMatrix, 1);
            }

        }

        if(countMutantSequence >= validations.NUMBER_SEQ_TO_BE_MUTANT){
            log.info("DNA is mutant, it has {} Mutant Sequences", countMutantSequence);
            return true;
        }else{
            log.info("DNA is not mutant, it has {} Mutant Sequences", countMutantSequence);
            return false;
        }

    }
}