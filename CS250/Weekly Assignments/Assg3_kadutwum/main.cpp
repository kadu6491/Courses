#include <iostream>
#include <fstream>
#include "party.h"
using namespace std;


bool menu();
void newParty();
void oldParty();
void addPartyToFile(party& Party);
void printOldParties(party Party[], int numParty);
int selectOldParty(party Party[], int numParty);
inline bool fileExists (const string& name);
int convert24(int hour, string period);

int main()
{
    bool flag=true;
    ///calls the menu in a loop until it returns false
    while(flag)
    {
        flag = menu();
        if(flag){
            cout<<"\n\n\tPress Enter to return to menu..."<<flush;
            cin.sync();
            cin.ignore();

            continue;
        }
        cout<<"\n\n\tHave A Great Party!"<<flush;
        cin.sync();
        cin.ignore();
    }
    return 0;
}

int convert24(int hour, string period){
    if(period=="pm" && hour != 12){
            return hour + 12;
    }
    if(period == "am" && hour == 12){
        return hour + 12;
    }
    return hour;

}

///add an instance of party to the file pizzaParties.txt
void addPartyToFile(party& Party){
    string filename= "pizzaParties.txt";
    int numParties=1;
    fstream File;
    if(!fileExists(filename)){
        File.open(filename.c_str(), ios::out);
        File<<numParties<<endl;
        Party.writeLine(File);
        File.close();
        return;
    }

    File.open(filename.c_str(), ios::in);
    File>>numParties;
    File.ignore();
    party Parties[numParties];

    for(int i=0; i<numParties; i++){
        Parties[i].readLine(File);
    }

    File.close();
    File.open(filename.c_str(), ios::out);
    File<<numParties+1<<endl;

    for(int i=0; i<numParties; i++){
        Parties[i].writeLine(File);
    }

    Party.writeLine(File);
}

///prints the names of the parties
void printOldParties(party Parties[], int numParty){

    for(int i=0; i<numParty; i++){
        cout<<'\t'<<i+1<<'\t';
        Parties[i].printName(cout);
        cout<<endl;
    }
}

///prints names of old parties and returns the index of selected party
int selectOldParty(party Parties[], int numParty){
    int selection=0;
    cout<<"Your Parties:"<<endl;
    printOldParties(Parties, numParty);

    while(true){
        cout<<"Select Party(1-"<<numParty<<"): ";
        cin>>selection;
        if(!cin || !(selection>0 && selection<=numParty)){
            cin.clear();
            cin.ignore();
            cout<<"\rInvalid Selection::: ";
            continue;
        }
        cin.clear();
        cin.ignore();
        break;
    }
    return selection-1;
}

///checks the existence of a file and returns boolean
inline bool fileExists (const std::string& name) {
    ifstream f(name.c_str());
    return f.good();
}

///loads older parties and displays selected older parties to the user
void oldParty(){
    string filename= "pizzaParties.txt";
    int numParties=0;
    ifstream inFile;

    if(!fileExists(filename)){
        cout<<"No Old Parties Available"<<endl;
        return;
    }

    inFile.open(filename.c_str());
    inFile>>numParties;
    inFile.ignore();
    party Parties[numParties];

    for(int i=0; i<numParties; i++){
        Parties[i].readLine(inFile);
    }

    while(true){
        char con=' ';
        int selection = selectOldParty(Parties, numParties);
        Parties[selection].print(cout);
        cout<<endl<<endl<<"Select Another Party?(y/n) ";
        cin>>con;
        cin.ignore(256,'\n');
        cin.clear();
        if(con == 'y' || con == 'Y'){
            continue;
        }
        break;
    }
}

///create a new party plan
void newParty(){
    party Party;
    int temp=0;
    string tamp="";

    while(true){
        cout<<"Party Title: ";
        getline(cin, tamp);
        if(!cin){
            cin.clear();
            cin.ignore(256, '\n');
            cout<<"\rEnter a title ::: ";
            continue;
        }
        Party.setName(tamp);
        cin.clear();
        break;
    }

    while(true){
        cout<<"# of Adults attending: ";
        cin>>temp;
        if(!cin || temp < 0){
            cin.clear();
            cin.ignore();
            cout<<"\rEnter an Integer >= 0 ::: ";
            continue;
        }
        Party.setAdults(temp);
        cin.clear();
        cin.ignore(256, '\n');
        break;
    }

    while(true){
        cout<<"# of Teens attending: ";
        cin>>temp;
        if(!cin || temp < 0){
            cin.clear();
            cin.ignore(256, '\n');
            cout<<"\rEnter an Integer >= 0 ::: ";
            continue;
        }
        Party.setTeens(temp);
        cin.clear();
        cin.ignore(256, '\n');
        break;
    }

    while(true){
        cout<<"# of Children attending: ";
        cin>>temp;
        if(!cin || temp < 0){
            cin.clear();
            cin.ignore(256, '\n');
            cout<<"\rEnter an Integer >= 0 ::: ";
            continue;
        }
        Party.setChildren(temp);
        cin.clear();
        cin.ignore(256, '\n');
        break;
    }

    while(true){
        cout<<"Day of the Week(1-7) 1 = Sunday: ";
        cin>>temp;
        if(!cin || !(temp < 8 && temp > 0)){
            cin.clear();
            cin.ignore(256, '\n');
            cout<<"\rEnter an Integer(1-7)::: ";
            continue;
        }
        Party.setDayOftheWeek(temp);
        cin.clear();
        cin.ignore(256, '\n');
        break;
    }
    while(true){
        cout<<"Around what Time of Day(example:1 pm): ";
        cin>>temp;
        cin>>tamp;
        if(!cin || !(tamp=="pm" || tamp=="am") || !(temp<13 && temp>0)){
            cin.clear();
            cin.ignore(256, '\n');
            cout<<"\rInvalid Input::: ";
            continue;
        }


        Party.setTimeOfDay(convert24(temp, tamp));
        cin.clear();
        cin.ignore(256, '\n');
        break;
    }

    calcPizzas(Party);
    addPartyToFile(Party);
    cout<<"++++++++++++++++++++Your Results+++++++++++++++++++++"<<endl<<endl;
    Party.print(cout);

}

///display the menu and call appropriate functions based on selections
bool menu(){
    int select=0;
    cout<<"+++++++++++++++++Pizza Party+++++++++++++++++"<<endl<<endl;
    cout<<"\t1\tPlan A New Party\n\t2\tReview Past Parties\n\t3\tExit"<<endl<<endl;

    while(true){
        cout<<"Enter Selection(1,2,3): ";
        cin>>select;
        if(!cin || select > 3 || select < 1){
            cin.clear();
            cin.ignore();
            cout<<"\rInvalid Selection ::: ";
            continue;
        }
        cin.clear();
        cin.ignore();
        break;
    }

    switch(select){
    case 1:
        newParty();
        return true;
    case 2:
        oldParty();
        return true;
    default:
        return false;
    }

    return true;
}
