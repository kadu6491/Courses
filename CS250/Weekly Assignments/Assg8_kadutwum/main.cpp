#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

int main()
{
    int n = 5000;

    int list1[n];
    int list2[n];

    int templist1;
    int templist2;
    int tempBub = 0;
    int tempSel = 0;
    int compBub = 0;
    int compSel = 0;


    srand(time(0));



    for(int i= 0; i < n; i++)
    {
        int temp = rand();
        list1[i] = temp;
        list2[i] = temp;
    }


    /// BUBBLE SORTING
    for(int i = 0; i < n - 1; i++)
    {


        for(int j = 0; j < n -i - 1; j++)
        {
            compBub++;

            if(list1[j] > list1[j+1])
            {
                templist1 = list1[j];
                list1[j] = list1[j+1];
                list1[j+1] = templist1;

                tempBub += 3;
            }

        }

    }


    /// SELECTION SORTING
    int mini;
    for (int i = 0; i < n - 1; i++)
    {
        mini = i;

        for (int j = i + 1; j < n; j++)
        {
           compSel++;

            if (list2[j] < list2[mini])
            {
                mini = j;
            }

        }
            templist2 = list2[i];
            list2[i] = list2[mini];
            list2[mini] = templist2;

            tempSel+=3;
    }

        //list2[i] = list1[i];
        cout << "Number of comparison---" << endl;
        cout << "  Bubble Sort    : " << compBub << endl;
        cout << "  Selection Sort : " << compSel << endl;
        cout << endl << endl;

        cout << "Number of Item assignments---" << endl;
        cout << "  Bubble Sort    : " << tempBub << endl;
        cout << "  Selection Sort : " << tempSel << endl;


    return 0;
}
