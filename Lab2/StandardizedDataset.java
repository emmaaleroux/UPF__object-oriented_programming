package Lab2;

public class StandardizedDataset extends Dataset {

    // ATTRIBUTES
    private Vector mi;
    private Vector si;
    private double mo;
    private double so;

    // CONSTRUCTOR
    public StandardizedDataset(Dataset d, Vector mi, Vector si, double mo, double so) {
        super(d.getDim());
        this.mi = mi;
        this.si = si;
        this.mo = mo;
        this.so = so;
    }
    /*
    public Record transform(Record r) {
        // return new Record(r2)
    }
    */

}
