package Lab3;

public class TestLearner {
    public static void main(String[] args) {
        

        int errors = 0; // Error counter

        // TESTING SUPERVISED LEARNER

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

        // toString() before solve(): should tell us that the learner is not trained yet
        if (learner.toString().equals("Untrained model, call solve() first")) {
            System.out.println("\ntoString BEFORE solve() works!");
        } else {
            errors++;
            System.out.println("\ntoString BEFORE solve() does not work.");
            System.out.println("Expected: Untrained model, call solve() first");
            System.out.println("Got: " + learner.toString());
        }

        // Train the model (gradient descend)
        learner.solve();

        // toString() after solve(): should not show the untrained model message
        if (!learner.toString().equals("Untrained model, call solve() first")) {
            System.out.println("toString AFTER solve() works!");
        } else {
            errors++;
            System.out.println("toString AFTER solve() does not work... (still untrained)");
        }


        // predict()
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

        // Errors count
        System.out.println("\nErrors found: " + errors);
        if (errors == 0) {System.out.println("Everything works! \n");}
    }
}