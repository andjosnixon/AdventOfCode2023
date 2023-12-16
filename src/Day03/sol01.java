package Day03;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class sol01 {
    public static void main(String[] args) {
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
        for (int i_lineCount = 0; i_lineCount < lineCount-2; i_lineCount++ ){
            String[] stringIn = Arrays.copyOfRange(puzzle, i_lineCount, i_lineCount+2);
            String[] stringOut1 = checkTopString(stringIn);
            String[] stringOut2 = checkBottomString(stringOut1);
            puzzle[i_lineCount] = stringOut2[0];
            puzzle[i_lineCount+1] = stringOut2[1];
        }
        String[] stringIn = Arrays.copyOfRange(puzzle, lineCount-3, lineCount-1);
        String[] stringOut = checkBottomString(stringIn);
        puzzle[lineCount-2] = stringOut[0];
        puzzle[lineCount-1] = stringOut[1];

        int total = 0;
        for (int i_lines = 0; i_lines<lineCount-1; i_lines++){
            Pattern p_num = Pattern.compile( "\\d+");
            Matcher m_num = p_num.matcher(puzzle[i_lines]);
            while (m_num.find()) {
                total = total + Integer.parseInt(m_num.group());
            }
        }
        System.out.println(total);
    }

    // takes in two strings at a time
    // check left/right/adjacent/down on the top string, check left/right on the bottom string
    // if number is detected with no symbol nearby, set to A matching original size
    // 5 becomes A, 24 becomes AA, 129 becomes AAA, etc.
    // return the two strings
    public static String[] checkTopString(String[] stringIn){
        String[] output = new String[2];
        int s1len = stringIn[0].length();
        int s2len = stringIn[1].length();
        int clearFlag = 1;
        char[] string1 = stringIn[0].toCharArray();
        char[] string2 = stringIn[1].toCharArray();
        Pattern p_top = Pattern.compile( "\\d+");
        Matcher m_top = p_top.matcher(stringIn[0]);
        while (m_top.find()) {
            clearFlag = 1;
            int first = m_top.start();
            int last = m_top.end();
            //check left and adjacent
            if (first > 0){
                if (Character.compare(string1[first-1], '.')!=0 || Character.compare(string2[first-1], '.')!=0){
                    clearFlag = 0;
                }
            }
            //check right and adjacent
            if (last+1 < s1len){
                if (Character.compare(string1[last+1], '.')!=0 || Character.compare(string2[last+1], '.')!=0){
                    clearFlag = 0;
                }
            }
            //check below
            String dots = ".".repeat(last - first);
            String copy = new String(string2, first,(last - first));
            if (!dots.equals(copy)) {
                clearFlag = 0;
            }
            if (clearFlag==1){
                for (int i_replace = first; i_replace<last; i_replace++){
                    string1[i_replace] = 'A';
                }
            }
        }
        output[0] = new String(string1);
        output[1] = new String(string2);
        return output;
    }

    public static String[] checkBottomString(String[] stringIn){
        String[] output = new String[2];
        int s1len = stringIn[0].length();
        int s2len = stringIn[1].length();
        int clearFlag = 1;
        char[] string1 = stringIn[0].toCharArray();
        char[] string2 = stringIn[1].toCharArray();
        Pattern p_bottom = Pattern.compile( "\\d+");
        Matcher m_bottom = p_bottom.matcher(stringIn[1]);
        while (m_bottom.find()) {
            int first = m_bottom.start();
            int last = m_bottom.end()-1;
            //check left and adjacent
            if (first > 0){
                if (Character.compare(string1[first-1], '.')!=0 || Character.compare(string2[first-1], '.')!=0){
                    clearFlag = 0;
                }
            }
            //check right and adjacent
            if (last+1 < s1len){
                if (Character.compare(string1[last+1], '.')!=0 || Character.compare(string2[last+1], '.')!=0){
                    clearFlag = 0;
                }
            }
            //check above
            String dots = ".".repeat(last - first);
            String copy = new String(string1, first,(last-first));

            if (!dots.equals(copy)) {
                clearFlag = 0;
            }
            if (clearFlag==1){
                for (int i_replace = first; i_replace<last; i_replace++){
                    string2[i_replace] = 'A';
                }
            }
        }
        output[0] = new String(string1);
        output[1] = new String(string2);
        return output;
    }
}
