package Lab2;

public class TestDataset {
    
    public static void main(String[] args) {

        // To run from the Terminal:
            // javac Lab2\*.java 
            // java Lab2.TestDataset

        int errors = 0;
        
        Dataset dataset = new Dataset(2);

        // We add records (each record = input vector + output value)
        dataset.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 5.0));
        dataset.addRecord(new Record(new Vector(new double[]{3.0, 4.0}), 7.0));
        dataset.addRecord(new Record(new Vector(new double[]{5.0, 6.0}), 9.0));

        System.out.println("Dataset: " + dataset.toString());


        // meanInput()
        Vector mean = dataset.meanInput();
        // Mean computation:
            // Dimension 1: (1 + 3 + 5) / 3 = 3
            // Dimension 2: (2 + 4 + 6) / 3 = 4
        if (mean.toString().equals("[3.0, 4.0]")) {
            System.out.println("meanInput() works!");
        } else {
            errors += 1;
            System.out.println("meanInput() does not work...");
        }

        // stdInput()
        Vector std = dataset.stdInput();
        // Std computation:
            // Dimension 1: sqrt(( (1-3)^2 + (3-3)^2 + (5-3)^2 ) / 3) = 1.63299
            // Dimension 2: sqrt(( (2-4)^2 + (4-4)^2 + (6-4)^2 ) / 3) = 1.63299

        if (std.toString().equals("[1.63299, 1.63299]")) {
            System.out.println("stdInput() works!");
        } else {
            errors += 1;
            System.out.println("stdInput() does not work...");
        }

        // meanOut()
        // Mean computation: (5.0 + 7.0 + 9.0) / 3.0) = 7.0
        if (dataset.meanOutput() == 7.0) {
            System.out.println("meanOutput() works!");
        } else {
            errors += 1;
            System.out.println("meanOutput() does not work...");
        }

        // stdOutput()
        // Std computation: sqrt(( (5-7)^2 + (7-7)^2 + (9-7)^2) / 3.0 ) = 1.63299
        if (dataset.stdOutput() == 1.63299) {
            System.out.println("stdOutput() works!");
        } else {
            errors += 1;
            System.out.println("stdOutput() does not work...");
        }

        // Tests for StandardizedDataset
        StandardizedDataset s = dataset.standardize();

        System.out.println("Standardized dataset: " + s.toString());

        // We check that the StandardizedDataset s' mean and std are correct
        
        if (s.meanInput().toString().equals("[0.0, 0.0]")) {
            System.out.println("Standardized input mean is correct: " + s.meanInput());
        } else {
            errors += 1;
            System.out.println("Standardized input mean is NOT correct: " + s.meanInput());
        }

        if (s.stdInput().toString().equals("[1.0, 1.0]")) {
            System.out.println("Standardized input std is correct: " + s.stdInput());
        } else {
            errors += 1;
            System.out.println("Standardized input std is NOT correct: " + s.stdInput());
        }

        if (s.meanOutput() == 0.0) {
            System.out.println("Standardized output mean is correct: " + s.meanOutput());
        } else {
            errors += 1;
            System.out.println("Standardized output mean is NOT correct: " + s.meanOutput());
        }

        if (s.stdOutput() == 1.0) {
            System.out.println("Standardized output std is correct: " + s.stdOutput());
        } else {
            errors += 1;
            System.out.println("Standardized output std is NOT correct: " + s.stdOutput());
        }
        

        // Errors count
        System.out.println("Errors found: " + errors);
        if (errors == 0) {System.out.println("Everything works!");}

    }
}
