package Lab2;

import java.util.*; // We import util to use List and ArrayList

public class Dataset {
    // DONT USE a set, as it removes duplicates

    // ATTRIBUTES

    protected int dim;
    protected List<Record> data = new ArrayList<>();

    // CONSTRUCTOR

    public Dataset(int d) {
        dim = d;
    }

    // GETTERS

    public int getDim() {
        return dim;
    }

    public List<Record> getData() {
        return data;
    }

    // METHODS

    public void addRecord(Record r) {
        data.add(r);
    }

    public Vector meanInput(){

    }

    public Vector stdInput() {

    }

    public double meanOutput() {

    }

    public double stdOutput() {

    }

    public StandardizedDataset standardize() {

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

}
