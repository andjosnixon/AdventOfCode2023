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

        String[] firstTwo = Arrays.copyOfRange(puzzle, 0, 2);
        String firstLineChecked = checkFirstTwoStrings(firstTwo);
        puzzle[0] = firstLineChecked;

        for (int i_lineCount = 1; i_lineCount < lineCount-1; i_lineCount++ ){
            String[] stringsIn = Arrays.copyOfRange(puzzle, i_lineCount-1, i_lineCount+2);
            String middleLineChecked = checkMiddleStrings(stringsIn);
            puzzle[i_lineCount] = middleLineChecked;
        }

        String[] lastTwo = Arrays.copyOfRange(puzzle, lineCount-2, lineCount);
        String lastLineChecked = checkLastTwoStrings(lastTwo);
        puzzle[lineCount-1] = lastLineChecked;

        int total = 0;
        for (int i_lines = 0; i_lines<lineCount; i_lines++){
            Pattern p_num = Pattern.compile( "\\d+");
            Matcher m_num = p_num.matcher(puzzle[i_lines]);
            while (m_num.find()) {
                total = total + Integer.parseInt(m_num.group());
            }
        }
        System.out.println(total);
    }

    // This method is use in lines 1 through end-1 (i.e. not the first or last lines)
    // Always takes in three lines, searching for numbers that aren't valuable, changing them to "AAA"
    // Checks up, down, left, right, adjacent
    public static String checkMiddleStrings(String[] stringsIn){
        String output;
        int len = stringsIn[0].length(); // assumes all are the same length
        int clearFlag = 1;
        char[] topString = stringsIn[0].toCharArray();
        char[] middleString = stringsIn[1].toCharArray();
        char[] bottomString = stringsIn[2].toCharArray();
        Pattern p_middle = Pattern.compile( "\\d+");
        Matcher m_middle = p_middle.matcher(stringsIn[1]); // pass in the middle string here
        while (m_middle.find()) {
            clearFlag = 1;
            int first = m_middle.start();
            int last = m_middle.end();
            //check left and adjacent
            if (first > 0){
                if (Character.compare(topString[first-1], '.')!=0 || Character.compare(middleString[first-1], '.')!=0 || Character.compare(bottomString[first-1], '.')!=0){
                    clearFlag = 0;
                }
            }
            //check right and adjacent
            if (last < len){
                if (Character.compare(topString[last], '.')!=0 || Character.compare(middleString[last], '.')!=0 || Character.compare(bottomString[last], '.')!=0){
                    clearFlag = 0;
                }
            }
            //check above and below
            String dots = ".".repeat(last - first);
            String top_copy = new String(topString, first,(last - first));
            String bottom_copy = new String(bottomString, first,(last - first));
            if (!dots.equals(top_copy) || !dots.equals(bottom_copy)) {
                clearFlag = 0;
            }

            if (clearFlag==1){
                for (int i_replace = first; i_replace<last; i_replace++){
                    middleString[i_replace] = 'A';
                }
            }
        }
        output = new String(middleString);
        return output;
    }

    //Check first two strings
    public static String checkFirstTwoStrings(String[] stringsIn){
        String output;
        int len = stringsIn[0].length(); // assumes all are the same length
        int clearFlag = 1;
        char[] topString = stringsIn[0].toCharArray();
        char[] bottomString = stringsIn[1].toCharArray();
        Pattern p_top = Pattern.compile( "\\d+");
        Matcher m_top = p_top.matcher(stringsIn[0]); // pass in the middle string here
        while (m_top.find()) {
            clearFlag = 1;
            int first = m_top.start();
            int last = m_top.end();
            //check left and adjacent
            if (first > 0){
                if (Character.compare(topString[first-1], '.')!=0 || Character.compare(bottomString[first-1], '.')!=0){
                    clearFlag = 0;
                }
            }
            //check right and adjacent
            if (last < len){
                if (Character.compare(topString[last], '.')!=0 || Character.compare(bottomString[last], '.')!=0){
                    clearFlag = 0;
                }
            }
            //check below
            String dots = ".".repeat(last - first);
            String bottom_copy = new String(bottomString, first,(last - first));
            if (!dots.equals(bottom_copy)) {
                clearFlag = 0;
            }

            if (clearFlag==1){
                for (int i_replace = first; i_replace<last; i_replace++){
                    topString[i_replace] = 'A';
                }
            }
        }
        output = new String(topString);
        return output;
    }

    //Check last two strings
    public static String checkLastTwoStrings(String[] stringsIn){
        String output;
        int len = stringsIn[0].length(); // assumes all are the same length
        int clearFlag = 1;
        char[] topString = stringsIn[0].toCharArray();
        char[] bottomString = stringsIn[1].toCharArray();
        Pattern p_bottom = Pattern.compile( "\\d+");
        Matcher m_bottom = p_bottom.matcher(stringsIn[1]); // pass in the middle string here
        while (m_bottom.find()) {
            clearFlag = 1;
            int first = m_bottom.start();
            int last = m_bottom.end();
            //check left and adjacent
            if (first > 0){
                if (Character.compare(topString[first-1], '.')!=0){
                    clearFlag = 0;
                }
            }
            //check right and adjacent
            if (last < len){
                if (Character.compare(topString[last], '.')!=0){
                    clearFlag = 0;
                }
            }
            //check above
            String dots = ".".repeat(last - first);
            String top_copy = new String(topString, first,(last - first));
            if (!dots.equals(top_copy)) {
                clearFlag = 0;
            }

            if (clearFlag==1){
                for (int i_replace = first; i_replace<last; i_replace++){
                    bottomString[i_replace] = 'A';
                }
            }
        }
        output = new String(bottomString);
        return output;
    }
}
