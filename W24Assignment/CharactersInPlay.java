
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay(){
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int index = names.indexOf(person);
        if (index == -1){
            names.add(person);
            counts.add(1);
        } else {
            int freq = counts.get(index);
            counts.set(index,freq+1);
        }
    }
    
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        names.clear();
        counts.clear();
        for(String line: fr.lines()){  
            if (line.contains(".")) {
                if(line.substring(0,2).equals("  ") && !Character.isWhitespace(line.charAt(2))){ 
                    int idx = line.indexOf("."); 
                    String person = line.substring(2,idx);
                    update(person);
                } 
            }                          
        }           
        
        for (int i=0; i<names.size(); i++){
            System.out.println(names.get(i) + " " + counts.get(i));
        }
    }
    
    public void findAllCharactersQuiz(){
        FileResource fr = new FileResource();
        names.clear();
        counts.clear();
        for(String line: fr.lines()){  
            if (line.contains(".")) {
                if(!Character.isWhitespace(line.charAt(0)) && Character.isUpperCase(line.charAt(0))){ 
                    int idx = line.indexOf("."); 
                    String person = line.substring(2,idx);
                    update(person);
                } 
            }                          
        }           
        
        for (int i=0; i<names.size(); i++){
            System.out.println(names.get(i) + " " + counts.get(i));
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        findAllCharacters();
        System.out.println("charactersWithNumParts result");
        for(int i=0; i<names.size(); i++){
            int c = counts.get(i);
            if(c>= num1 && c<=num2){
                System.out.println(names.get(i) + " : " + c);
            }
        }
        
    }
    
    public void charactersWithNumPartsQuiz(int num1, int num2){
        findAllCharactersQuiz();
        System.out.println("charactersWithNumParts result");
        int n = 0;
        for(int i=0; i<names.size(); i++){
            int c = counts.get(i);
            if(c>= num1 && c<=num2){
                System.out.println(names.get(i) + " : " + c);
                n += 1;
            }
        }    
        System.out.println("The number "+ num1 + " ~ " + num2 + " : " + n);
    }
    
    public void getMaxVale(){
        findAllCharactersQuiz();
        int mv = 0;
        for(int v: counts){
            if (v > mv){
                mv = v;
            }
        }
        System.out.println("max value: "+ mv);
        
    }
}
