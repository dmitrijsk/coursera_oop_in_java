
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> wordMap;

    public WordsInFiles() {
        wordMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        /* This method should add all the words from f into the map. */
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            /* If a word is not in the map, 
             * then you must create a new ArrayList of type String with this word,
             * and have the word map to this ArrayList. */
            if (!wordMap.containsKey(word)) {
                ArrayList<String> al = new ArrayList<String>();
                al.add(f.getName());
                wordMap.put(word, al);
            } 
            /* If a word is already in the map, 
             * then add the current filename to its ArrayList, 
             * unless the filename is already in the ArrayList.*/
            else {
                ArrayList<String> al = wordMap.get(word);
                al.add(f.getName());
                wordMap.put(word, al);
            }
        }
    }

    public void buildWordFileMap() {
        /* This method first clears the map, 
         * and then uses a DirectoryResource to select a group of files. 
         * For each file, it puts all of its words into the map by calling 
         * the method addWordsFromFile. */

        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    private int maxNumber() {
        /* This method returns the maximum number of files any word appears in,
         * considering all words from a group of files. */ 

        int maxValue = 0;
        for (String word : wordMap.keySet()) {
            int curValue = wordMap.get(word).size();
            if (curValue > maxValue) maxValue = curValue;
        }
        return maxValue;
    }

    private ArrayList<String> wordsInNumFiles(int number) {
        /* This method returns an ArrayList of words 
         * that appear in exactly number files. */

        ArrayList<String> al = new ArrayList<String>();
        for (String word : wordMap.keySet()) {
            if (wordMap.get(word).size() == number) al.add(word);
        }
        return al;
    }

    public void printFilesIn(String word) {
        /* This method prints the names of the files this word appears in, 
         * one filename per line. */

        ArrayList<String> al = wordMap.get(word);
        System.out.println("\nNames of files that contains the word \"" + word + "\"");
        for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i));
        }
        
    }
    
    public void tester() {
        /* This method tests the other methods. */

        buildWordFileMap();
        System.out.println("Unique words in files:");
        for (String word : wordMap.keySet()) {
            System.out.print(word + ": ");
            System.out.println(wordMap.get(word));
        }
        System.out.println("\nMax number of files a word is in: " + maxNumber());
        System.out.println("\nWords that appear in 3 files: " + wordsInNumFiles(3));
        printFilesIn("cats");
    }
}
