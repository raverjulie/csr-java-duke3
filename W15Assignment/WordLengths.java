
/**
 * Write a description of WordLengths here.
 * 
 * @author (Laryns) 
 * @version (20151119)
 */

import edu.duke.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.*;

public class WordLengths {
    // counting the frequency of each word in the file
    public HashMap<String,Integer> getWordsList(FileResource fr){
        HashMap<String,Integer> map = new HashMap<String, Integer>(); 
        for(String w: fr.words()){
            if (map.containsKey(w)){
                map.put(w, map.get(w)+1);
            }else{
                map.put(w,1);
            }
        }
        return map;
    }
    
    // testing HashMap and get elements by HashMap iteration
    public void test_HashMap(){
        FileResource fr = new FileResource();
        HashMap<String, Integer> ms = getWordsList(fr);
        System.out.println("The number of total words is " + howManyWords(fr));
        System.out.println("HashMap size is " + ms.size());
        
        // How to iterate HashMap
        // 1. iterator
        System.out.println("Iterating HashMap - iterator ");
        Iterator<String> map = ms.keySet().iterator();
        while (map.hasNext()){
            String k = map.next();            
            System.out.println(k + " : " + ms.get(k));            
        }
        
        // 2. keySet()
        System.out.println("Iterating HashMap - keySet() ");
        for (String key: ms.keySet()){             
            System.out.println(key + " : " + ms.get(key));
        }
        
        // 3. entrySet()
        System.out.println("Iterating HashMap - entrySet() ");
        for (Map.Entry<String, Integer> elem: ms.entrySet()){
            System.out.println(elem.getKey() + " : " + elem.getValue());
        }
    }
    
        // counting the number of words: fr.size()
    public int howManyWords(FileResource fr){
        int count = 0;
        for (String word: fr.words()) {
            count += 1;
        }
        return count;        
    }
    
    // test
    public void test_howManyWords(){
        FileResource fr = new FileResource();
        System.out.println(howManyWords(fr));
    }
    
    // find longest word in String array
    public int longestWordLength(FileResource fr){
        int longestLength = 0;
        for(String word: fr.words()){
            int temp = countChar(word);
            if(temp > longestLength){
                longestLength = temp;
            }
        }
        return longestLength;
    }
    
    // test
    public void test_longestWordLength(){
        FileResource fr = new FileResource();
        System.out.println(longestWordLength(fr));
    }
            
    // helper function - get words list
    public String[] wordsList(FileResource fr){
        String[] lst = new String[howManyWords(fr)];
        int index = 0;
        for (String word: fr.words()){
            lst[index] = word;
            index += 1;
        }
        return lst;
    }
    
    // test
    public void test_wordList(){
        FileResource fr = new FileResource();
        String[] lst = wordsList(fr);
        for (int i=0;i<lst.length;i++) {
            System.out.println(lst[i]);
        }        
    }
    
    // indexOf 
    public int indexOf(String[] list, String word){
        for(int k=0;k<list.length;k++) {
            if (list[k].equals(word)){
                return k;
            }
        }
        return -1;
    }
    
    // test
    public void test_indexOf(){
        String[] ls = {"second","first","third"};
        System.out.println(indexOf(ls, "first"));
    }
    
    // indexOf
    public int indexOfMax(int[] values){
        int maxValue = 0;
        int maxValueIndex = 0;
        for (int i=0; i<values.length; i++){
            if (values[i] > maxValue){
                maxValue = values[i];
                maxValueIndex = i;
            }
        }
        return maxValueIndex;
    }
    
    // test
    public void test_indexOfMax(){
        int[] values = {1,3,0,9,23,42,3,44,2};
        System.out.println(indexOfMax(values));       
    }
    
    // helper function - counting the number of characters in a word
    public int countChar(String word){
        int count = 0;      
        char[] cw = word.toCharArray();
        if (word.length() == 1 && Character.isLetter(cw[0])) {
            count += 1;
        } else if(word.length() == 2){
            if (Character.isLetter(cw[0])){
                count += 1;
            }
            if (Character.isLetter(cw[1])){
                count += 1;
            }
        } else {
            char first = cw[0];
            char last = cw[cw.length-1];
            char[] middle = new char[cw.length-2];
            word.getChars(1,cw.length-2,middle,0);
            if (Character.isLetter(first)) {
                count += 1; 
            }

            if(Character.isLetter(last)){
                count += 1;
            }
        
            for (char c: middle){
                count += 1;
            }      
        }
        return count;        
    }
    
    // test
    public void test_countChar(){
        FileResource fr = new FileResource();
        for (String s: fr.words()){
            System.out.println(s + " - " + s.length() + " - " + countChar(s));
        }       
    }
    
    public void countWordLengths(FileResource resource){
        String[] wds = wordsList(resource);
        int[] wls = new int[wds.length];
        for (int i=0;i<wds.length;i++){
            wls[i] = countChar(wds[i]);
        }      
        int indexMax = indexOfMax(wls);
        int maxValue = wls[indexMax];
        
        int[] counts = new int[maxValue+1];
        for (int i=0;i<counts.length;i++){
            counts[i] = 0;
            for (int j=0;j<wls.length;j++){
                if(i == wls[j]){
                    counts[i] += 1;
                }
            }
        }
        
        for (int i=0;i<counts.length;i++){
            if(counts[i] !=0){
                if (counts[i] > 1){
                    System.out.print(counts[i] + " words of length " + i + ": ");
                    for (String w: wds){
                        if (countChar(w)==i){
                            System.out.print(w + " ");
                        }
                    }
                    System.out.println("\n");
                } else {
                    System.out.print(counts[i] + " word of length " + i + ": ");
                    for (String w: wds){
                        if (countChar(w)==i){
                            System.out.print(w + " ");
                        }
                    }
                    System.out.println("\n");
                }                
            }            
        }
        
    }   
    
    public void test_countWordLengths(){
        FileResource fr = new FileResource();
        countWordLengths(fr);
        
    }
}
