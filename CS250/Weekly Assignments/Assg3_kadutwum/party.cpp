#include "party.h"
#include <iostream>
#include <sstream>
#include <string>


int calcSlices(party& Party);


///prints party info
void party::print(std::ostream& out){
    std::string titles[10]={"Cheese","Pepperoni","Sausage","Veggie","Mushroom","10\"","12\"","14\"","16\"","18\""};
    std::string days[7]={"Sun","Mon","Tues","Wed","Thur","Fri","Sat"};

    out<<std::endl<<name<<std::endl;
    out<<"Total Attending: "<<getTotalAttend()<<std::endl;
    out<<'\t'<<"Day"<<"\t\t"<<days[dayoftheWeek-1]<<std::endl;

    if(timeOfDay>12){
        out<<'\t'<<"Time"<<"\t\t"<<timeOfDay-12<<"pm"<<std::endl;
    }
    else if(timeOfDay==12){
        out<<'\t'<<"Time"<<"\t\t"<<timeOfDay<<"pm"<<std::endl;
    }
    else if(timeOfDay==24){
        out<<'\t'<<"Time"<<"\t\t"<<timeOfDay-12<<"am"<<std::endl;
    }
    else{
        out<<'\t'<<"Time"<<"\t\t"<<timeOfDay<<"am"<<std::endl;
    }

    for(int i = 0; i<5; i++){
        bool PrintThis=false;
        std::stringstream ss;
        ss<<'\t'<<titles[i]<<std::endl;
        for(int j = 0; j<5; j++){
            if(deets[i][j]!=0){
                ss<<"\t\t\t"<<titles[5+j]<<'\t'<<deets[i][j]<<std::endl;
                PrintThis=true;
            }
        }
        if(PrintThis){
            out<<ss.str();
        }
    }
}

///outputs the name to a stream
void party::printName(std::ostream& out){
    out<<name;
}

///calculates Party Order
void calcPizzas(party& Party){
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
/// 38% Of 2/3 reamining are veggie (11% of which is mushroom)
/// if percentage values of number of slices are less than 1 large pizza, split into smaller pizzas
/// once percentages are calculated, if the percentage is less than a slice, No pizzas of that type need to be ordered.
/// if remaining slices are less than 3, no more pizzas need to be ordered of that type.
/// 16" and 18" are reserved for parties over 50

    double numSlices=calcSlices(Party);
    int pizzaSizes[5]={6,8,10,12,14};
    int index = 2;

    if((Party.getAdults()+ Party.getTeens()+ Party.getChildren())>=50){
        index = 4;
    }
    int che = 0.33*numSlices;
    int pepp = 0.75*(0.62*(numSlices-che));
    int sau = (0.62*(numSlices-che))-pepp;
    int mush = 0.11*(0.38*(numSlices-che));
    int vegg = (0.38*(numSlices-che))-mush;

    for(int i = index; i>0; i--){

        while(che>=pizzaSizes[i]){

            che=che-pizzaSizes[i];
            Party.setCheese(1,i);
        }
    }

    if(che>2){
        Party.setCheese(1,0);
    }

    for(int i = index; i>0; i--){
        while(pepp>=pizzaSizes[i]){
            pepp=pepp-pizzaSizes[i];
            Party.setPepperoni(1,i);
        }
    }

    if(pepp>2){
        Party.setPepperoni(1,0);
    }

    for(int i = index; i>0; i--){
        while(sau>=pizzaSizes[i]){
            sau=sau-pizzaSizes[i];
            Party.setSausage(1,i);
        }
    }

    if(sau>2){
        Party.setSausage(1,0);
    }

    for(int i = index; i>0; i--){
        while(vegg>=pizzaSizes[i]){
            vegg=vegg-pizzaSizes[i];
            Party.setVeggie(1,i);
        }
    }

    if(vegg>2){
        Party.setVeggie(1,0);
    }

    for(int i = index; i>0; i--){
        while(mush>=pizzaSizes[i]){
            mush=mush-pizzaSizes[i];
            Party.setMushroom(1,i);
        }
    }

    if(mush>2){
        Party.setMushroom(1,0);
    }

}

///Reads from stream
void party::readLine(std::istream& in){
    getline(in, name, ':');
    in>>adults>>teens>>children>>dayoftheWeek>>timeOfDay;
    for(int i=0; i<5; i++){
        for(int j = 0; j < 5; j++){
            in>>deets[i][j];
        }
    }
    in.ignore();
}

///write instance to stream
void party::writeLine(std::ostream& out){
    out<<name<<':'<<adults<<' '<<teens<<' '<<children<<' '<<dayoftheWeek<<' '<<timeOfDay;
    for(int i=0; i<5; i++){
        for(int j=0; j<5; j++)
        out<<' '<<deets[i][j];
    }
    out<<std::endl;
}

///calc # slices
int calcSlices(party& Party){
    int numSlices = 0;
    if(Party.getTimeOfDay() >= 17 && Party.getDayOftheWeek()<6){
        numSlices += 3 * (00.6667 * Party.getAdults());
    }
    else{
        numSlices += 3 * Party.getAdults();
    }

    numSlices += 4 * Party.getTeens();
    numSlices += 2 * Party.getChildren();
    return numSlices;
}
