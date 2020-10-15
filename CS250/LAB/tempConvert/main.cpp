#include <iostream>
#include <string>
using namespace std;

// Farenheit to Centigrade converter
int main()
{
    cout << "Enter a temperature measured in degrees Farenheit: "
         << flush;

    double f;
    cin >> f; // reads f from the keyboard
    double c = (5.0 / 9.0) * (f - 32.0);

    cout << "In Centigrade, that would be " << c << " degrees." << endl;
    return 0;
}
