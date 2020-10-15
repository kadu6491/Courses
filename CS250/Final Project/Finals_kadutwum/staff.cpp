#include <iostream>
#include <fstream>
#include <string>

#include "arena.h";

using namespace std;

void createStuff(ofstream& outStuff)
{
    outStuff.open("staff.txt", ofstream::app);

    staff infoStaff;

    cout << "Enter the ID of the new staff member" << endl;
    cin >> infoStaff.ID;
    cout << endl;

    cout << "Enter the full name of the new stuff member" << endl;
    cin >> infoStaff.fname >> infoStaff.lname;
    cout << endl;

    cout << "Enter the job of the new staff member" << endl;
    cin >> infoStaff.job;
    cout << endl;

    cout << "Enter the salary of the new staff member" << endl;
    cin >> infoStaff.salary;
    cout << endl;

    outStuff << infoStaff.ID << " " << infoStaff.fname << " "
             << infoStaff.lname << " " << infoStaff.job << " "
             << infoStaff.salary << endl;

    outStuff.close();

    mainMenu();
}


void staff::printStuff(ifstream& inStuff)
{
    inStuff.open("staff.txt");

    int num = 5;
    staff iStaff[num];

    for(int i = 0; i < num; i++)
    {
        inStuff >> iStaff[i].ID >> iStaff[i].fname >> iStaff[i].lname;
        inStuff >> iStaff[i].job >> iStaff[i].salary;

        cout << "ID: " << iStaff[i].ID << endl;
        cout << "Name: " << iStaff[i].fname << " " << iStaff[i].lname
             << endl;
        cout << "Job: " << iStaff[i].job << endl;
        cout << "Salary: $" << iStaff[i].salary << endl;

        cout << endl;
    }

    inStuff.close();

    mainMenu();

}

void printById(ifstream& inStuff)
{
    inStuff.open("staff.txt");

    int staffID;
    int num = 5;
    staff byID[num];

    cout << "Enter staff ID" << endl;
    cin >> staffID;
    cout << endl;

    for(int i = 0; i < num; i++)
    {
        inStuff >> byID[i].ID >> byID[i].fname >> byID[i].lname;
        inStuff >> byID[i].job >> byID[i].salary;
    }

    for(int i = 0; i < 1; i++)
    {
        if(staffID == byID[i].ID)
        {
            cout << "ID: " << byID[i].ID << endl;
            cout << "Name: " << byID[i].fname << " " << byID[i].lname
                 << endl;
            cout << "Job: " << byID[i].job << endl;
            cout << "Salary: $" << byID[i].salary << endl;
        }

        else {
            cout << "Could not find staff" << endl << endl;

            mainMenu();
        }
    }


}


void printByJob(ifstream& inStuff)
{
    inStuff.open("staff.txt");

    string staffJob;
    int num = 5;
    staff byJob[num];

    cout << "Enter Jobs title" << endl;
    cin >> staffJob;
    cout << endl;

    for(int i = 0; i < num; i++)
    {
        inStuff >> byJob[i].ID >> byJob[i].fname >> byJob[i].lname;
        inStuff >> byJob[i].job >> byJob[i].salary;
    }

    for(int i = 0; i < 1; i++)
    {
        if(staffJob == byJob[i].job)
        {
            cout << "ID: " << byJob[i].ID << endl;
            cout << "Name: " << byJob[i].fname << " " << byJob[i].lname
                 << endl;
            cout << "Job: " << byJob[i].job << endl;
            cout << "Salary: $" << byJob[i].salary << endl;
        }

        else {
            cout << "No stuff with that Job title" << endl << endl;

            mainMenu();
        }
    }


}

void fire()
{
    string ln , fn;

    cout << "Enter the name of staff you want to fire" << endl;
    cin >> fn >> ln;

    cout << endl << endl;

    mainMenu();
}

void stSubMenu()
{
    ofstream outStuff;
    ifstream inStuff;

    staff myStaff;

    int ans;

    cout << "1. Show all staff info" << endl;
    cout << "2. Print a staff member's info by ID" << endl;
    cout << "3. Print all staff of certain job" << endl;
    cout << "4. Create new staff member" << endl;
    cout << "5. Fire existing staff member" << endl;
    cout << "6. Main Menu" << endl << endl;

    cin >> ans;
    cout << endl << endl;

    if(ans == 1)
    {
        myStaff.printStuff(inStuff);
    }

    else if(ans == 2)
    {
        printById(inStuff);
    }

    else if(ans == 3)
    {
        printByJob(inStuff);
    }

    else if(ans == 4)
    {
        createStuff(outStuff);
    }

    else if(ans == 5)
    {
        fire();
    }

    else {
        mainMenu();
    }
}


