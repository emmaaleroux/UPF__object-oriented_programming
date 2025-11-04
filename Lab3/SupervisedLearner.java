package Lab3;

public class SupervisedLearner {
    // Composition with Model; aggregation with Dataset and Algorithm
    // Use/Dependency with vector

    //ATRIBUTTES
    private Algorithm algorithm; 
    private Dataset dataset;
    private Model model;

    //CONSTRUCTOR
    public SupervisedLearner(Algorithm a, Dataset d){
        algorithm = a;
        dataset = d;
        model = null;
    }

    //GETTER 
    public Algorithm getAlgorithm(){
        return algorithm; 
    }

    public Dataset getDataset(){ 
        return dataset; 
    }

    public Model getModel(){ 
        return model; 
    }


    //METHODS
    public void solve(){
        this.model = algorithm.solve(dataset);
    }

    public double predict(Vector v) {
        if (model == null) {
            System.out.println("Model not learned, call solve() first");
        }
        Vector vAug = v.augment(); //create x̄ from x
        return model.predict(vAug); //f(x) = θ · x̄
    }

    public String toString() {
        if (model == null) {
            return "Untrained model, call solve() first";
        }
        return model.toString();
    }
    
    
}
