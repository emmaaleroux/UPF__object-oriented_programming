package Lab3;
// Optional assignment: we import EJML
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.linsol.svd.SolvePseudoInverseSvd_DDRM;

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

    // Optional assignment: Moore-Penrose inverse
    public Model MPInverse() {
        // dimension
        int n = dataset.getData().size();
        int dim = dataset.getDim() + 1;

        // build X matrix
        DMatrixRMaj X = new DMatrixRMaj(n, dim);

        for (int i = 0; i < n; i++) {
            Record r = dataset.getData().get(i);
            double[] aug = r.getInput().augment().getElems();
            for (int j = 0; j < dim; j++) {
                X.set(i, j, aug[j]);
            }
        }
        // build y vector
        DMatrixRMaj y = new DMatrixRMaj(n, 1);
        for (int i = 0; i < n; i++) {
            y.set(i, 0, dataset.getData().get(i).getOutput());
        }

        // compute pseudoinverse θ* = X† y
        DMatrixRMaj theta = new DMatrixRMaj(dim, 1); // result
        SolvePseudoInverseSvd_DDRM pinv = new SolvePseudoInverseSvd_DDRM();
        pinv.setA(X);       // compute pseudoinverse of X
        pinv.solve(y, theta);

        // return model
        double[] params = new double[dim];
        for (int i = 0; i < dim; i++) {
            params[i] = theta.get(i, 0);
        }

        Model m = new Model(dim);
        m.update(new Vector(params).multiply(-1), -1);    // force replace

        return m;
    }
    
}