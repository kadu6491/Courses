#include <iostream>
#include <fstream>
#include <string>
#include <stdio.h>

#include "arena.h";

using namespace std;

void createEvent(ofstream& outFile, Schedule eName)
{

    outFile.open("schedule.txt", ofstream::app);

    cout << "Enter the name of the new event" << endl;;
    cin >> eName.eventName;
    cout << endl;

    cout << "Enter the year of the event" << endl;
    cin >> eName.date.year;
    cout << endl;

    cout << "Enter the month of the event" << endl;
    cin >> eName.date.month;
    cout << endl;

    cout << "Enter the day of the event" << endl;
    cin >> eName.date.day;
    cout << endl;

    cout << "Enter the hour of the event" << endl;
    cin >> eName.hour;
    cout << endl;

    cout << "Enter the minute of the event" << endl;
    cin >> eName.minute;
    cout << endl;

    outFile << eName.eventName << " " << eName.date.month << "/"
            << eName.date.day << "/" << eName.date.year << "\t "
            << eName.hour << ":" << eName.minute << endl;


    outFile.close();

    mainMenu();
}

void Schedule::printEvent()
{
    ifstream inFile;

    inFile.open("schedule.txt");

    Schedule event;

    for(int i = 0; i < 3; i++)
    {
        inFile >> event.ename[i] >> event.eMon[i] >> event.bb >> event.eDay[i];
        inFile >> event.bb >> event.eYear[i] >> event.eHour[i] >> event.col >> event.eMin[i];

        cout << "Event name: " << event.ename[i] << endl;
        cout << "Date: " << event.eMon[i] << "/" << event.eDay[i] << "/" << event.eYear[i]
             << "  " << event.eHour[i] << ":" << event.eMin[i] << endl;
        cout << endl;
    }

    inFile.close();

    mainMenu();
}

void sSubMenu()
{
    Schedule eName;
    ofstream outFile;
    ifstream inFile;

    Schedule myEvent;

    int sAns;

    cout << "1. Create new event" << endl;
    cout << "2. Print all event information" << endl;
    cout << "3. Adjust event information" << endl;
    cout << "4. Cancel an event" << endl;
    cout << "5. Print events on a certain date" << endl;
    cout << "6. Main Menu" << endl << endl;

    cin >> sAns;
    cout << endl << endl;

    if(sAns == 1)
    {
        createEvent(outFile, eName);
    }

    else if(sAns == 2)
    {
        myEvent.printEvent();
    }

    else if(sAns == 3)
    {
        mainMenu();
    }

    else if(sAns == 4)
    {
        mainMenu();
    }

    else if(sAns == 5)
    {
        mainMenu();
    }

    else {
        mainMenu();
    }
}
