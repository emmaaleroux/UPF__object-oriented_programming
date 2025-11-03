package Lab2;

//import static Lab2.Dataset.round5;

public class NormalizedDataset extends Dataset {

    // ATTRIBUTES
    private Vector minI;
    private Vector maxI;
    private double minO;
    private double maxO;

    // CONSTRUCTOR
    public NormalizedDataset(Dataset d, Vector minI, Vector maxI, double minO, double maxO) {
        super(d.getDim());
        this.minI = minI;
        this.maxI = maxI;
        this.minO = minO;
        this.maxO = maxO;
    }

    // GETTERS
    public Vector getMinIn(){
        return minI; 
    }

    public Vector getMaxIn(){ 
        return maxI; 
    }

    public double getMinOut(){ 
        return minO; 
    }

    public double getMaxOut(){
        return maxO; 
    }

    // METHODS
    
    public Record transform(Record r) {
        //rangeI = (maxI - minI)
        //denominator for inputs 
        Vector rangeI = maxI.subtract(minI);

        //check zero range in inputs
        double[] range = rangeI.getElems(); 

        for (int i = 0; i < range.length; i++) {
            if (range[i] == 0.0) {
                System.err.println("Input range = 0 at " + i + ", can't normalize.");
                return r; // leave unchanged
            }
        }
        
        //rangeO = (maxO - minO)
        //check zero range in output
        double rangeO = maxO - minO;
        if (rangeO == 0.0) {
            System.err.println("Output range = 0, can't normalize.");
            return r; // leave unchanged
        }

        // x' = (x - minI) / (maxI - minI)
        Vector normalizedInput = r.getInput().subtract(minI).divide(rangeI);

        // round input to 5 decimals
        double[] rounded_i = normalizedInput.getElems().clone();
        for (int i = 0; i < rounded_i.length; i++) {
            rounded_i[i] = round5(rounded_i[i]);
        }
        normalizedInput = new Vector(rounded_i);
        

        // y' = (y - minO) / (maxO - minO)
        double normalizedOutput = (r.getOutput() - minO) / (maxO - minO);

        //round output to 5 decimals
        normalizedOutput = round5(normalizedOutput);


        return new Record(normalizedInput, normalizedOutput);
    }

}
