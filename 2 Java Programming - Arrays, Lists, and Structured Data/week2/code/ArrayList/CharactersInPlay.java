
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {

    private ArrayList<String> names;
    private ArrayList<Integer> freqs;

    public CharactersInPlay() {
        names = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }

    public void update(String person) {
        /* This method should update the two ArrayLists, 
         * adding the character’s name if it is not already there, 
         * and counting this line as one speaking part for this person. */
        String personLower = person.toLowerCase();
        int ndx = names.indexOf(personLower);
        if (ndx == -1) { // first occurance.
            names.add(personLower);
            freqs.add(1);
        } else { // repeated occurance.
            int count = freqs.get(ndx);
            freqs.set(ndx, count+1);
        }
    }

    public void findAllCharacters() {
        /* Write a void method called findAllCharacters that opens a file, 
         * and reads the file line-by-line. 
         * For each line, if there is a period on the line, 
         * extract the possible name of the speaking part, 
         * and call update to count it as an occurrence for this person. 
         * Make sure you clear the appropriate instance variables before each new file. */
        names.clear();
        freqs.clear();

        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int dotIndex = line.indexOf(".");
            if (dotIndex != -1) {
                String person = line.substring(0, dotIndex);
                update(person);
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("\nTop characters:");
        for (int i = 0; i < names.size(); i++) {
            int curFreq = freqs.get(i);
            if (curFreq >= num1 && curFreq <= num2) System.out.println(names.get(i) + "\t" + curFreq);
        }
    }

    public void tester() {
        /* This method should call findAllCharacters, 
         * and then for each main character, print out the main character, 
         * followed by the number of speaking parts that character has. 
         * A main character is one who has more speaking parts than most people.
         * You’ll have to estimate what that number should be. 
         * Test your method on the file macbethSmall.txt. and then macbeth.txt.*/
        findAllCharacters();
        for (int i = 0; i < names.size(); i++) {
            int curFreq = freqs.get(i);
            if (curFreq > 1) System.out.println(names.get(i) + "\t" + curFreq);
        }

        charactersWithNumParts(10, 15);
    }
}
