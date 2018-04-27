package labreq;

import com.mongodb.client.FindIterable;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import com.mongodb.client.model.Indexes;
import org.bson.conversions.Bson;

import java.util.*;

public class DB {
    private MongoClient mongo;
    private MongoDatabase database;
    public MongoCollection<Document> collection;
    public MongoCollection<Document> wordCollection;
    private MongoCollection<Document> URLcollection;
    private MongoCollection<Document> updatescollection;
    private MongoCollection<Document> suggestionsCollection;
    private MongoCollection<Document> QueryURLs;

    public DB() {
        //Creating a Mongo client
        mongo = new MongoClient();
        database = mongo.getDatabase("apt");
        // Accessing the database
        //getting the collection
        collection = database.getCollection("indexer");
        wordCollection = database.getCollection("wordcoll");
        wordCollection.createIndex(Indexes.ascending("word"));
        QueryURLs = database.getCollection("query_url");
        QueryURLs.createIndex(Indexes.ascending("url"));

        updatescollection=database.getCollection("updates");
        URLcollection = database.getCollection("URLs");
        suggestionsCollection = database.getCollection("suggestions");

        BasicDBObject bObj = new BasicDBObject();
        bObj.put("URL", 1);
        collection.createIndex(bObj);

//        BasicDBObject bObj2 = new BasicDBObject();
//        bObj.put("statement", 1);
//        suggestionsCollection.createIndex(bObj2);
    }

    public ArrayList<String> getUserStatements() {
        List<Document> sc = suggestionsCollection.find().into(new ArrayList<>());
        ArrayList<String> res = new ArrayList<>();
        for (Document anSc: sc) {
            res.add(anSc.getString("statement"));
        }
        return res;
    }

    public void insertStatement(String statment) {
        final Map<String, Object> empMap = new HashMap<>();
        empMap.put("statement", statment);
        suggestionsCollection.insertOne(new Document(empMap));
    }

    public String getDesc(String URL)
    {
        Bson filter = Filters.eq("URL", URL);
        String s="";
        FindIterable<Document> iterDoc = URLcollection.find(filter);
        //ArrayList<String> arr = new ArrayList();
        for (Document d1 : iterDoc) {
            System.out.println(d1);
            System.out.println((String) d1.get("description"));
            s=((String) d1.get("description"));
        }
        if(s.equals(""))
        {
            FindIterable<Document> iterDoc2 = updatescollection.find(filter);
            for (Document d2 : iterDoc2) {
                System.out.println(d2);
                System.out.println((String) d2.get("description"));
                s=((String) d2.get("description"));
            }
        }
        return s;
    }
    public String getTitle(String URL)
    {
        Bson filter = Filters.eq("URL", URL);
        String s="";
        FindIterable<Document> iterDoc = URLcollection.find(filter);
        //ArrayList<String> arr = new ArrayList();
        for (Document d1 : iterDoc) {
            System.out.println(d1);
            System.out.println((String) d1.get("title"));
            s=((String) d1.get("title"));
        }
        if(s.equals(""))
        {
            FindIterable<Document> iterDoc2 = updatescollection.find(filter);
            for (Document d2 : iterDoc2) {
                System.out.println(d2);
                System.out.println((String) d2.get("title"));
                s=((String) d2.get("title"));
            }
        }
        return s;
    }

    public ArrayList<String> retrieveURLs(String word) {
        Bson filter = Filters.eq("word", word);
        //FindIterable<Document> iterDoc = QueryURLs.find(filter);
        FindIterable<Document> iterDoc = wordCollection.find(filter);
        ArrayList<String> arr = new ArrayList();
        //Iterator it = iterDoc.iterator();

        for (Document d1 : iterDoc) {
            System.out.println(d1.get("urls"));
            arr.addAll((ArrayList<String>) d1.get("urls"));
            // System.out.println(d1.get("urls"));
        }
        return arr;
    }


    public Map<String, String> checkPhraseWords(String url, ArrayList<String> stemmedWord, ArrayList<String> originalWord, Double[] size) {

        System.out.println("Entered retrieve URLs**************");

        Bson filter1 = Filters.eq("URL", url);
        FindIterable<Document> iterDoc1 = collection.find(filter1);
        System.out.print("the positions for the following URL: ");
        System.out.print(url);
        System.out.println(" are: ");
        TreeMap<String, String> originalWordPosition = new TreeMap<String, String>();
        for (Document d1 : iterDoc1) {
            size[0] = d1.getDouble("size");

            System.out.println("printing the size of doc  " + size[0]);

            Document doc1 = (Document) d1.get("words");
            if (doc1 != null) {
                for (int i = 0; i < stemmedWord.size(); i++) {
                    Document doc2 = (Document) doc1.get(stemmedWord.get(i));
                    if (doc2 != null) {
                        Document doc3 = (Document) doc2.get(originalWord.get(i));
                        if (doc3 != null) {
                            ArrayList<String> positions = new ArrayList();
                            positions.addAll((ArrayList<String>) doc3.get("positions"));
                            System.out.println(positions);
                            for (String ab : positions) {
                                System.out.println(ab);
                                originalWordPosition.put(ab, originalWord.get(i));
                            }
                        } else {
                            originalWordPosition.clear();
                            return originalWordPosition;
                        }
                    }
                }
                //TreeMap<Object, String> treeMap = new TreeMap<Object,String>;
                System.out.println(originalWordPosition);


            }
        }


        return originalWordPosition;

    }

    public double checkMatching(Map<String, String> wordPosition, ArrayList<String> originalQuery, Double[] size) {

        int idx = -1;
        int i = 0;
        double phraseCount = 0.0;

        ArrayList<Integer> mappingPositions = new ArrayList<>();
        ArrayList<String> mappingWords = new ArrayList<>();
        ArrayList<String> paragraphOrHeader = new ArrayList<>();
        for (Map.Entry<String, String> mp : wordPosition.entrySet()) {
            String[] parsingStrToIntFactor = mp.getKey().split("-");

            mappingPositions.add(Integer.parseInt(parsingStrToIntFactor[0]));
            paragraphOrHeader.add(parsingStrToIntFactor[1]);
            mappingWords.add(mp.getValue());
        }
        //String val=wordPosition.get(mappingPositions.get(i));
        for (int j = 0; j < wordPosition.size(); j++) {

            if ((!mappingWords.get(j).equals(originalQuery.get(i))) || ((j > 0) && (i > 0) && (mappingWords.get(j).equals(originalQuery.get(i))) && (mappingPositions.get(j) - mappingPositions.get(j - 1) != 1))) {
                if (mappingWords.get(j).equals(originalQuery.get(0))) {
                    i = 1;
                } else
                    i = 0;
                System.out.println("entered first one");
            } else if ((i == originalQuery.size() - 1) && (mappingWords.get(j).equals(originalQuery.get(i))) && ((i > 0) && (j > 0) && (mappingPositions.get(j) - mappingPositions.get(j - 1) == 1))) {
                //phraseCount++;
                if (paragraphOrHeader.get(j).equals("h"))
                    phraseCount += (1 / size[0]) * originalQuery.size() * 0.6;
                else
                    phraseCount += (1 / size[0]) * originalQuery.size() * 0.4;
                i = 0;
                System.out.println("entered second one, the factor is: " + paragraphOrHeader.get(j));
            } else if (mappingWords.get(j).equals(originalQuery.get(i))) {
                i++;
                System.out.println("entered third one");
            }

            System.out.println(mappingPositions.get(j) + " " + mappingWords.get(j) + " " + phraseCount + " " + i);
        }


        return phraseCount;
    }
}
