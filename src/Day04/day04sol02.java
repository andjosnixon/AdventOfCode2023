package Day04;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//Guesses
// 2298946 - too low
// 9369740 - incorrect
// 9425061



public class day04sol02 {
    public static void main(String[] args) {
        int numLines = 0;
        int numLineIndex = 0;
        long[] reps = {1,1,1,1,1,1,1,1,1,1,1};
        int[][] myNums = new int[220][25];
        int[][] winning = new int[220][10];
        long totalScratchCards = 0;
        try {
            //File myObj = new File("C:/Users/andjo/Repo/AdventOfCode2023/src/Day04/input04.txt");
            File myObj = new File("/Users/nixonaj1/AdventOfCode2023/src/Day04/input04.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                numLineIndex = 0;
                String thisLine = myReader.nextLine();
                String[] twoHalves = thisLine.split("\\|");
                String winningNums = twoHalves[0].split(": ")[1];

                Pattern p_mine = Pattern.compile( "\\d+");
                Matcher m_mine = p_mine.matcher(twoHalves[1]);
                Pattern p_win = Pattern.compile( "\\d+");
                Matcher m_win = p_mine.matcher(winningNums);

                while (m_mine.find()) {
                    myNums[numLines][numLineIndex] = Integer.parseInt(m_mine.group());
                    numLineIndex++;
                }
                numLineIndex = 0;
                while (m_win.find()) {
                    winning[numLines][numLineIndex] = Integer.parseInt(m_win.group());
                    numLineIndex++;
                }
                numLines++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
        int thisLineMatches;
        for( int lineCount = 0; lineCount < numLines ; lineCount++){
            thisLineMatches = getMatches(myNums[lineCount], winning[lineCount]);
            for ( int repCount =1; repCount < thisLineMatches+1; repCount++){
                reps[repCount] = reps[repCount] + reps[0];
            }
            totalScratchCards = totalScratchCards + reps[0];
            reps = shift(reps);
        }
        System.out.println(totalScratchCards);
    }

    public static int getMatches(int[] mine, int[] winning){
        int matchCount = 0;
        for (int iWinCount = 0; iWinCount<winning.length; iWinCount++) {
            for (int iMineCount = 0; iMineCount<mine.length; iMineCount++){
                if (mine[iMineCount] == winning[iWinCount]){
                    matchCount++;
                    break;
                }
            }
        }
        return matchCount;
    }

    public static long[] shift(long[] reps){
        for (int cnt = 1; cnt<11; cnt++){
            reps[cnt-1] = reps[cnt];
        }
        reps[10] = 1;
        return reps;
    }
}
