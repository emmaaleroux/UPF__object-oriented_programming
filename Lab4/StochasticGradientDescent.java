package Lab4;

import java.util.Random;
import javax.xml.crypto.Data;

public class StochasticGradientDescent extends Algorithm{
    private final int batchSize;
    private final int iterations;
    private final Random random;

    public StochasticGradientDescent(double lr, int bs, int i, Random r) {
        super(lr);
        batchSize = bs;
        iterations = i;
        random = (r == null) ? new Random() : r;
    }

    public StochasticGradientDescent(double learningRate, int batchSize, int iterations) {
        this(learningRate, batchSize, iterations, new Random());
    }

    public Vector stochasticGradient(Dataset ds, Model m){
        int n = ds.size();
        Vector g = new Vector(ds.getDim() + 1, 0.0);
        int[] indices = random.ints(0, n).distinct().limit(batchSize).toArray();
        for (int idx : indices) {
            Record r = ds.getData()[idx];

            Vector aug = r.getInput().augment();
            double pred = m.predict(aug);
            double error = pred - r.getOutput();

            g = g.add(aug.multiply(error));
        }
        return g.divide(batchSize); // We return the average
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