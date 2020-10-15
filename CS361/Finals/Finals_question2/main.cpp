#include <iostream>
#include <stdlib.h>

#include "box.h"

using namespace std;

int main()
{
    Box b[8];
    int n,m;
    bool d1,d2;
    int arr[8],max=0,id;


    for(int i=0;i<8;i++)
    {
        d1=d2=false;
        n=rand();
        m=rand();
        if(n%2==0)
        d1=true;
        if(m%2==0)
        d2=true;

        b[i].setBox(i,d1,d2);

    }

    display(b);
    for(int i=0;i<8;i++)
        arr[i]=visit(b,i);


    for(int i=0;i<8;i++)
    {
        if(max<arr[i]){
            max=arr[i];
            id=i;
        }

    }
    print(b,id);

    cout << endl;
    return 0;
}
