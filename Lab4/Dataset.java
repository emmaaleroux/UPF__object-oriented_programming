package Lab4;

import java.util.*; // We import util to use ArrayList

public abstract class Dataset { 

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
        return data;
    }

    // METHODS

    // Helper method to round the values (5 decimals)
    protected static double round5(double val) { //made it protected instead of private to be able to use it in NormalizedDataset.java
        return Math.round(val * 100000.0) / 100000.0;
    }


    public void addRecord(Record r) {
        // We check if the record and input are null to avoid exceptions
        if (r == null || r.getInput() == null) {
            throw new IllegalArgumentException("Cannot add record: record or input is null.");
        }
        // We check that the record's input vector length matches the dataset's dim
        if (r.getInput().getDim() != dim) {
            throw new IllegalArgumentException("Cannot add record: input dimension mismatch (" + r.getInput().getDim() + " != " + dim + ").");
        }

        data.add(r);
    }

    public abstract Record transform(Record r);  
    
    public abstract double output(double d);
    
    
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