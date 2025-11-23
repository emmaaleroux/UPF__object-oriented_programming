package Lab4;

public class TestLearner {
    public static void main(String[] args) {
        
        int errors = 0; // Error counter


        // 1. Testing SupervisedLearner.predict() for StandardizedDatasets

        System.out.println("\n-- Testing the new predict() for standardized datasets --");
        RawDataset d1 = new RawDataset(3);
        // Linear relationship: x1 + x2 + x3
        d1.addRecord(new Record(new Vector(new double[]{1.0, 2.0, 3.0}), 6.0)); // 1 + 2 + 3 = 6
        d1.addRecord(new Record(new Vector(new double[]{4.0, 5.0, 6.0}), 15.0)); // 4 + 5 + 6 = 15
        System.out.println("\nDataset: " + d1.toString());
        System.out.println("Linear relationship: x1 + x2 + x3");
        StandardizedDataset d2 = d1.standardize();
        System.out.println("Standardized Dataset: " + d2.toString());
        Algorithm a = new GradientDescent(0.1, 0.000001); 
        SupervisedLearner learner = new SupervisedLearner(a, d2);
        learner.solve();
        Vector v = new Vector(new double[]{3.0, 4.0, 5.0}); 
        double predicted = learner.predict(v); 
        // Expected: 3 + 4 + 5 = 12
        System.out.println("Expected: 12");
        if (Math.abs(predicted - 12) < 0.01) { // small tolerance
            System.out.println("--> predict() works: " + Dataset.round5(predicted)); // We round the value for printing the test
        } else {
            errors++;
            System.out.println("--> predict() does not work (too far from expected): " + Dataset.round5(predicted));
        }

        // 2. Tests of stochastic vs gradient for RawDataset

        System.out.println("\n\n-- Testing Stochastic gradient vs Gradient descend for RawDataset -- ");

        // 2.1 - Create RawDataset
        // As in Lab 3, linear relationship: y = x1 + 2*x2 + 1
        RawDataset dRaw = new RawDataset(2);
        dRaw.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 4.0)); // 1 + 2*1 + 1 = 4
        dRaw.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 5.0)); // 2 + 2*1 + 1 = 5
        dRaw.addRecord(new Record(new Vector(new double[]{3.0, 1.0}), 6.0)); // 3 + 2*1 + 1 = 6
        dRaw.addRecord(new Record(new Vector(new double[]{1.0, 3.0}), 8.0)); // 1 + 2*3 + 1 = 8
        dRaw.addRecord(new Record(new Vector(new double[]{3.0, 2.0}), 8.0)); // 3 + 2*2 + 1 = 8
        dRaw.addRecord(new Record(new Vector(new double[]{5.0, 2.0}), 10.0)); // 5 + 2*2 + 1 = 10
        System.out.println("\nRawDataset: " + dRaw.toString());
        System.out.println("Linear relationship: y = x1 + 2*x2 + 1");

        // 2.2 - Train with GradientDescent
        Algorithm algRawGd = new GradientDescent(0.1, 0.000001); 
        SupervisedLearner learnerRawGd = new SupervisedLearner(algRawGd, dRaw);
        learnerRawGd.solve();
        System.out.println("\nGradient Descent: " + learnerRawGd.toString());

        // 2.3 - Train with StochasticGradientDescent
        int batchSize = 2;
        int iterations = 500;
        Algorithm algRawSg = new StochasticGradientDescent(0.05, batchSize, iterations); 
        SupervisedLearner learnerRawSg = new SupervisedLearner(algRawSg, dRaw);
        learnerRawSg.solve();
        System.out.println("Stochastic Gradient Descent: " + learnerRawSg.toString());

        // 2.4 - Compare predictions (expected: 9.0)
        Vector test = new Vector(new double[]{2.0, 3.0});

        double predRawGd  = learnerRawGd.predict(test);
        System.out.println("\nGradient Descent prediction:  " + predRawGd);
        double predRawSg  = learnerRawSg.predict(test);
        System.out.println("Stochastic Gradient Descent prediction:  " + predRawSg);

        if (Math.abs(predRawGd - predRawSg) < 0.2) {
            System.out.println("\n--> RawDataset predictions agree.");
        } else {
            System.out.println("\n--> RawDataset predictions differ too much");
            errors++;
        }


        // 3. Tests of stochastic vs gradient for StandardizedDataset

        System.out.println("\n\n-- Testing Stochastic gradient vs Gradient descend for StandardizedDataset --");

        // 3.1 - Wrap RawDataset in StandardizedDataset
        StandardizedDataset dStd = dRaw.standardize();
        System.out.println("\nStandardizedDataset: " + dStd.toString());

        // 3.2 - Train with GradientDescent
        Algorithm algStdGd = new GradientDescent(0.1, 0.000001); 
        SupervisedLearner learnerStdGd = new SupervisedLearner(algStdGd, dStd);
        learnerStdGd.solve();
        System.out.println("\nGradient Descent: " + learnerStdGd.toString());

        // 3.3 - Train with StochasticGradientDescent
        Algorithm algStdSg = new StochasticGradientDescent(0.05, batchSize, iterations); 
        SupervisedLearner learnerStdSg = new SupervisedLearner(algStdSg, dStd);
        learnerStdSg.solve();
        System.out.println("Stochastic Gradient Descent: " + learnerStdSg.toString());

        // 3.4 - Compare predictions
        double predStdGd  = learnerStdGd.predict(test);
        System.out.println("\nGradient Descent prediction:  " + predStdGd);
        double predStdSg  = learnerStdSg.predict(test);
        System.out.println("Stochastic Gradient Descent prediction:  " + predStdSg);

        if (Math.abs(predStdGd - predStdSg) < 0.2) {
            System.out.println("\n--> StandardizedDataset predictions agree.");
        } else {
            System.out.println("\n--> StandardizedDataset predictions differ too much");
            errors++;
        }


        // Errors count
        System.out.println("\n\nErrors found: " + errors);
        if (errors == 0) {System.out.println("Everything works! \n");}
    }
}