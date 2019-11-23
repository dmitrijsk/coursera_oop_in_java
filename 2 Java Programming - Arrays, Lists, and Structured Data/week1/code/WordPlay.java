
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(Character ch) {
        /* This method returns true if ch is a vowel 
         * (one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) 
         * and false otherwise. */
        String vowels = "aeiou";
        for (int i = 0; i < vowels.length(); i++) {
            if (Character.toLowerCase(ch) == vowels.charAt(i)) return true;
        }
        return false; // ch was not found in String vowels.
    }

    public void testIsVowel() {
        /* Tester method to see if this method works correctly.
         * For example, isVowel(‘F’) should return false, 
         * and isVowel(‘a’) should return true. */
        String text = "QWERTYUIOP[]ASDFGHJKL;\'ZXCVBNM,./1234567890-=!@#$%^&*()_+qwertyuiop[]\';lkjhgfdsazxcvbnm,./";
        for (int i = 0; i < text.length(); i++) {
            char charToTest = text.charAt(i);
            boolean res = isVowel(charToTest);
            System.out.println("Char "+ charToTest + ": " + res);
        }
    }

    public String replaceVowels(String phrase, char ch) {
        /* Write a method replaceVowels that has two parameters, 
         * a String named phrase and a Char named ch. 
         * This method should return a String that is the string phrase with 
         * all the vowels (uppercase or lowercase) replaced by ch. */
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char charToTest = phrase.charAt(i);
            if (isVowel(charToTest)) { // if true then replace with `ch`.
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }

    public void testReplaceWowels() {
        String text = "Hello, World At One!";
        System.out.println("Original: " + text);
        System.out.println("Replaced: " + replaceVowels(text, '*'));
    }

    public String emphasize(String phrase, char ch ) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char charToTest = phrase.charAt(i);
            if (Character.toLowerCase(charToTest) == Character.toLowerCase(ch)) {
                if (((i + 1) % 2) == 0) { // position is even.
                    sb.setCharAt(i, '+');
                } else {                  // position is odd.
                    sb.setCharAt(i, '*');
                }
            }
        }
        return sb.toString();
    }

    public void testEmphasize() {
        String text = "dna ctgaaactga";
        System.out.println("Original: " + text);
        System.out.println("Replaced: " + emphasize(text, 'a'));

        text = "Mary Bella Abracadabra";
        System.out.println("Original: " + text);
        System.out.println("Replaced: " + emphasize(text, 'a'));
    }
}
