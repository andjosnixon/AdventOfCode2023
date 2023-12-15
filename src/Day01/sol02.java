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
            File myObj = new File("/Users/nixonaj1/AdventOfCode2023/src/Day01/input01.txt");
            Scanner myReader = new Scanner(myObj);
            int count = 1;
            while (myReader.hasNextLine()) {
                String thisLine = myReader.nextLine();

                String[] detectNums = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
                String[] replacementNums = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
                int[] indices = new int[9];

                //Get FIRST occurrences of all numbers in the string
                for ( int iPossibleNumbers = 0; iPossibleNumbers<9; iPossibleNumbers++){
                    indices[iPossibleNumbers] = thisLine.indexOf(detectNums[iPossibleNumbers], 0);
                }

                //Find the earliest occurrence of each FIRST number in the string (minimum index)
                int minValue = 1000; // something much higher than the length of each line
                int minIndex = -1;
                for ( int iPossibleNumbers = 0; iPossibleNumbers<9; iPossibleNumbers++){
                    if (indices[iPossibleNumbers]<minValue && indices[iPossibleNumbers]>=0) {
                        minValue = indices[iPossibleNumbers];
                        minIndex= iPossibleNumbers;
                    }
                }
                if (minValue!=1000) {
                    thisLine = thisLine.replaceAll(detectNums[minIndex], replacementNums[minIndex]);
                }

                //Get LAST occurrences of all numbers in the string
                for ( int iPossibleNumbers = 0; iPossibleNumbers<9; iPossibleNumbers++){
                    indices[iPossibleNumbers] = thisLine.indexOf(detectNums[iPossibleNumbers], 0);
                }

                //Find the last spelled out number in the string
                int maxValue = -1;
                int maxIndex = -1;
                for ( int iPossibleNumbers = 0; iPossibleNumbers<9; iPossibleNumbers++ ){
                    if (indices[iPossibleNumbers]>maxValue && indices[iPossibleNumbers]>=0) {
                        maxValue = indices[iPossibleNumbers];
                        maxIndex = iPossibleNumbers;
                    }
                }

                //replace all once this max index is determined
                if (maxValue!=-1) {
                    thisLine = thisLine.replaceAll(detectNums[maxIndex], replacementNums[maxIndex]);
                }

                String numbersOnlyAsString = thisLine.replaceAll("[^0-9]", "");
                char[] numbersOnlyAsCharArray = numbersOnlyAsString.toCharArray();
                char[] s = new char[2];
                s[0] = numbersOnlyAsCharArray[0];
                if (numbersOnlyAsCharArray.length == 1) {
                    s[1] = numbersOnlyAsCharArray[0];
                }
                else{
                    s[1] = numbersOnlyAsCharArray[numbersOnlyAsCharArray.length-1];
                }
                String outString = toString(s);

                if (count == 1000) {
                    System.out.println(count);
                }
                count++;
                output = output + Integer.parseInt(outString);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
        System.out.println(output);
    }
}
