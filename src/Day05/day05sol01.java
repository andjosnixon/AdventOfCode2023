package Day05;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//Guesses


public class day05sol01 {
    public static void main(String[] args) {
        String[] allLines = new String[200]; //183 line in the file
        long[] seeds;
        long[][] seed_soil = new long[1][3];
        long[][] soil_fertilizer = new long[1][3];
        long[][] fertilizer_water = new long[1][3];
        long[][] water_light = new long[1][3];
        long[][] light_temperature = new long[1][3];
        long[][] temperature_humidity = new long[1][3];
        long[][] humidity_location = new long[1][3];
        int lineCount = 0;
        try {
            File myObj = new File("/Users/nixonaj1/AdventOfCode2023/src/Day05/input05.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                allLines[lineCount] = myReader.nextLine();
                if (allLines[lineCount].contains("seeds:")){
                    Pattern p_seeds = Pattern.compile( "\\d+");
                    Matcher m_seeds = p_seeds.matcher(allLines[lineCount] );
                    int numSeeds = 1;
                    seeds = new long[1];
                    while (m_seeds.find()) {
                        long[] newSeeds = extendByOne(seeds, Long.parseLong(m_seeds.group()));
                        seeds = newSeeds;
                        numSeeds++;
                    }
                } else if (allLines[lineCount].contains("seed-to-soil map:")) {
                    int i_rowCounter = 0;
                    int j_columnCounter = 0;
                    while (true) {
                        allLines[lineCount] = myReader.nextLine();
                        if (allLines[lineCount].isEmpty() == true){
                            break;
                        } else {
                            if (i_rowCounter > 0) {
                                seed_soil = addEmptyRow(seed_soil);
                            }
                            j_columnCounter = 0;
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m = p.matcher(allLines[lineCount]);
                            while (m.find()) {
                                seed_soil[i_rowCounter][j_columnCounter] = Long.parseLong(m.group());
                                j_columnCounter++;
                            }
                            i_rowCounter++;
                        }
                        lineCount++;
                    }

                } else if (allLines[lineCount].contains("soil-to-fertilizer map:")) {
                    int i_rowCounter = 0;
                    int j_columnCounter = 0;
                    while (true) {
                        allLines[lineCount] = myReader.nextLine();
                        if (allLines[lineCount].isEmpty() == true){
                            break;
                        } else {
                            if (i_rowCounter > 0) {
                                soil_fertilizer = addEmptyRow(soil_fertilizer);
                            }
                            j_columnCounter = 0;
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m = p.matcher(allLines[lineCount]);
                            while (m.find()) {
                                soil_fertilizer[i_rowCounter][j_columnCounter] = Long.parseLong(m.group());
                                j_columnCounter++;
                            }
                            i_rowCounter++;
                        }
                        lineCount++;
                    }
                } else if (allLines[lineCount].contains("fertilizer-to-water map:")) {
                    int i_rowCounter = 0;
                    int j_columnCounter = 0;
                    while (true) {
                        allLines[lineCount] = myReader.nextLine();
                        if (allLines[lineCount].isEmpty() == true) {
                            break;
                        } else {
                            if (i_rowCounter > 0) {
                                fertilizer_water  = addEmptyRow(fertilizer_water );
                            }
                            j_columnCounter = 0;
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m = p.matcher(allLines[lineCount]);
                            while (m.find()) {
                                fertilizer_water [i_rowCounter][j_columnCounter] = Long.parseLong(m.group());
                                j_columnCounter++;
                            }
                            i_rowCounter++;
                        }
                        lineCount++;
                    }

                } else if (allLines[lineCount].contains("water-to-light map:")) {
                    int i_rowCounter = 0;
                    int j_columnCounter = 0;
                    while (true) {
                        allLines[lineCount] = myReader.nextLine();
                        if (allLines[lineCount].isEmpty() == true) {
                            break;
                        } else {
                            if (i_rowCounter > 0) {
                                water_light  = addEmptyRow(water_light);
                            }
                            j_columnCounter = 0;
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m = p.matcher(allLines[lineCount]);
                            while (m.find()) {
                                water_light [i_rowCounter][j_columnCounter] = Long.parseLong(m.group());
                                j_columnCounter++;
                            }
                            i_rowCounter++;
                        }
                        lineCount++;
                    }

                } else if (allLines[lineCount].contains("light-to-temperature map:")) {
                    int i_rowCounter = 0;
                    int j_columnCounter = 0;
                    while (true) {
                        allLines[lineCount] = myReader.nextLine();
                        if (allLines[lineCount].isEmpty() == true) {
                            break;
                        } else {
                            if (i_rowCounter > 0) {
                                light_temperature  = addEmptyRow(light_temperature );
                            }
                            j_columnCounter = 0;
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m = p.matcher(allLines[lineCount]);
                            while (m.find()) {
                                light_temperature [i_rowCounter][j_columnCounter] = Long.parseLong(m.group());
                                j_columnCounter++;
                            }
                            i_rowCounter++;
                        }
                        lineCount++;
                    }

                } else if (allLines[lineCount].contains("temperature-to-humidity map:")) {
                    int i_rowCounter = 0;
                    int j_columnCounter = 0;
                    while (true) {
                        allLines[lineCount] = myReader.nextLine();
                        if (allLines[lineCount].isEmpty() == true) {
                            break;
                        } else {
                            if (i_rowCounter > 0) {
                                temperature_humidity  = addEmptyRow(temperature_humidity );
                            }
                            j_columnCounter = 0;
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m = p.matcher(allLines[lineCount]);
                            while (m.find()) {
                                temperature_humidity [i_rowCounter][j_columnCounter] = Long.parseLong(m.group());
                                j_columnCounter++;
                            }
                            i_rowCounter++;
                        }
                        lineCount++;
                    }
                } else if (allLines[lineCount].contains("humidity-to-location map:")) {
                    int i_rowCounter = 0;
                    int j_columnCounter = 0;
                    while (true) {
                        allLines[lineCount] = myReader.nextLine();
                        if (allLines[lineCount].isEmpty() == true) {
                            break;
                        } else {
                            if (i_rowCounter > 0) {
                                humidity_location  = addEmptyRow(humidity_location );
                            }
                            j_columnCounter = 0;
                            Pattern p = Pattern.compile("\\d+");
                            Matcher m = p.matcher(allLines[lineCount]);
                            while (m.find()) {
                                humidity_location[i_rowCounter][j_columnCounter] = Long.parseLong(m.group());
                                j_columnCounter++;
                            }
                            i_rowCounter++;
                        }
                        if (myReader.hasNextLine() == false){
                            break;
                        }
                        lineCount++;
                    }
                }
            }

            // got all the stupid numbers at this point. 
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }

    public static long[] extendByOne(long[] existingArray, long extension){
        if (existingArray[0]==0){
            long[] outputArray = new long[1];
            outputArray[0] = extension;
            return outputArray;
        } else {
            long[] outputArray = new long[existingArray.length+1];
            for (int i_seedCount = 0; i_seedCount<outputArray.length-1; i_seedCount++) {
                outputArray[i_seedCount] = existingArray[i_seedCount];
            }
            outputArray[outputArray.length-1] = extension;
            return outputArray;
        }
    }
    public static long[][] addEmptyRow( long[][] existingArray ){
        long[][] outputArray = new long[existingArray.length+1][3];
        for (int i_rowCounter = 0; i_rowCounter < outputArray.length-1; i_rowCounter++) {
            for (int j_columnCounter = 0; j_columnCounter < 3; j_columnCounter++){
                outputArray[i_rowCounter][j_columnCounter] = existingArray[i_rowCounter][j_columnCounter];
            }
        }
        outputArray[outputArray.length-1] = new long[3];
        return outputArray;
    }
}
