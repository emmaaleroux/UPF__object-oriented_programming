package Lab4;

public class GradientDescent extends Algorithm {
    
    // ATTRIBUTES
    private final double stoppingCriterion; // tolerance for stopping (on parameter-change magnitude)

    //CONSTRUCTOR
    public GradientDescent(double lr, double sc) {
        super(lr);
        stoppingCriterion = sc;
    }

    //METHODS
    public Vector gradient(Dataset ds, Model m){
        int n = ds.getData().size(); // number n of elements in ds
        // We initialize empty gradient of the loss function
        Vector g = new Vector(ds.getDim() + 1, 0.0);
        if (n == 0) {
            System.out.println("Empty dataset.");
            return g;
        }
        // We go through each record
        for (Record r : ds.getData()) {
            // gradient = ( x̄ * (θ · x̄ - y) ) / n
            //          = ( x̄ * error ) / n
            Vector aug = r.getInput().augment(); // aug = x̄
            double pred = m.predict(aug); // pred = θ · x̄
            double error = pred - r.getOutput(); // error = pred - y
            g = g.add(aug.multiply(error)); // g += x̄ * error
        }
        return g.divide(n);
    }

    @Override
    public Model solve(Dataset ds){
        Model model = new Model(ds.getDim() + 1);

        Vector g = gradient(ds, model);
        // We use an iterator to check the gradient norm (should get smaller)
        // int i = 0; 
        while (g.norm() > stoppingCriterion) {
            // System.out.println("i = " + i + ": gradient norm:" + g.norm());
            model.update(g, learningRate);
            g = gradient(ds, model);
            // i++;
        }
        return model;
    }

}
