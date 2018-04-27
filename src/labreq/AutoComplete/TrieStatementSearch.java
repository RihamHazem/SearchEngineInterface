package labreq.AutoComplete;

import labreq.DB;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TrieStatementSearch {

    // trie node
    public class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();

        // isEndOfStatement is true if the node represents
        // end of a word
        boolean isEndOfStatement;

        TrieNode() {
            isEndOfStatement = false;
        }
    }

    private TrieNode root;
    private DB db = new DB();

    public TrieStatementSearch() {
        root = new TrieNode();
    }

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node
    public void insert(String[] key) {
        TrieNode pCrawl = root;

        for (String index: key) {
            if (pCrawl.children.get(index) == null)
                pCrawl.children.put(index, new TrieNode());

            pCrawl = pCrawl.children.get(index);
        }

        // mark last node as leaf
        pCrawl.isEndOfStatement = true;
    }

    // Returns true if key presents in trie, else false
    public boolean search(String[] key) {
        TrieNode pCrawl = root;

        for (String index: key) {

            if (pCrawl.children.get(index) == null)
                return false;

            pCrawl = pCrawl.children.get(index);
        }

        return (pCrawl != null && pCrawl.isEndOfStatement);
    }

    public void traverse() {
        traverse(root);
    }

    // Returns true if key presents in trie, else false
    public void traverse(TrieNode pCrawl) {
        for (Map.Entry<String, TrieNode> node : pCrawl.children.entrySet()) {
            if (node.getValue() != null) {
                System.out.println(node);
                // if it's not a loaf then traverse it
                System.out.println("I'm traversing again");
                traverse(node.getValue());
            }
        }
    }

    public ArrayList<ArrayList<String>> autoCompleteWord(ArrayList<String> statement) {
        if (statement.size() == 0) return null;
        boolean exclude = false;
        int lstTrue = 0;
        TrieNode pCrawl = root;
        ArrayList<ArrayList<String>> allPrefix = new ArrayList<>();
        for (String index: statement) { // TODO:check that first word is found
            if (pCrawl.children.get(index) == null) {
                System.out.println("I'm breaked " + index);
                exclude = true;
                break;
            }
            pCrawl = pCrawl.children.get(index);
            lstTrue++;
        }
        if (pCrawl == root) {
            // Statement not found we can add it to DATABASE
            return null;
        }
        if (lstTrue < statement.size()-1) {
            return null;
        }
//        String lstItem = statement.get(statement.size()-1);
        if (exclude) {
            statement.remove(statement.size()-1);
        }
        for (Map.Entry<String, TrieNode> node: pCrawl.children.entrySet()) {
            ArrayList<String> baseWord = new ArrayList<>(statement);
            baseWord.add(node.getKey());
            if (node.getValue() != null) {
                if (node.getValue().isEndOfStatement) {
                    allPrefix.add(baseWord);
                }
                ArrayList<ArrayList<String>> newStatements = traverseStatement(baseWord, node.getValue());
                if (newStatements != null && newStatements.size() > 0) {
                    allPrefix.addAll(newStatements);
                }
            }
        }
        return allPrefix;
    }

    private ArrayList<ArrayList<String>> traverseStatement(ArrayList<String> initStatement, TrieNode rt) {
        ArrayList<ArrayList<String>> statements = new ArrayList<>();
        for (Map.Entry<String, TrieNode> node : rt.children.entrySet()) {
            if (node.getValue() != null) {
                ArrayList<String> baseWord = new ArrayList<>(initStatement);
                baseWord.add(node.getKey());
                ArrayList<ArrayList<String>> newWords = traverseStatement(baseWord, node.getValue());
                if (node.getValue().isEndOfStatement) {
                    newWords.add(baseWord);
                }
                statements.addAll(newWords);
            }
        }
        return statements;
    }
    public void fillTrieStatementSearch() {
        System.out.println("I'm heee");
        ArrayList<String> al = db.getUserStatements();
        for (String anAl: al) {
                String[] line = anAl
                        .toLowerCase()
                        .replace(",", "")
                        .replace(".", "")
                        .replace(";", "")
                        .split("\\s+");
                insert(line);
        }
    }
}
