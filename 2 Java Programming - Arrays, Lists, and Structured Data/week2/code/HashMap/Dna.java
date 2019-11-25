
/**
 * Write a description of Dna here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Dna {

    private HashMap<String, Integer> codonMap;

    public Dna() {
        codonMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        /* This method will build a new map of codons mapped to their counts 
         * from the string dna with the reading frame with the position start 
         * (a value of 0, 1, or 2). */

        // CGTTCAAGTTCAA
        // 0         1
        // 0123456789012

        codonMap.clear();
        // System.out.println(dna);
        // System.out.println("String length: " + dna.length());
        for (int i=start; i < dna.length()-3; i += 3) {
            String codon = dna.substring(i, i+3);
            // System.out.println(i + "\t" + codon);
            if (codonMap.containsKey(codon)) {
                codonMap.put(codon, codonMap.get(codon) + 1);
            } else {
                codonMap.put(codon, 1);
            }
        }
    }

    public String getMostCommonCodon() {
        String mostCommon = "";
        int maxValue = 0;
        for (String codon : codonMap.keySet()) {
            int curValue = codonMap.get(codon);
            if (curValue > maxValue) {
                maxValue = curValue;
                mostCommon = codon; 
            }
        }
        return mostCommon;
    }

    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + 
            " and " + end + " inclusive are:");
        for (String codon : codonMap.keySet()) {
            int curValue = codonMap.get(codon);
            if (curValue >= start && curValue <= end) {
                System.out.println(codon + "\t" + curValue);
            }
        }
    }

    public void tester() {
        /* Write a tester method that prompts the user for a file that 
         * contains a DNA strand (could be upper or lower case letters in the 
         * file, convert them all to uppercase, since case should not matter). */
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        /* Then for each of the three possible reading frames, 
         * this method builds a HashMap of codons to their number of occurrences 
         * in the DNA strand, */ 

        for (int i = 0; i <= 2; i++) {
            buildCodonMap(i, dna);
            /* prints the total number of unique codons in the reading frame */
            System.out.println("Reading frame starting with " + i + 
                " results in " + codonMap.size() + " unique codons");
            /* prints the most common codon and its count, */ 
            String mostCommon = getMostCommonCodon();
            System.out.println("Most common codon: " + mostCommon + "\t" 
                + codonMap.get(mostCommon));
            /* and prints the codons and their number of occurrences for those 
             * codons whose number of occurrences in this reading frame are 
             * between two numbers inclusive. */
            printCodonCounts(7, 7);
        }
    }
}
