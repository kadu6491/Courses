package edu.odu.cs.cs350.avocado4;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class SystemTest {

	String test;
	String expectedResult;
	String actualResult;
	
	@Before
	public void setUp() throws Exception {
		test = "<NER> This is a test, Joe.</NER>\n<NER>A very good test!</NER>";
		expectedResult = "<NER> This is a test, <PER>Joe</PER>.</NER>\n<NER>A very good test!</NER>";
	}

	@Test
	public void test() {
        try
        { 
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", "PNE.jar");
            builder.directory(new File(System.getProperty("user.dir") + File.separator + "build" + File.separator + "libs"));
            Process process = builder.start();

            OutputStream stdin = process.getOutputStream();
            InputStream stdout = process.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

            writer.write(test);
            writer.flush();
            writer.close();

            Scanner scanner = new Scanner(stdout);
            StringBuffer buf = new StringBuffer();
            while (scanner.hasNextLine()) {
            	buf.append(scanner.nextLine());
            	buf.append("\n");
            }
            actualResult = buf.substring(0,buf.length()-1);
        } 
  
        catch (IOException e) 
        { 
            e.printStackTrace(); 
        } 
        
        assertThat(actualResult, is(expectedResult));
	}

}
