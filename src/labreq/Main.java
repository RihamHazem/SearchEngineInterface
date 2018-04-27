package labreq;

import labreq.AutoComplete.TrieStatementSearch;
import labreq.DoYouMean.Entry;
import labreq.DoYouMean.TrieSearchTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    private static TrieStatementSearch trieStatementSearch = new TrieStatementSearch();
    private static TrieSearchTree dictionaryTrie = new TrieSearchTree();

    public void main(String[] args) {
        dictionaryTrie.fillTrieSearchDict();
        trieStatementSearch.fillTrieStatementSearch();
        /**
         * We can autocomplete a word to the user while he is writing
         * or autoComplete a statement
         * if statement isn't found and the user chose to search it
         * then we insert it in the tree
         */
        ArrayList<String> testAutoComplete = new ArrayList<>();
        testAutoComplete.add("nulla");
        testAutoComplete.add("varius");

        ArrayList< ArrayList<String> > suggestedStatements = trieStatementSearch.autoCompleteWord(testAutoComplete);
        if (suggestedStatements != null)
            System.out.println("Suggested Statements: " + suggestedStatements.toString());
        else
            System.out.println("No Suggestions");
        ///////////////////////////////////
        String testDoYouMean = "comptuer";
//        ArrayList<String> suggestedWords = dictionaryTrie.didYouMean(testDoYouMean);
//        if (suggestedWords != null)
//            System.out.println("Did you Mean: " + suggestedWords.toString());
//        else
//            System.out.println("it's correct");
    }

}
