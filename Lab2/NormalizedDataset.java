package Lab2;

public class NormalizedDataset extends Dataset {

    // ATTRIBUTES
    private Vector minI;
    private Vector maxI;
    private double minO;
    private double maxO;

    // CONSTRUCTOR
    public NormalizedDataset(Dataset d, Vector minInput, Vector maxInput, double minOutput, double maxOutput) {
        super(d.getDim());
        minI = minInput;
        maxI = maxInput;
        minO = minOutput;
        maxO = maxOutput;
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
        // rangeI = (maxI - minI)
        Vector rangeI = maxI.subtract(minI);

        //check zero range in inputs
        double[] range = rangeI.getElems(); 
        for (int i = 0; i < range.length; i++) {
            if (range[i] == 0.0) {
                System.err.println("Input range = 0 at " + i + ", can't normalize.");
                return r;
            }
        }
        
        // rangeO = (maxO - minO)
        double rangeO = maxO - minO;
        //check zero range in output
        if (rangeO == 0.0) {
            System.err.println("Output range = 0, can't normalize.");
            return r;
        }

        // x' = (x - minI) / rangeI
        Vector normalizedInput = r.getInput().subtract(minI).divide(rangeI);
        // We round the input to 5 decimals
        double[] roundedNorm = normalizedInput.getElems();
        for (int i = 0; i < roundedNorm.length; i++) {
            roundedNorm[i] = round5(roundedNorm[i]);
        }
        normalizedInput = new Vector(roundedNorm);
        

        // y' = (y - minO) / rangeO
        double normalizedOutput = (r.getOutput() - minO) / (maxO - minO);
        // We round the output to 5 decimals
        normalizedOutput = round5(normalizedOutput);

        return new Record(normalizedInput, normalizedOutput);
    }

}
