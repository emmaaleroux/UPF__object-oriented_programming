package Lab4;

public class Model {

    // ATTRIBUTE
    private Vector params;

    // CONSTRUCTOR
    public Model(int dim) {
        // Thhe parameter vector takes 0.0 as default value
        params = new Vector(dim, 0.0);
    }

    /* OPTIONAL: MOORE-PENROSE INVERSE
    public Model(Vector params) {
        this.params = params;
    */

    // GETTER
    public Vector getParams() {
        return params;
    }

    // OTHER METHODS

    public double predict(Vector v) {
        // We assume x is already augmented (x̄)
        // f(x) = θ · x̄
        return params.dotProduct(v);
    }

    public void update(Vector v, double rate) {
        params = params.subtract(v.multiply(rate));
    }

    /* OPTIONAL: MOORE-PENROSE INVERSE
    public String toString() {
        return params.toString();
    }
    */

}