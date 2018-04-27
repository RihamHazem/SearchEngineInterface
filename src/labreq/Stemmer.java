package labreq;

import java.util.ArrayList;


public class Stemmer {
    public ArrayList<String> work(ArrayList<String> nonStopWords) {
        PorterStemmer stemmer = new PorterStemmer();
        ArrayList<String> stemmedWords = new ArrayList<>();
        for(String nsw: nonStopWords) {
            stemmedWords.add(stemmer.stemWord(nsw));
        }
        return stemmedWords;
    }

}
