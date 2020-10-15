#include <iostream>
#include <list>
#include <random>
#include <ctime>

using namespace std;

int main()
{
    default_random_engine gen(time(NULL));
    uniform_int_distribution<int> ranode(-50, 50);

    int n;
    int sum = 0;
    int m;


    cout << "Enter Height: ";
    cin >> n;
    cout << "Enter Width: ";
    cin >> m;

    int num[n][m];
    cout << endl << endl;

    for(int i = 0; i < n; i++)
    {
        for(int x= 0; x < m; x++)
        {
            num[i][x] = ranode(gen);
            cout << num[i][x] << "  ";
            sum += num[i][x];
        }
        cout << endl;

    }

    cout << endl;
    cout << "Sum = " << sum << endl;
    return 0;
}
