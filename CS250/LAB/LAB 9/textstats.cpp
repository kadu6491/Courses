#include <iomanip>
#include <iostream>
#include <string>

#include "words.h"


using namespace std;


int main (int argc, char** argv)
{
  initializeWords(10000);

  cout << "Enter some text, terminated by an empty line:" << endl;
  string line;
  getline (cin, line);
  while (line != "")
    {
      addText (line);
      getline (cin, line);
    }

  cout << "There are " << getNumberOfWords()
       << " words in " << getNumberOfSentences()
       << " sentences." << endl;

  double avg = ((double)getNumberOfAlphabeticCharacters()) / getNumberOfWords();

  cout << "The average word length is "
       << setprecision(1) << fixed << avg
       << " characters." << endl;


  return 0;
}
