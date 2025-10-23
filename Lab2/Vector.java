package Lab2;

// Optional assignment: more compact methods of Vector
import java.util.function.DoubleUnaryOperator;
import java.util.function.DoubleBinaryOperator;

public class Vector {
    
    // ATTRIBUTE
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


    // Optional assignment: more compact methods of Vector
    // We need two helper (private) methods to do unary (1 double --> 1 double) binary (2 double --> 1 double) operations.

    private Vector unary(DoubleUnaryOperator op) {
        // unary takes a unary operator op as a parameter and returns a Vector
        // the input is of the form (variable -> result)
        // ex: a -> Math.sqrt(a) (lambda calculus)

        double[] e = new double[elems.length];
        // for every element of the vector, we apply op
        for (int i = 0; i < elems.length; i++) {
            e[i] = op.applyAsDouble(elems[i]);
        }
        return new Vector(e);
    }

    private Vector binary(Vector v, DoubleBinaryOperator op) {
        // binary takes a binary operator op (like +) as a parameter and returns a Vector
        // the input is of the form (vector, (a,b) --> result)
        // ex: v, (a, b) -> (a + b) (lambda calculus)

        // We add an exception in order to avoid dimension issues
        if (v.getDim() != elems.length) {
            throw new IllegalArgumentException("Vector dimensions must match");
        }
        double[] e = new double[elems.length];
        // for every pair of elements, we apply op
        for (int i = 0; i < elems.length; i++) {
            e[i] = op.applyAsDouble(elems[i], v.elems[i]);
        }
        return new Vector(e);
    }


    // METHODS

    public Vector add(Vector v) {
        return binary(v, (a, b) -> a + b);
    }

    public Vector subtract(Vector v) {
        return binary(v, (a, b) -> a - b);
    }

    public Vector multiply(Vector v) {
        return binary(v, (a, b) -> a * b);
    }
 
    public Vector divide(Vector v) {
        return binary(v, (a, b) -> {
            // We check for exceptions to avoid errors
            if (b == 0) {
                throw new IllegalArgumentException("Cannot divide by 0");
            }
            return a / b;
        });
    }

    public Vector multiply(double scalar) {
        return unary(a -> a * scalar);
    }

    public Vector divide(double scalar) {
        // Exception, scalar == 0
        if (scalar == 0) {
            throw new IllegalArgumentException("Cannot divide by 0");
        }
        return unary(a -> a / scalar);
    }

    public Vector sqrt() {
        return unary(a -> Math.sqrt(a));
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
            if (i > 0) { s+= ", "; }
            s += elems[i];
        }
        s += "]";
        return s;
    }
}