package Lab3;
// javac -cp "Lab3\ejml-all-0.44.0.jar;." Lab3\*.java
// java -cp "Lab3\ejml-all-0.44.0.jar;." Lab3.TestLearner
// import org.ejml.*; ???

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
    // Optional assignment: Moore-Penrose inverse
    public Model MPInverse() {

        int n = dataset.getData().size();
        int dim = dataset.getDim() + 1;

        // Build X
        double[][] Xdata = new double[n][dim];
        for (int i = 0; i < n; i++) {
            Record r = dataset.getData().get(i);
            double[] aug = r.getInput().augment().getElems();
            System.arraycopy(aug, 0, Xdata[i], 0, dim);
        }
        Matrix X = new SimpleMatrix(Xdata);

        // Build y
        double[][] ydata = new double[n][1];
        for (int i = 0; i < n; i++) {
            ydata[i][0] = dataset.getData().get(i).getOutput();
        }
        Matrix y = new SimpleMatrix(ydata);

        // θ* = X^\+ * y
        SimpleMatrix theta = X.pseudoInverse().mult(y);

        // Convert to Model
        double[] params = new double[dim];
        for (int i = 0; i < dim; i++)
            params[i] = theta.get(i, 0);

        Model m = new Model(dim);
        m.update(new Vector(params).multiply(-1), -1); // your forced replace
        return m;
    }
    */
}