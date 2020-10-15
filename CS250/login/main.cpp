#include <iostream>
#include <fstream>

using namespace std;

void create();
void login();

int main()
{
    int ans;

    cout << "1. Login" << endl;
    cout << "2. Create an account" << endl;
    cout << "3. Exit " << endl << endl;
    cin >> ans;
    cout << endl << endl;

    if(ans == 1)
    {
        login();
    }

    else if(ans == 2)
    {
        create();
    }

    else {
        cout << "existing" << endl;
    }


    return 0;
}


void create()
{
    ofstream outFile;
    outFile.open("login.txt");

    //ofstream::app

    string fname;
    string lname;
    string username;
    string pass;

    cout << "Enter first and last name: ";
    cin >> fname >> lname;

    cout << "Enter a username (Just letters, no numbers): ";
    cin >> username;

    cout << "Create a password: ";
    cin >> pass;

    outFile << "First Name" << "   |   " << "Last Name" << "   |   "
            << "Username" << "   |   " << "Password" << endl;

    outFile  << fname << "\t\t" << lname << "\t\t"
             << username << "\t\t" << pass << endl;


}

void login()
{
    ifstream inFile;
    inFile.open("login.txt");

    string f[5], l[5], u[5] , p[5];
    for(int i = 0; i < 5; i++)
    {
        inFile >> f[i] >> l[i] >> u[i] >> p[i];
        cout << f[i] << l[i] << u[i] << p[i] << endl;
    }

    cout << endl;

    string user;
    string psd;

    cout << "Enter Username: ";
    cin >> user;
    cout << endl;

    cout << "Enter Password: ";
    cin >> psd;
    cout << endl << endl;

    for (int i = 0; i < 5; i++)
    {
        if(psd == p[i])
        {
            cout << "Successfully login" << endl;
        }

        else {
            cout << "Wrong user name/password" << endl;
        }
    }

}
