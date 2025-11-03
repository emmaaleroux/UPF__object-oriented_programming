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
        
        
        //Extra Tests for exception checks (optional)

        //addRecord exception check test (nulls and dimension mismatch)
        Dataset d2 = new Dataset(2);
        d2.addRecord(null);
        d2.addRecord(new Record(null, 1.0));
        d2.addRecord(new Record(new Vector(new double[]{1.0, 2.0, 3.0}), 0.0));
        d2.addRecord(new Record(new Vector(new double[]{9.0, 9.0}), 9.0));

        if (d2.getData().size() == 1) {
            System.out.println("addRecord exception check works!");
        } else {
            errors += 1;
            System.out.println("addRecord exception check does not work...");
        }

        //standardize exception check test (zero-variance → unchanged records)
        Dataset d3 = new Dataset(2);
        d3.addRecord(new Record(new Vector(new double[]{1.0, 5.0}), 10.0));
        d3.addRecord(new Record(new Vector(new double[]{2.0, 5.0}), 20.0));
        d3.addRecord(new Record(new Vector(new double[]{3.0, 5.0}), 30.0));

        StandardizedDataset s0v = d3.standardize();

        if (d3.toString().equals(s0v.toString())) {
            System.out.println("Zero-variance exception check works!");
        } else {
            errors += 1;
            System.out.println("Zero-variance exception check does NOT work.");
        }



        //OPTIONAL: Tests for NormalizedDataset (min-max to [0,1])

        Dataset dn = new Dataset(2);
        dn.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 5.0));
        dn.addRecord(new Record(new Vector(new double[]{3.0, 4.0}), 7.0));
        dn.addRecord(new Record(new Vector(new double[]{5.0, 6.0}), 9.0));

        NormalizedDataset nz = dn.normalize();
        /* (code to find error)
        System.out.println("NZ first: " + nz.getData().get(0));
        System.out.println("NZ second: " + nz.getData().get(1));
        System.out.println("NZ third: " + nz.getData().get(2));
        */ 

        //expected normalized inputs: [ (0,0), (0.5,0.5), (1,1) ]
        //expected normalized outputs: 0, 0.5, 1
        String expected = "{[0.0, 0.0] -> 0.0, [0.5, 0.5] -> 0.5, [1.0, 1.0] -> 1.0}";

        if (nz.toString().equals(expected)) {
            System.out.println("normalize() works!");
        } else {
            errors += 1;
            System.out.println("normalize() does NOT work...");
            System.out.println("Got: " + nz.toString());
        }


        //Extra: normalized zero-range exception check
        Dataset dz = new Dataset(2);
        dz.addRecord(new Record(new Vector(new double[]{1.0, 5.0}), 7.0));
        dz.addRecord(new Record(new Vector(new double[]{2.0, 5.0}), 7.0));
        dz.addRecord(new Record(new Vector(new double[]{3.0, 5.0}), 7.0));

        NormalizedDataset nz2 = dz.normalize();
        
        if (dz.toString().equals(nz2.toString())) {
            System.out.println("Zero-range exception check works!");
        } else {
            errors += 1;
            System.out.println("Zero-range exception check does NOT work.");
        }


        // Errors count
        System.out.println("Errors found: " + errors);
        if (errors == 0) {System.out.println("Everything works!");}

    }
}
