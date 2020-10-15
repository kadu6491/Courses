
#ifndef studentData_h_included
#define studentData_h_included
#include <iostream>

struct  student
{
    std::string studentName;
    int studentID;
    int course1;
    int course2;
    int course3;
    
    student()
    {
        studentName="";
        course1=0;
        course2=0;
        course3=0;
    }
    student( std::string studentName_, int studentID_, int course1_, int course2_, int course3_)
    {
        studentName=studentName_;
        studentID=studentID_;
        course1=course1_;
        course2=course2_;
        course3=course3_;
        
    }
    
    double studentAverage();
    bool checkIfGreater(student studentRecord );
    char academicSituation();
};


student * readDataFromFile(student * studentList, int & numberOfStudents);
void printDataToFile(student * studentList, int numberOfStudents);
student * sortStudentData(student * studentList, int numberOfStudents);

#endif /* studentData_h */
