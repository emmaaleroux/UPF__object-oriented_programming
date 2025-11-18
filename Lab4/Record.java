package Lab4;
public class Record {
   
    // ATTRIBUTES
    private double output;
    private Vector input;

    // CONSTRUCTOR
    public Record(Vector i, double o) {
        input = i;
        output = o;
    }

    // GETTERS
    public Vector getInput() {
        return input;
    }

    public double getOutput() {
        return output;
    }

    // METHODS
    public String toString() {
        return input.toString() + " -> " + output; 
    }
}