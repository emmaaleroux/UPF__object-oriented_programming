package Lab4;

import java.util.ArrayList;
import java.util.List;

public class RawDataset extends Dataset{

    //CONSTRUCTOR
    public RawDataset(int d) {
        super(d);
    }

    //METHODS

    //Methods that were in Dataset

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

    public abstract Record transform(Record r){
        return r;
    }  
    

    public abstract double output(double d){
        return d;
    }
    
}
