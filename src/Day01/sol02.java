package Day01;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class sol02 {

    // Not totally necessary, but practicing writing separate functions
    public static String toString(char[] a){
        return new String(a);
    }

    //Commenting from personal computer, just to test making changes on multiple computers
    //Commenting from APL mac, just make test changes 
    public static void main(String[] args) {
        int output = 0;
        try {
            //File myObj = new File("/Users/nixonaj1/AdventOfCode2023/src/Day01/test01.txt"); //APL Mac
            File myObj = new File("C:/Users/andjo/Repo/AdventOfCode2023/src/Day01/input01.txt"); //personal computer
            Scanner myReader = new Scanner(myObj);
            int count = 1;
            while (myReader.hasNextLine()) {
                String thisLine = myReader.nextLine();

                char[] sol = new char[2];

                sol[0] = getFirstNumber(thisLine);
                sol[1] = getLastNumber(thisLine);

                String outString = toString(sol);
                output = output + Integer.parseInt(outString);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
        System.out.println(output);
    }

    private static char getFirstNumber(String thisLine) {
        String[] alphaNums = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] numerNums = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        char minValue = '0';
        int minIndex = 1000; // something much higher than the length of each line
        int tempIndex;

        //Find the earliest occurrence of each FIRST number in the string
        for ( int iPossibleNumbers = 0; iPossibleNumbers<9; iPossibleNumbers++){
            //alphabetic numbers first
            tempIndex = thisLine.indexOf(alphaNums[iPossibleNumbers]);
            if (tempIndex<minIndex && tempIndex>=0) {
                minIndex = tempIndex;
                minValue = numerNums[iPossibleNumbers].charAt(0);
            }
            tempIndex = thisLine.indexOf(numerNums[iPossibleNumbers]);
            if (tempIndex<minIndex && tempIndex>=0) {
                minIndex = tempIndex;
                minValue = numerNums[iPossibleNumbers].charAt(0);
            }
        }
        return minValue;
    }

    private static char getLastNumber(String thisLine) {
        String[] alphaNums = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] numerNums = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        char maxValue = '0'; // impossible starting value
        int maxIndex = -1; // impossible starting location
        int tempIndex;

        //Find the last occurrence of number in the string, alphabetic and numeric
        for ( int iPossibleNumbers = 0; iPossibleNumbers<9; iPossibleNumbers++){
            //alphabetic numbers first
            tempIndex = thisLine.lastIndexOf(alphaNums[iPossibleNumbers]);
            if (tempIndex>maxIndex) {
                maxIndex = tempIndex;
                maxValue = numerNums[iPossibleNumbers].charAt(0);
            }
            tempIndex = thisLine.lastIndexOf(numerNums[iPossibleNumbers]);
            if (tempIndex>maxIndex) {
                maxIndex = tempIndex;
                maxValue = numerNums[iPossibleNumbers].charAt(0);
            }
        }
        return maxValue;
    }
}
