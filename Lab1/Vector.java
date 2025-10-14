package Lab1;
public class Vector {
    
    // ATTRIBUTES
    private double[] elems;

    // CONSTRUCTOR
    public Vector(double[] e) {
        elems = e;
    }

    // SECOND CONSTRUCTOR
    public Vector(int dim, double val) {
        elems = new double[dim];
        for (int i = 0; i < dim; i++) {
            elems[i] = val; 
        }
    }

    // GETTERS
    public int getDim() {
        return elems.length;
    }

    public double[] getElems() {
        // We also created a getter for the elements array to be able to access them
        return elems.clone();
    }

    // METHODS

    public Vector add(Vector v) {
        // We add an exception in order to avoid dimension issues
        if (v.getDim() != elems.length) {
            throw new IllegalArgumentException("Vector dimensions must match");
        }
        double[] e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] + v.elems[i];
        }
        return new Vector(e);
    }

    public Vector subtract(Vector v) {
        // Exception
        if (v.getDim() != elems.length) {
            throw new IllegalArgumentException("Vector dimensions must match");
        }
        double[] e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] - v.elems[i];
        }
        return new Vector(e);
    }

    public Vector multiply(Vector v) {
        // Exception
        if (v.getDim() != elems.length) {
            throw new IllegalArgumentException("Vector dimensions must match");
        }
        double[] e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] * v.elems[i];
        }
        return new Vector(e);
    }

    public Vector divide(Vector v) {
        // Exception
        if (v.getDim() != elems.length) {
            throw new IllegalArgumentException("Vector dimensions must match");
        }
        double[] e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            // Another exception
            if (v.elems[i] == 0) {
                throw new IllegalArgumentException("Cannot divide by 0");
            }
            e[i] = elems[i] / v.elems[i];
        }
        return new Vector(e);
    }

    public Vector multiply(double scalar) {
        double[] e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] * scalar;
        }
        return new Vector(e);
    }

    public Vector divide(double scalar) {
        // Exception, scalar == 0
        if (scalar == 0) {
            throw new IllegalArgumentException("Cannot divide by 0");
        }
        double[] e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] / scalar;
        }
        return new Vector(e);
    }

    public Vector sqrt() {
        double[] e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = Math.sqrt(elems[i]);
        }
        return new Vector(e);
    }

    public double dotProduct(Vector v) {
        // Exception
        if (v.getDim() != elems.length) {
            throw new IllegalArgumentException("Vector dimensions must match");
        }
        double dot = 0;
        for (int i = 0; i < elems.length; i++) {
            dot += elems[i] * v.elems[i];
        }
        return dot;
    }

    public double norm() {
        double n = 0;
        for (int i = 0; i < elems.length; i++) {
            n += elems[i] * elems[i];
        }
        n = Math.sqrt(n);
        return n;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < elems.length; i++) {
            if (i > 0) {
                s+= ", ";
            }
            s += elems[i];
        }
        s += "]";
        return s;
    }
}
