
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public void tester() {
        FileResource fr = new FileResource("./exports/exportdata.csv");

        CSVParser parser = fr.getCSVParser();     
        System.out.println(countryInfo(parser, "Nauru"));
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
        
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
        
    }

    public String countryInfo(CSVParser parser, String country) {
        String result;

        for (CSVRecord record : parser) {
            String currCountry = record.get("Country");
            if (currCountry.equals(country)) {
                result = currCountry + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return result;
            }
        }

        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        // This method prints the names of all the countries that have both exportItem1 and exportItem2 
        // as export items. 
        // For example, using the file exports_small.csv, this method called with the items “gold” and 
        // “diamonds” would print the countries

        for (CSVRecord record : parser) {
            String currExports = record.get("Exports");
            if (currExports.contains(exportItem1) && currExports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        
        for (CSVRecord record : parser) {
            String currExports = record.get("Exports");
            if (currExports.contains(exportItem)) {
                count++;
            }
        }

        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        String currValue;
        for (CSVRecord record : parser) {
            currValue = record.get("Value (dollars)");
            if (currValue.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + currValue);
            }
        }
    }
}
