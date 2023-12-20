package Day06;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//Guesses input 1:
// 131376, correct

//Guesses input 2:
// 34123437, correct

// Accidentally forgot to separate parts 1 and 2 by filename
// The current version of this code solves part 2

public class day06sol01 {
    public static void main(String[] args) {
        long maxTime = 0;
        long rec = 0;
        long winCount = 0;
        try {
            File myObj = new File("C:/Users/andjo/Repo/AdventOfCode2023/src/Day06/input06.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String thisLine = myReader.nextLine();
                if (thisLine.contains("Time:")) {
                    thisLine = thisLine.replaceAll("[^0-9]", "");
                    maxTime = Long.parseLong(thisLine);
                } else if (thisLine.contains("Distance:")) {
                    thisLine = thisLine.replaceAll("[^0-9]", "");
                    rec = Long.parseLong(thisLine);
                }
            }
            int decreasingFlag = 0;
            for (int i_raceAttempt = 1; i_raceAttempt < maxTime; i_raceAttempt++){
                double distanceTraveled_mm = -Math.pow(i_raceAttempt, 2) + i_raceAttempt * maxTime;
                if (distanceTraveled_mm > rec){
                    winCount++;
                } else if (winCount>0) {
                    break;
                }
            }
            System.out.println(winCount);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }
}
