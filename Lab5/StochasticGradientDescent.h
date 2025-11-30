
# ifndef __STOCHASTICGRADIENTDESCENT__
# define __STOCHASTICGRADIENTDESCENT__

#include <iostream>
#include <string>
#include <vector>
#include <random>
#include <algorithm>

#include "Algorithm.h"
#include "Dataset.h"
#include "Model.h"
#include "Vector.h"
#include "Record.h"


class StochasticGradientDescent : public Algorithm {
private:

    //ATTRIBUTES
    int batchSize;
    int iterations;

public: 

    //CONSTRUCTOR
    StochasticGradientDescent(double lr = 0.01, int bs = 10, int iters = 1000) 
        : Algorithm(lr), batchSize(bs), iterations(iters) {}


    //METHODS

    Vector stochasticGradient(const Dataset& ds, const Model& m){
        const auto data = ds.getData(); 
        std::size_t n = data.size();

        Vector g(ds.getDim() + 1, 0.0); // Gradient vector

        if (n == 0) { // if empty dataset then return zero gradient
            std::cout << "StochasticGradientDescent::stochasticGradient(): empty dataset\n" << std::endl;
            return g;
        }

        int bs = batchSize;

        if (bs > static_cast<int>(n)) {
            std::cout << "Batch size greater than dataset. Setting batch size = n - 1.\n" << std::endl;
            bs = static_cast<int>(n) - 1;
        }
        if (bs <= 0) bs = 1;

        std::vector<Record> batch;
        
        std::sample(data.begin(), data.end(), std::back_inserter(batch), bs, std::mt19937{ std::random_device{}() }); 
        
        // compute avg stochastic gradient (∇ = (1/bs) Σ (x_aug * (θ·x_aug - y)))
        for (const Record& r : batch) {
            Vector xAug = r.getInput().augment();  // augmented input (adds bias)
            double y = r.getOutput();
            double pred = m.predict(xAug);
            double err = pred - y;

            g = g.add(xAug.multiply(err));
        }

        // return mean gradient
        return g.multiply(1.0 / static_cast<double>(bs));

    }

    
    Model solve(Dataset &ds) override{
        Model model(ds.getDim() + 1);
        for (int i = 0; i < iterations; ++i) {
            Vector sg = stochasticGradient(ds, model);
            model.update(sg, learningRate);
        }
        return model;
    }


};

#endif