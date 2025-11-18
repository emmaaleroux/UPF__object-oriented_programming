package Lab4;

public class GradientDescent extends Algorithm {
    private final double stoppingCriterion; // tolerance for stopping (on parameter-change magnitude)
    private final int maxIterations;

    public GradientDescent(double learningRate, double stoppingCriterion, int maxIterations) {
        super(learningRate);
        this.stoppingCriterion = stoppingCriterion;
        this.maxIterations = maxIterations;
    }

    public Vector gradient(Dataset d, Model m){
        
    }

    public Model solve(Dataset ds){
        
    }

}
