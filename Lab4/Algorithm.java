package Lab4;

public abstract class Algorithm {
    
    // ATTRIBUTES
    protected final double learningRate;

    // CONSTRUCTOR
    public Algorithm(double lr) {
        learningRate = lr;
    }

    // GETTERS
    public double getLearningRate() {
        return learningRate;
    }

    // METHODS
    public abstract Model solve(Dataset ds);

}