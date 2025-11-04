package Lab3;

public class Algorithm {
    
    // ATTRIBUTES
    private double learningRate;
    private double stoppingCriterion;

    // CONSTRUCTOR
    public Algorithm(double lr, double sc) {
        learningRate = lr;
        stoppingCriterion = sc;
    }

    // GETTERS
    public double getLearningRate() {
        return learningRate;
    }

    public double getStoppingCriterion() {
        return stoppingCriterion;
    }

    // OTHER METHODS

    public Vector gradient(Dataset ds, Model m) {
        int n = ds.getData().size(); // number n of elements in ds

        // We initialize empty gradient of the loss function
        Vector g = new Vector(ds.getDim() + 1, 0.0);

        if (n == 0) {
            System.out.println("Empty dataset.");
            return g;
        }
 

        // We go through each record
        for (Record r : ds.getData()) {
            // gradient = ( augmented * (prediction - value) ) / n
            //          = ( augmented * error ) / n
            Vector aug = r.getInput().augment();
            double pred = m.predict(aug);
            double error = pred - r.getOutput();
            g = g.add(aug.multiply(error));
        }

        return g.divide(n);
    }

    public Model solve(Dataset ds) {

        Model model = new Model(ds.getDim() + 1);

        Vector g = gradient(ds, model);
        while (g.norm() > stoppingCriterion) {
            model.update(g, learningRate);
            g = gradient(ds, model);
        }
         return model;
    }

}
