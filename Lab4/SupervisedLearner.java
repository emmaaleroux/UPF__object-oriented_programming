package Lab4;

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
        // create a dummy record so that dataset.transform can be used
        Record dummy = new Record(v, 0.0); // create a dummy using given input
        Record transformed = dataset.transform(dummy); 
        Vector vAug = transformed.getInput().augment(); // once transformed we augment it
        double predInternal = model.predict(vAug); // obtain prediction
        // convert prediction back to original output space
        double predOriginal = dataset.output(predInternal);
        return predOriginal;
    }


    public String toString() {
        if (model == null) {
            return "Untrained model, call solve() first";
        }
        return model.toString();
    }
   
}