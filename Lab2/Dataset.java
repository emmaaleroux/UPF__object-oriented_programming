package Lab2;

import java.util.*; // We import util to use List and ArrayList

public class Dataset {

    // ATTRIBUTES

    protected int dim;
    protected List<Record> data;

    // CONSTRUCTOR

    public Dataset(int d) {
        dim = d;
        data = new ArrayList<>();
    }

    // GETTERS

    public int getDim() {
        return dim;
    }

    public List<Record> getData() {
        return data;
    }

    // METHODS

    // Helper method to round the values (5 decimals)

    private static double round5(double val) {
        return Math.round(val * 100000.0) / 100000.0;
    }

    public void addRecord(Record r) {
        data.add(r);
    }

    public Vector meanInput(){
        // First, we check that the Dataset is not empty.
        if (data.isEmpty()) {
            return new Vector(dim, 0.0);
        }
        Vector mean = new Vector(dim, 0.0);
        for (Record r : data) {
            mean = mean.add(r.getInput());
        }
        mean = mean.divide(data.size());
        // Now that we have the mean, we round it (in case it has more than 5 decimals)
        double[] roundedMean = new double[dim];
        for (int i = 0; i < dim; i++) {
            roundedMean[i] = round5(mean.getElems()[i]);
        }
        return new Vector(roundedMean);
    }

    public Vector stdInput() {
        if (data.isEmpty()) {
            return new Vector(dim, 0.0);
        }

        Vector mean = meanInput();
        Vector variance = new Vector(dim, 0.0);

        for (Record r : data) {
            Vector diff = r.getInput().subtract(mean);     // element-wise (x - mean)
            variance = variance.add(diff.multiply(diff));  // element-wise (x - mean)^2 summed
        }

        variance = variance.divide(data.size()); // mean of squared differences
        variance = variance.sqrt(); // element-wise square root to get std

        // We rounded the values to make the test simpler
        double[] roundedStd = new double[dim];
        // Round each element to 5 decimals before returning
        for (int i = 0; i < dim; i++) {
            roundedStd[i] = round5(variance.getElems()[i]); 
        }
        return new Vector(roundedStd);
    }
    
    public double meanOutput() {
        if (data.isEmpty()) {
            return 0.0;
        }
        double mean = 0;
        for (Record r : data) {
            mean += r.getOutput();
        }
        mean /= data.size();
        // We return the rounded mean
        return round5(mean);
    }
    
    public double stdOutput() {
        if (data.isEmpty()) {
            return 0.0;
        }
        double mean = meanOutput();
        double variance = 0.0;

        for (Record r : data) {
            double diff = r.getOutput() - mean;
            variance += diff * diff;
        }
        variance /= data.size();
        double std = Math.sqrt(variance);
        return round5(std); 
    }

    /*
    public StandardizedDataset standardize() {

    }
    */
    
    public String toString() {
        String s = "{";
        for (int i = 0; i < data.size(); i++) {
            if (i > 0) { s+= ", "; }
            s += data.get(i).toString();
        }
        s += "}";
        return s;
    }

}
