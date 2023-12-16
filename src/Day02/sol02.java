package Day02;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class sol02 {

    public static void main(String[] args) {
        int gameCount = 1;
        int gameIDSum = 0;
        try {
            File myObj = new File("C:/Users/andjo/Repo/AdventOfCode2023/src/Day02/input02.txt");
            Scanner myReader = new Scanner(myObj);

            int maxRed = 12;
            int maxGreen = 13;
            int maxBlue = 14;

            int impossibleFlag;

            while (myReader.hasNextLine()) {
                impossibleFlag = 0;
                String thisLine = myReader.nextLine();
                String[] cubeSets = thisLine.split(";");
                Pattern p_red = Pattern.compile( "\\d+ red");
                Pattern p_green = Pattern.compile( "\\d+ green");
                Pattern p_blue = Pattern.compile( "\\d+ blue");
                for (int iSets = 0; iSets < cubeSets.length; iSets++){
                    Matcher m_red = p_red.matcher(cubeSets[iSets]);
                    Matcher m_green = p_green.matcher(cubeSets[iSets]);
                    Matcher m_blue = p_blue.matcher(cubeSets[iSets]);
                    if (m_red.find()) {
                        String thisRed = m_red.group().replaceAll("[^0-9]", "");
                        if (Integer.parseInt(thisRed) > maxRed){
                            impossibleFlag = 1;
                            break;
                        }
                    }
                    if (m_green.find()) {
                        String thisGreen = m_green.group().replaceAll("[^0-9]", "");
                        if (Integer.parseInt(thisGreen) > maxGreen){
                            impossibleFlag = 1;
                            break;
                        }
                    }
                    if (m_blue.find()) {
                        String thisBlue = m_blue.group().replaceAll("[^0-9]", "");
                        if (Integer.parseInt(thisBlue) > maxBlue){
                            impossibleFlag = 1;
                            break;
                        }
                    }
                }
                if (impossibleFlag==0) {
                    gameIDSum = gameIDSum + gameCount;
                }
                gameCount++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
        System.out.println(gameIDSum);
    }
}
