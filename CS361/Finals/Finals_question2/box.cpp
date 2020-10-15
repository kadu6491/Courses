#include <iostream>
#include <stdlib.h>

#include "box.h"
using namespace std;

void display(Box b[])
{
    for(int i=0;i<8;i++)
    {
        if(b[i].getWest())
            cout<<":";
        else
            cout<<"|";
        cout<<b[i].getID();

        if(b[i].getEast())
            cout<<":";
        else
            cout<<"|";
    }
}


int visit(Box b[],int i)
{
    int arr[7],n=0;
    int m=i,j;


    if(!b[i].getWest() && !b[i].getEast()){
        cout<<"\nBox "<<i<<" you can go no where";
        return 0;
    }

    while(m>0&& b[m].getWest() )
    {
        arr[n]=m-1;
        n++;
        m=m-1;
    }

    cout<<"\nBox "<<i<<" you can visit ";
    for(j=0;j<n;j++)
        cout<<arr[j]<<" ";
        m=i;

    while(m<7 && b[m].getEast())
    {
        arr[n]=m+1;
        n++;
        m=m+1;
    }
    if(m>j)
        cout<<" AND ";


    for(int k=j;k<n;k++)
        cout<<arr[k]<<" ";

    return n; //return the boo count can visit

}

//print the maximum reachability
void print(Box b[],int id)
{
    cout<<"\nThe box with maximum reachability is box "<<id<<" where you can reach ";
    int n=visit(b,id);

}
