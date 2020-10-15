#include <iostream>
#include <fstream>
#include <string>

#include "arena.h";

using namespace std;

void mainMenu()
{
    int choose;
    cout << "Welcome to Arena Management System. Please choose an action:" << endl << endl;
    cout << "Main Main:" << endl;
    cout << "1. Manage Tickets" << endl;
    cout << "2. Manage Schedule" << endl;
    cout << "3. Manage Parking" << endl;
    cout << "4. Manage Staff" << endl;
    cout << "5. Manage Bank Account" << endl;
    cout << "6. Manage Concession Stand" << endl;
    cout << "7. Exit " << endl << endl;

    cin >> choose;
    cout << endl << endl;

    if(choose == 1)
    {
        tSubMenu();
    }

    else if(choose == 2)
    {
         sSubMenu();
    }

    else if(choose == 3)
    {
         pSubMenu();
    }

    else if(choose == 4)
    {
         stSubMenu();
    }

    else if(choose == 5)
    {
         bSubMenu();
    }

    else if(choose == 6)
    {
         cSubMain();
    }

    else {
        cout << "EXIT" << endl;
    }
}
