
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
        // Tester creates CSVParser and calls each of the methods below.

        FileResource fr = new FileResource();

        CSVParser parser = fr.getCSVParser();     
        System.out.println(countryInfo(parser, "Nauru"));
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        parser = fr.getCSVParser();
        String exportItem = "cocoa";
        System.out.println("Number of exporters of " + 
                           exportItem + ": " + numberOfExporters(parser, exportItem));
        
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
        
    }

    public String countryInfo(CSVParser parser, String country) {
        // This method returns a string of information about the country or 
        // returns “NOT FOUND” if there is no information about the country.
        
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
        // This method prints the names of all the countries that have
        // both exportItem1 and exportItem2 as export items.

        System.out.println("Countries that export both " + exportItem1 + 
                           " and " + exportItem2 + ":");
        for (CSVRecord record : parser) {
            String currExports = record.get("Exports");
            if (currExports.contains(exportItem1) && currExports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        // This method returns the number of countries that export exportItem.
        
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
        // This method prints the names of countries and their Value amount 
        // for all countries whose Value (dollars) string is larger than the amount string.
        
        String currValue;
        for (CSVRecord record : parser) {
            currValue = record.get("Value (dollars)");
            if (currValue.length() > amount.length()) {
                System.out.println("Big exporters (above " + amount + "): " + 
                                   record.get("Country") + " " + currValue);
            }
        }
    }
}
