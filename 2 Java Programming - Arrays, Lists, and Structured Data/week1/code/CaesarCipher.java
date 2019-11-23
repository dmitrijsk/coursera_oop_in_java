
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        /* This method returns a String that has been encrypted using the Caesar Cipher algorithm */

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        // System.out.println(alphabet);
        // System.out.println(shiftedAlphabet);
        // System.out.println("Input: " + input);

        StringBuilder output = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {
            char inChar = input.charAt(i);
            boolean isLower = Character.isLowerCase(inChar);
            int chIndex = alphabet.indexOf(Character.toUpperCase(inChar));
            if (chIndex != -1) {
                char outChar = shiftedAlphabet.charAt(chIndex);
                if (isLower) {
                    outChar = Character.toLowerCase(outChar);
                }
                output.setCharAt(i, outChar);
            }
        }

        // System.out.println("Ouput: " + output);

        return output.toString();
    }

    public void testCaesar() {
        encrypt("FIRST LEGION ATTACK EAST FLANK!", 23);
        encrypt("First Legion", 23);
        encrypt("First Legion", 17);

        /*FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);*/
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        /* This method returns a String that has been encrypted using the following algorithm. 
         * Parameter key1 is used to encrypt every other character with 
         * the Caesar Cipher algorithm, starting with the first character, 
         * and key2 is used to encrypt every other character, 
         * starting with the second character. */

        StringBuilder output = new StringBuilder(input);
        // System.out.println("Input: " + output.toString());
        for (int i = 0; i < input.length(); i++) {
            String oneSymbol = input.substring(i, i+1);
            // System.out.println("Changing one symbol: " + oneSymbol);
            if (((i + 1) % 2) == 0) { // position is even.
                oneSymbol = encrypt(oneSymbol, key2);
            } else {                  // position is odd.
                oneSymbol = encrypt(oneSymbol, key1);
            }
            output.setCharAt(i, oneSymbol.charAt(0));
        }
        // System.out.println("Ouput: " + output.toString());
        return output.toString();
    }
    
    public void testEncryptTwoKeys() {
        encryptTwoKeys("First Legion", 23, 17);
    }
}
