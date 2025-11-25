package Lab4;

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

    // METHODS
    @Override
    public Record transform(Record r){ 
        // We check that we are not dividing by zero to avoid errors
        for (double s : si.getElems())
            if (s == 0.0) {
                throw new IllegalStateException("Input std = 0, can't standardize.");
            }
        if (so == 0.0) {
            throw new IllegalStateException("Output std = 0, can't standardize.");
        }
        // compute a transformed input x̂ = (x − μ_in)/σ_in and reverse v · σ_out + μ_out
        // standardize input
        Vector x = r.getInput(); //get input vector x
        Vector xStd = x.subtract(mi).divide(si);  

        double[] elems = xStd.getElems();
        for (int i = 0; i < elems.length; i++)
            elems[i] = round5(elems[i]);

        xStd = new Vector(elems);

        // Standardize output
        double yStd = (r.getOutput() - mo) / so; 
        yStd = round5(yStd);

        return new Record(xStd, yStd); //return standarized record
    }


    @Override
    public double output(double d){
        return d * so + mo;
    }

}