#ifndef NODE_H_INCLUDED
#define NODE_H_INCLUDED

#include<iostream>
#include<vector>
#include<queue>
#include<random>
#include<ctime>

using namespace std;

class node{

public:
    int key;
    vector<int> values;
    node(){}
    node(int k){
        key = k;
    }

    void pushValue(int val){
        values.push_back(val);
    }

};


class compar {
public:
    bool operator() (node* const& lhs, node* const& rhs) {
        return lhs->key > rhs->key;
    }

};

#endif // NODE_H_INCLUDED
