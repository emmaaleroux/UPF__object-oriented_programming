package Lab4;

import java.util.Random;

public class StochasticGradientDescent extends Algorithm{
    private final int batchSize;
    private final int iterations;
    private final Random random;

    public StochasticGradientDescent(double lr, int bs, int i) {
        super(lr);
        batchSize = bs;
        iterations = i;
        random = new Random();
    }

    public Vector stochasticGradient(Dataset ds, Model m){
        int n = ds.getData().size();
        int bs = batchSize;
        if (bs > n) {
            System.out.println("Batch is greater than dataset. Setting batch size = dataset size - 1.");
            bs = n - 1;
        }
        Vector g = new Vector(ds.getDim() + 1, 0.0);
        int[] indices = random.ints(0, n).distinct().limit(bs).toArray();
        for (int idx : indices) {
            Record r = ds.getData().get(idx);
            Vector aug = r.getInput().augment();
            double pred = m.predict(aug);
            double error = pred - r.getOutput();

            g = g.add(aug.multiply(error));
        }
        return g.divide(bs); // We return the average
    }

    @Override
    public Model solve(Dataset ds){
        Model model = new Model(ds.getDim() + 1);
        for (int i = 0; i < iterations; i++) {
            Vector sg = stochasticGradient(ds, model);
            model.update(sg, learningRate);
        }
        return model;
    }

}