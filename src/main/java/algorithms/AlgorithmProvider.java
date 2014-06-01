package algorithms;

import algorithms.algorithm2.Algorithm2;
import algorithms.algorithm3.Algorithm3;
import algorithms.random.TerrainGenerator;

/**
 * Created by Vortim on 2014-06-01.
 */
public class AlgorithmProvider {

    private AlgorithmProvider() {
    }

    public static Algorithm getRandomAlgorithm() {
        return new TerrainGenerator();
    }

    public static Algorithm getAlgorithm2Algorithm() {
        return new Algorithm2();
    }

    public static Algorithm getAlgorithm3Algorithm() {
        return new Algorithm3();
    }

}