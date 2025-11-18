package Lab4;

public class TestLearner {
    public static void main(String[] args) {
        
        int errors = 0; // Error counter

        // 1. SupervisedLearner.predict()

        // 2. Tests of stochastic vs gradient for RawDataset
        System.out.println("\nTesting Stochastic gradient vs Gradient descend for RawDataset.");

        // 3. Tests of stochastic vs gradient for StandardizedDataset
        System.out.println("\nTesting Stochastic gradient vs Gradient descend for StandardizedDataset.")






        // LAB 3

        // TESTING ALGORITHM AND MODEL

        Dataset d0 = new Dataset(2);
        d0.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 4.0));
        d0.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 5.0));
        System.out.println("Dataset: " + d0.toString());

        Algorithm a0 = new Algorithm(0.01, 0.000001);
        Model m0 = new Model(d0.getDim() + 1);


        // TESTING SUPERVISED LEARNER (needs ALGORITHM and MODEL)

        // We build a small dataset (dim = 2, n = 4) with a simple linear relationship: y = x1 + 2*x2 + 1
        Dataset d = new Dataset(2);
        d.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 4.0)); // 1 + 2*1 + 1 = 4
        d.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 5.0)); // 2 + 2*1 + 1 = 5
        d.addRecord(new Record(new Vector(new double[]{1.0, 3.0}), 8.0)); // 1 + 2*3 + 1 = 8
        d.addRecord(new Record(new Vector(new double[]{3.0, 2.0}), 8.0)); // 3 + 2*2 + 1 = 8
        System.out.println("\nDataset: " + d.toString());
        System.out.println("Linear relationship: y = x1 + 2*x2 + 1");

        // Create algorithm (rate = 0.01, tolerance = 0.000001) and learner
        Algorithm alg = new Algorithm(0.01, 0.000001); 
        SupervisedLearner learner = new SupervisedLearner(alg, d);
    
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

        // Errors count
        System.out.println("\nErrors found: " + errors);
        if (errors == 0) {System.out.println("Everything works! \n");}
    }
}