package Lab2;

public class TestDataset {
    
    public static void main(String[] args) {

        // As in Lab 1, we have an error counter
        int errors = 0;
        

        // Testing DATASET

        Dataset d1 = new Dataset(2);
        // We add records (record = input vector + output value)
        d1.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 5.0));
        d1.addRecord(new Record(new Vector(new double[]{3.0, 4.0}), 7.0));
        d1.addRecord(new Record(new Vector(new double[]{5.0, 6.0}), 9.0));
        // We print the dataset
        System.out.println("Dataset 1: " + d1.toString());


        // meanInput()
        Vector mean = d1.meanInput();
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
        Vector std = d1.stdInput();
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
        if (d1.meanOutput() == 7.0) {
            System.out.println("meanOutput() works!");
        } else {
            errors += 1;
            System.out.println("meanOutput() does not work...");
        }

        // stdOutput()
        // Std computation: sqrt(( (5-7)^2 + (7-7)^2 + (9-7)^2) / 3.0 ) = 1.63299
        if (d1.stdOutput() == 1.63299) {
            System.out.println("stdOutput() works!");
        } else {
            errors += 1;
            System.out.println("stdOutput() does not work...");
        }

        // Dataset: exception tests to avoid potential errors

        System.out.println("\nLet's check for addRecord exceptions: ");

        Dataset d2 = new Dataset(2);
        d2.addRecord(null); // null array
        d2.addRecord(new Record(null, 1.0)); // null dimension
        d2.addRecord(new Record(new Vector(new double[]{1.0, 2.0, 3.0}), 0.0)); // wrong dimension (3 instead of 2)
        d2.addRecord(new Record(new Vector(new double[]{9.0, 9.0}), 9.0)); // correct input

        if (d2.getData().size() == 1) {
            System.out.println("addRecord() exceptions work!");
        } else {
            errors += 1;
            System.out.println("addRecord() exceptions do work...");
        }


        // Testing StandardizedDataset

        StandardizedDataset s1 = d1.standardize();
        System.out.println("\nStandardized dataset 1: " + s1.toString());

        // s.meanInput()
        if (s1.meanInput().toString().equals("[0.0, 0.0]")) {
            System.out.println("Standardized input mean is correct: " + s1.meanInput());
        } else {
            errors += 1;
            System.out.println("Standardized input mean is NOT correct: " + s1.meanInput());
        }

        // s.stdInput()
        if (s1.stdInput().toString().equals("[1.0, 1.0]")) {
            System.out.println("Standardized input std is correct: " + s1.stdInput());
        } else {
            errors += 1;
            System.out.println("Standardized input std is NOT correct: " + s1.stdInput());
        }

        // s.meanOutput()
        if (s1.meanOutput() == 0.0) {
            System.out.println("Standardized output mean is correct: " + s1.meanOutput());
        } else {
            errors += 1;
            System.out.println("Standardized output mean is NOT correct: " + s1.meanOutput());
        }

        // s.stdOutput()
        if (s1.stdOutput() == 1.0) {
            System.out.println("Standardized output std is correct: " + s1.stdOutput());
        } else {
            errors += 1;
            System.out.println("Standardized output std is NOT correct: " + s1.stdOutput());
        }
        

        // StandardizedDataset: exception tests to avoid potential errors

        System.out.println("\nLet's check for zero-variance exceptions: ");

        Dataset d3 = new Dataset(2);
        d3.addRecord(new Record(new Vector(new double[]{1.0, 5.0}), 10.0));
        d3.addRecord(new Record(new Vector(new double[]{2.0, 5.0}), 20.0));
        d3.addRecord(new Record(new Vector(new double[]{3.0, 5.0}), 30.0));
        System.out.println("Dataset 3: " + d3.toString());

        StandardizedDataset s3 = d3.standardize();
        System.out.println("Standardized dataset 3: " + s3.toString());

        if (d3.toString().equals(s3.toString())) {
            System.out.println("Zero-variance exception check works!");
        } else {
            errors += 1;
            System.out.println("Zero-variance exception check does not work...");
        }


        // Testing NormalizedDataset
        
        NormalizedDataset n1 = d1.normalize();
        System.out.println("\nNormalized dataset 1: " + n1.toString());

        // Expected normalized inputs: [ (0,0), (0.5,0.5), (1,1) ]
        // Expected normalized outputs: 0, 0.5, 1
        if (n1.toString().equals("{[0.0, 0.0] -> 0.0, [0.5, 0.5] -> 0.5, [1.0, 1.0] -> 1.0}")) {
            System.out.println("normalize() works!");
        } else {
            errors += 1;
            System.out.println("normalize() does not work: " + n1.toString());
        }


        // NormalizedDataset: exception tests to avoid potential errors

        System.out.println("\nLet's check for 0-range exceptions: ");

        Dataset d4 = new Dataset(2);
        d4.addRecord(new Record(new Vector(new double[]{1.0, 5.0}), 7.0));
        d4.addRecord(new Record(new Vector(new double[]{2.0, 5.0}), 7.0));
        d4.addRecord(new Record(new Vector(new double[]{3.0, 5.0}), 7.0));
        System.out.println("Dataset 4: " + d4.toString());

        NormalizedDataset n4 = d4.normalize();
        System.out.println("Normalized dataset 4: " + n4.toString());
        
        if (d4.toString().equals(n4.toString())) {
            System.out.println("Zero-range exception check works!");
        } else {
            errors += 1;
            System.out.println("Zero-range exception check does not work...");
        }


        // Errors count
        System.out.println("\nErrors found: " + errors);
        if (errors == 0) {System.out.println("Everything works!");}

    }
}
