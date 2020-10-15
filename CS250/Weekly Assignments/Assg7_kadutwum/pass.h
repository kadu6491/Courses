#ifndef PASS_H_INCLUDED
#define PASS_H_INCLUDED

#include <string>

using namespace std;

class passenger
{
private:
    string fname;
    string lname;

public:
    passenger(){}
    passenger(string f, string l);
    string getfname(){return fname;}
    string getlname(){return lname;}
    void setfname(string val){fname = val;}
    void setlanme(string val){lname = val;}

    string getname(){return fname + " " + lname;}
};


class staff
{
private:
    string Sfname;
    string Slname;

public:
    staff(){}
    staff(string Sf, string Sl);
    string getSfname(){return Sfname;}
    string getSlname(){return Slname;}
    void setSfname(string Sval){Sfname = Sval;}
    void setSlanme(string Sval){Slname = Sval;}

    string getSname(){return Sfname + " " + Slname;}
};

#endif // PASS_H_INCLUDED
