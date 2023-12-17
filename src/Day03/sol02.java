package Day03;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class sol02 {
    public static void main(String[] args) {
        int program_output = 0;
        //140 lines, initialize array with 150 to be safe
        String[] puzzle = new String[150];
        int lineCount = 0;
        try {
            File myObj = new File("C:/Users/andjo/Repo/AdventOfCode2023/src/Day03/input03.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                puzzle[lineCount] = myReader.nextLine();
                lineCount++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }

        int thisLineGearRatios;
        for (int i_lineCount = 1; i_lineCount < lineCount-1; i_lineCount++ ){
            String[] stringsIn = Arrays.copyOfRange(puzzle, i_lineCount-1, i_lineCount+2);
            thisLineGearRatios = getGearRatios(stringsIn);
            program_output = program_output + thisLineGearRatios;
        }
        System.out.println(program_output);
    }

    // Takes in three lines, searches for a * in the middle row
    public static int getGearRatios(String[] stringsIn){
        int output = 0;
        int len = stringsIn[0].length(); // assumes all are the same length
        char[] topString = stringsIn[0].toCharArray();
        char[] middleString = stringsIn[1].toCharArray();
        char[] bottomString = stringsIn[2].toCharArray();
        Pattern p_star = Pattern.compile( "\\*");
        Matcher m_star = p_star.matcher(stringsIn[1]);
        while (m_star.find()) {
            int firstGear = 0;
            int secondGear = 0;
            char[][] snapshot = new char [3][7];
            snapshot[0] = Arrays.copyOfRange(topString, m_star.start()-3, m_star.start()+4);
            snapshot[1] = Arrays.copyOfRange(middleString, m_star.start()-3, m_star.start()+4);
            snapshot[2] = Arrays.copyOfRange(bottomString, m_star.start()-3, m_star.start()+4);
            Pattern p_top = Pattern.compile( "\\d+");
            Matcher m_top = p_top.matcher(new String(snapshot[0]));
            Pattern p_middle = Pattern.compile( "\\d+");
            Matcher m_middle = p_middle.matcher(new String(snapshot[1]));
            Pattern p_bottom = Pattern.compile( "\\d+");
            Matcher m_bottom = p_bottom.matcher(new String(snapshot[2]));
            while (m_top.find()) {
                if (m_top.start() <= 4 && m_top.end() >= 3) {
                    if (firstGear==0) {
                        firstGear = Integer.parseInt(m_top.group());
                    } else{
                        secondGear = Integer.parseInt(m_top.group());
                        output = output + firstGear * secondGear;
                        break;
                    }
                }
            }
            while (m_middle.find()) {
                if (m_middle.start() <= 4 && m_middle.end() >= 3) {
                    if (firstGear == 0) {
                        firstGear = Integer.parseInt(m_middle.group());
                    } else {
                        secondGear = Integer.parseInt(m_middle.group());
                        output = output + firstGear * secondGear;
                        break;
                    }
                }
            }
            while (m_bottom.find()) {
                if (m_bottom.start() <= 4 && m_bottom.end() >= 3) {
                    if (firstGear == 0) {
                        firstGear = Integer.parseInt(m_bottom.group());
                    } else {
                        secondGear = Integer.parseInt(m_bottom.group());
                        output = output + firstGear * secondGear;
                        break;
                    }
                }
            }
        }
        return output;
    }
}
