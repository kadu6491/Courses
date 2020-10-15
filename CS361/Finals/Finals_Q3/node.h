#ifndef NODE_H_INCLUDED
#define NODE_H_INCLUDED

class node
{
public:
    node(){x=0;}
    node(char ch, int i){d = ch; x = i;}
    ~node(){}
    int getX()const{return x;}
    char getD()const{return d;}

private:
    char d;
    int x;
};


#endif // NODE_H_INCLUDED
