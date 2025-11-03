package Lab2;

public class StandardizedDataset extends Dataset {

    // ATTRIBUTES
    private Vector mi;
    private Vector si;
    private double mo;
    private double so;

    // CONSTRUCTOR
    public StandardizedDataset(Dataset d, Vector mi, Vector si, double mo, double so) {
        super(d.getDim()); //call Dataset's contructor
        this.mi = mi;
        this.si = si;
        this.mo = mo;
        this.so = so;
    }

    // GETTERS
    public Vector getMeanIn() {
        return mi;
    }

    public Vector getStdIn() {
        return si;
    }

    public double getMeanOut() {
        return mo;
    }

    public double getStdOut() {
        return so;
    }

    // OTHER METHODS
    public Record transform(Record r) {
        // We check that we are not dividing by zero to avoid errors
        for (double s : si.getElems())
            if (s == 0.0) {
                System.err.println("Input std = 0, can't standardize.");
                return r; 
            }
        if (so == 0.0) {
            System.err.println("Output std = 0, can't standardize.");
            return r; 
        }

        // We standardize the input (x: Vector) as x' = (x - mi) / si 
        Vector standardInput = r.getInput().subtract(mi).divide(si);
        // We round the input to 5 decimals
        double[] roundedStandard = standardInput.getElems();
        for (int i = 0; i < roundedStandard.length; i++) {
            roundedStandard[i] = round5(roundedStandard[i]);
        }
        standardInput = new Vector(roundedStandard);

        // We standardize the output (y: double) as y' = (y - mo) / so
        double standardOutput = (r.getOutput() - mo) / so;
        //We round the output to 5 decimals
        standardOutput = round5(standardOutput);
        
        return new Record(standardInput, standardOutput);
    }


}
