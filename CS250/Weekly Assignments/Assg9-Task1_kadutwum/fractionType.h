#ifndef FRACTIONTYPE_H_INCLUDED
#define FRACTIONTYPE_H_INCLUDED

#include <iostream>
using namespace std;

class fractionType
{
private:
    int nom;
    int den;

public:

    ///constructor
    fractionType(int a, int b);
    fractionType();


    /// +
    fractionType operator+( fractionType num);
    /// -
    fractionType operator-( fractionType num);
    /// *
    fractionType operator*( fractionType num);
    /// /
    fractionType operator/( fractionType num);
    /// <=
    fractionType operator<=( fractionType num);
    /// >=
    fractionType operator>=( fractionType num);
    /// ==
    fractionType operator==( fractionType num);
    /// !*
    fractionType operator!=( fractionType num);

    fractionType operator<( fractionType num);

    fractionType operator>( fractionType num);

    /// input stream
    friend istream& operator>>( istream& in, const fractionType& num);
    /// output stream
    friend ostream& operator<<(ostream& out, const fractionType& num);
};

#endif // FRACTIONTYPE_H_INCLUDED
