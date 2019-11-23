
/**
 * Write a description of CaesarCipherTwoKeysDecrypt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarBreaker {

    public int[] countLetters(String message) {
        /* This method counts the letters in a message 
         * and returns an array of integers. */

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            int indx = alphabet.indexOf(Character.toUpperCase(message.charAt(i)));
            if (indx != -1) {
                counts[indx]++;
            }
        }
        return counts;
    }

    public int maxIndex(int[] counts) {
        /* This method returns the index in an array where the value is maximum. */
        int maxValue = 0, maxIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            int currValue = counts[i];
            if (currValue > maxValue) {
                maxValue = currValue;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void testCountLettersAndMaxIndex() {
        /* This method tests the method countLetters. */

        String text = "AaBbb.,123CcZzZz";
        int[] res = countLetters(text);
        for (int i = 0; i < res.length; i++) {
            System.out.println("Index " + i + " count: " + res[i]);
        }
        System.out.println("Max index is " + maxIndex(res) + ".");
    } 

    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //                  0        1         2
        //                 012345678901234567890123456

        // System.out.println("Most popular E letter is: " + alphabet.charAt(maxDex));
        // System.out.println("Index of most popular letter is: " + maxDex);

        // Decryption key is the distance from most popular letter in an
        // encryptedv message to the letter "e", which is the most popular 
        // letter in English alphabet. 

        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public String decrypt(String encrypted, int knownKey) {
        CaesarCipher cc = new CaesarCipher();
        int dkey;
        if (knownKey == -1) {
            dkey = getKey(encrypted);
        } else {
            dkey = knownKey;
        }
        System.out.println("Decryption key is: " + dkey);
        return cc.encrypt(encrypted, 26-dkey);   
    }

    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        String encrypted = cc.encrypt(message, 17);
        String decrypted = decrypt(encrypted, -1);

        System.out.println("Orig. message: " + message);
        System.out.println("Encr. message: " + encrypted);
        System.out.println("Decr. message: " + decrypted);
    }

    public String halfOfString(String input, int start) {
        StringBuilder output = new StringBuilder("");

        // A better realization of the counter would be 
        // (int i = start; i < input.length; i += 2)
        // Then "if" would not be necessary.
        for (int i = 0; i < input.length(); i++) {
            if ((i - start) % 2 == 0) { // even
                output.append(input.charAt(i));
            }              
        }

        return output.toString();
    }

    public void testHalfOfString(){
        String text = "Qbkm Zgis";   
        System.out.println(halfOfString(text, 0));
        System.out.println(halfOfString(text, 1));
    }

    public String decryptTwoKeys(String encrypted, int knownKey1, int knownKey2){
        String halfOne = halfOfString(encrypted, 0);
        String halfTwo = halfOfString(encrypted, 1);

        String halfOneDecrypted = decrypt(halfOne, knownKey1);
        String halfTwoDecrypted = decrypt(halfTwo, knownKey2);

        StringBuilder output = new StringBuilder("");

        int maxLength = Math.max(halfOne.length(), halfTwo.length());
        for (int i = 0; i < maxLength; i++) {
            if (i < halfOneDecrypted.length()) {
                output.append(halfOneDecrypted.charAt(i));
            }
            if (i < halfTwoDecrypted.length()) {
                output.append(halfTwoDecrypted.charAt(i));
            }
        }

        System.out.println("Decrypted with two keys: " + output.toString());
        return output.toString();
    }

    public void testDecryptTwoKeys() {

        CaesarCipher cc = new CaesarCipher();
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeeees";
        //String message = "Top ncmy qkff vi vguv vbg ycpx";
        //String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        
        String encrypted = cc.encryptTwoKeys(message, 0, 1);
        String decrypted = decryptTwoKeys(encrypted, -1, -1);

        System.out.println("--- Two keys ---");
        System.out.println("Orig. message: " + message);
        System.out.println("Encr. message: " + encrypted);
        System.out.println("Decr. message: " + decrypted);
    }
}
