package Lab3;

public class Model {

    // ATTRIBUTE
    private Vector params;

    // CONSTRUCTOR
    public Model(int dim) {
        // Thhe parameter vector takes 0.0 as default value
        params = new Vector(dim, 0.0);
    }

    // GETTER
    public Vector getParams() {
        return params;
    }

    // OTHER METHODS

    public double predict(Vector v) {
        return params.dotProduct(v);
    }

    public void update(Vector v, double rate) {
        params = params.subtract(v.multiply(rate));
    }

}
