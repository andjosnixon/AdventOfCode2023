package Day04;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class sol01 {
    public static void main(String[] args) {
        double program_output = 0;
        int lineCount = 0;
        try {
            File myObj = new File("C:/Users/andjo/Repo/AdventOfCode2023/src/Day04/input04.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                lineCount = 0;
                String thisLine = myReader.nextLine();
                String[] twoHalves = thisLine.split("\\|");
                String winningNums = twoHalves[0].split(": ")[1];

                int[] myNums = new int[25];
                int[] winning = new int[10];

                Pattern p_mine = Pattern.compile( "\\d+");
                Matcher m_mine = p_mine.matcher(twoHalves[1]);
                Pattern p_win = Pattern.compile( "\\d+");
                Matcher m_win = p_mine.matcher(winningNums);

                while (m_mine.find()) {
                    myNums[lineCount] = Integer.parseInt(m_mine.group());
                    lineCount++;
                }
                lineCount = 0;
                while (m_win.find()) {
                    winning[lineCount] = Integer.parseInt(m_win.group());
                    lineCount++;
                }
                program_output = program_output + getPoints(myNums, winning);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
        System.out.println(program_output);
    }

    public static double getPoints(int[] mine, int[] winning){
        int matchCount = 0;
        double result = 0;
        for (int iWinCount = 0; iWinCount<winning.length; iWinCount++) {
            for (int iMineCount = 0; iMineCount<mine.length; iMineCount++){
                if (mine[iMineCount] == winning[iWinCount]){
                    matchCount++;
                    break;
                }
            }
        }
        if (matchCount > 0) {
            result = Math.pow(2, matchCount - 1);
        }
        return result;
    }
}
