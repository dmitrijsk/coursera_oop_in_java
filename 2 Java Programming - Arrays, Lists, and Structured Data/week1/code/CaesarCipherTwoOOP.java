
/**
 * Write a description of TestCaesarCipherTwoOOP here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwoOOP {

    // Instance variables.
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    // Constructor.
    public CaesarCipherTwoOOP(int key1, int key2) {
        /*  This method should initialize all the private fields. */
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input) {
        /* This method returns a String that has been encrypted using 
         * two keys that were initialized by the constructor.
         * key1 is used to encrypt every other character, starting with 
         * the first character, 
         * key2 is used to encrypt every other character, starting with 
         * the second character. */
        StringBuilder output = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            String oneSymbol = input.substring(i, i+1);
            if (((i + 1) % 2) == 0) { // position is even.
                CaesarCipherOOP cco = new CaesarCipherOOP(mainKey1);
                oneSymbol = cco.encrypt(oneSymbol);
            } else {                  // position is odd.
                CaesarCipherOOP cco = new CaesarCipherOOP(mainKey2);
                oneSymbol = cco.encrypt(oneSymbol);
            }
            output.setCharAt(i, oneSymbol.charAt(0));
        }
        return output.toString();
    }

    public String decrypt(String input){
        /* This method returns a String that is the encrypted String 
         * decrypted using the key1 and key2 associated with this 
         * CaesarCipherTwo object. */

        CaesarCipherTwoOOP ccto = new CaesarCipherTwoOOP(26 - mainKey1, 
                                                         26 - mainKey2);
        return ccto.encrypt(input);
    }
}
