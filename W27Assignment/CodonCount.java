
/**
 * Write a description of CodonCount here.
 * 
 * @author (Larynx) 
 * @version (20151129)
 */

import edu.duke.*;
import java.util.*;

public class CodonCount {
    // private field
    private HashMap<String, Integer> map;

    public CodonCount(){
        map = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna){
        String sub = dna.substring(start);
        for (int i=0; i<sub.length()/3; i++){
            String codon = sub.substring(3*i,3*i+3);
            if (map.containsKey(codon)) {
                map.put(codon, map.get(codon)+1);
            }else{
                map.put(codon,1);
            }            
        }
        System.out.println("Reading frame starting with "+start+" results in "+map.size()+" unique codons");
    }

    public String getMostCommonCodon(){
        int maxV = Integer.MIN_VALUE;
        String maxK = "";
        for (String k: map.keySet()){
            if (map.get(k) > maxV){
                maxV = map.get(k);
                maxK = k;
            }
        }
        return maxK;
    }

    public void printCodonCounts(int start, int end){
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
        for (String k: map.keySet()){
            if (map.get(k)>=start && map.get(k)<=end){
                System.out.println(k + " : " + map.get(k));
            }
        }
    }

    // tester
    public void test(){
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();      //strip() in Python
        buildCodonMap(0,dna);  
        String maxKey = getMostCommonCodon();
        System.out.println("and most common codon is " + maxKey + " with count " + map.get(maxKey));        
        printCodonCounts(7, 7);

    }

 
}
