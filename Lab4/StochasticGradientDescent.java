package Lab4;

import java.util.Random;
import javax.xml.crypto.Data;

public class StochasticGradientDescent extends Algorithm{
    private final int batchSize;
    private final int iterations;
    private final Random random;

    public StochasticGradientDescent(double learningRate, int batchSize, int iterations, Random random) {
        super(learningRate);
        this.batchSize = batchSize;
        this.iterations = iterations;
        this.random = (random == null) ? new Random() : random;
    }

    public StochasticGradientDescent(double learningRate, int batchSize, int iterations) {
        this(learningRate, batchSize, iterations, new Random());
    }

    public Vector stochasticGradient(Dataset d, Model m){

    }

    public Model solve(Dataset ds){

    }

}
