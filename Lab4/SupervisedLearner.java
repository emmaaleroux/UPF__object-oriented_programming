package Lab4;
// javac -cp "Lab3\ejml-all-0.44.0.jar;." Lab3\*.java
// java -cp "Lab3\ejml-all-0.44.0.jar;." Lab3.TestLearner
// import org.ejml.simple.SimpleMatrix; 

public class SupervisedLearner {

    // ATRIBUTTES
    private Algorithm algorithm; 
    private Dataset dataset;
    private Model model;

    // CONSTRUCTOR
    public SupervisedLearner(Algorithm a, Dataset d){
        algorithm = a;
        dataset = d;
        model = null;
    }

    // GETTERS
    public Algorithm getAlgorithm(){
        return algorithm; 
    }

    public Dataset getDataset(){ 
        return dataset; 
    }

    public Model getModel(){ 
        return model; 
    }


    // OTHER METHODS

    public void solve(){
        this.model = algorithm.solve(dataset);
    }

    public double predict(Vector v) {
        if (model == null) {
            System.out.println("Model not learned, call solve() first");
        }
        Vector vAug = v.augment(); // create x̄ from x
        return model.predict(vAug); // f(x) = θ · x̄
    }

    public String toString() {
        if (model == null) {
            return "Untrained model, call solve() first";
        }
        return model.toString();
    }

    
    /*
    // OPTIONAL assignment: Moore-Penrose inverse
    public Model MPInverse() {

        int n = dataset.getData().size();
        int dim = dataset.getDim() + 1;

        if (n == 0) {
            System.out.println("Empty dataset.");
            return new Model(dim);
        }

        // Build X
        double[][] Xdata = new double[n][dim];
        double[][] ydata = new double[n][1];

        for (int i = 0; i < n; i++) {
            Record r = dataset.getData().get(i);
            double[] aug = r.getInput().augment().getElems();
            if (aug.length != dim) {
                throw new IllegalStateException("Augmented input length mismatch (expected " + dim + ", got " + aug.length + ")");
            }
            System.arraycopy(aug, 0, Xdata[i], 0, dim);
            ydata[i][0] = r.getOutput();
        }

        Matrix X = new SimpleMatrix(Xdata);

        // Build y
        double[][] ydata = new double[n][1];
        for (int i = 0; i < n; i++) {
            ydata[i][0] = dataset.getData().get(i).getOutput();
        }
        SimpleMatrix X = new SimpleMatrix(Xdata); // n x dim
        SimpleMatrix y = new SimpleMatrix(ydata); // n x 1

        // θ* = X^\+ * y
        SimpleMatrix theta = X.pseudoInverse().mult(y);

        // Convert to Model
        double[] params = new double[dim];
        for (int i = 0; i < dim; i++){
            params[i] = theta.get(i, 0);
        }
        
        return new Model(new Vector(params));
    }
    */
}