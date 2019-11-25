
/**
 * Write a description of MostFrequentWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        /* This method should first clear both myWords and myFreqs, using the .clear() method. 
         * Then it selects a file and then iterates over every word in the file, 
         * putting the unique words found into myWords. 
         * For each word in the kth position of myWords, 
         * it puts the count of how many times that word occurs from the selected 
         * file into the kth position of myFreqs, as was demonstrated in the lesson.*/
        myWords.clear();
        myFreqs.clear();

        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            // System.out.println("Next word: " + word);
            String wordLower = word.toLowerCase();
            if (!myWords.contains(wordLower)) { // first occurance.
                // System.out.println("Unique word.");
                myWords.add(wordLower);
                myFreqs.add(1);
            } else { // repeated occurance.
                // System.out.print("Repeated word ");
                int ndx = myWords.indexOf(wordLower);
                // System.out.print("at index " + ndx);
                int count = myFreqs.get(ndx);
                // System.out.println(" with old count " + count);
                myFreqs.set(ndx, count+1);
            }
        }
    }

    private int findIndexOfMax() {
        int maxValue = 0, indexOfMax = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            int curValue = myFreqs.get(i);
            if (curValue > maxValue) {
                maxValue = curValue;
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        Scanner sc = new Scanner(System.in);
        System.out.print("PRESS ANY KEY TO CONTINUE...");
        sc.next();
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        }
        int indexOfMax = findIndexOfMax();
        System.out.println("Most freq. word: " + myWords.get(indexOfMax) + "\t" + myFreqs.get(indexOfMax));
    }
}
