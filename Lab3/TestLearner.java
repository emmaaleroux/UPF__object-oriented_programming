package Lab3;

public class TestLearner {
    public static void main(String[] args) {
        
        int errors = 0; // Error counter

        // TESTING VECTOR

        System.out.println("\nLet's test augment().");
        Vector v0 = new Vector(3, 2.0);
        System.out.println("Vector: " + v0.toString());
        if (v0.augment().toString().equals("[2.0, 2.0, 2.0, 1.0]")) {
            System.out.println("augment() works: " + v0.augment().toString());
        } else {
            errors++;
            System.out.println("augment() does not work: " + v0.augment().toString());
        }

        // TESTING ALGORITHM AND MODEL

        Dataset d0 = new Dataset(2);
        d0.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 4.0));
        d0.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 5.0));
        System.out.println("Dataset: " + d0.toString());

        Algorithm a0 = new Algorithm(0.01, 0.000001);
        Model m0 = new Model(d0.getDim() + 1);

        // algorithm.gradient()
        System.out.println("\nLet's test Algorithm.gradient().");
        Vector g0 = a0.gradient(d0, m0);
        System.out.println("Gradient: " + g0.toString());
        // The gradient should not be zero before training
        if (g0.norm() > 0) {
            System.out.println("gradient() works: nonzero norm = " + Dataset.round5(g0.norm()));
        } else {
            errors++;
            System.out.println("gradient() does not work: zero norm");
        }
        
        // model.update()
        System.out.println("\nLet's test model.update().");
        Model m1 = new Model(3);
        Vector g1 = new Vector(new double[]{1.0, 1.0, 1.0});
        System.out.println("Before update: " + m1.getParams());
        m1.update(g1, 0.1);
        // Expected: 0 - x * rate --> [-0.1, -0.1, -0.1]
        if (m1.getParams().toString().equals("[-0.1, -0.1, -0.1]")) {
            System.out.println("update() works: " + m1.getParams().toString());
        } else {
            errors++;
            System.out.println("update() does not work (parameters unchanged).");
        }


        // TESTING SUPERVISED LEARNER (needs ALGORITHM and MODEL)

        System.out.println("\nNow we can test our SupervisedLearner.");

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
        System.out.println("\nLet's test toString() before training (exception).");
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
            System.out.println("");
        } else {
            errors++;
            System.out.println("predict() does not work (too far from expected): " + Dataset.round5(predicted));
        }

        /*
        //OPTIONAL: MOORE-PENROSE INVERSE 
        //Compare gradient-descent model and Moore-Penrose model
        System.out.println("\nComparing Gradient Descent vs Moore-Penrose solution:");

        Model gdModel = learner.getModel();
        System.out.println("Gradient Descent θ: " + gdModel.getParams().toString());

        Model mpModel = learner.MPInverse();
        System.out.println("Moore-Penrose θ: " + mpModel.getParams().toString());

        Vector diff = gdModel.getParams().subtract(mpModel.getParams());
        System.out.println("Norm of difference (GD - MP): " + diff.norm());
         */

        // Errors count
        System.out.println("\nErrors found: " + errors);
        if (errors == 0) {System.out.println("Everything works! \n");}
    }
}