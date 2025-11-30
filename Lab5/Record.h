
#ifndef __RECORD__
#define __RECORD__

#include "Vector.h"

class Record {

private:

    Vector input;
    double output;

public:
    
    Record( const Vector & i, double o )
      : input( i ), output( o ) {
    }
    
    Vector getInput() const {
        return input;
    }
    
    double getOutput() const {
        return output;
    }
    
    friend std::ostream & operator<<( std::ostream & os, Record & r ) {
        return os << "(" << r.input << "; " << r.output << ")";
    }
    
};

#endif

