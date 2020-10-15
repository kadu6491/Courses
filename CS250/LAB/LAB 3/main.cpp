#include <iostream>
#include <fstream>

using namespace std;

int main()
{
    ifstream input ("data2.txt");
    while (!input)
    {
        int k = -12345;
        input >> k;
        if (input) // input.good()
        {
            cout << k << endl;
        }
    }
    return 0;
}
