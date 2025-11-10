package Lab3;

public class TestLearner {
    public static void main(String[] args) {
        
        int errors = 0; // Error counter

        // TESTING VECTOR

        System.out.println("\nLet's test augment().");
        Vector v = new Vector(3, 2.0);
        System.out.println("Vector: " + v.toString());
        if (v.augment().toString().equals("[2.0, 2.0, 2.0, 1.0]")) {
            System.out.println("augment() works: " + v.augment().toString());
        } else {
            errors++;
            System.out.println("augment() does not work: " + v.augment().toString());
        }


        // TESTING SUPERVISED LEARNER (needs ALGORITHM and MODEL)

        System.out.println("\nLet's test our SupervisedLearner.");

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

        // Algorithm.gradient()
        System.out.println("\nFirst, let's test Algorithm.gradient(), needed for solve().");
        Vector g = alg.gradient(d, new Model(d.getDim() + 1));
        System.out.println("Gradient: " + g.toString());
        // The gradient should not be zero before training
        if (g.norm() > 0) {
            System.out.println("gradient() works: nonzero norm = " + Dataset.round5(g.norm()));
        } else {
            errors++;
            System.out.println("gradient() does not work: zero norm");
        }

        // toString() before solve(): should tell us that the learner is not trained yet
        System.out.println("\nLet's test toString() before training.");
        if (learner.toString().equals("Untrained model, call solve() first")) {
            System.out.println("\ntoString BEFORE solve() works: " + learner.toString());
        } else {
            errors++;
            System.out.println("\ntoString BEFORE solve() does not work: " + learner.toString());
        }
    
        // SupervisedLearner.solve(), needs Algorithm.solve()
        System.out.println("\nLet's test solve().");
        learner.solve();
        if (!learner.toString().equals("Untrained model, call solve() first")) {
            System.out.println("solve() works!");
        } else {
            errors++;
            System.out.println("solve() does not work... (still untrained)");
        }

        // SupervisedLearner.predict(), needs Model.predict()
        System.out.println("\nLet's test predict().");
        Vector v1 = new Vector(new double[]{2.0, 3.0});
        double predicted = learner.predict(v1); 
        // Expected: 2 + 2*3 + 1 = 9
        System.out.println("Expected: 9.0");
        if (Math.abs(predicted - 9.0) < 0.01) { // small tolerance
            System.out.println("predict() works: " + Dataset.round5(predicted)); // We round the value for printing the test
            System.out.println("");
        } else {
            errors++;
            System.out.println("predict() does not work (too far from expected): " + Dataset.round5(predicted));
        }

        // Errors count
        System.out.println("\nErrors found: " + errors);
        if (errors == 0) {System.out.println("Everything works! \n");}
    }
}