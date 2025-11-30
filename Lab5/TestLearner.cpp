
#include <iostream>
#include <vector>
#include <random>
#include <cmath>

#include "Dataset.h"
#include "Model.h"
#include "Record.h"
#include "GradientDescent.h"
#include "StochasticGradientDescent.h"
#include "SupervisedLearner.h"
#include "Algorithm.h"

int main() {

    Vector v1( std::vector<double> { 0.5, 1.5, 2.5 } );
    Vector v2( std::vector<double> { 8.0, 6.0, 4.0 } );
    Vector v3( std::vector<double> { 3.6, 4.8, 7.2 } );
    
    std::cout << v1 << "\n" << v2 << "\n" << v3 << "\n";
    
    Vector v4 = v1.add( v3 );
    Vector v5 = v2.subtract( v1 );
    Vector v6 = v1.multiply( 4 );
    Vector v7 = v3.divide( 1.2 );
    Vector v8 = v2.multiply( v2 );
    
    std::cout << v4 << "\n" << v5 << "\n" << v6 << "\n" << v7 << "\n" << v8 << "\n";
    
    double d1 = v1.dotProduct( v2 );
    double d2 = v2.norm();
    
    std::cout << d1 << " " << d2 << "\n";

    return 0;
}

