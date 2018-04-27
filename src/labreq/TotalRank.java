/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labreq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author Reham Abdallatef
 */
public class TotalRank {
       Map<String,List<Pair<Double,Double>>> map=new HashMap<String,List<Pair<Double,Double>>>();
      List<String>Docs= new ArrayList<String>();
      Map<String,Double>rank=new HashMap<>();
      Map<String,Double>Totalrank=new HashMap<>();
    TotalRank(){}
    TotalRank(Map<String,List<Pair<Double,Double>>> maping,List<String>docs,Map<String,Double>rankmap)
   {  
   map=maping;
   Docs=docs;
   rank=rankmap;
   }
    void ComputeTotalRank()
    {// System.out.print("here");
    for(int i=0;i<Docs.size();i++)
    {
        Double sum=0.0;
        for(int j=0;j<map.get(Docs.get(i)).size();j++)
        {
        sum+=(map.get(Docs.get(i)).get(j).getKey())*0.6+(map.get(Docs.get(i)).get(j).getValue())*0.4;
        }
        sum=(sum*0.7+rank.get(Docs.get(i))*0.3);
           System.out.print(Docs.get(i));
         System.out.print("  rank");
        System.out.println(sum);
     
        Totalrank.put(Docs.get(i), sum);
            System.out.print(Docs.get(i));
            System.out.print("  ");
            System.out.println(sum);
    }
    }
}
