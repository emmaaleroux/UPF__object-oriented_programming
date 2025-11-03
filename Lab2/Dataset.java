package Lab2;

import java.util.*; // We import util to use ArrayList

public class Dataset { 
    // ATTRIBUTES

    protected int dim;
    protected ArrayList<Record> data; //aggregation relation with Record

    // CONSTRUCTOR

    public Dataset(int d) {
        dim = d;
        data = new ArrayList<>(); //starts empty, it will eventually be filled with Records 
    }

    // GETTERS

    public int getDim() {
        return dim;
    }

    public List<Record> getData() {
        return data; //returns the actual list, not a copy
    }

    // METHODS

    // Helper method to round the values (5 decimals)

    protected static double round5(double val) { //made it protected instead of private to be able to use it in NormalizedDataset.java
        return Math.round(val * 100000.0) / 100000.0;
    }


    public void addRecord(Record r) {
        //Optional: before adding we check that the record and input are null to avoid exceptions
        if (r == null || r.getInput() == null) {
            System.err.println("Can't add: record or input is null.");
            return;
        }
        //Optional: before adding we check that the record's input vector length matches the dataset's dim
        if (r.getInput().getDim() != dim) {
            System.err.println("Can't add: input dim mismatch (" + r.getInput().getDim() + " != " + dim + ").");
            return;
        }

        data.add(r);
    }

    public Vector meanInput(){ //dependency relation with Vector  
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

    public Vector stdInput() { //dependency relation with Vector
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

    
    public StandardizedDataset standardize() {
        // First, we compute the attributes
        Vector mi = meanInput();
        Vector si = stdInput();
        double mo = meanOutput();
        double so = stdOutput();

        StandardizedDataset standard = new StandardizedDataset(this, mi, si, mo, so);

        // We use StandardizedDataset's method transform() to standardize each record
        for (Record r : data) {
            Record standardizedRecord = standard.transform(r);
            standard.addRecord(standardizedRecord);
        }

        return standard;
    }
    
    
    public String toString() {
        String s = "{";
        for (int i = 0; i < data.size(); i++) {
            if (i > 0) { s+= ", "; }
            s += data.get(i).toString();
        }
        s += "}";
        return s;
    }

    //OPTIONAL: Normalized Datasets
    
    //get minI to normalize
    public Vector minInput() {
        if (data.isEmpty()) { //in case there is no data return a 0 vector of length dim
            return new Vector(dim, 0.0);
        }

        double[] m = data.get(0).getInput().getElems().clone(); //copy of first's input element
        
        //get the minimum: if x[i] is SMALLER than current m[i] then update m[i]
        for (Record r : data) {
            double[] x = r.getInput().getElems();
            for (int i = 0; i < dim; i++){
                if (x[i] < m[i]){
                    m[i] = x[i];
                }
            }
        }

        return new Vector(m);
    }

    //get maxI to normalize
    public Vector maxInput() {
        if (data.isEmpty()) { //in case there is no data return a 0 vector of length dim
            return new Vector(dim, 0.0);
        }

        double[] M = data.get(0).getInput().getElems().clone();

        for (Record r : data) {
            double[] x = r.getInput().getElems();
            for (int i = 0; i < dim; i++) {
                if (x[i] > M[i]) { //same as minI but now if x[i] is BIGGER than current m[i] then update m[i]
                    M[i] = x[i];
                }
            }
        }
                
        return new Vector(M);
    }

    //get minO to normalize
    public double minOutput() { //in case there is no data return a 0 vector of length dim
        if (data.isEmpty()) return 0.0;

        double m = data.get(0).getOutput();

        for (Record r : data) { //scan all records 
            if (r.getOutput() < m) { //update when we find a SMALLER output
                m = r.getOutput();
            }
        }

        return m;
    }

    //get maxO to normalize
    public double maxOutput() { //in case there is no data return a 0 vector of length dim
        if (data.isEmpty()) return 0.0;

        double M = data.get(0).getOutput();

        for (Record r : data) {
            if (r.getOutput() > M) { //same as minO but now update if we find BIGGER output
                M = r.getOutput();
            }
        }

        return M;
    }

    //normalize method
    public NormalizedDataset normalize() {
        Vector minI = minInput();
        Vector maxI = maxInput();
        double minO = minOutput();
        double maxO = maxOutput();

        //Build normalized dataset
        //counstructor calls super(d.getDim()) so when passing this we are actually passing dim 
        NormalizedDataset norm_d = new NormalizedDataset(this, minI, maxI, minO, maxO); 
        //System.out.println("minI=" + minI + ", maxI=" + maxI + ", minO=" + minO + ", maxO=" + maxO); 

        for (Record r : data) {
            norm_d.addRecord(norm_d.transform(r)); //transform each record, it computes x' and y' and then stores transformed record
        }

        return norm_d; //return normalized dataset
    }


}
