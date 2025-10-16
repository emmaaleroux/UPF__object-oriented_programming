package Lab1;

public class TestRecord {
    // MAIN
    public static void main(String[] args) {

        int errors = 0;

        /// Create and print Vector instances
        
        Vector v1 = new Vector(new double[] {1, 2, 3}); // First constructor
        Vector v2 = new Vector(3, 1); // Second constructor
        // toString() for printing
        System.out.println("v1: " + v1.toString());
        System.out.println("v2: " + v2.toString());
        if (v1.toString().equals("[1.0, 2.0, 3.0]") && v2.toString().equals("[1.0, 1.0, 1.0]")) {
            System.out.println("toString() works!");
        } else {errors +=1; System.out.println("toString() does not work...");}

        /// Test the rest of Vector methods
        
        // getDim()
        if (v1.getDim() == 3 && v2.getDim() == 3) {
            System.out.println("getDim() works!");
        } else {errors +=1; System.out.println("getDim() does not work...");}

        // add()
        if (v1.add(v2).toString().equals("[2.0, 3.0, 4.0]")) {
            System.out.println("add() works!");
        } else {errors +=1; System.out.println("add() does not work...");}

        // subtract()
        if (v1.subtract(v2).toString().equals("[0.0, 1.0, 2.0]")) {
            System.out.println("subtract() works!");
        } else {errors +=1; System.out.println("subtract() does not work...");}

        // multiply() by a vector
        if (v1.multiply(v2).toString().equals("[1.0, 2.0, 3.0]")) {
            System.out.println("multiply() works for a vector!");
        } else {errors +=1; System.out.println("multiply() for a vector does not work...");}

        // divide() by a vector
        if (v1.divide(v2).toString().equals("[1.0, 2.0, 3.0]")) {
            System.out.println("divide() works for a vector!");
        } else {errors +=1; System.out.println("divide() for a vector does not work...");}

        // multiply() by a scalar
        if (v1.multiply(2).toString().equals("[2.0, 4.0, 6.0]")) {
            System.out.println("multiply() works for a scalar!");
        } else {errors +=1; System.out.println("multiply() for a scalar does not work...");}

        // divide() by a scalar
        if (v1.divide(2).toString().equals("[0.5, 1.0, 1.5]")) {
            System.out.println("divide() works for a scalar!");
        } else {errors +=1; System.out.println("divide() for a scalar does not work...");}

        // sqrt()
        Vector v3 = v1.sqrt();
        System.out.println("v3: " + v3.toString());
        if (Math.sqrt(v1.getElems()[0]) == v3.getElems()[0] && Math.sqrt(v1.getElems()[1]) == v3.getElems()[1] && Math.sqrt(v1.getElems()[2]) == v3.getElems()[2]) {
            System.out.println("sqrt() works!");
        } else {errors +=1; System.out.println("sqrt() does not work...");}

        // dotProduct()
        if (v1.dotProduct(v2) == v2.dotProduct(v1) && v1.dotProduct(v2) == 6) {
            System.out.println("dotProduct() works!");
        } else {errors +=1; System.out.println("dotProduct() does not work...");}

        // norm()
        if (v1.norm() == Math.sqrt(14) && v2.norm() == Math.sqrt(3)) {
            System.out.println("norm() works!");
        } else {errors +=1; System.out.println("norm() does not work...");}

        
        /// Create and print Vector instances
        
        Record r1 = new Record(v1, 1);
        Record r2 = new Record(new Vector(new double[] {5, 10, 15}), 2.5);
        // toString()
        System.out.println("r1: " + r1.toString());
        System.out.println("r2: " + r2.toString());

        /// Test Record methods
        
        // getInput()
        if (r1.getInput().equals(v1)) {
            System.out.println("getInput() works!");
        } else {errors +=1; System.out.println("getInput() does not work...");}

        // getOutput()
        if (r1.getOutput() == 1.0 && r2.getOutput() == 2.5) {
            System.out.println("getOutput() works!");
        } else {errors +=1; System.out.println("getOutput() does not work...");}

        // Errors count
        System.out.println("Errors found: " + errors);
        if (errors == 0) {System.out.println("Everything works!");}
    }
}