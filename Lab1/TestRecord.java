package Lab1;

public class TestRecord {
    
    // ATTRIBUTES ???

    // MAIN
    public static void main(String[] args) {
        /// Create instances
        Vector v1 = new Vector(new double[] {1, 2, 3}); // First constructor
        Vector v2 = new Vector(3, 1); // Second constructor

        /// Test methods
        
        // getDim()
        if (v1.getDim() == 3 && v2.getDim() == 3) {
            System.out.println("getDim() works!");
        }
        // add()
        if (v1.add(v2).toString().equals("[2.0, 3.0, 4.0]")) {
            System.out.println("add() works!");
        };
        // subtract()
        if (v1.subtract(v2).toString().equals("[0.0, 1.0, 2.0]")) {
            System.out.println("subtract() works!");
        };
        // multiply() by a vector
        if (v1.multiply(v2).toString().equals("[1.0, 2.0, 3.0]")) {
            System.out.println("multiply() works for a vector!");
        };
        // divide() by a vector
        if (v1.divide(v2).toString().equals("[1.0, 2.0, 3.0]")) {
            System.out.println("divide() works for a vector!");
        };
        // multiply() by a scalar
        if (v1.multiply(2).toString().equals("[2.0, 4.0, 6.0]")) {
            System.out.println("multiply() works for a scalar!");
        };
        // divide() by a scalar
        if (v1.divide(2).toString().equals("[0.5, 1.0, 1.5]")) {
            System.out.println("divide() works for a scalar!");
        };

        System.out.println("end");
    }
}