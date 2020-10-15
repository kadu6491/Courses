#ifndef __PARTY_H_INCLUDED__
#define __PARTY_H_INCLUDED__

#include <iostream>
/// 3 slices per adult/ if its sunday thru thursday and 5pm or later, then adults probably won't stay long, so you only need enough for 2/3 the adults
/// 4 slices per teen
/// 2 slices per child
/// 10" pizzas have 6 slices
/// 12" pizzas have 8 slices
/// 14" pizzas have 10 slices (most popular)
/// 16" pizzas have 12 slices
/// 18" pizzas have 14 slices
/// 1/3 cheese
/// 62% 0f 2/3 remaining slices are meat (75% of which is pepperoni, 25% sausage)
/// 48% Of 2/3 reamining are veggie (11% of which is mushroom)
/// if percentage values of number of slices are less than 1 large pizza, split into smaller pizzas
/// once percentages are calculated, if the percentage is less than a slice, No pizzas of that type need to be ordered.
/// if remaining slices are less than 5, no more pizzas need to be ordered of that type.
/// 16" and 18" are reserved for parties over 50
class party{
    std::string name;
    int adults;
    int teens;
    int children;
    int dayoftheWeek;
    int timeOfDay;
    int** deets;

public:
    party(){
        name = "";
        adults=0;
        teens=0;
        children=0;
        dayoftheWeek=0;
        timeOfDay=0;
        deets = new int*[5];
        for(int i=0; i<5; i++){
            deets[i] = new int[5];
        }
        for(int i=0; i<5;i++){
            for(int j=0; j<5; j++){
                deets[i][j]=0;
            }
        }

        /*
        adults
        teens
        children
        ten
        twelve
        fourteen
        sixteen
        eighteen
        cheese
        pepperoni
        sausage
        veggie
        mushroom
        dayoftheWeek
        timeOfDay
        */
    };
    ~party(){
        delete deets;
    }
    void print(std::ostream& out);
    void printName(std::ostream& out);
    void readLine(std::istream& in);
    void writeLine(std::ostream& out);

    void setName(std::string Name){
        this->name = Name;
    }
    std::string getName(){
        return name;
    }
    void setAdults(int adults){
        this->adults=adults;
    }
    int getAdults(){
        return adults;
    }
    void setTeens(int teens){
        this->teens=teens;
    }
    int getTeens(){
        return teens;
    }
    void setChildren(int children){
        this->children=children;
    }
    int getChildren(){
        return children;
    }


    void setCheese(int num, int pieIndex){
        deets[0][pieIndex]+=num;
    }
    int getCheese(int pieIndex){
        return deets[0][pieIndex];
    }
    void setPepperoni(int num, int pieIndex){
        deets[1][pieIndex]+=num;
    }
    int getPepperoni(int pieIndex){
        return deets[1][pieIndex];
    }
    void setSausage(int num, int pieIndex){
        deets[2][pieIndex]+=num;
    }
    int getSausage(int pieIndex){
        return deets[2][pieIndex];
    }
    void setVeggie(int num, int pieIndex){
        deets[3][pieIndex]+=num;
    }
    int getVeggie(int pieIndex){
        return deets[3][pieIndex];
    }
    void setMushroom(int num, int pieIndex){
        deets[4][pieIndex]+=num;
    }
    int getMushroom(int pieIndex){
        return deets[4][pieIndex];
    }
    void setDayOftheWeek(int day){
        dayoftheWeek=day;
    }
    int getDayOftheWeek(){
        return dayoftheWeek;
    }
    void setTimeOfDay(int time){
        timeOfDay=time;
    }
    int getTimeOfDay(){
        return timeOfDay;
    }

    int getTotalAttend(){
        return adults+teens+children;
    }

};

///does all of the calculations and function calls to accomplish it
void calcPizzas(party& Party);

#endif // __PARTY_H_INCLUDED__
