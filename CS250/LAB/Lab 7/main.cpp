#include"studentData.h"

int main()
{
    student * studentList;
    int numberOfStudents=0;
    studentList=readDataFromFile(studentList, numberOfStudents);
    studentList=sortStudentData(studentList, numberOfStudents);
    printDataToFile(studentList, numberOfStudents);
    return 0;
}
