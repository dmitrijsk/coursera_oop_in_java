import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWordsList; // contains used words so that they don't repeat.
    private ArrayList<String> usedCategoriesList; // contains used categories.

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        usedWordsList = new ArrayList<String>();
        usedCategoriesList = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        usedWordsList = new ArrayList<String>();
        usedCategoriesList = new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] as = {"adjective", "noun", "color", "country", "name", 
                "animal", "timeframe", "verb", "fruit"};
        for (int i = 0; i < as.length; i++) {
            ArrayList<String> al = readIt(source+"/"+as[i]+".txt");
            // System.out.println("Initializing " + as[i] + ":");
            // System.out.println(al);
            myMap.put(as[i], al);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (myMap.containsKey(label)) {
            usedCategoriesList.add(label);
            return randomFrom(myMap.get(label));
        }
        if (label.equals("number"))   return ""+myRandom.nextInt(50)+5;
        return "**UNKNOWN**"; // if label not found in myMap and is not a number.
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);

        String sub;
        do {
            sub = getSubstitute(w.substring(first+1,last));
        } while (usedWordsList.contains(sub) == true);

        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        usedWordsList.clear();
        usedCategoriesList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\nTotal number of words to choose from: " + 
            totalWordsInMap());
            System.out.println("Total number of words considered: " + 
            totalWordsConsidered());
    }

    private int totalWordsInMap() {
        /* This method returns the total number of words in all the ArrayLists 
         * in the HashMap. */
        int totalWords = 0;
        for (String category : myMap.keySet()) {
            totalWords += myMap.get(category).size();
        }
        return totalWords;
    }

    private int totalWordsConsidered() {
        /* This method returns the total number of words in the ArrayLists of 
         * the categories that were used for a particular GladLib. 
         * If only noun, color, and adjective were the categories used in a 
         * GladLib, then only calculate the sum of all the words in those three 
         * ArrayLists. */
        int totalWords = 0;
        for (String category : myMap.keySet()) {
            if (usedCategoriesList.contains(category)) {
                totalWords += myMap.get(category).size();
            }
        }
        return totalWords;
    }
}
