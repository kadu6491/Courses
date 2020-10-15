#include <iostream>
#include <string>
#include <fstream>
#include <vector>


#include "pass.h";


using namespace std;

int main()
{
    char ans;

    vector<passenger> passengers;
    vector<staff> staffs;

    ifstream inPass;
    ifstream inStaff;

    inPass.open("Passengers.txt");
    inStaff.open("Staff.txt");



    while(!inPass.eof())
    {
        string fname;
        string lname;

        inPass >> fname >> lname;
        ///cout << fname << " " << lname;

        passenger tempPassenger(fname, lname);
        passengers.push_back(tempPassenger);
    }

    inPass.close();


    /// Staff

    while(!inStaff.eof())
    {
        string Sfname;
        string Slname;

        inStaff >> Sfname >> Slname;
        ///cout << Sfname << " " << Slname;

        staff tempStaff(Sfname, Slname);
        staffs.push_back(tempStaff);
    }

    inStaff.close();

    string fname;
    string lname;

    cout << "Enter a person's name that should not be traveling: " << endl;
    cin >> fname;
    cin >> lname;

    string name = fname + " " + lname;
    string Sname = fname + " " + lname;
    cin.ignore();

    for(int i = 0; i < passengers.size(); i++)
    {
        if(passengers[i].getname() == name)
        {
            cout << "Security has been called" << endl;
        }
    }

    for(int i = 0; i < staffs.size(); i++)
    {
        if(staffs[i].getSname() == Sname)
        {
            cout << "Security has been called" << endl;
        }
    }


    cout << endl;

    cout << "Would you like to search for another person (y/n)" << endl;
    cin >> ans;
    cout << endl;





    /// Ask for another person name

    while (ans == 'y')
    {
        while(!inPass.eof())
        {
            string fname;
            string lname;

            inPass >> fname >> lname;
            ///cout << fname << " " << lname;

            passenger tempPassenger(fname, lname);
            passengers.push_back(tempPassenger);
        }

        inPass.close();

        /// Staff

        while(!inStaff.eof())
        {
            string Sfname;
            string Slname;

            inStaff >> Sfname >> Slname;
            ///cout << Sfname << " " << Slname;

            staff tempStaff(Sfname, Slname);
            staffs.push_back(tempStaff);
        }

        inStaff.close();

        string fname;
        string lname;

        cout << "Enter a person's name that should not be traveling: " << endl;
        cin >> fname;
        cin >> lname;

        string name = fname + " " + lname;
        cin.ignore();

        for(int i = 0; i < passengers.size(); i++)
        {
            if(passengers[i].getname() == name)
            {
                cout << "Security has been called" << endl;
            }
        }

        for(int i = 0; i < staffs.size(); i++)
        {
            if(staffs[i].getSname() == Sname)
            {
                cout << "Security has been called" << endl;
            }
        }


        cout << endl;

        cout << "Would you like to search for another person (y/n)" << endl;
        cin >> ans;
        cout << endl;
    }

    return 0;
}
