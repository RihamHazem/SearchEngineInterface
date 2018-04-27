package labreq;

import java.util.*;

public class PhraseSearch {
    private static DB mydb;
    PhraseSearch()
    {
        mydb = new DB();
    }
    public Map<String,Double> work(ArrayList<String> stemmedQuery,ArrayList<String> originalQuery)
    {
        System.out.println("Enter the job of phrase search!");


        //making the map to store the urls
        System.out.println("making the hash table");
        Map<String,Double> urlSet=new HashMap<>();
        Map<String, Integer> URLsContainingPhrase = new HashMap<>();
        ArrayList<String> urlSetThisTime=new ArrayList<>();

        for(int i=0;i<stemmedQuery.size()-1;i++)
        {

            //clear ArrayList at the end of the loop to store the urls of the new word
            urlSetThisTime.clear();
            //search for the new URLs of the new word
            urlSetThisTime.addAll(mydb.retrieveURLs(stemmedQuery.get(i)));
            System.out.println("print URLs from phrase search");
            System.out.println(urlSetThisTime);
            for(String s:urlSetThisTime){
                Integer count=URLsContainingPhrase.get(s);
                if(count != null)
                    count++;
                else
                    count=1;

                URLsContainingPhrase.put(s,count);

            }

        }

        //check if the url has all the words in the query then we will add it to the set of URLs
        //clear ArrayList at the end of the loop to store the urls of the new word
        urlSetThisTime.clear();
        //search for the new URLs of the last word
        System.out.println("the last time in  phrase search");
        urlSetThisTime.addAll(mydb.retrieveURLs(stemmedQuery.get(stemmedQuery.size()-1)));
        for(String s:urlSetThisTime){
            Integer count=URLsContainingPhrase.get(s);
            if(count == null)
                count=1;
            if((count == stemmedQuery.size()-1))//this means that the last word in the phrase exists in this URL so add it to the set of URLs
            {
                Double[] size=new Double[1];

                Map<String,String>mp=mydb.checkPhraseWords(s,stemmedQuery,originalQuery,size);
                System.out.println("size after returning from function is: "+size[0]);
                if(mp.size() != 0)
                {
                    double phraseTF=mydb.checkMatching(mp,originalQuery,size);
                    System.out.print("TF: ");
                    System.out.println(phraseTF);
                    urlSet.put(s,phraseTF);
                }

            }

        }

        //clear ArrayList at the end of the loop to store the urls of the new word
        urlSetThisTime.clear();

        return urlSet;
    }
}
