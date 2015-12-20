
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (Larynx) 
 * @version (20151208)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    // key=word, value=ArrayList of filenames
    private HashMap<String, ArrayList<String>> m;

    public WordsInFiles(){
        m = new HashMap<String, ArrayList<String>>();
        buildWordFileMap();
    }

    public void addWordsFromFile(File f){
        String fname = f.getName(); 
        FileResource fr = new FileResource("data/" + fname);
        for (String w: fr.words()){
            if(!m.keySet().contains(w)){
                ArrayList<String> lst = new ArrayList<String>();
                lst.add(fname);
                m.put(w, lst);
            } else {
                if(!m.get(w).contains(fname)){
                    m.get(w).add(fname);
                }               
            }
        }
    }

    public void buildWordFileMap(){
        m.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    public int maxNumber(){
        int maxN = 0;
        for(String w: m.keySet()){
            if (m.get(w).size() > maxN){
                maxN = m.get(w).size();
            }
        }
        return maxN;
    }

    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> al = new ArrayList<String>();
        for(String w: m.keySet()){
            int n = m.get(w).size();
            if(n == number){
                al.add(w);
            }
        }
        return al;
    }

    public void printFilesIn(String word){
        System.out.println("filenames: ");
        for (String w: m.keySet()){
            if (word.equals(w)){
                for(String s: m.get(w)){
                    System.out.println(s);
                }
                
            }
        }
    }

    public void tester(){
        System.out.println("max number of files: " + maxNumber());
        ArrayList<String> al5 = wordsInNumFiles(5);
        System.out.print("all words in max number of files: ");
        for(String s: al5){
            System.out.print(s + ", ");
        }
        System.out.println("How many words in all files: "+al5.size());
        ArrayList<String> al4 = wordsInNumFiles(4);
        System.out.println("How many words in all files: "+al4.size());
    }
}
