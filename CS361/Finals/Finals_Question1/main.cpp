#include<iostream>
#include<vector>
#include<list>
#include<queue>
#include<random>
#include<ctime>

#include "node.h"

using namespace std;



int main(){

    default_random_engine gen(time(NULL));
    uniform_int_distribution<int> ranode(1, 100);

    priority_queue<node *, vector<node*>, compar> PQueue;

    int n;

    cout<<"Please enter the value of n Greater than 10: ";
    cin>>n;


    if(n <= 10){
        cout<<"Entered values is not greater than 10\n";
        return 0;
    }


    int arr[n];

    for(int i = 0; i < n; i++){
        arr[i] = ranode(gen); //number from 1 to 100
    }


    node mn[5];

    for(int i = 0; i < 5; i++){
        mn[i] = node(arr[i]);
        PQueue.push(&mn[i]);
    }


    priority_queue<node* , vector<node*>, compar> PQueue2;

    for(int i = 5; i < n; i++){
        while(!PQueue.empty() && PQueue.top()->key < arr[i]){
            PQueue2.push(PQueue.top());
            PQueue.pop();
        }

        if(!PQueue.empty()){
            PQueue.top()->pushValue(arr[i]);
        }

        while(!PQueue2.empty()){
            PQueue.push(PQueue2.top());
            PQueue2.pop();
        }

    }

    for(int i = 0; i < 5; i++){
        node t = *PQueue.top();
        cout<<"Node "<<t.key<<" has ";
        if(t.values.size() == 0){
            cout<<"NO values\n";
        }
        else{
            cout<<"values ";
            for(auto k : t.values){
                cout<<k<<",";
            }
            cout<<endl;
        }

        PQueue.pop();

    }

}
