package Lab4;

public class TestLearner {
    public static void main(String[] args) {
        
        int errors = 0; // Error counter

        // 1. SupervisedLearner.predict()

        // 2. Tests of stochastic vs gradient for RawDataset
        System.out.println("\nTesting Stochastic gradient vs Gradient descend for RawDataset.");

        // - Create RawDataset
        // linear relationship: y = x1 + 2*x2 + 1
        Dataset dRaw = new RawDataset(2);
        dRaw.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 4.0)); // 1 + 2*1 + 1 = 4
        dRaw.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 5.0)); // 2 + 2*1 + 1 = 5
        dRaw.addRecord(new Record(new Vector(new double[]{1.0, 3.0}), 8.0)); // 1 + 2*3 + 1 = 8
        dRaw.addRecord(new Record(new Vector(new double[]{3.0, 2.0}), 8.0)); // 3 + 2*2 + 1 = 8
        System.out.println("\nRawDataset: " + dRaw.toString());

        // - Train with GradientDescent
        Algorithm algRawGd = new GradientDescent(0.01, 0.000001); 
        SupervisedLearner learnerRawGd = new SupervisedLearner(algRawGd, dRaw);

        // - Train with StochasticGradientDescent
        int batchSize = 2;
        int iterations = 4;
        Algorithm algRawSg = new StochasticGradientDescent(0.01, batchSize, iterations); 
        SupervisedLearner learnerRawSg = new SupervisedLearner(algRawGd, dRaw);
        // - Compare predictions
        // - Compare parameters (SGD is approximate)

        // 3. Tests of stochastic vs gradient for StandardizedDataset
        System.out.println("\nTesting Stochastic gradient vs Gradient descend for StandardizedDataset.");

        // - Wrap RawDataset in StandardizedDataset
        // - Repeat GD vs SGD training
        // - Compare predictions
        // - Predictions should be consistent after destandardizing




        /* LAB 3


        // TESTING SUPERVISED LEARNER (needs ALGORITHM and MODEL)

        // We build a small dataset (dim = 2, n = 4) with a simple linear relationship: y = x1 + 2*x2 + 1
    
        // SupervisedLearner.solve(), needs Algorithm.solve()
        System.out.println("\nLet's test solve().");
        learner.solve();
        if (!learner.toString().equals("Untrained model, call solve() first")) {
            System.out.println("solve() works!");
        } else {
            errors++;
            System.out.println("solve() does not work, still untrained");
        }

        // SupervisedLearner.predict(), needs Model.predict()
        System.out.println("\nLet's test predict().");
        Vector v1 = new Vector(new double[]{2.0, 3.0});
        double predicted = learner.predict(v1); 
        // Expected: 2 + 2*3 + 1 = 9
        System.out.println("Expected: 9.0");
        if (Math.abs(predicted - 9.0) < 0.01) { // small tolerance
            System.out.println("predict() works: " + Dataset.round5(predicted)); // We round the value for printing the test
        } else {
            errors++;
            System.out.println("predict() does not work (too far from expected): " + Dataset.round5(predicted));
        }

        // toString() after solve(): should show the final parameters ([1.0, 2.0, 1.0])
        System.out.println("\nLet's test toString() AFTER training.");
        Vector elements = learner.getModel().getParams();
        if ((Math.abs(elements.getElems()[0] - 1.0) < 0.01) && (Math.abs(elements.getElems()[1] - 2.0) < 0.01) && (Math.abs(elements.getElems()[2] - 1.0) < 0.01)) {
            System.out.println("toString AFTER solve() works: " + learner.toString());
        } else {
            errors++;
            System.out.println("toString AFTER solve() does not work: " + learner.toString());
        }
        */

        // Errors count
        System.out.println("\nErrors found: " + errors);
        if (errors == 0) {System.out.println("Everything works! \n");}
    }
}