
/**
 * Write a description of TestCaesarCipherOOP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherOOP {

    private int maxIndex(int[] counts) {
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

    private int[] countLetters(String message) {
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

    private int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public String breakCaesarCipher(String input) {
        int dkey = getKey(input);
        System.out.println("Decryption key is: " + dkey);
        CaesarCipherOOP cco = new CaesarCipherOOP(dkey);     
        return cco.decrypt(input);
    }

    public void simpleTests() {
        /* This method should read in a file as a String, 
         * create a CaesarCipher object with key 18, 
         * encrypt the String read in using the CaesarCipher object, 
         * print the encrypted String, 
         * and decrypt the encrypted String using the decrypt method. */
        
        // FileResource fr = new FileResource();
        // String original = fr.asString();
        
        String original = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipherOOP cc = new CaesarCipherOOP(15);
        String encrypted = cc.encrypt(original);
        String decrypted = cc.decrypt(encrypted);

        System.out.println("---- Testing methods `encrypt` and `decrypt` ----");
        System.out.println("Original : " + original);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        System.out.println("---- Testing method `breakCaesarCipher` ----");
        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

}
