
# ifndef __GRADIENTDESCENT__
# define __GRADIENTDESCENT__

#include <iostream> //to print out (input/output library)
#include <string> //string class 

#include "Vector.h"
#include "Model.h"
#include "Algorithm.h"
#include "Dataset.h"
#include "Record.h"

class GradientDescent : public Algorithm{

private: 

    //ATTRIBUTES
    double stoppingCriterion; 

public: 

    //CONSTRUCTOR
    GradientDescent(double lr = 0.01, double sc = 1e-6) : Algorithm(lr), stoppingCriterion(sc) {}

    //GETTER
    double getStoppingCriterion() const {
        return stoppingCriterion;
    }

    //METHOD

    Vector gradient(const Dataset& ds, const Model& m) const {
        const auto data = ds.getData(); // take copy/reference depending on Dataset implementation
        std::size_t n = data.size();

        // We initialize empty gradient of the loss function
        Vector g(ds.getDim() + 1, 0.0); //no new in cpp
        if (n == 0) {
            std::cout << "Empty dataset." << std::endl;
            return g;
        }
        // We go through each record
        for (const Record &r : data) {
            // gradient = ( x̄ * (θ · x̄ - y) ) / n
            //          = ( x̄ * error ) / n
            Vector aug = r.getInput().augment(); // aug = x̄
            double y = r.getOutput();
            double pred = m.predict(aug);
            double err = pred - y;
            g = g.add(aug.multiply(err) );
        }
        return g.multiply(1.0 / static_cast<double>(n));
    }

    Model solve(Dataset &ds) override{
        Model model(ds.getDim() + 1);

        Vector g = gradient(ds, model);
        // We use an iterator to check the gradient norm (should get smaller)
        // int i = 0; 
        const int maxIter = 100000; // to avoid infinite loops
        int iter = 0;

        while (g.norm() > stoppingCriterion && iter++ < maxIter) {
            // System.out.println("i = " + i + ": gradient norm:" + g.norm());
            model.update(g, learningRate);
            g = gradient(ds, model);
            // i++;
        }

        if (iter >= maxIter) { // to avoid infinite loops
            std::cout << "GradientDescent::solve(): reached max iterations (" << maxIter << ")\n" << std::endl;
        }

        return model;
    }

};

#endif