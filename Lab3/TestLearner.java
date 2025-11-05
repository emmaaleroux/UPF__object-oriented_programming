package Lab3;

public class TestLearner {
    public static void main(String[] args) {
        
        //Error counter (as in the previous Labs)
        int errors = 0;

        // We build a tiny dataset (dim = 2)
        // We choose outputs all 0.0 because linear regression learners will return zero-parameter vector
        int dim = 2;
        Dataset train = new Dataset(dim);
        train.addRecord(new Record(new Vector(new double[]{1.0,  2.0}), 0.0)); //x₁ = [1, 2], y₁ = 0
        train.addRecord(new Record(new Vector(new double[]{-3.0, 4.0}), 0.0)); //x₁ = [-3, 4], y₁ = 0
        System.out.println("Dataset: " + train.toString());

        // Create an Algorithm
        Algorithm alg = new Algorithm(0.01, 0.000001); //learning algorithm with learning rate 0.01 and tolerance 0.000001

        // Create the learner 
        SupervisedLearner learner = new SupervisedLearner(alg, train);

        // toString before solve() should tell us that the learner is not trained yet
        String expectedUntrained = "Untrained model (call solve() first)";
        String before = learner.toString();
        if (expectedUntrained.equals(before)) {
            System.out.println("toString BEFORE solve works!");
        } else {
            errors = errors + 1;
            System.out.println("toString BEFORE solve does not work.");
            System.out.println("Expected: " + expectedUntrained);
            System.out.println("Got: " + before);
        }

        // Train the model 
        learner.solve(); //algorithm.solve(dataset) gets back a model
        System.out.println("solve() called.");

        // After training the model, toString should no longer show the untrained model message
        String after = learner.toString();
        if (!expectedUntrained.equals(after)) {
            System.out.println("toString AFTER solve works!"); //the model is trained correctly
        } else {
            errors = errors + 1;
            System.out.println("toString AFTER solve dones not work (still untrained)");
        }

        ////////////////////
        //falta test predict on non-augmented vector (predict after solve)
        //////////////////

        System.out.println("\nErrors found: " + errors);
        if (errors == 0) {System.out.println("Everything works! \n");}
    }
}