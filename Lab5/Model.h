
#ifndef __MODEL__
#define __MODEL__

#include "Vector.h"

class Model {

private:

    Vector params;

public:

    Model( int dim )
      : params( dim, 1 ) {
    }
    
    double predict( const Vector & input ) const {
        return input.dotProduct( params );
    }
    
    void update( const Vector & delta, double learningRate ) {
        Vector rate( params.getDim(), learningRate );
        params = params.subtract( delta.multiply( rate ) );
    }
    
    friend std::ostream & operator<<( std::ostream & os, Model & m ) {
        return os << m.params;
    }

};

#endif

