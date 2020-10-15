#include <iostream>
#include <cmath>
#include <fstream>
#include "arrayUtils.h"

using namespace std;

// Determine if a data set is skewed
void skew ()
{
  ///Measure how bad our data is skewed
    ///* read input
        ///** declare the input variables
        float data[10000];
        int inData=0;
        ///** read the values from the text file
            ///*** include library
            ifstream infile;
            infile.open("input.txt");

            ///*** do a loop to read the data
            while(infile >> data[inData])
            {

                inData++;
            }

    ///* Calculate the skew
        ///** Calculating the mean
            ///*** Declaring a variable to store mean inside
            double skew = 0;
            double mean = 0;
            double sumOfData = 0;
            ///*** Do a for loop to calculate the summation of the data set
            for(int i = 0; i < inData; i++)
            {
                sumOfData += data[i];
            }
        ///** calculating stdev
            ///*** declaring a variable to store stdev
            double stdev = 0;
            double sumOfSquare = 0;

            ///*** do a for loop to calculate the summation of squared data
            for(int i = 0; i<inData; i++)
            {
                sumOfSquare += pow(data[i], 2); /// or data[i] * data[i];
            }

            stdev = sqrt((sumOfSquare - inData * mean * mean) / (double) (inData - 1) );
        ///** Calculating median
            ///*** Declaring a variable to store median
            double median = 0;
            ///*** sorting the data set
            insertionSort(data, inData);
            ///*** if the number of elements is odd pick the middle element
            if (inData % 2 == 1)
            {
                median = data[inData / 2];
            }
            ///*** else the average of two middle elements
            else {
                median = (data[inData / 2] + data[inData / 2 - 1]) / 2.0;
            }

        ///*** calculating the final skew
        skew = 3.0 * (mean - median) / stdev;

    ///* display the result
        ///** if is badly skewed print out the proper statement
            if(skew > 1 || skew < -1)
            {
                cout << "badly skew" << endl;
            }
        ///** else
            else {
                cout << "it is normal" << endl;
            }
}


int main()
{
  skew();
  return 0;
}

