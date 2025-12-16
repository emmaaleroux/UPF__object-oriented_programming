
#include <iostream>
#include <random>
#include <cmath>

#include "Vector.h"
#include "Dataset.h"
#include "Model.h"
#include "Record.h"
#include "GradientDescent.h"
#include "StochasticGradientDescent.h"
#include "SupervisedLearner.h"
#include "Algorithm.h"

int main() {

    int errors = 0; // Error counter

        // VECTOR
    std::cout << "\nLet's test Vector.\n";

    Vector v1(std::vector<double>{1.0, 2.0, 3.0});
    Vector v2(3, 1.0);

    std::cout << "v1: " << v1 << "\n";
    std::cout << "v2: " << v2 << "\n";

    if (v1.getDim() == 3 && v2.getDim() == 3)
        std::cout << "getDim() works!\n";
    else { errors++; std::cout << "getDim() does not work\n"; }
    
    Vector v4 = v1.add( v2 );
    Vector v5 = v2.subtract( v1 );
    Vector v6 = v1.multiply( 4 );
    Vector v7 = v2.divide( 1.2 );
    Vector v8 = v2.multiply( v2 );
    
    std::cout <<"v4 = v1+v2 = " << v4 << "\nv5 = v2-v1 = " << v5 << "\nv6 = v1*4 = " << v6 << "\nv7 = v2/1.3 = " << v7 << "\nv8 = v2*v2 = " << v8 << "\n";
    
    if (v1.dotProduct(v2) == 1*1 + 2*1 + 3*1) {
        std::cout << "dotProduct() works!\n";
    } else {
        errors++;
        std::cout << "dotProduct() does not work...\n";
    }

    // Expected: sqrt(1^2 + 1^2 + 1^2) = sqrt(3)
    if (std::abs(v2.norm() - std::sqrt(3.0)) < 1e-6) {
        std::cout << "norm() works! \n";
    } else {
        errors++;
        std::cout << "norm() does not work...\n";
    }

        // RECORD
    std::cout << "\nLet's test Record.\n";

    Record r1(v1, 1.0);
    std::cout << "r1: " << r1 << "\n";

    if (r1.getOutput() == 1.0)
        std::cout << "getOutput() works!\n";
    else { errors++; std::cout << "getOutput() does not work\n"; }

        // DATASET
    std::cout << "\nLet's test Dataset.\n";
    Dataset ds(2);
    ds.addRecord(Record(Vector(std::vector<double>{1.0, 1.0}), 4.0));
    ds.addRecord(Record(Vector(std::vector<double>{2.0, 1.0}), 5.0));
    ds.addRecord(Record(Vector(std::vector<double>{1.0, 3.0}), 8.0));
    ds.addRecord(Record(Vector(std::vector<double>{3.0, 2.0}), 8.0));

    if (ds.getData().size() == 4)
        std::cout << "addRecord() works!";
    else { errors++; std::cout << "addRecord() does not work..."; }

    std::cout << "\nDataset:" << ds;

        // GRADIENT DESCENT
    std::cout << "\nLet's test Gradient Descent.\n";

    return 0;
}