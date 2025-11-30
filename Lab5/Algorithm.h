
# ifndef __ALGORITHM__
# define __ALGORITHM__

#include "Dataset.h"
#include "Model.h"

class Algorithm {

protected: 

    //ATTRIBUTES
    double learningRate; 

public: 

    //CONSTRUCTOR
    Algorithm(double lr) : learningRate(lr) {}

    //GETTER
    double getLearningRate() const{ //const mean this method does not modify the object
        return learningRate;
    }

    //METHOD
    virtual Model solve(Dataset &ds) = 0; // = 0 because it makes the class abstract

}; 

#endif