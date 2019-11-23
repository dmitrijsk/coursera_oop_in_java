
/**
 * Write a description of CaesarCipherOOP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherOOP {

    // Instance variables.
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    // Constructor.
    public CaesarCipherOOP(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        /* This method returns a String that is the input encrypted 
         * using shiftedAlphabet. */

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
        return output.toString();
    }

    public String decrypt(String input) {
        /* This method returns a String that is the encrypted String decrypted 
         * using the key associated with this CaesarCipher object.*/
        CaesarCipherOOP cco = new CaesarCipherOOP(26 - mainKey);
        return cco.encrypt(input);
    }
}
