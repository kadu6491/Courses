#include <iostream>
#include <iomanip>
#include "fractionType.h"

using namespace std;

fractionType::fractionType(int a, int b)
{
    nom = a;
    den = b;
}

fractionType::fractionType()
{
    nom = 1;
    den = 1;
}

fractionType fractionType::operator+(fractionType num)
{
   // fractionType res;

    int x = nom;
    int i = den;

    x = num.nom * den + num.den * nom;
    i*= num.nom;

    return fractionType(x, i);

}

fractionType fractionType::operator-(fractionType num)
{
   // fractionType res;

    int x = nom;
    int i = den;

    x = num.nom * den + num.den * nom;
    i*= num.nom;

    return fractionType(x, i);

}




fractionType fractionType::operator*(fractionType num)
{
   // fractionType res;

    int x = nom;
    int i = den;

    x*= num.nom;
    i*= num.den;

    return fractionType(x, i);

}

fractionType fractionType::operator/(fractionType num)
{
   // fractionType res;

    return *this*fractionType(num.nom, num.den);

}


fractionType fractionType::operator<=(fractionType num)
{
   // fractionType res;

    int x = nom;
    int i = den;

    x = num.nom * den + num.den * nom;
    i*= num.nom;

    return fractionType(x, i);

}

fractionType fractionType::operator>=(fractionType num)
{
   // fractionType res;

    int x = nom;
    int i = den;

    x = num.nom * den + num.den * nom;
    i*= num.nom;

    return fractionType(x, i);

}


fractionType fractionType::operator==(fractionType num)
{
   // fractionType res;

    int x = nom;
    int i = den;

    x = num.nom * den + num.den * nom;
    i*= num.nom;

    return fractionType(x, i);

}

fractionType fractionType::operator!=(fractionType num)
{
   // fractionType res;

    int x = nom;
    int i = den;

    x = num.nom * den + num.den * nom;
    i*= num.nom;

    return fractionType(x, i);

}

fractionType fractionType::operator<(fractionType num)
{
   // fractionType res;

    int x = nom;
    int i = den;

    x = num.nom * den + num.den * nom;
    i*= num.nom;

    return fractionType(x, i);

}


fractionType fractionType::operator>(fractionType num)
{
   // fractionType res;

    int x = nom;
    int i = den;

    x = num.nom * den + num.den * nom;
    i*= num.nom;

    return fractionType(x, i);

}




ostream& operator<<(ostream& out, const fractionType& num)
{
    out << num.nom << "/" << num.den;

    return out;
}

istream& operator>>( istream& in, const fractionType& num)
{
    int y;
    cout << "Line 9: Enter the fraction "
		 << "in the form a / b:   ";                //Line 9
    in >> y;

    return in;                                    //Line 10

}


