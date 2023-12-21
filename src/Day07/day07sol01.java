package Day07;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.IntStream;

//Guesses
// Worked on this for about 3 hours December 21, 2023 and got frustrated with how few things are possible in Java.
// I could probably do this in Matlab in about 20 minutes.

public class day07sol01 {
    public static void main(String[] args) {
        try {
            File myObj = new File("/Users/nixonaj1/AdventOfCode2023/src/Day07/input07.txt");
            Scanner myReader = new Scanner(myObj);
            String handPlusBid;
            String hand;
            int[] hand_int;
            String classification;
            String bid;
            char[] cards = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
            String[] fiveOfAKind = new String[1];
            String[] fourOfAKind = new String[1];
            String[] fullHouse = new String[1];
            String[] threeOfAKind = new String[1];
            String[] twoPair = new String[1];
            String[] onePair = new String[1];
            String[] highCard = new String[1];
            while (myReader.hasNextLine()) {
                handPlusBid = myReader.nextLine();
                hand = handPlusBid.split(" ")[0];
                classification = countCards(hand, cards);
                handPlusBid = handPlusBid.replaceAll(" ", "A")
                if (classification.contains("4")){
                    fiveOfAKind = addRow(fiveOfAKind, handPlusBid);
                } else if (classification.contains("3")){
                    fourOfAKind = addRow(fourOfAKind, handPlusBid);
                } else if (classification.contains("2") && classification.contains("1")){
                    fullHouse = addRow(fullHouse, handPlusBid);
                } else if (classification.contains("2")){
                    threeOfAKind = addRow(threeOfAKind, handPlusBid);
                } else if (checkDuplicate(classification) == 2) {
                    twoPair = addRow(twoPair, handPlusBid);
                } else if (checkDuplicate(classification) == 1) {
                    onePair = addRow(onePair, handPlusBid);
                } else {
                    highCard = addRow(highCard, handPlusBid);
                }
            }

            int index_count = 1;
            long bid_total = 0;

            int[] sortedFiveOfAKind = new int[fiveOfAKind.length];
            for (int i = 0; i<fiveOfAKind.length; i++){
                sortedFiveOfAKind[i] = convertHandToNum(fiveOfAKind[i]);
            }
            int[] sFiveOfAKind = Arrays.sort(sortedFiveOfAKind);
            for(int i = 0; i<sortedFiveOfAKind.length; i++){
                int intFromHex = Integer.parseInt(Integer.toString(sortedFiveOfAKind[i]));

                bid_total = bid_total + index_count *
            }

            int[] sortedFourOfAKind = new int[fourOfAKind.length];
            for (int i = 0; i<fourOfAKind.length; i++){
                sortedFourOfAKind[i] = convertHandToNum(fourOfAKind[i]);
            }

            int[] sortedFullHouse = new int[fullHouse.length];
            for (int i = 0; i<fullHouse.length; i++){
                sortedFullHouse[i] = convertHandToNum(fullHouse[i]);
            }

            int[] sortedThreeOfAKind = new int[threeOfAKind.length];
            for (int i = 0; i<threeOfAKind.length; i++){
                sortedThreeOfAKind[i] = convertHandToNum(threeOfAKind[i]);
            }

            int[] sortedTwoPair = new int[twoPair.length];
            for (int i = 0; i<twoPair.length; i++){
                sortedTwoPair[i] = convertHandToNum(twoPair[i]);
            }

            int[] sortedOnePair = new int[onePair.length];
            for (int i = 0; i<onePair.length; i++){
                sortedOnePair[i] = convertHandToNum(onePair[i]);
            }

            int[] sortedHighCard = new int[highCard.length];
            for (int i = 0; i<highCard.length; i++){
                sortedHighCard[i] = convertHandToNum(highCard[i]);
            }

            int index_count = 1;
            long bid_total = 0;
            for(){

            }



            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }

    public static String countCards(String hand_str, char[] cards ){
        char[] hand = hand_str.toCharArray();
        int[] reps = new int[5];
        String outputString = "";

        for (int comparisonCard = 0; comparisonCard < hand.length-1; comparisonCard++) {
            if (hand[comparisonCard] == '0') {
                continue;
            } else {
                for (int i_handCount = comparisonCard + 1; i_handCount < hand.length; i_handCount++) {
                    if (hand[i_handCount] == hand[comparisonCard]) {
                        reps[comparisonCard]++;
                        hand[i_handCount] = '0';
                    }
                }
            }
        }
        return Arrays.toString(reps);
    }
    public static String[] addRow( String[] existingArray , String newRow){
        if (existingArray[0] == null){
            String[] outputArray = new String[1];
            outputArray[0] = newRow;
            return outputArray;
        } else {
            String[] outputArray = new String[existingArray.length + 1];
            for (int i_rowCounter = 0; i_rowCounter < outputArray.length - 1; i_rowCounter++) {
                outputArray[i_rowCounter] = existingArray[i_rowCounter];
            }
            outputArray[outputArray.length - 1] = newRow;
            return outputArray;
        }
    }

    public static int checkDuplicate(String inputArray){
        int check = 0;
        Pattern p = Pattern.compile("1");
        Matcher m = p.matcher(inputArray);
        while (m.find()) {
            check++;
        }
        return check;
    }

    public static int convertHandToNum(String in_str){
        char[] in_char = new char[5];
        char[] hex_char = new char[5];
        in_char = in_str.toCharArray();
        for (int i = 0; i<5; i++){
            if (in_char[i] == '2'){
                hex_char[i] = '1';
            } else if (in_char[i] == '3'){
                hex_char[i] = '2';
            } else if (in_char[i] == '4'){
                hex_char[i] = '3';
            }else if (in_char[i] == '5'){
                hex_char[i] = '4';
            }else if (in_char[i] == '6'){
                hex_char[i] = '5';
            }else if (in_char[i] == '7'){
                hex_char[i] = '6';
            }else if (in_char[i] == '8'){
                hex_char[i] = '7';
            }else if (in_char[i] == '9'){
                hex_char[i] = '8';
            }else if (in_char[i] == 'T'){
                hex_char[i] = '9';
            }else if (in_char[i] == 'J'){
                hex_char[i] = 'A';
            }else if (in_char[i] == 'Q'){
                hex_char[i] = 'B';
            }else if (in_char[i] == 'K'){
                hex_char[i] = 'C';
            }else if (in_char[i] == 'A'){
                hex_char[i] = 'D';
            }
        }
        return Integer.parseInt(hex_char.toString(), 16);
    }


}

