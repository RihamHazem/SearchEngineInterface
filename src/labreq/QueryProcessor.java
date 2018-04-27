package labreq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class QueryProcessor {
    private DB mydb;

    QueryProcessor() {
        mydb = new DB();
    }

    public Map<String, Double> work(ArrayList<String> stemmedQuery) {
        System.out.println("Enter the job of Query processor!");


        Set<String> urlSet = new HashSet<>();
        Map<String, Double> urlsMap = new HashMap<>();
        for (int i = 0; i < stemmedQuery.size(); i++) {
            urlSet.addAll(mydb.retrieveURLs(stemmedQuery.get(i)));
        }
        //print the values in the set just for testing
        for (String s : urlSet) {
            urlsMap.put(s, 0.0);
            System.out.println(s);
        }

        return urlsMap;


    }
}
