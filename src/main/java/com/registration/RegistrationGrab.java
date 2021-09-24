package com.registration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class RegistrationGrab {
    public static void main(String[] args) {
        File file = new File("car_input.txt");
        List<String> inputSet = grabRegistration(file);
        List<String[]> outputSet = getData(inputSet);
        generateOutput(outputSet);
    }

    public static List<String> grabRegistration(File file) {
        List<String> regNumbers = new ArrayList<>();
        try {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine()) {
                String dataIn = readFile.nextLine();
                String[] words = dataIn.split("\\s");
                for(int i = 0; i<words.length; i++) {
                    words[i] = words[i].replaceAll("[^\\w]", "");
                }
                for(String word : words) {
                    if(word.length() == 7 && isStringUpperCase(word)) {
                        System.out.println("word");
                        System.out.println(word);
                        regNumbers.add(word);
                    }
                }

            }
            readFile.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found in location");
            e.printStackTrace();
        }
        return regNumbers;
    }

    public static boolean isStringUpperCase(String string) {
        char [] cArray = string.toCharArray();

        for (char c : cArray) {
            if (!Character.isUpperCase(c) && !Character.isDigit(c))
                return false;
        }
        return true;
    }

    public static void generateOutput(List<String[]> outputSet) {
        try {
            File outputFile = new File("car_output.txt");
            if(outputFile.createNewFile()) {
                System.out.println("car_output.txt created");
            }
        }
        catch (IOException e2) {
            System.out.println("createNewFile Error occurred");
            e2.printStackTrace();
        }
        try {
            FileWriter outputWrite = new FileWriter("car_output.txt");
            outputWrite.write("REGISTRATION,MAKE,MODEL,COLOR,YEAR");
            StringBuilder stringBuilder = new StringBuilder();
            for (String[] k : outputSet) {
                stringBuilder.append("\n\n");
                for (String l : k) {
                    stringBuilder.append(l);
                    stringBuilder.append(",");
                }
                stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
            }
            outputWrite.write(stringBuilder.toString());
            outputWrite.close();
        }
        catch (IOException e3) {
            System.out.println("writeFile Error occurred");
            e3.printStackTrace();
        }
    }

    public static List<String[]> getData(List<String> inputSet) {
        List<String[]> outputSet = new ArrayList<>();
        for(String input : inputSet) {
            EnterRegGrabDetails run = new EnterRegGrabDetails();
            run.setUp();
            String[] runOutput = run.enterRegGrabDetails(input);
            run.tearDown();
            outputSet.add(runOutput);
        }
        return outputSet;
    }
}
