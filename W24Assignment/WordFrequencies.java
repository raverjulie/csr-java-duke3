
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (Larynx) 
 * @version (20151129)
 */

import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique(){
        FileResource fr = new FileResource();
        myWords.clear();
        myFreqs.clear();
        for(String s : fr.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }

    public void tester(){
        findUnique();
        for (int i=0; i<myWords.size(); i++){
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        int index = findIndexOfMax();
        System.out.println("# unique words: "+ myWords.size());
        System.out.println("The word that occurs most often and its count are: "+ myWords.get(index) + " " + myFreqs.get(index));
    }

    public int findIndexOfMax(){
        int maxValue = Integer.MIN_VALUE;
        int ans = 0;
        for (int i=0; i<myFreqs.size(); i++){
            if(myFreqs.get(i) > maxValue){
                ans = i;
                maxValue = myFreqs.get(i);
            }
        }
        return ans;
    }

    
}
