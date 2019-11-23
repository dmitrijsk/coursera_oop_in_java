
/**
 * Write a description of TestCaesarCipherTwoOOP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwoOOP {

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

    private String halfOfString(String input, int start) {
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
        /* This method should figure out which keys were used to encrypt this 
         * message (in a similar manner as before), 
         * then create a CaesarCipherTwo object with that key and 
         * decrypt the message. */

        String halfOne = halfOfString(input, 0);
        String halfTwo = halfOfString(input, 1);

        int dkey2 = getKey(halfOne);
        System.out.println("Decryption key is: " + dkey2);
        int dkey1 = getKey(halfTwo);
        System.out.println("Decryption key is: " + dkey1);

        CaesarCipherTwoOOP ccto = new CaesarCipherTwoOOP(dkey1, dkey2);

        return ccto.decrypt(input);
    }

    public void simpleTests() {
        /* This method should read in a file as a String, 
         * create a CaesarCipherTwo object with keys 17 and 3, 
         * encrypt the String using the CaesarCipherTwo object, 
         * print the encrypted String, 
         * and decrypt the encrypted String using the decrypt method.*/

        // FileResource fr = new FileResource();
        // String original = fr.asString();

        String original = "Can you imagine life WITHOUT the internet AND computers in your pocket?";

        CaesarCipherTwoOOP ccto = new CaesarCipherTwoOOP(21, 8);
        String encrypted = ccto.encrypt(original);
        String decrypted = ccto.decrypt(encrypted);

        System.out.println("---- Testing methods `encrypt` and `decrypt` ----");
        System.out.println("Original : " + original);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        System.out.println("---- Testing method `breakCaesarCipher` ----");
        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

}
