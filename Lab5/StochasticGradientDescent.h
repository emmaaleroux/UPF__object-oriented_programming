
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
    std::mt19937 rng;   // random engine for sampling

public: 

    //CONSTRUCTOR
    StochasticGradientDescent(double lr = 0.01, int bs = 10, int iters = 1000) 
        : Algorithm(lr), batchSize(bs), iterations(iters), rng(std::random_device{}()) {}


    //METHODS

    Vector stochasticGradient(const Dataset& ds, const Model& m){
        const auto data = ds.getData(); 
        int n = data.size();
        int bs = batchSize;

        if (bs > n) {
            std::cout << "Batch is greater than dataset. Setting batch size = dataset size - 1." << std::endl;
            bs = n - 1;
        }
        if (bs <= 0) bs = 1;

        Vector g(ds.getDim() + 1, 0.0); 

        std::vector<int> indices(n); // Create a vector of indices 0,1,2,...,n-1
        for (int i = 0; i < n; ++i) indices[i] = i;

        std::shuffle(indices.begin(), indices.end(), rng); //Shuffle indices to simulate distinct random picks

        // Take the first 'bs' indices as the batch
        for (int i = 0; i < bs; ++i) {
            int idx = indices[i];
            const Record& r = data[idx];
            Vector aug = r.getInput().augment();
            double pred = m.predict(aug);
            double error = pred - r.getOutput();

            g = g.add(aug.multiply(error));
        }
        return g.multiply(1.0 / bs); // We return the average
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