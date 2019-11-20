
/**
 * Write a description of ParseWeatherData here.
 * 
 * @author Dmitrijs Kass
 * @version November 2019
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.Scanner;

public class ParseWeatherData {

    public CSVRecord UpdateMinTempRecord(CSVRecord coldestRecordSoFar, CSVRecord currRecord) {

        if (coldestRecordSoFar == null) {
            coldestRecordSoFar = currRecord;
        } else {
            double minTemp = Double.parseDouble(coldestRecordSoFar.get("TemperatureF"));
            double currTemp = Double.parseDouble(currRecord.get("TemperatureF"));
            if (currTemp < minTemp && currTemp != -9999) {
                coldestRecordSoFar = currRecord;

            }
        }
        return coldestRecordSoFar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecordSoFar = null;
        for (CSVRecord currRecord : parser) {
            coldestRecordSoFar = UpdateMinTempRecord(coldestRecordSoFar, currRecord);
        }
        return coldestRecordSoFar;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + 
            coldest.get("TemperatureF") + " on UTC " + coldest.get("DateUTC"));
    }

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestRecordSoFar = null;
        String coldestFileName = "";

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRecord = coldestHourInFile(fr.getCSVParser());
            coldestRecordSoFar = UpdateMinTempRecord(coldestRecordSoFar, currRecord);
            if (coldestRecordSoFar == currRecord) {
                coldestFileName = f.getPath();
            }
        }

        return coldestFileName;
    }

    public void testFileWithColdestTemperature() {
        String coldestFileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldestFileName);

        FileResource fr = new FileResource(coldestFileName);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());

        System.out.println("Coldest T on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the T on the coldest day were: ");

        CSVParser parser = fr.getCSVParser();
        for (CSVRecord currRecord : parser) {
            System.out.println(currRecord.get("DateUTC") + " " + currRecord.get("TemperatureF"));
        }

    }

    public CSVRecord UpdateMinHumRecord(CSVRecord lowestRecordSoFar, CSVRecord currRecord) {

        double minHum, currHum;

        if (lowestRecordSoFar == null) {
            lowestRecordSoFar = currRecord;
        } else {
            // Check lowest lowest if Humudity is available.
            String minHumStr = lowestRecordSoFar.get("Humidity");
            if (minHumStr == "N/A") {
                minHum = 9999;
            } else {
                minHum = Double.parseDouble(minHumStr);
            }
            // Check if current Humudity is available.
            String currHumString = currRecord.get("Humidity");
            // System.out.println("currHumString = " + currHumString);
            // System.out.println(currHumString == "N/A");
            if (currHumString.equals("N/A")) {
                currHum = 9999;
            } else {
                currHum = Double.parseDouble(currHumString);
            }

            if (currHum < minHum && currHum != 9999) {
                lowestRecordSoFar = currRecord;

            }
        }
        return lowestRecordSoFar;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestRecordSoFar = null;
        for (CSVRecord currRecord : parser) {
            lowestRecordSoFar = UpdateMinHumRecord(lowestRecordSoFar, currRecord);
        }
        return lowestRecordSoFar;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);

        System.out.println("Lowest humidity was " + 
            csv.get("Humidity") + " on " + csv.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestRecordSoFar = null;

        for (File f : dr.selectedFiles()) {
            System.out.println("Starting with filename:" + f.getName());
            FileResource fr = new FileResource(f);
            CSVRecord currRecord = lowestHumidityInFile(fr.getCSVParser());
            lowestRecordSoFar = UpdateMinHumRecord(lowestRecordSoFar, currRecord);
        }

        return lowestRecordSoFar;
    }

    public void testLowestHumidityInManyFiles() {

        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + 
            lowest.get("Humidity") + " at " + 
            lowest.get("DateUTC"));

    }

    public double averageTemperatureInFile(CSVParser parser, int value) {
        // Returns a double that represents the average temperature of 
        // only those temperatures when the humidity was greater than or 
        // equal to value.

        double sumTemp = 0, countTemp = 0, averageTemp = 0;

        for (CSVRecord currRecord : parser) {
            double currHum  = Double.parseDouble(currRecord.get("Humidity"));
            if (currHum >= value) {
                double currTemp = Double.parseDouble(currRecord.get("TemperatureF"));    
                sumTemp = sumTemp + currTemp;
                countTemp++;
            }           
        }

        if (countTemp != 0) averageTemp = sumTemp / countTemp;

        return averageTemp;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        //  Test method averageTemperatureInFile.
        FileResource fr = new FileResource();
        Scanner sc = new Scanner(System.in); // Create a Scanner object.
        String minHum = sc.nextLine();       // Read user input as a String.
        double averageTemp = averageTemperatureInFile(fr.getCSVParser(), 
                                                      Integer.parseInt(minHum));
        if (averageTemp == 0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when Humidity >= " + 
                               minHum + " is " + averageTemp);
        }

    }
}
