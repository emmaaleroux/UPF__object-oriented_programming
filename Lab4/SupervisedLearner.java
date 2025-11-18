package Lab4;
// OPTIONAL ASSIGNMENT: 
// javac -cp "Lab3\ejml-all-0.44.0.jar;." Lab3\*.java
// java -cp "Lab3\ejml-all-0.44.0.jar;." Lab3.TestLearner
// import org.ejml.data.DMatrixRMaj;
// import org.ejml.dense.row.linsol.svd.SolvePseudoInverseSvd_DDRM;

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

    
    
    // OPTIONAL ASSIGNEMENT: Moore-Penrose inverse θ* = X† y

    public Model mp() {
        /*

        // Matrix dimensions
        int n = dataset.getData().size();
        int dim = dataset.getDim() + 1; // dim = d + 1

        // We try to avoid errors
        if (n == 0) {
            System.out.println("Empty dataset.");
            return new Model(dim);
        }

        // We build a matrix X of dimensions n x dim
        // and a Vector Y of dimensions n x 1
        DMatrixRMaj xData = new DMatrixRMaj(n, dim);
        DMatrixRMaj yData = new DMatrixRMaj(n, 1);

        int row = 0;
        for (Record r : dataset.getData()) {
            Vector aug = r.getInput().augment();
            for (int col = 0; col < dim; col++) {
                X.set(row, col, aug.get(col));
            }
            y.set(row, 0, r.getOutput());
            row++;
        }

        // We compute the pseudoinverse X† using SVD
        SolvePseudoInverseSvd_DDRM pinv = new SolvePseudoInverseSvd_DDRM();
        DMatrixRMaj theta = new DMatrixRMaj(dim, 1);

        pinv.setA(X);
        pinv.solve(y, theta);

        // Convert theta matrix to Model params
        double[] params = new double[dim];
        for (int i = 0; i < dim; i++) {
            params[i] = theta.get(i, 0);
        }

        return new Model(new Vector(params));
        */
        return null;
    }
   
}