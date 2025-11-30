
#ifndef __DATASET__
#define __DATASET__

#include "Record.h"

class Dataset {

protected:

    int dim;
    std::vector<Record> data;
    
public:
    
    Dataset( int n )
      : dim( n ) {
    }
    
    int getDim() const {
        return dim;
    }
    
    std::vector<Record> getData() const {
        return data;
    }
    
    void addRecord( Record r ) {
        data.push_back(r);
    }
    
    friend std::ostream & operator<<( std::ostream & os, Dataset & ds ) {
        os << "[" << ds.data[0];
        for ( int i = 1; i < ds.data.size(); ++i )
            os << ", " << ds.data[i];
        return os << "]";
    }
    
};

#endif

