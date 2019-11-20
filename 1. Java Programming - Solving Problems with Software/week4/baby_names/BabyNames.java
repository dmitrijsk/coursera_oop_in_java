
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {
    public void totalBirths() {
        /* This method counts the number of F and M baby births in a data file. */

        int totalBirths = 0, totalMBirths = 0, totalFBirths = 0, numBirths;
        String gender;

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            gender = rec.get(1); // Gender
            numBirths = Integer.parseInt(rec.get(2)); // Num births

            if (gender.equals("M")) {
                totalMBirths += numBirths;
            } else {
                totalFBirths += numBirths;
            }
        }

        System.out.println("Total num births: " + (totalMBirths + totalFBirths));
        System.out.println("Total M births: " + totalMBirths);
        System.out.println("Total F births: " + totalFBirths);
    }

    public int getRank(int year, String name, String gender) {
        /* This method returns the rank of the name in the file for the given 
         * gender, where rank 1 is the name with the largest number of births. 
         * If the name is not in the file, then -1 is returned. */

        int currRank = 0;
        String fileName = "us_babynames_small/yob" + year + "short.csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);

        for (CSVRecord rec : parser) {
            String currGender = rec.get(1); // Gender
            // If the gender is as required then...
            if (currGender.equals(gender)) {
                // Increase the rank counter.
                currRank++;
                String currName = rec.get(0); // Name
                // If the name is as required then return the current rank.
                if (currName.equals(name)) return currRank;
            }
        }

        return -1;
    }

    public String getName(int year, int rank, String gender) {
        /*  This method returns the name of the person in the file at this rank,
         * for the given gender, where rank 1 is the name with the largest number 
         * of births. If the rank does not exist in the file, then “NO NAME” is 
         * returned. */

        int currRank = 0;
        String fileName = "us_babynames_small/yob" + year + "short.csv";

        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);

        for (CSVRecord rec : parser) {
            String currGender = rec.get(1); // Gender
            // If the gender is as required then...
            if (currGender.equals(gender)) {
                // Increase the rank counter.
                currRank++;
                // If the rank is as required then return the current name.
                if (currRank == rank) return rec.get(0); // Name
            }
        }

        // Either name not found or requested rank does not exist.
        return "NO NAME";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        /* This method determines what name would have been named if they were 
         * born in a different year, based on the same popularity. */

        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);

        System.out.println(name + " born in " + year + 
            " would be " + newName + 
            " if she was born in " + newYear + ".");
    }

    public int yearOfHighestRank(String name, String gender) {
        /* This method selects a range of files to process and returns an integer,
         * the year with the highest rank for the name and gender. 
         * If the name and gender are not in any of the selected files, 
         * it should return -1. */
        int fileCounter = 0, maxRank = 0; 
        String filenameMaxRank = "";
        DirectoryResource dr = new DirectoryResource();
        // Loop over all selected files.
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currRank = 0;
            // Read one CSV file by rows.
            for (CSVRecord rec : fr.getCSVParser(false)) {
                String currGender = rec.get(1); // Gender
                if (currGender.equals(gender)) { // Correct gender.
                    currRank++;
                }
                String currName = rec.get(0); // Name
                if (currName.equals(name)) break;
            }
            // Make maxRank = currRank for the very first file.
            if (fileCounter == 0) {
                maxRank = currRank;
                filenameMaxRank = f.getName();
                fileCounter++;
            }
            if (currRank < maxRank) {
                maxRank = currRank;
                filenameMaxRank = f.getName();
            }
        }
        // Parse year from the filename.
        String yearStr = filenameMaxRank.replaceAll("[^0-9]","");
        return Integer.parseInt(yearStr);
    }

    public double getAverageRank(String name, String gender) {
        /* This method selects a range of files to process and returns a double 
         * representing the average rank of the name and gender over 
         * the selected files. It should return -1.0 if the name is not ranked 
         * in any of the selected files. */

        int sumRank = 0, countRank = 0, averageRank = -1; 
        DirectoryResource dr = new DirectoryResource();
        // Loop over all selected files.
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currRank = 0;
            // Read one CSV file by rows.
            for (CSVRecord rec : fr.getCSVParser(false)) {
                String currGender = rec.get(1); // Gender
                if (currGender.equals(gender)) { // Correct gender.
                    currRank++;
                }
                String currName = rec.get(0); // Name
                if (currName.equals(name)) {
                    sumRank +=currRank;
                    countRank++;         
                    break;
                }
            }
        }

        if (countRank != 0) averageRank = sumRank / countRank;
        return averageRank;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        /*  This method returns an integer, the total number of births of those 
         * names with the same gender and same year who are ranked higher than name.*/

        int totalBirths = 0;
        String fileName = "us_babynames_small/yob" + year + "short.csv";

        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);

        for (CSVRecord rec : parser) {
            String currGender = rec.get(1); // Gender
            // If the gender is as required then...
            if (currGender.equals(gender)) {
                if (currGender.equals(gender)) {
                    String currName = rec.get(0); // Name.
                    if (currName.equals(name)) {
                        return totalBirths;
                    } else {
                        int currBirths = Integer.parseInt(rec.get(2)); // Number of births.
                        totalBirths += currBirths;
                    }
                }
            }
        }

        // Either name has highest rank or was not found.
        return 0;
    }
}
