
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        /* This method should read in the words from resource and count 
         * the number of words of each length for all the words in resource, 
         * storing these counts in the array counts. */

        for (String word : resource.words()) {
            int wordLen = word.length();
            
            char firstChar = word.charAt(0); 
            System.out.println("First char: " + firstChar);
            char lastChar = word.charAt(wordLen-1);
            System.out.println("Last char: " + lastChar);
            if (!Character.isAlphabetic(firstChar)) wordLen--;
            if (!Character.isAlphabetic(lastChar))  wordLen--;
            
            System.out.println(word + "\t\t" + wordLen);
            if (wordLen > 30) {
                wordLen = 30;
            }
            counts[wordLen]++;
        }

        for (int i = 1; i < counts.length; i++) {
            System.out.println("Length " + i + ": " + counts[i] + " words" );    
        }

    }

    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);

    }
}
