#include "words.h"
#include <cstdlib>
#include <sstream>

using namespace std;

int numWords;

string* words;

void initializeWords (int MaxWords)
{
  words = new string[MaxWords];
  numWords = 0;
}

void addText (std::string lineOfText)
{
  istringstream in (lineOfText);
  while (in >> words[numWords])
    ++numWords;
}


int getNumberOfWords()
{
  return numWords;
}

int getNumberOfSentences()
{
  int numSentences = 0;
  for (int i = 0; i < numWords; ++i)
    {
      char lastChar = words[i][words[i].size()-1];
      if (lastChar == '.' || lastChar == '?' || lastChar == '!')
	++numSentences;
    }
  return numSentences;
}


int getNumberOfAlphabeticCharacters()
{
  int numChars = 0;
  for (int i = 0; i < numWords; ++i)
    {
      for (int k = 0; k < words[i].size(); ++k)
	{
	  char c = words[i][k];
	  if ((c  >= 'A' && c <= 'Z') || (c  >= 'a' && c <= 'z'))
	    ++numChars;
	}
    }
  return numChars;
}



