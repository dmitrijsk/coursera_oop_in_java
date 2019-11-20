
/**
 * Write a description of ParseWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParseWeatherData {

    public CSVRecord UpdateRecord(CSVRecord coldestRecordSoFar, CSVRecord currRecord) {

        if (coldestRecordSoFar == null) {
            coldestRecordSoFar = currRecord;
        } else {
            double maxTemp = Double.parseDouble(coldestRecordSoFar.get("TemperatureF"));
            double currTemp = Double.parseDouble(currRecord.get("TemperatureF"));
            if (currTemp < maxTemp && currTemp != -9999) {
                coldestRecordSoFar = currRecord;

            }
        }
        return coldestRecordSoFar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecordSoFar = null;
        for (CSVRecord currRecord : parser) {
            coldestRecordSoFar = UpdateRecord(coldestRecordSoFar, currRecord);
        }
        return coldestRecordSoFar;
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2015/weather-2015-01-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + 
            coldest.get("TemperatureF") + " at EST " + 
            coldest.get("TimeEST") + " on UTC " + 
            coldest.get("DateUTC"));
    }

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestRecordSoFar = null;
        String coldestFileName = "";

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRecord = coldestHourInFile(fr.getCSVParser());
            coldestRecordSoFar = UpdateRecord(coldestRecordSoFar, currRecord);
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
}
