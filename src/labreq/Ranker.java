/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labreq;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Scanner;
/**
 *
 * @author Reham Abdallatef
 */
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection; 
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.io.File;
import java.util.Scanner;
import javafx.util.Pair;
import org.bson.Document;
import org.bson.conversions.Bson;


public class Ranker {
    public static MongoCollection<Document> urlsCollection;
      public static MongoCollection<Document> RankCollection;
     //map<url,map<stem,pair<double,double>>
     static Map<String,List<Pair<Double,Double>>> map= new HashMap<String,List<Pair<Double,Double>>>();
     static List<String>Docs= new ArrayList<String>();
     static Map<String,Double>rank=new HashMap<>();

public static void Rankering(List<String> docs,List<String>stems,List<String>originals)
{
       MongoClient mongo = new MongoClient();
    MongoDatabase database = mongo.getDatabase("apt");
    urlsCollection = database.getCollection("indexer");
    FindIterable<Document>RCollection =(database.getCollection("pageRank")).find();
    for(Document Iter:RCollection)
    {
    String urll=Iter.getString("address");
    String rankk=Iter.getString("rank");
    rank.put(urll, Double.parseDouble(rankk));
    //Docs.add(urll);
  /*  System.out.print(urll);
    System.out.print(" ");
    System.out.println(rankk);*/
    }
    Docs=docs;	

    
    System.out.println(Docs.size());
for(String doc:Docs)
{

Bson filter=Filters.eq("URL",doc);
List free=new ArrayList<Pair<Double,Double>>();
Document data = urlsCollection.find(filter).first();
//System.out.println(data); 
for(int i=0 ;i< stems.size();i++){
    if(data ==null)continue;
Document words = (Document) data.get("words");
if(words ==null)continue;
Document stem=(Document) words.get(stems.get(i));
//System.out.print(stem); 
if(stem ==null)continue;
Double tf= (Double)stem.get("TF");

Document orgine=(Document)stem.get(originals.get(i));
Double inerTf=(Double)orgine.get("innerTF");
if(inerTf==null)inerTf=0.0;
if(tf==null)tf=0.0;
////////////////////////////
/* System.out.println(doc);
    System.out.print("  TF  ");
    System.out.println(tf);
    System.out.print("  innerTF  ");
    System.out.println(inerTf);*/
///////////////////////////////////////////////
Pair<Double,Double>ff = new Pair<Double,Double>(tf,inerTf);
free.add(ff);
}

if(free != null)
{ 
map.put(doc,free);
}
}



}
     public static Map<String,Double> sorted(Map<String,Double> x)
     {
     Map<String, Double> unsortedMap = x;

		//convert map to a List
    List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortedMap.entrySet());

		//sorting the list with a comparator
		Collections.sort(list, new Comparator<Entry<String, Double>>() {
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		//convert sortedMap back to Map
		Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
		for (Entry<String, Double> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
                return sortedMap;
	}
     
    public static Map run(List<String> docs,List<String>ll,List<String>orr) throws FileNotFoundException, InterruptedException {
     
           Rankering(docs,ll,orr);
     TotalRank finalRank;
        finalRank = new TotalRank(map,Docs,rank);
        finalRank.ComputeTotalRank();
      Map total= finalRank.Totalrank;
     Map TotalSort= sorted(total); //de ely feha elnatg el25er for Sara

        System.out.print(TotalSort);
	return TotalSort;
    } 
}

     
