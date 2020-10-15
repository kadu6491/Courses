#include <string>
#include <sstream>
#include <iostream>

using namespace std;


void checkParagraphStart (int width, string inputLine, string& outputLine)
{
  if (inputLine.size() == 0)
    {
      if (outputLine.size() > 0)
	cout << outputLine << endl;
      outputLine = "";
      cout << endl;
    }
  else if (inputLine[0] == ' ')
    {
      if (outputLine.size() > 0)
	cout << outputLine << endl;
      outputLine = "";

      bool isEmpty = true;
      for (int i = 0; isEmpty && i < inputLine.size(); ++i)
	{
	  if (inputLine[i] != ' ')
	    isEmpty = false;
	}
      if (isEmpty)
	cout << endl;
      else
	{
	  for (int i = 0; i < inputLine.size() && inputLine[i] == ' '; ++i)
	    {
	      outputLine += ' ';
	    }
	}
    }
}


bool endsASentence (string word)
{
  if (word.size() > 0)
    {
      char c = word[word.size()-1];
      return (c == '.' || c == '!' || c == '?');
    }
  else
    return false;
}


void formatLine (int width, string inputLine, string& outputLine)
{
  checkParagraphStart (width, inputLine, outputLine);

  istringstream in (inputLine);
  string word;
  bool sentenceEnded = false;
  while (in >> word)
    {
      string spacing = (sentenceEnded)? "  " : " ";
      if (word.size() + outputLine.size() + spacing.size() > width)
	{
	  cout << outputLine << endl;
	  outputLine = "";
	}
      if (outputLine.size() > 0)
	  outputLine += spacing;
      outputLine += word;
      sentenceEnded = endsASentence(word);
    }
}


void format (int width)
{
  string inputLine;
  string outputLine;
  getline (cin, inputLine);
  while (cin)
    {
      formatLine (width, inputLine, outputLine);
      getline (cin, inputLine);
    }
  if (outputLine.size() > 0)
    cout << outputLine << endl;
}



int main (int argc, char** argv)
{
  // get width of output lines from 1st line of cin
  int lineWidth;
  string line;
  cin >> lineWidth;
  if (!cin)
    return 1;
  getline (cin, line);
  if (!cin)
    return 1;

  format (lineWidth);

  return 0;
}
