package labreq;

import labreq.AutoComplete.TrieStatementSearch;
import labreq.DoYouMean.Entry;
import labreq.DoYouMean.TrieSearchTree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;

@WebServlet(name = "Search",urlPatterns = "/Search")
public class SearchServlet extends HttpServlet {

    public static TrieSearchTree trieStatementSearch = new TrieSearchTree();
    public static TrieSearchTree dictionaryTrie = new TrieSearchTree();

    @Override
    public void init() throws ServletException {
        System.out.println("Init finished");
        dictionaryTrie.fillTrieSearchDict();
        trieStatementSearch.fillTrieStatementSearch();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = request.getParameter("msg").trim();
        System.out.println("msg: " + msg);
        if (msg.length() == 0) {
            System.out.println("it's empty");
            return;
        }
        /**
         * We can autocomplete a word to the user while he is writing
         * or autoComplete a statement
         * if statement isn't found and the user chose to search it
         * then we insert it in the tree
         */
//        ArrayList<String> test = new ArrayList<>();
//        String[] userInput = msg.split("\\s+");
//
//        for (String ui: userInput) {
//            test.add(ui.toLowerCase());
//        }
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        ArrayList< String > suggestedStatements = trieStatementSearch.autoCompleteWord(msg);
        if (suggestedStatements != null) {
            suggestedStatements.forEach((k)-> {
                out.print(k.replaceAll("\\{", " ")+",");
                System.out.println("Suggested Statements: " + k);
            });

        }
        else {
            out.print("");
            System.out.println("No Suggestions");
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
