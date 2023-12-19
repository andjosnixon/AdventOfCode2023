package Day05;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//Guesses
// 0, but then discovered I did something wrong. Need to
// 219529182, correct. Took a few minutes to run though.

public class day05sol02 {
    public static void main(String[] args) {
        long closestLocation = 1234567890L;
        String[] allLines = new String[200]; //183 line in the file
        long[] seeds = new long[1];
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
                                seed_soil = addEmptyRow(seed_soil, 3);
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
                                soil_fertilizer = addEmptyRow(soil_fertilizer, 3);
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
                                fertilizer_water  = addEmptyRow(fertilizer_water, 3);
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
                                water_light  = addEmptyRow(water_light, 3);
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
                                light_temperature  = addEmptyRow(light_temperature,3);
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
                                temperature_humidity  = addEmptyRow(temperature_humidity, 3);
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
                                humidity_location  = addEmptyRow(humidity_location, 3);
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

            for(int i_SeedCount = 0; i_SeedCount<seeds.length/2; i_SeedCount++){
                long i_start = seeds[2*i_SeedCount];
                long i_range = seeds[2*i_SeedCount+1];
                for (long i_ManySeedCount = i_start; i_ManySeedCount < i_start+i_range; i_ManySeedCount++){
                    long temp = getLocation(i_ManySeedCount, seed_soil, soil_fertilizer,
                            fertilizer_water, water_light, light_temperature, temperature_humidity, humidity_location);
                    if (temp < closestLocation){
                        closestLocation = temp;
                    }
                }
            }
            System.out.println(closestLocation);
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
    public static long[][] addEmptyRow( long[][] existingArray , int width){
        long[][] outputArray = new long[existingArray.length+1][width];
        for (int i_rowCounter = 0; i_rowCounter < outputArray.length-1; i_rowCounter++) {
            for (int j_columnCounter = 0; j_columnCounter < width; j_columnCounter++){
                outputArray[i_rowCounter][j_columnCounter] = existingArray[i_rowCounter][j_columnCounter];
            }
        }
        outputArray[outputArray.length-1] = new long[width];
        return outputArray;
    }

    public static long getLocation(long seed, long[][] seed_soil, long[][] soil_fert, long[][] fert_water,
                                   long[][] water_light, long[][] light_temp, long[][] temp_hum, long[][] hum_loc){
        // start with seed to seed_soil
        long next = translateSourceToDestination(seed, seed_soil);
        next = translateSourceToDestination(next, soil_fert);
        next = translateSourceToDestination(next, fert_water);
        next = translateSourceToDestination(next, water_light);
        next = translateSourceToDestination(next, light_temp);
        next = translateSourceToDestination(next, temp_hum);
        next = translateSourceToDestination(next, hum_loc);
        return next;
    }

    public static long translateSourceToDestination(long a, long[][] b){
        long next = -1;
        for (int i_seedCount = 0; i_seedCount<b.length; i_seedCount++){
            if (a >= b[i_seedCount][1] && a <= b[i_seedCount][1] + b[i_seedCount][2]){
                return a + (b[i_seedCount][0] - b[i_seedCount][1]);
            }
        }
        return a;
    }
}
