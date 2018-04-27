package labreq.DoYouMean;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TrieSearchTree {
    // Alphabet size (# of symbols)
    private final int ALPHABET_SIZE = 26;
//    private static TrieSearchTree dictionaryTrie = new TrieSearchTree();
    private static ArrayList<String> dictionaryArr = new ArrayList<>();
    private static Levenshtein levenshtein = new Levenshtein();

    // trie node
    class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // isEndOfStatement is true if the node represents
        // end of a word
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    }

    private TrieNode root;

    public TrieSearchTree() {
        root = new TrieNode();
    }

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node
    public void insert(String key) {
        key = key.toLowerCase();
        int length = key.length();

        TrieNode pCrawl = root;

        for (int level = 0; level < length; level++) {
            int index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        // mark last node as leaf
        pCrawl.isEndOfWord = true;
    }

    // Returns true if key presents in trie, else false
    public boolean search(String key) {
        key = key.toLowerCase();
        int length = key.length();
        TrieNode pCrawl = root;

        for (int level = 0; level < length; level++) {
            int index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }

        return (pCrawl != null && pCrawl.isEndOfWord);
    }

    public void traverse() {
        traverse(root);
    }

    // Returns true if key presents in trie, else false
    public void traverse(TrieNode pCrawl) {
        for (int i = 0; i < pCrawl.children.length; i++) {
            if (pCrawl.children[i] != null) {
                System.out.println((char)(i + 'a'));
                // if it's not a loaf then traverse it
                System.out.println("I'm traversing again");
                traverse(pCrawl.children[i]);
            }
        }
    }

    public ArrayList<String> autoCompleteWord(String word) {
        if (word.length() == 0) return null;

        TrieNode pCrawl = root;
        ArrayList<String> allPrefix = new ArrayList<>();
        int indx = word.charAt(0)-'a';
        int len = word.length();
        for (int i = 0; i < len; i++) {
            int index = word.charAt(i) - 'a';

            if (pCrawl.children[index] == null)
                break;

            pCrawl = pCrawl.children[index];
        }
        if (pCrawl == root) {
            // Word not found we can add it to database or whatever
            return null;
        }
        for (int i = 0; i < pCrawl.children.length; i++) {
            String baseWord = word+(char)(i+'a');
            if (pCrawl.children[i] != null) {
                if (pCrawl.children[i].isEndOfWord) {
                    allPrefix.add(baseWord);
                }
                ArrayList<String> newWords = traverseWord(baseWord, pCrawl.children[i]);
                if (newWords != null && newWords.size() > 0)
                    allPrefix.addAll(newWords);
            }
        }
        return allPrefix;
    }

    private ArrayList<String> traverseWord(String initWord, TrieNode rt) {
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < rt.children.length; i++) {
            if (rt.children[i] != null) {
                String baseWord = initWord + (char)(i+'a');
                ArrayList<String> newWords = traverseWord(baseWord, rt.children[i]);
                if (rt.children[i].isEndOfWord) {
                    newWords.add(baseWord);
                }
                words.addAll(newWords);
            }
        }
        return words;
    }

    public void fillTrieSearchDict() {
        File file = new File("/home/riham/IdeaProjects/labreq/words10K.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine().split("\\t+")[0];
                dictionaryArr.add(line);
                insert(line);
            }
            System.out.println(dictionaryArr.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * for do you mean
     * first we need to check whether the word is found or not in the fastest possible way
     * so we search the word first with the trie search algorithm
     * if unfortunately word is not found we use an array of all words to traverse it
     * and get the word with the smallest distance.
     */
    public String didYouMean(String sample) {
        PriorityQueue<Entry> suggestedWords = new PriorityQueue<>();
        if (!search(sample)) { // not found
            for (String word: dictionaryArr) {
                int dist = levenshtein.calculate(sample, word);
                suggestedWords.add(new Entry(word, dist));
            }
            if (suggestedWords.size() != 0) {
                Entry thePeak = suggestedWords.peek();
                if (thePeak != null) {
                    System.out.println("Suggested Words: " + thePeak.getKey());
                }
            } else {
                System.out.println("No Suggestions, Although it's not correct");
                return null;
            }
        } else { // Found then do nothing
            System.out.println("Word: " + sample + " is spelt correctly.");
            return null;
        }
//        ArrayList<String> res = new ArrayList<>();
//        int cnt = 0;
//        while (!suggestedWords.isEmpty()) {
//            Entry entry = new Entry(suggestedWords.poll());
//            res.add(entry.getKey());
//            cnt++;
//            if ( cnt > 10 ) break;
//        }
        return suggestedWords.peek().getKey();
    }
}
