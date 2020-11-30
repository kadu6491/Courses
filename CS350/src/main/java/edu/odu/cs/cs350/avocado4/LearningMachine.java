package edu.odu.cs.cs350.avocado4;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map.Entry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.RBFKernel;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Instance;
import java.io.InputStream;

/**
 * LearningMachine class
 * Trains the learning machine, and classifies String data based on the trained learning machine's data
 */
public class LearningMachine {
	
	/**
	 * Main function
	 * Reads in training data and calls the train(String text) function
	 * 
	 * @param args Argument required for driver method
	 * @throws FileNotFoundException File not found error
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//Reads the training data file and saves it to a string
		File inputFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + "trainingDataWithPER.txt");
		Scanner scan = new Scanner(inputFile);
		StringBuffer buf = new StringBuffer();
		while(scan.hasNext())
		{
			buf.append(scan.nextLine());
			if(scan.hasNext()) {
				buf.append("\n");
			}
		}
		scan.close();
		
		String text = buf.toString();
		
		//Trains the machine given the input string
		train(text);

	}

	/**
	 * Trains the learning machine with input String data provided 
	 * 
	 * @param textBlocks Provided input of one or more textblocks
	 */
	public static void train(String textBlocks) {
		
		//Add the attributes, which includes the features of the 11 token window
		String[] booleanNames = {"true", "false", "null"};
		Attribute dictionary0 = new Attribute("dictionary0", Arrays.asList(booleanNames));
		Attribute initial0 = new Attribute("initial0", Arrays.asList(booleanNames));
		Attribute honorific0 = new Attribute("honorific0", Arrays.asList(booleanNames));
		Attribute prefix0 = new Attribute("prefix0", Arrays.asList(booleanNames));
		Attribute suffix0 = new Attribute("suffix0", Arrays.asList(booleanNames));
		Attribute npname0 = new Attribute("npname0", Arrays.asList(booleanNames));
		Attribute stop0 = new Attribute("stop0", Arrays.asList(booleanNames));
		String[] posNames = {"ARTICLE","CONJUNCTION","PERIOD","COMMA","HYPHEN","OTHER","null"};
		Attribute pos0 = new Attribute("pos0", Arrays.asList(posNames));
		String[]lexNames = {"NUMBER","PUNCTUATION","CAPLETTER","CAPITAL","ALLCAPS","NEW_LINE","OTHER","null"};
		Attribute lex0 = new Attribute("lex0", Arrays.asList(lexNames));
		Attribute commonFirst0 = new Attribute("commonFirst0", Arrays.asList(booleanNames));
		Attribute first0 = new Attribute("first0", Arrays.asList(booleanNames));
		Attribute commonLast0 = new Attribute("commonLast0", Arrays.asList(booleanNames));
		Attribute last0 = new Attribute("last0", Arrays.asList(booleanNames));
		Attribute cityState0 = new Attribute("cityState0", Arrays.asList(booleanNames));
		Attribute country0 = new Attribute("country0", Arrays.asList(booleanNames));
		Attribute place0 = new Attribute("place0", Arrays.asList(booleanNames));
		String[] classificationNames = {"start","end","continue","both","none","null"};
		Attribute classification0 = new Attribute("classification0", Arrays.asList(classificationNames));
		
		Attribute dictionary1 = new Attribute("dictionary1", Arrays.asList(booleanNames));
		Attribute initial1 = new Attribute("initial1", Arrays.asList(booleanNames));
		Attribute honorific1 = new Attribute("honorific1", Arrays.asList(booleanNames));
		Attribute prefix1 = new Attribute("prefix1", Arrays.asList(booleanNames));
		Attribute suffix1 = new Attribute("suffix1", Arrays.asList(booleanNames));
		Attribute npname1 = new Attribute("npname1", Arrays.asList(booleanNames));
		Attribute stop1 = new Attribute("stop1", Arrays.asList(booleanNames));
		Attribute pos1 = new Attribute("pos1", Arrays.asList(posNames));
		Attribute lex1 = new Attribute("lex1", Arrays.asList(lexNames));
		Attribute commonFirst1 = new Attribute("commonFirst1", Arrays.asList(booleanNames));
		Attribute first1 = new Attribute("first1", Arrays.asList(booleanNames));
		Attribute commonLast1 = new Attribute("commonLast1", Arrays.asList(booleanNames));
		Attribute last1 = new Attribute("last1", Arrays.asList(booleanNames));
		Attribute cityState1 = new Attribute("cityState1", Arrays.asList(booleanNames));
		Attribute country1 = new Attribute("country1", Arrays.asList(booleanNames));
		Attribute place1 = new Attribute("place1", Arrays.asList(booleanNames));
		Attribute classification1 = new Attribute("classification1", Arrays.asList(classificationNames));
		
		Attribute dictionary2 = new Attribute("dictionary2", Arrays.asList(booleanNames));
		Attribute initial2 = new Attribute("initial2", Arrays.asList(booleanNames));
		Attribute honorific2 = new Attribute("honorific2", Arrays.asList(booleanNames));
		Attribute prefix2 = new Attribute("prefix2", Arrays.asList(booleanNames));
		Attribute suffix2 = new Attribute("suffix2", Arrays.asList(booleanNames));
		Attribute npname2 = new Attribute("npname2", Arrays.asList(booleanNames));
		Attribute stop2 = new Attribute("stop2", Arrays.asList(booleanNames));
		Attribute pos2 = new Attribute("pos2", Arrays.asList(posNames));
		Attribute lex2 = new Attribute("lex2", Arrays.asList(lexNames));
		Attribute commonFirst2 = new Attribute("commonFirst2", Arrays.asList(booleanNames));
		Attribute first2 = new Attribute("first2", Arrays.asList(booleanNames));
		Attribute commonLast2 = new Attribute("commonLast2", Arrays.asList(booleanNames));
		Attribute last2 = new Attribute("last2", Arrays.asList(booleanNames));
		Attribute cityState2 = new Attribute("cityState2", Arrays.asList(booleanNames));
		Attribute country2 = new Attribute("country2", Arrays.asList(booleanNames));
		Attribute place2 = new Attribute("place2", Arrays.asList(booleanNames));
		Attribute classification2 = new Attribute("classification2", Arrays.asList(classificationNames));
		
		Attribute dictionary3 = new Attribute("dictionary3", Arrays.asList(booleanNames));
		Attribute initial3 = new Attribute("initial3", Arrays.asList(booleanNames));
		Attribute honorific3 = new Attribute("honorific3", Arrays.asList(booleanNames));
		Attribute prefix3 = new Attribute("prefix3", Arrays.asList(booleanNames));
		Attribute suffix3 = new Attribute("suffix3", Arrays.asList(booleanNames));
		Attribute npname3 = new Attribute("npname3", Arrays.asList(booleanNames));
		Attribute stop3 = new Attribute("stop3", Arrays.asList(booleanNames));
		Attribute pos3 = new Attribute("pos3", Arrays.asList(posNames));
		Attribute lex3 = new Attribute("lex3", Arrays.asList(lexNames));
		Attribute commonFirst3 = new Attribute("commonFirst3", Arrays.asList(booleanNames));
		Attribute first3 = new Attribute("first3", Arrays.asList(booleanNames));
		Attribute commonLast3 = new Attribute("commonLast3", Arrays.asList(booleanNames));
		Attribute last3 = new Attribute("last3", Arrays.asList(booleanNames));
		Attribute cityState3 = new Attribute("cityState3", Arrays.asList(booleanNames));
		Attribute country3 = new Attribute("country3", Arrays.asList(booleanNames));
		Attribute place3 = new Attribute("place3", Arrays.asList(booleanNames));
		Attribute classification3 = new Attribute("classification3", Arrays.asList(classificationNames));
		
		Attribute dictionary4 = new Attribute("dictionary4", Arrays.asList(booleanNames));
		Attribute initial4 = new Attribute("initial4", Arrays.asList(booleanNames));
		Attribute honorific4 = new Attribute("honorific4", Arrays.asList(booleanNames));
		Attribute prefix4 = new Attribute("prefix4", Arrays.asList(booleanNames));
		Attribute suffix4 = new Attribute("suffix4", Arrays.asList(booleanNames));
		Attribute npname4 = new Attribute("npname4", Arrays.asList(booleanNames));
		Attribute stop4 = new Attribute("stop4", Arrays.asList(booleanNames));
		Attribute pos4 = new Attribute("pos4", Arrays.asList(posNames));
		Attribute lex4 = new Attribute("lex4", Arrays.asList(lexNames));
		Attribute commonFirst4 = new Attribute("commonFirst4", Arrays.asList(booleanNames));
		Attribute first4 = new Attribute("first4", Arrays.asList(booleanNames));
		Attribute commonLast4 = new Attribute("commonLast4", Arrays.asList(booleanNames));
		Attribute last4 = new Attribute("last4", Arrays.asList(booleanNames));
		Attribute cityState4 = new Attribute("cityState4", Arrays.asList(booleanNames));
		Attribute country4 = new Attribute("country4", Arrays.asList(booleanNames));
		Attribute place4 = new Attribute("place4", Arrays.asList(booleanNames));
		Attribute classification4 = new Attribute("classification4", Arrays.asList(classificationNames));
		
		Attribute dictionary5 = new Attribute("dictionary5", Arrays.asList(booleanNames));
		Attribute initial5 = new Attribute("initial5", Arrays.asList(booleanNames));
		Attribute honorific5 = new Attribute("honorific5", Arrays.asList(booleanNames));
		Attribute prefix5 = new Attribute("prefix5", Arrays.asList(booleanNames));
		Attribute suffix5 = new Attribute("suffix5", Arrays.asList(booleanNames));
		Attribute npname5 = new Attribute("npname5", Arrays.asList(booleanNames));
		Attribute stop5 = new Attribute("stop5", Arrays.asList(booleanNames));
		Attribute pos5 = new Attribute("pos5", Arrays.asList(posNames));
		Attribute lex5 = new Attribute("lex5", Arrays.asList(lexNames));
		Attribute commonFirst5 = new Attribute("commonFirst5", Arrays.asList(booleanNames));
		Attribute first5 = new Attribute("first5", Arrays.asList(booleanNames));
		Attribute commonLast5 = new Attribute("commonLast5", Arrays.asList(booleanNames));
		Attribute last5 = new Attribute("last5", Arrays.asList(booleanNames));
		Attribute cityState5 = new Attribute("cityState5", Arrays.asList(booleanNames));
		Attribute country5 = new Attribute("country5", Arrays.asList(booleanNames));
		Attribute place5 = new Attribute("place5", Arrays.asList(booleanNames));
		Attribute classification5 = new Attribute("classification5", Arrays.asList(classificationNames));
		
		Attribute dictionary6 = new Attribute("dictionary6", Arrays.asList(booleanNames));
		Attribute initial6 = new Attribute("initial6", Arrays.asList(booleanNames));
		Attribute honorific6 = new Attribute("honorific6", Arrays.asList(booleanNames));
		Attribute prefix6 = new Attribute("prefix6", Arrays.asList(booleanNames));
		Attribute suffix6 = new Attribute("suffix6", Arrays.asList(booleanNames));
		Attribute npname6 = new Attribute("npname6", Arrays.asList(booleanNames));
		Attribute stop6 = new Attribute("stop6", Arrays.asList(booleanNames));
		Attribute pos6 = new Attribute("pos6", Arrays.asList(posNames));
		Attribute lex6 = new Attribute("lex6", Arrays.asList(lexNames));
		Attribute commonFirst6 = new Attribute("commonFirst6", Arrays.asList(booleanNames));
		Attribute first6 = new Attribute("first6", Arrays.asList(booleanNames));
		Attribute commonLast6 = new Attribute("commonLast6", Arrays.asList(booleanNames));
		Attribute last6 = new Attribute("last6", Arrays.asList(booleanNames));
		Attribute cityState6 = new Attribute("cityState6", Arrays.asList(booleanNames));
		Attribute country6 = new Attribute("country6", Arrays.asList(booleanNames));
		Attribute place6 = new Attribute("place6", Arrays.asList(booleanNames));
		Attribute classification6 = new Attribute("classification6", Arrays.asList(classificationNames));
		
		Attribute dictionary7 = new Attribute("dictionary7", Arrays.asList(booleanNames));
		Attribute initial7 = new Attribute("initial7", Arrays.asList(booleanNames));
		Attribute honorific7 = new Attribute("honorific7", Arrays.asList(booleanNames));
		Attribute prefix7 = new Attribute("prefix7", Arrays.asList(booleanNames));
		Attribute suffix7 = new Attribute("suffix7", Arrays.asList(booleanNames));
		Attribute npname7 = new Attribute("npname7", Arrays.asList(booleanNames));
		Attribute stop7 = new Attribute("stop7", Arrays.asList(booleanNames));
		Attribute pos7 = new Attribute("pos7", Arrays.asList(posNames));
		Attribute lex7 = new Attribute("lex7", Arrays.asList(lexNames));
		Attribute commonFirst7 = new Attribute("commonFirst7", Arrays.asList(booleanNames));
		Attribute first7 = new Attribute("first7", Arrays.asList(booleanNames));
		Attribute commonLast7 = new Attribute("commonLast7", Arrays.asList(booleanNames));
		Attribute last7 = new Attribute("last7", Arrays.asList(booleanNames));
		Attribute cityState7 = new Attribute("cityState7", Arrays.asList(booleanNames));
		Attribute country7 = new Attribute("country7", Arrays.asList(booleanNames));
		Attribute place7 = new Attribute("place7", Arrays.asList(booleanNames));
		Attribute classification7 = new Attribute("classification7", Arrays.asList(classificationNames));
		
		Attribute dictionary8 = new Attribute("dictionary8", Arrays.asList(booleanNames));
		Attribute initial8 = new Attribute("initial8", Arrays.asList(booleanNames));
		Attribute honorific8 = new Attribute("honorific8", Arrays.asList(booleanNames));
		Attribute prefix8 = new Attribute("prefix8", Arrays.asList(booleanNames));
		Attribute suffix8 = new Attribute("suffix8", Arrays.asList(booleanNames));
		Attribute npname8 = new Attribute("npname8", Arrays.asList(booleanNames));
		Attribute stop8 = new Attribute("stop8", Arrays.asList(booleanNames));
		Attribute pos8 = new Attribute("pos8", Arrays.asList(posNames));
		Attribute lex8 = new Attribute("lex8", Arrays.asList(lexNames));
		Attribute commonFirst8 = new Attribute("commonFirst8", Arrays.asList(booleanNames));
		Attribute first8 = new Attribute("first8", Arrays.asList(booleanNames));
		Attribute commonLast8 = new Attribute("commonLast8", Arrays.asList(booleanNames));
		Attribute last8 = new Attribute("last8", Arrays.asList(booleanNames));
		Attribute cityState8 = new Attribute("cityState8", Arrays.asList(booleanNames));
		Attribute country8 = new Attribute("country8", Arrays.asList(booleanNames));
		Attribute place8 = new Attribute("place8", Arrays.asList(booleanNames));
		Attribute classification8 = new Attribute("classification8", Arrays.asList(classificationNames));
		
		Attribute dictionary9 = new Attribute("dictionary9", Arrays.asList(booleanNames));
		Attribute initial9 = new Attribute("initial9", Arrays.asList(booleanNames));
		Attribute honorific9 = new Attribute("honorific9", Arrays.asList(booleanNames));
		Attribute prefix9 = new Attribute("prefix9", Arrays.asList(booleanNames));
		Attribute suffix9 = new Attribute("suffix9", Arrays.asList(booleanNames));
		Attribute npname9 = new Attribute("npname9", Arrays.asList(booleanNames));
		Attribute stop9 = new Attribute("stop9", Arrays.asList(booleanNames));
		Attribute pos9 = new Attribute("pos9", Arrays.asList(posNames));
		Attribute lex9 = new Attribute("lex9", Arrays.asList(lexNames));
		Attribute commonFirst9 = new Attribute("commonFirst9", Arrays.asList(booleanNames));
		Attribute first9 = new Attribute("first9", Arrays.asList(booleanNames));
		Attribute commonLast9 = new Attribute("commonLast9", Arrays.asList(booleanNames));
		Attribute last9 = new Attribute("last9", Arrays.asList(booleanNames));
		Attribute cityState9 = new Attribute("cityState9", Arrays.asList(booleanNames));
		Attribute country9 = new Attribute("country9", Arrays.asList(booleanNames));
		Attribute place9 = new Attribute("place9", Arrays.asList(booleanNames));
		Attribute classification9 = new Attribute("classification9", Arrays.asList(classificationNames));
		
		Attribute dictionary10 = new Attribute("dictionary10", Arrays.asList(booleanNames));
		Attribute initial10 = new Attribute("initial10", Arrays.asList(booleanNames));
		Attribute honorific10 = new Attribute("honorific10", Arrays.asList(booleanNames));
		Attribute prefix10 = new Attribute("prefix10", Arrays.asList(booleanNames));
		Attribute suffix10 = new Attribute("suffix10", Arrays.asList(booleanNames));
		Attribute npname10 = new Attribute("npname10", Arrays.asList(booleanNames));
		Attribute stop10 = new Attribute("stop10", Arrays.asList(booleanNames));
		Attribute pos10 = new Attribute("pos10", Arrays.asList(posNames));
		Attribute lex10 = new Attribute("lex10", Arrays.asList(lexNames));
		Attribute commonFirst10 = new Attribute("commonFirst10", Arrays.asList(booleanNames));
		Attribute first10 = new Attribute("first10", Arrays.asList(booleanNames));
		Attribute commonLast10 = new Attribute("commonLast10", Arrays.asList(booleanNames));
		Attribute last10 = new Attribute("last10", Arrays.asList(booleanNames));
		Attribute cityState10 = new Attribute("cityState10", Arrays.asList(booleanNames));
		Attribute country10 = new Attribute("country10", Arrays.asList(booleanNames));
		Attribute place10 = new Attribute("place10", Arrays.asList(booleanNames));
		Attribute classification10 = new Attribute("classification10", Arrays.asList(classificationNames));
		
		//Create attributes for the machine
		ArrayList<Attribute> attrInfo = new ArrayList<Attribute>();
		attrInfo.add(dictionary0);
		attrInfo.add(initial0);
		attrInfo.add(honorific0);
		attrInfo.add(prefix0);
		attrInfo.add(suffix0);
		attrInfo.add(npname0);
		attrInfo.add(stop0);
		attrInfo.add(pos0);
		attrInfo.add(lex0);
		attrInfo.add(commonFirst0);
		attrInfo.add(first0);
		attrInfo.add(commonLast0);
		attrInfo.add(last0);
		attrInfo.add(cityState0);
		attrInfo.add(country0);
		attrInfo.add(place0);
		attrInfo.add(classification0);
		
		attrInfo.add(dictionary1);
		attrInfo.add(initial1);
		attrInfo.add(honorific1);
		attrInfo.add(prefix1);
		attrInfo.add(suffix1);
		attrInfo.add(npname1);
		attrInfo.add(stop1);
		attrInfo.add(pos1);
		attrInfo.add(lex1);
		attrInfo.add(commonFirst1);
		attrInfo.add(first1);
		attrInfo.add(commonLast1);
		attrInfo.add(last1);
		attrInfo.add(cityState1);
		attrInfo.add(country1);
		attrInfo.add(place1);
		attrInfo.add(classification1);
		
		attrInfo.add(dictionary2);
		attrInfo.add(initial2);
		attrInfo.add(honorific2);
		attrInfo.add(prefix2);
		attrInfo.add(suffix2);
		attrInfo.add(npname2);
		attrInfo.add(stop2);
		attrInfo.add(pos2);
		attrInfo.add(lex2);
		attrInfo.add(commonFirst2);
		attrInfo.add(first2);
		attrInfo.add(commonLast2);
		attrInfo.add(last2);
		attrInfo.add(cityState2);
		attrInfo.add(country2);
		attrInfo.add(place2);
		attrInfo.add(classification2);
		
		attrInfo.add(dictionary3);
		attrInfo.add(initial3);
		attrInfo.add(honorific3);
		attrInfo.add(prefix3);
		attrInfo.add(suffix3);
		attrInfo.add(npname3);
		attrInfo.add(stop3);
		attrInfo.add(pos3);
		attrInfo.add(lex3);
		attrInfo.add(commonFirst3);
		attrInfo.add(first3);
		attrInfo.add(commonLast3);
		attrInfo.add(last3);
		attrInfo.add(cityState3);
		attrInfo.add(country3);
		attrInfo.add(place3);
		attrInfo.add(classification3);
		
		attrInfo.add(dictionary4);
		attrInfo.add(initial4);
		attrInfo.add(honorific4);
		attrInfo.add(prefix4);
		attrInfo.add(suffix4);
		attrInfo.add(npname4);
		attrInfo.add(stop4);
		attrInfo.add(pos4);
		attrInfo.add(lex4);
		attrInfo.add(commonFirst4);
		attrInfo.add(first4);
		attrInfo.add(commonLast4);
		attrInfo.add(last4);
		attrInfo.add(cityState4);
		attrInfo.add(country4);
		attrInfo.add(place4);
		attrInfo.add(classification4);
		
		attrInfo.add(dictionary5);
		attrInfo.add(initial5);
		attrInfo.add(honorific5);
		attrInfo.add(prefix5);
		attrInfo.add(suffix5);
		attrInfo.add(npname5);
		attrInfo.add(stop5);
		attrInfo.add(pos5);
		attrInfo.add(lex5);
		attrInfo.add(commonFirst5);
		attrInfo.add(first5);
		attrInfo.add(commonLast5);
		attrInfo.add(last5);
		attrInfo.add(cityState5);
		attrInfo.add(country5);
		attrInfo.add(place5);
		attrInfo.add(classification5);	
		
		attrInfo.add(dictionary6);
		attrInfo.add(initial6);
		attrInfo.add(honorific6);
		attrInfo.add(prefix6);
		attrInfo.add(suffix6);
		attrInfo.add(npname6);
		attrInfo.add(stop6);
		attrInfo.add(pos6);
		attrInfo.add(lex6);
		attrInfo.add(commonFirst6);
		attrInfo.add(first6);
		attrInfo.add(commonLast6);
		attrInfo.add(last6);
		attrInfo.add(cityState6);
		attrInfo.add(country6);
		attrInfo.add(place6);
		attrInfo.add(classification6);
		
		attrInfo.add(dictionary7);
		attrInfo.add(initial7);
		attrInfo.add(honorific7);
		attrInfo.add(prefix7);
		attrInfo.add(suffix7);
		attrInfo.add(npname7);
		attrInfo.add(stop7);
		attrInfo.add(pos7);
		attrInfo.add(lex7);
		attrInfo.add(commonFirst7);
		attrInfo.add(first7);
		attrInfo.add(commonLast7);
		attrInfo.add(last7);
		attrInfo.add(cityState7);
		attrInfo.add(country7);
		attrInfo.add(place7);
		attrInfo.add(classification7);
		
		attrInfo.add(dictionary8);
		attrInfo.add(initial8);
		attrInfo.add(honorific8);
		attrInfo.add(prefix8);
		attrInfo.add(suffix8);
		attrInfo.add(npname8);
		attrInfo.add(stop8);
		attrInfo.add(pos8);
		attrInfo.add(lex8);
		attrInfo.add(commonFirst8);
		attrInfo.add(first8);
		attrInfo.add(commonLast8);
		attrInfo.add(last8);
		attrInfo.add(cityState8);
		attrInfo.add(country8);
		attrInfo.add(place8);
		attrInfo.add(classification8);
		
		attrInfo.add(dictionary9);
		attrInfo.add(initial9);
		attrInfo.add(honorific9);
		attrInfo.add(prefix9);
		attrInfo.add(suffix9);
		attrInfo.add(npname9);
		attrInfo.add(stop9);
		attrInfo.add(pos9);
		attrInfo.add(lex9);
		attrInfo.add(commonFirst9);
		attrInfo.add(first9);
		attrInfo.add(commonLast9);
		attrInfo.add(last9);
		attrInfo.add(cityState9);
		attrInfo.add(country9);
		attrInfo.add(place9);
		attrInfo.add(classification9);
		
		attrInfo.add(dictionary10);
		attrInfo.add(initial10);
		attrInfo.add(honorific10);
		attrInfo.add(prefix10);
		attrInfo.add(suffix10);
		attrInfo.add(npname10);
		attrInfo.add(stop10);
		attrInfo.add(pos10);
		attrInfo.add(lex10);
		attrInfo.add(commonFirst10);
		attrInfo.add(first10);
		attrInfo.add(commonLast10);
		attrInfo.add(last10);
		attrInfo.add(cityState10);
		attrInfo.add(country10);
		attrInfo.add(place10);
		attrInfo.add(classification10);
		
		final int numberOfAttributes = attrInfo.size();
		
		//Gets the actual feature data from the textBlocks
		List<String> trainingData = getTrainingData(textBlocks);
		
		/*
		List<String> trainingData =  new ArrayList<>();

		try {
			File myObj = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                    + File.separator + "resources" + File.separator + "trainedData.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
                trainingData.add(myReader.nextLine());
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		*/
		
		Instances training = new Instances("TrainingData", attrInfo, trainingData.size());
		
		
		//Define the attribute to be predicted
		training.setClass(classification5);
		
		//loops through each training data example, creates an instance, and sets the instance's values
		System.err.println("Adding training data...");
		int count = 1;
		for (String data : trainingData) {
			String[] values = data.split(",");
			
			double[] instanceValues = new double[numberOfAttributes];
			
			for (int i = 0; i < values.length; i++) {
				instanceValues[i] = training.attribute(i).indexOfValue(values[i]);
			}
			
			Instance instance = new DenseInstance(1.0, instanceValues);
			instance.setDataset(training);
			
			//adds Instance to the Instances 
			training.add(instance);
			count++;
		}
		
		//Not sure about this - taken clear out of the weka office hours example
		//final double gamma = 0.01; //initial guess
		//final double C = 1.0; // initial guess
		String[] options = {"-N", "0", "-V", "-1"};
		
		
		final double[] GAMMA_values = {0.01, 0.1, 1};
		final double[] C_values = {0.5, 1.0, 2.0};
		
		HashMap<Map<Double, Double>, Double> highest = new HashMap<Map<Double, Double>, Double>();
		
		for(int i = 0; i < GAMMA_values.length; i++) {
			for(int j = 0; j < C_values.length; j++) {
				SMO svm = new SMO();
				try {
					svm.setOptions(options);
					svm.setKernel(new RBFKernel(training, 25007, GAMMA_values[i]));
					svm.setC(C_values[j]);

					//System.err.println("Starting building classifer now.");
					//svm.buildClassifier(training);
					//System.err.println("Successfully built with training data!");
	
					System.err.println("Setting up N-Fold Evaluation");
					
					// Set up for statistics collection
					Evaluation eval = new Evaluation(training);
					final int numberOfCrossClasses = 5;

					// Cross-validate
					eval.crossValidateModel(svm, training, numberOfCrossClasses, new Random(1));
					double score = eval.pctCorrect();
					
					Map<Double, Double> high = new HashMap<Double, Double>();
					high.put(GAMMA_values[i], C_values[j]);
					highest.put(high, score); 
					
					System.err.println ("On average, the machine was correct " + score + "% of the time.");
					
					//saves the model to trainedPNE.model in resources folder
					//weka.core.SerializationHelper.write(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
					//		+ File.separator + "resources" + File.separator + "trainedPNE.model", svm);
				}
				catch (Exception e) {
					System.err.println("Couldn't make the machine");
					e.printStackTrace();
				}
			}
		}
		
		//max percent
		double max = Collections.max(highest.values()); 
		double best_gamma = 0.01;
		double best_c = 1;
		System.out.println("max: " + max);
		
		//map to key
		boolean found = false;
		for(Map.Entry<Map<Double, Double>, Double> e : highest.entrySet()) {
			if(max == e.getValue().doubleValue()) {
				Map<Double, Double> map = e.getKey();
				for(Map.Entry<Double, Double> f : map.entrySet()) {
					best_gamma = f.getKey();
					best_c = f.getValue();
					System.out.println("Best Gamma/C : " + best_gamma + "," + best_c);
					found = true;
					break;
				}
			}
			if(found) break;
		}
		
		
		SMO svm = new SMO();
		try {
			svm.setOptions(options);
			svm.setKernel(new RBFKernel(training, 25007, best_gamma));
			svm.setC(best_c);

			System.err.println("Starting building final classifier now.");
			svm.buildClassifier(training);
			System.err.println("Successfully built with training data!");
			
			weka.core.SerializationHelper.write(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "trainedPNE_validation_included.model", svm);
		}
		catch (Exception e) {
			System.err.println("Couldn't make the machine");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Classifies tokens used to create the training data based on the presence of PER tags
	 * 
	 * @param tokensList Tokenized list of the provided input
	 */
	public static void trainClassifyToken(List<Token> tokensList) {
		tokensList.get(0).setClassification("none");
		tokensList.get(tokensList.size()-1).setClassification("none");
		
		for (int i=1; i<tokensList.size()-1; i++) {
			if (tokensList.get(i-1).getName().contains("<PER>")
					&& tokensList.get(i+1).getName().contains("</PER>")) {
				tokensList.get(i).setClassification("both");
			}
			else if (tokensList.get(i-1).getName().contains("<PER>")) {
				tokensList.get(i).setClassification("start");
			}
			else if (tokensList.get(i+1).getName().contains("</PER>")) {
				tokensList.get(i).setClassification("end");
			}
			else if (tokensList.get(i-1).getClassification().contains("start")
					|| tokensList.get(i-1).getClassification().contains("continue")) {
				tokensList.get(i).setClassification("continue");
			}
			else {
				tokensList.get(i).setClassification("none");
			}
		}
	}
	
	/**
	 * Removes PER tags from the training data to prevent the classification of those tokens
	 * @param tokensList Tokenized list of provided input
	 * @return List of tokens, minus any PER tags
	 */
	public static List<Token> removePERTags(List<Token> tokensList) {
		List<Token> newList = new ArrayList<Token>();
		for (Token t : tokensList) {
			if (!t.getName().contains("<PER>") && !t.getName().contains("</PER>")) {
				newList.add(t);
			}
		}
		
		return newList;
	}
	
	/**
	 * Removes NER tags from the training data to prevent the classification of those tokens
	 * @param tokensList Tokenized list of provided input
	 * @return List of tokens, minus any PER tags
	 */
	public static List<Token> removeNERTags(List<Token> tokensList) {
		List<Token> newList = new ArrayList<Token>();
		for (Token t : tokensList) {
			if (!t.getName().contains("<NER>") && !t.getName().contains("</NER>")) {
				newList.add(t);
			}
		}
		
		return newList;
	}
	  
	/**
	 * Creates the training data based on the provided String input.
	 * Training data is saved to a file and returned to the calling function
	 * @param textBlocks The provided input
	 * @return List<String> String list of the data of the Window for each token in the provided input
	 */
	private static List<String> getTrainingData(String textBlocks) {
		List<String> trainingData = new ArrayList<String>();
		List<String> splitBlocks = TextBlocks.parseTextBlocks(textBlocks);
		HashMap<String, Token> map = new HashMap<>();
		

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "trainedData.txt"));
			
			int count = 0;
			for (String block : splitBlocks) {
				System.err.println("Running Split Block Percentage: " + ((double)count/splitBlocks.size())*100 + "% done... (" + count + "/" + splitBlocks.size() + ")");
				
				List<Token> tokenList = Token.createTokenList(block);
				
				trainClassifyToken(tokenList);
				
				tokenList = removePERTags(tokenList);
				tokenList = removeNERTags(tokenList);
				
				List<Token> newTokenList = new ArrayList<Token>();
				
				for (Token t : tokenList) {
					if(map.containsKey(t.getName())) {
						Token temp = map.get(t.getName());
						t.setFeatures(temp.getFeatures());
					}
					else {
						newTokenList.add(t);
					}
				}
				
				//These will run on all tokens
				Cataloger.checkPunctuation(newTokenList);
				Cataloger.partOfSpeech(newTokenList);
				Cataloger.checkNewLine(newTokenList);
				
				//These will run on all word/numeric tokens
				Cataloger.checkDictionary(newTokenList);
				Cataloger.checkInitial(newTokenList);
				Cataloger.checkCapitalization(newTokenList);
				Cataloger.checkNumber(newTokenList);
				Cataloger.checkFirstNames(newTokenList);
				Cataloger.checkLastNames(newTokenList);
				Cataloger.checkCitiesStates(newTokenList);
				Cataloger.checkCountries(newTokenList);
				Cataloger.checkPlaces(newTokenList);
				
				for(Token t : newTokenList) {
					Cataloger.checkHonorific(t);
					Cataloger.checkPrefix(t);
					Cataloger.checkSuffix(t);
					Cataloger.checkNonPersonalProperName(t);
					Cataloger.checkStopList(t);
					
					map.put(t.getName(), t);
				}
				
				//For each token, adds the features set of the 11 token window as a single set of training data
				//Needs to be done separately from all of the checks because the tokens need to be fully populated to create the window
				for(int i = 0; i < tokenList.size(); i++) {
					Window win = new Window(tokenList, i);
					String s = win.getWindowFeatures();
					trainingData.add(s);
				    
				    writer.write(s);
				    writer.write("\n");
				}
				
				count++;
			}
			writer.close();
		}
		catch (Exception e) {
			System.err.println("Couldn't write data to file");
			e.printStackTrace();
		}
			
		return trainingData;
	  }
	
	
	/**
	 * Catalogues and classifies provided tokens using the trained learning machine
	 * Model is loaded in from a file
	 * 
	 * @param tokenList List of Token objects to be classified
	 */
	public static void classifyTokens(List<Token> tokenList) {
		
		//Add the attributes, which includes the features of the 11 token window
		String[] booleanNames = {"true", "false", "null"};
		Attribute dictionary0 = new Attribute("dictionary0", Arrays.asList(booleanNames));
		Attribute initial0 = new Attribute("initial0", Arrays.asList(booleanNames));
		Attribute honorific0 = new Attribute("honorific0", Arrays.asList(booleanNames));
		Attribute prefix0 = new Attribute("prefix0", Arrays.asList(booleanNames));
		Attribute suffix0 = new Attribute("suffix0", Arrays.asList(booleanNames));
		Attribute npname0 = new Attribute("npname0", Arrays.asList(booleanNames));
		Attribute stop0 = new Attribute("stop0", Arrays.asList(booleanNames));
		String[] posNames = {"ARTICLE","CONJUNCTION","PERIOD","COMMA","HYPHEN","OTHER","null"};
		Attribute pos0 = new Attribute("pos0", Arrays.asList(posNames));
		String[]lexNames = {"NUMBER","PUNCTUATION","CAPLETTER","CAPITAL","ALLCAPS","NEW_LINE","OTHER","null"};
		Attribute lex0 = new Attribute("lex0", Arrays.asList(lexNames));
		Attribute commonFirst0 = new Attribute("commonFirst0", Arrays.asList(booleanNames));
		Attribute first0 = new Attribute("first0", Arrays.asList(booleanNames));
		Attribute commonLast0 = new Attribute("commonLast0", Arrays.asList(booleanNames));
		Attribute last0 = new Attribute("last0", Arrays.asList(booleanNames));
		Attribute cityState0 = new Attribute("cityState0", Arrays.asList(booleanNames));
		Attribute country0 = new Attribute("country0", Arrays.asList(booleanNames));
		Attribute place0 = new Attribute("place0", Arrays.asList(booleanNames));
		String[] classificationNames = {"start","end","continue","both","none","null"};
		Attribute classification0 = new Attribute("classification0", Arrays.asList(classificationNames));
		
		Attribute dictionary1 = new Attribute("dictionary1", Arrays.asList(booleanNames));
		Attribute initial1 = new Attribute("initial1", Arrays.asList(booleanNames));
		Attribute honorific1 = new Attribute("honorific1", Arrays.asList(booleanNames));
		Attribute prefix1 = new Attribute("prefix1", Arrays.asList(booleanNames));
		Attribute suffix1 = new Attribute("suffix1", Arrays.asList(booleanNames));
		Attribute npname1 = new Attribute("npname1", Arrays.asList(booleanNames));
		Attribute stop1 = new Attribute("stop1", Arrays.asList(booleanNames));
		Attribute pos1 = new Attribute("pos1", Arrays.asList(posNames));
		Attribute lex1 = new Attribute("lex1", Arrays.asList(lexNames));
		Attribute commonFirst1 = new Attribute("commonFirst1", Arrays.asList(booleanNames));
		Attribute first1 = new Attribute("first1", Arrays.asList(booleanNames));
		Attribute commonLast1 = new Attribute("commonLast1", Arrays.asList(booleanNames));
		Attribute last1 = new Attribute("last1", Arrays.asList(booleanNames));
		Attribute cityState1 = new Attribute("cityState1", Arrays.asList(booleanNames));
		Attribute country1 = new Attribute("country1", Arrays.asList(booleanNames));
		Attribute place1 = new Attribute("place1", Arrays.asList(booleanNames));
		Attribute classification1 = new Attribute("classification1", Arrays.asList(classificationNames));
		
		Attribute dictionary2 = new Attribute("dictionary2", Arrays.asList(booleanNames));
		Attribute initial2 = new Attribute("initial2", Arrays.asList(booleanNames));
		Attribute honorific2 = new Attribute("honorific2", Arrays.asList(booleanNames));
		Attribute prefix2 = new Attribute("prefix2", Arrays.asList(booleanNames));
		Attribute suffix2 = new Attribute("suffix2", Arrays.asList(booleanNames));
		Attribute npname2 = new Attribute("npname2", Arrays.asList(booleanNames));
		Attribute stop2 = new Attribute("stop2", Arrays.asList(booleanNames));
		Attribute pos2 = new Attribute("pos2", Arrays.asList(posNames));
		Attribute lex2 = new Attribute("lex2", Arrays.asList(lexNames));
		Attribute commonFirst2 = new Attribute("commonFirst2", Arrays.asList(booleanNames));
		Attribute first2 = new Attribute("first2", Arrays.asList(booleanNames));
		Attribute commonLast2 = new Attribute("commonLast2", Arrays.asList(booleanNames));
		Attribute last2 = new Attribute("last2", Arrays.asList(booleanNames));
		Attribute cityState2 = new Attribute("cityState2", Arrays.asList(booleanNames));
		Attribute country2 = new Attribute("country2", Arrays.asList(booleanNames));
		Attribute place2 = new Attribute("place2", Arrays.asList(booleanNames));
		Attribute classification2 = new Attribute("classification2", Arrays.asList(classificationNames));
		
		Attribute dictionary3 = new Attribute("dictionary3", Arrays.asList(booleanNames));
		Attribute initial3 = new Attribute("initial3", Arrays.asList(booleanNames));
		Attribute honorific3 = new Attribute("honorific3", Arrays.asList(booleanNames));
		Attribute prefix3 = new Attribute("prefix3", Arrays.asList(booleanNames));
		Attribute suffix3 = new Attribute("suffix3", Arrays.asList(booleanNames));
		Attribute npname3 = new Attribute("npname3", Arrays.asList(booleanNames));
		Attribute stop3 = new Attribute("stop3", Arrays.asList(booleanNames));
		Attribute pos3 = new Attribute("pos3", Arrays.asList(posNames));
		Attribute lex3 = new Attribute("lex3", Arrays.asList(lexNames));
		Attribute commonFirst3 = new Attribute("commonFirst3", Arrays.asList(booleanNames));
		Attribute first3 = new Attribute("first3", Arrays.asList(booleanNames));
		Attribute commonLast3 = new Attribute("commonLast3", Arrays.asList(booleanNames));
		Attribute last3 = new Attribute("last3", Arrays.asList(booleanNames));
		Attribute cityState3 = new Attribute("cityState3", Arrays.asList(booleanNames));
		Attribute country3 = new Attribute("country3", Arrays.asList(booleanNames));
		Attribute place3 = new Attribute("place3", Arrays.asList(booleanNames));
		Attribute classification3 = new Attribute("classification3", Arrays.asList(classificationNames));
		
		Attribute dictionary4 = new Attribute("dictionary4", Arrays.asList(booleanNames));
		Attribute initial4 = new Attribute("initial4", Arrays.asList(booleanNames));
		Attribute honorific4 = new Attribute("honorific4", Arrays.asList(booleanNames));
		Attribute prefix4 = new Attribute("prefix4", Arrays.asList(booleanNames));
		Attribute suffix4 = new Attribute("suffix4", Arrays.asList(booleanNames));
		Attribute npname4 = new Attribute("npname4", Arrays.asList(booleanNames));
		Attribute stop4 = new Attribute("stop4", Arrays.asList(booleanNames));
		Attribute pos4 = new Attribute("pos4", Arrays.asList(posNames));
		Attribute lex4 = new Attribute("lex4", Arrays.asList(lexNames));
		Attribute commonFirst4 = new Attribute("commonFirst4", Arrays.asList(booleanNames));
		Attribute first4 = new Attribute("first4", Arrays.asList(booleanNames));
		Attribute commonLast4 = new Attribute("commonLast4", Arrays.asList(booleanNames));
		Attribute last4 = new Attribute("last4", Arrays.asList(booleanNames));
		Attribute cityState4 = new Attribute("cityState4", Arrays.asList(booleanNames));
		Attribute country4 = new Attribute("country4", Arrays.asList(booleanNames));
		Attribute place4 = new Attribute("place4", Arrays.asList(booleanNames));
		Attribute classification4 = new Attribute("classification4", Arrays.asList(classificationNames));
		
		Attribute dictionary5 = new Attribute("dictionary5", Arrays.asList(booleanNames));
		Attribute initial5 = new Attribute("initial5", Arrays.asList(booleanNames));
		Attribute honorific5 = new Attribute("honorific5", Arrays.asList(booleanNames));
		Attribute prefix5 = new Attribute("prefix5", Arrays.asList(booleanNames));
		Attribute suffix5 = new Attribute("suffix5", Arrays.asList(booleanNames));
		Attribute npname5 = new Attribute("npname5", Arrays.asList(booleanNames));
		Attribute stop5 = new Attribute("stop5", Arrays.asList(booleanNames));
		Attribute pos5 = new Attribute("pos5", Arrays.asList(posNames));
		Attribute lex5 = new Attribute("lex5", Arrays.asList(lexNames));
		Attribute commonFirst5 = new Attribute("commonFirst5", Arrays.asList(booleanNames));
		Attribute first5 = new Attribute("first5", Arrays.asList(booleanNames));
		Attribute commonLast5 = new Attribute("commonLast5", Arrays.asList(booleanNames));
		Attribute last5 = new Attribute("last5", Arrays.asList(booleanNames));
		Attribute cityState5 = new Attribute("cityState5", Arrays.asList(booleanNames));
		Attribute country5 = new Attribute("country5", Arrays.asList(booleanNames));
		Attribute place5 = new Attribute("place5", Arrays.asList(booleanNames));
		Attribute classification5 = new Attribute("classification5", Arrays.asList(classificationNames));
		
		Attribute dictionary6 = new Attribute("dictionary6", Arrays.asList(booleanNames));
		Attribute initial6 = new Attribute("initial6", Arrays.asList(booleanNames));
		Attribute honorific6 = new Attribute("honorific6", Arrays.asList(booleanNames));
		Attribute prefix6 = new Attribute("prefix6", Arrays.asList(booleanNames));
		Attribute suffix6 = new Attribute("suffix6", Arrays.asList(booleanNames));
		Attribute npname6 = new Attribute("npname6", Arrays.asList(booleanNames));
		Attribute stop6 = new Attribute("stop6", Arrays.asList(booleanNames));
		Attribute pos6 = new Attribute("pos6", Arrays.asList(posNames));
		Attribute lex6 = new Attribute("lex6", Arrays.asList(lexNames));
		Attribute commonFirst6 = new Attribute("commonFirst6", Arrays.asList(booleanNames));
		Attribute first6 = new Attribute("first6", Arrays.asList(booleanNames));
		Attribute commonLast6 = new Attribute("commonLast6", Arrays.asList(booleanNames));
		Attribute last6 = new Attribute("last6", Arrays.asList(booleanNames));
		Attribute cityState6 = new Attribute("cityState6", Arrays.asList(booleanNames));
		Attribute country6 = new Attribute("country6", Arrays.asList(booleanNames));
		Attribute place6 = new Attribute("place6", Arrays.asList(booleanNames));
		Attribute classification6 = new Attribute("classification6", Arrays.asList(classificationNames));
		
		Attribute dictionary7 = new Attribute("dictionary7", Arrays.asList(booleanNames));
		Attribute initial7 = new Attribute("initial7", Arrays.asList(booleanNames));
		Attribute honorific7 = new Attribute("honorific7", Arrays.asList(booleanNames));
		Attribute prefix7 = new Attribute("prefix7", Arrays.asList(booleanNames));
		Attribute suffix7 = new Attribute("suffix7", Arrays.asList(booleanNames));
		Attribute npname7 = new Attribute("npname7", Arrays.asList(booleanNames));
		Attribute stop7 = new Attribute("stop7", Arrays.asList(booleanNames));
		Attribute pos7 = new Attribute("pos7", Arrays.asList(posNames));
		Attribute lex7 = new Attribute("lex7", Arrays.asList(lexNames));
		Attribute commonFirst7 = new Attribute("commonFirst7", Arrays.asList(booleanNames));
		Attribute first7 = new Attribute("first7", Arrays.asList(booleanNames));
		Attribute commonLast7 = new Attribute("commonLast7", Arrays.asList(booleanNames));
		Attribute last7 = new Attribute("last7", Arrays.asList(booleanNames));
		Attribute cityState7 = new Attribute("cityState7", Arrays.asList(booleanNames));
		Attribute country7 = new Attribute("country7", Arrays.asList(booleanNames));
		Attribute place7 = new Attribute("place7", Arrays.asList(booleanNames));
		Attribute classification7 = new Attribute("classification7", Arrays.asList(classificationNames));
		
		Attribute dictionary8 = new Attribute("dictionary8", Arrays.asList(booleanNames));
		Attribute initial8 = new Attribute("initial8", Arrays.asList(booleanNames));
		Attribute honorific8 = new Attribute("honorific8", Arrays.asList(booleanNames));
		Attribute prefix8 = new Attribute("prefix8", Arrays.asList(booleanNames));
		Attribute suffix8 = new Attribute("suffix8", Arrays.asList(booleanNames));
		Attribute npname8 = new Attribute("npname8", Arrays.asList(booleanNames));
		Attribute stop8 = new Attribute("stop8", Arrays.asList(booleanNames));
		Attribute pos8 = new Attribute("pos8", Arrays.asList(posNames));
		Attribute lex8 = new Attribute("lex8", Arrays.asList(lexNames));
		Attribute commonFirst8 = new Attribute("commonFirst8", Arrays.asList(booleanNames));
		Attribute first8 = new Attribute("first8", Arrays.asList(booleanNames));
		Attribute commonLast8 = new Attribute("commonLast8", Arrays.asList(booleanNames));
		Attribute last8 = new Attribute("last8", Arrays.asList(booleanNames));
		Attribute cityState8 = new Attribute("cityState8", Arrays.asList(booleanNames));
		Attribute country8 = new Attribute("country8", Arrays.asList(booleanNames));
		Attribute place8 = new Attribute("place8", Arrays.asList(booleanNames));
		Attribute classification8 = new Attribute("classification8", Arrays.asList(classificationNames));
		
		Attribute dictionary9 = new Attribute("dictionary9", Arrays.asList(booleanNames));
		Attribute initial9 = new Attribute("initial9", Arrays.asList(booleanNames));
		Attribute honorific9 = new Attribute("honorific9", Arrays.asList(booleanNames));
		Attribute prefix9 = new Attribute("prefix9", Arrays.asList(booleanNames));
		Attribute suffix9 = new Attribute("suffix9", Arrays.asList(booleanNames));
		Attribute npname9 = new Attribute("npname9", Arrays.asList(booleanNames));
		Attribute stop9 = new Attribute("stop9", Arrays.asList(booleanNames));
		Attribute pos9 = new Attribute("pos9", Arrays.asList(posNames));
		Attribute lex9 = new Attribute("lex9", Arrays.asList(lexNames));
		Attribute commonFirst9 = new Attribute("commonFirst9", Arrays.asList(booleanNames));
		Attribute first9 = new Attribute("first9", Arrays.asList(booleanNames));
		Attribute commonLast9 = new Attribute("commonLast9", Arrays.asList(booleanNames));
		Attribute last9 = new Attribute("last9", Arrays.asList(booleanNames));
		Attribute cityState9 = new Attribute("cityState9", Arrays.asList(booleanNames));
		Attribute country9 = new Attribute("country9", Arrays.asList(booleanNames));
		Attribute place9 = new Attribute("place9", Arrays.asList(booleanNames));
		Attribute classification9 = new Attribute("classification9", Arrays.asList(classificationNames));
		
		Attribute dictionary10 = new Attribute("dictionary10", Arrays.asList(booleanNames));
		Attribute initial10 = new Attribute("initial10", Arrays.asList(booleanNames));
		Attribute honorific10 = new Attribute("honorific10", Arrays.asList(booleanNames));
		Attribute prefix10 = new Attribute("prefix10", Arrays.asList(booleanNames));
		Attribute suffix10 = new Attribute("suffix10", Arrays.asList(booleanNames));
		Attribute npname10 = new Attribute("npname10", Arrays.asList(booleanNames));
		Attribute stop10 = new Attribute("stop10", Arrays.asList(booleanNames));
		Attribute pos10 = new Attribute("pos10", Arrays.asList(posNames));
		Attribute lex10 = new Attribute("lex10", Arrays.asList(lexNames));
		Attribute commonFirst10 = new Attribute("commonFirst10", Arrays.asList(booleanNames));
		Attribute first10 = new Attribute("first10", Arrays.asList(booleanNames));
		Attribute commonLast10 = new Attribute("commonLast10", Arrays.asList(booleanNames));
		Attribute last10 = new Attribute("last10", Arrays.asList(booleanNames));
		Attribute cityState10 = new Attribute("cityState10", Arrays.asList(booleanNames));
		Attribute country10 = new Attribute("country10", Arrays.asList(booleanNames));
		Attribute place10 = new Attribute("place10", Arrays.asList(booleanNames));
		Attribute classification10 = new Attribute("classification10", Arrays.asList(classificationNames));
		
		//Create attributes for the machine
		ArrayList<Attribute> attrInfo = new ArrayList<Attribute>();
		attrInfo.add(dictionary0);
		attrInfo.add(initial0);
		attrInfo.add(honorific0);
		attrInfo.add(prefix0);
		attrInfo.add(suffix0);
		attrInfo.add(npname0);
		attrInfo.add(stop0);
		attrInfo.add(pos0);
		attrInfo.add(lex0);
		attrInfo.add(commonFirst0);
		attrInfo.add(first0);
		attrInfo.add(commonLast0);
		attrInfo.add(last0);
		attrInfo.add(cityState0);
		attrInfo.add(country0);
		attrInfo.add(place0);
		attrInfo.add(classification0);
		
		attrInfo.add(dictionary1);
		attrInfo.add(initial1);
		attrInfo.add(honorific1);
		attrInfo.add(prefix1);
		attrInfo.add(suffix1);
		attrInfo.add(npname1);
		attrInfo.add(stop1);
		attrInfo.add(pos1);
		attrInfo.add(lex1);
		attrInfo.add(commonFirst1);
		attrInfo.add(first1);
		attrInfo.add(commonLast1);
		attrInfo.add(last1);
		attrInfo.add(cityState1);
		attrInfo.add(country1);
		attrInfo.add(place1);
		attrInfo.add(classification1);
		
		attrInfo.add(dictionary2);
		attrInfo.add(initial2);
		attrInfo.add(honorific2);
		attrInfo.add(prefix2);
		attrInfo.add(suffix2);
		attrInfo.add(npname2);
		attrInfo.add(stop2);
		attrInfo.add(pos2);
		attrInfo.add(lex2);
		attrInfo.add(commonFirst2);
		attrInfo.add(first2);
		attrInfo.add(commonLast2);
		attrInfo.add(last2);
		attrInfo.add(cityState2);
		attrInfo.add(country2);
		attrInfo.add(place2);
		attrInfo.add(classification2);
		
		attrInfo.add(dictionary3);
		attrInfo.add(initial3);
		attrInfo.add(honorific3);
		attrInfo.add(prefix3);
		attrInfo.add(suffix3);
		attrInfo.add(npname3);
		attrInfo.add(stop3);
		attrInfo.add(pos3);
		attrInfo.add(lex3);
		attrInfo.add(commonFirst3);
		attrInfo.add(first3);
		attrInfo.add(commonLast3);
		attrInfo.add(last3);
		attrInfo.add(cityState3);
		attrInfo.add(country3);
		attrInfo.add(place3);
		attrInfo.add(classification3);
		
		attrInfo.add(dictionary4);
		attrInfo.add(initial4);
		attrInfo.add(honorific4);
		attrInfo.add(prefix4);
		attrInfo.add(suffix4);
		attrInfo.add(npname4);
		attrInfo.add(stop4);
		attrInfo.add(pos4);
		attrInfo.add(lex4);
		attrInfo.add(commonFirst4);
		attrInfo.add(first4);
		attrInfo.add(commonLast4);
		attrInfo.add(last4);
		attrInfo.add(cityState4);
		attrInfo.add(country4);
		attrInfo.add(place4);
		attrInfo.add(classification4);
		
		attrInfo.add(dictionary5);
		attrInfo.add(initial5);
		attrInfo.add(honorific5);
		attrInfo.add(prefix5);
		attrInfo.add(suffix5);
		attrInfo.add(npname5);
		attrInfo.add(stop5);
		attrInfo.add(pos5);
		attrInfo.add(lex5);
		attrInfo.add(commonFirst5);
		attrInfo.add(first5);
		attrInfo.add(commonLast5);
		attrInfo.add(last5);
		attrInfo.add(cityState5);
		attrInfo.add(country5);
		attrInfo.add(place5);
		attrInfo.add(classification5);	
		
		attrInfo.add(dictionary6);
		attrInfo.add(initial6);
		attrInfo.add(honorific6);
		attrInfo.add(prefix6);
		attrInfo.add(suffix6);
		attrInfo.add(npname6);
		attrInfo.add(stop6);
		attrInfo.add(pos6);
		attrInfo.add(lex6);
		attrInfo.add(commonFirst6);
		attrInfo.add(first6);
		attrInfo.add(commonLast6);
		attrInfo.add(last6);
		attrInfo.add(cityState6);
		attrInfo.add(country6);
		attrInfo.add(place6);
		attrInfo.add(classification6);
		
		attrInfo.add(dictionary7);
		attrInfo.add(initial7);
		attrInfo.add(honorific7);
		attrInfo.add(prefix7);
		attrInfo.add(suffix7);
		attrInfo.add(npname7);
		attrInfo.add(stop7);
		attrInfo.add(pos7);
		attrInfo.add(lex7);
		attrInfo.add(commonFirst7);
		attrInfo.add(first7);
		attrInfo.add(commonLast7);
		attrInfo.add(last7);
		attrInfo.add(cityState7);
		attrInfo.add(country7);
		attrInfo.add(place7);
		attrInfo.add(classification7);
		
		attrInfo.add(dictionary8);
		attrInfo.add(initial8);
		attrInfo.add(honorific8);
		attrInfo.add(prefix8);
		attrInfo.add(suffix8);
		attrInfo.add(npname8);
		attrInfo.add(stop8);
		attrInfo.add(pos8);
		attrInfo.add(lex8);
		attrInfo.add(commonFirst8);
		attrInfo.add(first8);
		attrInfo.add(commonLast8);
		attrInfo.add(last8);
		attrInfo.add(cityState8);
		attrInfo.add(country8);
		attrInfo.add(place8);
		attrInfo.add(classification8);
		
		attrInfo.add(dictionary9);
		attrInfo.add(initial9);
		attrInfo.add(honorific9);
		attrInfo.add(prefix9);
		attrInfo.add(suffix9);
		attrInfo.add(npname9);
		attrInfo.add(stop9);
		attrInfo.add(pos9);
		attrInfo.add(lex9);
		attrInfo.add(commonFirst9);
		attrInfo.add(first9);
		attrInfo.add(commonLast9);
		attrInfo.add(last9);
		attrInfo.add(cityState9);
		attrInfo.add(country9);
		attrInfo.add(place9);
		attrInfo.add(classification9);
		
		attrInfo.add(dictionary10);
		attrInfo.add(initial10);
		attrInfo.add(honorific10);
		attrInfo.add(prefix10);
		attrInfo.add(suffix10);
		attrInfo.add(npname10);
		attrInfo.add(stop10);
		attrInfo.add(pos10);
		attrInfo.add(lex10);
		attrInfo.add(commonFirst10);
		attrInfo.add(first10);
		attrInfo.add(commonLast10);
		attrInfo.add(last10);
		attrInfo.add(cityState10);
		attrInfo.add(country10);
		attrInfo.add(place10);
		attrInfo.add(classification10);
		
		final int numberOfAttributes = attrInfo.size();
		
		//Creates the learning machine
		SMO svm = new SMO();
		
		//Reads the model in from trainedPNE.model
		try {
			InputStream inputStream = LearningMachine.class.getClassLoader().getResourceAsStream("trainedPNE.model");
			svm = (SMO) weka.core.SerializationHelper.read(inputStream);
		}
		catch (Exception e) {
			System.err.println("Couldn't read the svm from training model");
			e.printStackTrace();
		}
		
		tokenList = removeNERTags(tokenList);
		
		Instances classificationInstances = new Instances("toBeClassified", attrInfo, tokenList.size());
		classificationInstances.setClass(classification5);
		
		for(int i = 0; i < tokenList.size(); i++) {
			
			Window win = new Window(tokenList, i);
			String s = win.getWindowFeatures();
			
			String[] dataToClassify = s.split(",");
			

			// Build the instance to be classified
			//Skips the value/attribute that we want to be classified
			//Instance toClassify = new DenseInstance(numberOfAttributes-1);
			double[] instanceValues = new double[numberOfAttributes];
			for (int j=0; j<dataToClassify.length; j++) {
	            instanceValues[j] = classificationInstances.attribute(j).indexOfValue(dataToClassify[j]);
				//toClassify.setValue(classificationInstances.attribute(j), dataToClassify[j]);	
			}
			
			Instance toClassify = new DenseInstance(1.0, instanceValues);
			toClassify.setDataset(classificationInstances);
			
			
			// Classify the instance with the machine
			//Sets the classification of the token based on the predicted classification
			try {
				double predictedCategory = svm.classifyInstance(toClassify);
				String predictedClassification = classification5.value((int) predictedCategory);
				tokenList.get(i).setClassification(predictedClassification);
				//System.err.println("Predicted classification: " + tokenList.get(i).getClassification());
			}
			catch (Exception e) {
				System.err.println("couldn't classify the instance");
				e.printStackTrace();
			}
			
		}		
	}

}
