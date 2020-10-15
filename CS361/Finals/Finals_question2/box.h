#ifndef BOX_H_INCLUDED
#define BOX_H_INCLUDED

#include <iostream>
#include <stdlib.h>

using namespace std;

class Box
{
    int Id;
    bool east;
    bool west;

public:
    Box(){};
    //set box values
    void setBox(int i, bool e, bool w){
        this->Id=i;
        this->east=e;
        this->west=w;
    }

    int getID(){
    return Id;
}

    bool getEast(){
        return east;
    }

    bool getWest(){
        return west;
        }
};


void display(Box b[]);

int visit(Box b[],int i);
void print(Box b[],int id);


#endif // BOX_H_INCLUDED
