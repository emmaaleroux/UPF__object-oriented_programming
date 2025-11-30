
#ifndef __VECTOR__
#define __VECTOR__

#include <bits/stdc++.h>

class Vector {

private:

    std::vector<double> elems;

public:

    Vector( int dim, double v ) {
        elems = std::vector<double>( dim );
        std::fill( elems.begin(), elems.end(), v );
    }

    Vector( const std::vector<double> & e )
      : elems( e.begin(), e.end() ) {
    }
    
    int getDim() const {
        return elems.size();
    }
    
    Vector add( const Vector & v ) const {
        std::vector<double> out( elems.begin(), elems.end() );
        for ( int i = 0; i < getDim() && i < v.getDim(); ++i )
            out[i] += v.elems[i];
        return Vector( out );
    }
    
    Vector subtract( const Vector & v ) const {
        std::vector<double> out( elems.begin(), elems.end() );
        for ( int i = 0; i < getDim() && i < v.getDim(); ++i )
            out[i] -= v.elems[i];
        return Vector( out );
    }
    
    Vector multiply( const Vector & v ) const {
        std::vector<double> out( elems.begin(), elems.end() );
        for ( int i = 0; i < getDim() && i < v.getDim(); ++i )
            out[i] *= v.elems[i];
        return Vector( out );
    }
    
    Vector divide( const Vector & v ) const {
        std::vector<double> out( elems.begin(), elems.end() );
        for ( int i = 0; i < getDim() && i < v.getDim(); ++i )
            out[i] /= v.elems[i];
        return Vector( out );
    }
    
    Vector multiply( double scalar ) const {
        return multiply( Vector( getDim(), scalar ) );
    }
    
    Vector divide( double scalar ) const {
        return divide( Vector( getDim(), scalar ) );
    }
    
    Vector do_sqrt() const {
        std::vector<double> out( elems.begin(), elems.end() );
        for ( int i = 0; i < getDim(); ++i )
            out[i] += sqrt( out[i] );
        return Vector( out );
    }
    
    double dotProduct( const Vector & v ) const {
        double out = 0;
        for ( int i = 0; i < getDim() && i < v.getDim(); ++i )
            out += elems[i] * v.elems[i];
        return out;
    }
    
    double norm() const {
        return sqrt( dotProduct( *this ) );
    }
    
    Vector augment() const {
        std::vector<double> out( elems.begin(), elems.end() );
        out.push_back( 1 );
        return Vector( out );
    }
    
    friend std::ostream & operator<<( std::ostream & os, Vector & v ) {
        os << "[" << v.elems[0];
        for ( int i = 1; i < v.getDim(); ++i )
            os << "," << v.elems[i];
        return os << "]";
    }

};

#endif

