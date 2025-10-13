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

    // GETTER
    public int getDim() {
        return elems.length;
    }

    // METHODS

    public Vector add(Vector v) {
        e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] + v.elems[i];
        }
        return new Vector(e);
    }

    public Vector subtract(Vector v) {
        e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] - v.elems[i];
        }
        return Vector(e);
    }

    public Vector multiply(Vector v) {
        e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] * v.elems[i];
        }
        return Vector(e);
    }

    public Vector divide(Vector v) {
        e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] / v.elems[i];
        }
        return Vector(e);
    }

    public Vector multiply(double scalar) {
        e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] * scalar;
        }
        return Vector(e);
    }

    public Vector divide(double scalar) {
        e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = elems[i] / scalar;
        }
        return Vector(e);
    }

    public Vector sqrt() {
        e = new double[elems.length];
        for (int i = 0; i < elems.length; i++) {
            e[i] = Math.sqrt(elems[i]);
        }
        return Vector(e);
    }

    public double dotProduct(Vector v) {
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
            s.append(elems[i]); // ???
            if (i != elems.length - 1) {s += ", ";}
        }
        s += "]";
        return s;
    }

}
