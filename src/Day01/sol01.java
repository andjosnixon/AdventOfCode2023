package Day01;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class sol01 {

    // Not totally necessary, but practicing writing separate functions
    public static String toString(char[] a){
        return new String(a);
    }


    public static void main(String[] args) {
        double output = 0;
        try {
            File myObj = new File("/Users/nixonaj1/AdventOfCode2023/src/Day01/input01.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String thisLine = myReader.nextLine();
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
                output = output + Double.parseDouble(outString);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
        System.out.println(output);
    }
}
