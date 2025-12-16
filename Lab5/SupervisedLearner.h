
# ifndef __SUPERVISEDLEARNER__
# define __SUPERVISEDLEARNER__

#include <iostream>
#include <string>
#include <sstream>

#include "Model.h"
#include "Dataset.h"
#include "Algorithm.h"
#include "Vector.h"
#include "Record.h"

class SupervisedLearner {

private:

    //ATTRIBUTES
    Algorithm *algorithm; // pointer to an Algorithm
    Dataset dataset;
    std::unique_ptr<Model> model; // Model model;(java)

public:

    // CONSTRUCTOR
    SupervisedLearner(Algorithm* a, const Dataset& d) : algorithm(a), dataset(d), model(nullptr) {}


    // GETTERS
    Algorithm* getAlgorithm() const { 
        return algorithm; 
    }

    const Dataset& getDataset() const { 
        return dataset; 
    }

    Model* getModel() const { 
        return model.get(); // returns nullptr if not trained
    } 

    // METHODS

    void solve(){
        if (!algorithm) {
            std::cout << "SupervisedLearner::solve(): no algorithm provided\n" << std::endl;
            return;
        }
        Model trained = algorithm->solve(dataset); // assumes Algorithm::solve returns Model by value
        model = std::make_unique<Model>(std::move(trained));
        
        //this.model = algorithm.solve(dataset); (java code)
    }

    double predict(Vector v) { //change to cpp
        if (!model) {
            std::cout << "Model not learned, call solve() first" << std::endl;
            return 0.0;
        }
        // In Dataset there is NO transform() or output(), so we skip those steps and directly augment the vector.
        Vector vAug = v.augment();
        // Predict using the internal model
        double predInternal = model->predict(vAug);
        return predInternal;
    }

    /* 
    //since Model.h does not have a toString method we need to change the method 

    std::string toString() const { 
        if (!model) {
            return "Untrained model, call solve() first";
        }
        return model->toString();
    }*/

    std::string toString() const {
        if (!model) return "Untrained model, call solve() first";
        std::ostringstream ss;
        ss << *model;   // requires operator<< for Model
        return ss.str();
    }

};

#endif