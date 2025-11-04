package Lab3;
public class Record {
   
    // ATTRIBUTES
    private double output;
    private Vector input; //this is aggregation

    // CONSTRUCTOR
    public Record(Vector i, double o) {
        input = i;
        output = o;
    }

    // GETTER
    public Vector getInput() {
        return input;
    }

    // GETTER 2
    public double getOutput() {
        return output;
    }

    // METHODS
    public String toString() {
        return input.toString() + " -> " + output; 
    }
}