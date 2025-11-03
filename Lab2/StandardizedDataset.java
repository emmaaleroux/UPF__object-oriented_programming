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
        //in case std = 0, avoid dividing by 0 and getting an error
        for (double s : si.getElems())
            if (s == 0.0) {
                System.err.println("Input std = 0, can't standardize.");
                return r; //we leave it unmodified
            }
        if (so == 0.0) {
            System.err.println("Output std = 0, can't standardize.");
            return r; //we leave it unmodified
        }

        // We standardize the input (x: Vector) as x' = (x - mi)/si 
        Vector standardInput = r.getInput().subtract(mi).divide(si);

        // We standardize the output (y: double) as y' = (y - mo) / so
        double standardOutput = (r.getOutput() - mo) / so;
        
        return new Record(standardInput, standardOutput);
    }


}
