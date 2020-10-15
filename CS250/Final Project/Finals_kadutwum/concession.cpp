#include <iostream>
#include <fstream>
#include <string>

#include "arena.h";

using namespace std;

void standInfo(ifstream& inCon)
{
    inCon.open("concession.txt");

    int num = 1;
    concession con[num];

    for (int i = 0; i < num; i++)
    {
        inCon >> con[i].NumHotDogs >> con[i].HotDogPrice;
        inCon >> con[i].NumPeanuts >> con[i].PeanutsPrice;
        inCon >> con[i].NumBurger >> con[i].BurgerPrice;
        inCon >> con[i].NumDrinks >> con[i].DrinkPrice;
        inCon >> con[i].NumBeer >> con[i].BeerPrice;
        inCon >> con[i].NumJersey >> con[i].JerseyPrice;

        cout << "Hot Dog Inventory: " << con[i].NumHotDogs << endl;
        cout << "Price: $"<< con[i].HotDogPrice << endl << endl;

        cout << "Peanut Inventory: " << con[i].NumPeanuts << endl;
        cout << "Price: $" << con[i].PeanutsPrice << endl << endl;

        cout << "Burger Inventory: " << con[i].NumBurger << endl;
        cout << "Price: $" << con[i].BurgerPrice << endl << endl;

        cout << "Soft Drinks Inventory: " << con[i].NumDrinks << endl;
        cout << "Price: $" << con[i].DrinkPrice << endl << endl;

        cout << "Beer Inventory: " << con[i].NumBeer << endl;
        cout << "Price: $" << con[i].BeerPrice << endl << endl;

        cout << "Jersey Inventory: " << con[i].NumJersey << endl;
        cout << "Price: $" << con[i].JerseyPrice << endl << endl << endl;

        mainMenu();

    }
}


void sell(ifstream& inCon, ofstream& outCon)
{
    inCon.open("concession.txt");

    int num = 1;
    concession con;
    int sell;
    int qty;


    inCon >> con.NumHotDogs >> con.HotDogPrice;
    inCon >> con.NumPeanuts >> con.PeanutsPrice;
    inCon >> con.NumBurger >> con.BurgerPrice;
    inCon >> con.NumDrinks >> con.DrinkPrice;
    inCon >> con.NumBeer >> con.BeerPrice;
    inCon >> con.NumJersey >> con.JerseyPrice;

    cout << "1. Hot Dog" << endl;
    cout << "2. Peanuts" << endl;
    cout << "3. Burger" << endl;
    cout << "4. Soft drink" << endl;
    cout << "5. Beer" << endl;
    cout << "6. Jersey" << endl << endl;

    cout << "Enter Item you want to sell" << endl;
    cin >> sell;
    cout << endl;

    cout << "Quality you selling: " << endl;
    cin >> qty;
    cout << endl << endl;

    inCon.close();

    outCon.open("concession.txt");

    if (sell == 1)
    {
        /// con.NumHotDogs -= qty;
    }

    if (sell == 2)
    {

    }

    if (sell == 3)
    {

    }

    if (sell == 4)
    {

    }

    if (sell == 5)
    {

    }

    if (sell == 6)
    {

    }

    else {
        mainMenu();
    }

/*
    outCon << con.NumHotDogs << " " << con.HotDogPrice
           << con.NumPeanuts << " " << con.PeanutsPrice
           << con.NumBurger << " " << con.BurgerPrice
           << con.NumDrinks << " " << con.DrinkPrice
           << con.NumBeer << " " << con.BeerPrice
           << con.NumJersey << " " << con.JerseyPrice << endl;
*/
    mainMenu();

}

void concession::print(ifstream& inCon)
{
    inCon.open("concession.txt");

    concession con;
    ///double total = 0;

    inCon >> con.NumHotDogs >> con.HotDogPrice;
    inCon >> con.NumPeanuts >> con.PeanutsPrice;
    inCon >> con.NumBurger >> con.BurgerPrice;
    inCon >> con.NumDrinks >> con.DrinkPrice;
    inCon >> con.NumBeer >> con.BeerPrice;
    inCon >> con.NumJersey >> con.JerseyPrice;

    con.TotalSales = con.HotDogPrice + con.PeanutsPrice + con.BurgerPrice
            + con.DrinkPrice + con.BeerPrice + con.JerseyPrice;

    cout << "The total lifetime sales amount: $" << con.TotalSales << endl <<endl;

    mainMenu();
}

void order(ifstream& inCon, ofstream& outCon)
{
    int order;
    cout << "How many would you like to buy?" << endl;
    cin >> order;
    cout << endl << endl;

    mainMenu();
}

void cSubMain()
{
    ifstream inCon;
    ofstream outCon;

    concession myCon;

    int cChoose;

    cout << "1. Show concession stand info" << endl;
    cout << "2. Sell item" << endl;
    cout << "3. Print total lifetime sales amount" << endl;
    cout << "4. Order inventory" << endl;
    cout << "5. Main Menu" << endl << endl;

    cin >> cChoose;
    cout << endl << endl;

    if(cChoose == 1)
    {
        standInfo(inCon);
    }

    else if(cChoose == 2)
    {
        sell(inCon, outCon);
    }

    else if(cChoose == 3)
    {
        myCon.print(inCon);
    }

    else if(cChoose == 4)
    {
        order(inCon, outCon);
    }

    else
    {
        mainMenu();
    }
}

