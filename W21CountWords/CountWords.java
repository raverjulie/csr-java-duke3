import java.util.*;

import edu.duke.*;

public class CountWords {
    
    StorageResource myWords;
    
    public CountWords() {
        myWords = new StorageResource();
    }
    
    public int getCount(){
        return myWords.size();
    }
    
    public String getRandomWord(){
        // random word를 선택하는 방법이 독특하고 이상하다.
        // 밑에 더 간단한 함수가 있다.
        Random rand = new Random();
        int choice = rand.nextInt(myWords.size());
        for(String s : myWords.data()){
            if (choice == 0) {
                return s;
            }
            choice = choice - 1;
        }
        return "*** NEVER HAPPENS ***";
    }

    public String getRandomWord(String[] words) {
        Random rand = new Random();
        int index = rand.nextInt(words.length);
        return words[index];
    }
	
    public void readWords(String source){
        // html page 인지 아니면 일반 folder인지에 따라 
        // 다르게 source를 읽는 method
        myWords.clear();
        if (source.startsWith("http")){
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                myWords.add(word.toLowerCase());
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                    myWords.add(word.toLowerCase());
            }
        }
    }
    
    public boolean contains(String[] list, int size, String word){
        // Array로 문제를 해결하기 위한 helper function
        for(int k=0; k < size; k++){
            if (list[k].equals(word)){
                return true;
            }
        }
        return false;
    }
    
    public int countDifferentArray(){
        // Array로 문제를 해결하려니 매우 비효율적이다.
        int diffCount = 0;
        String[] words = new String[getCount()];
        for(String s : myWords.data()){
             if (! contains(words,diffCount,s)){
                 words[diffCount] = s;
                 diffCount++;
             }
        }
        return diffCount;
    }
    
    public int countDifferentArrayList(){
        // ArrayList는 Array에 크기가 정해져 있지 않아 효율적이다.
        // ArrayList는 mutable하다. 
        // Array처럼 [index]가 아닌, get & set method를 사용한다.
        ArrayList<String> words = new ArrayList<String>();
        for(String s : myWords.data()){
            if (! words.contains(s)) {
                words.add(s);
            }
        }
        return words.size();
    }
    
    public void tester(){
        readWords("data/confucius.txt");
        //readWords("http://dukelearntoprogram.com/data/confucius.txt");
        System.out.println("number of words read: "+getCount());
        int carray = countDifferentArray();
        int clist = countDifferentArrayList();
        System.out.println(carray+" "+clist);
    }  
	
    public void randomTester(){
        // 먼저 source로부터 읽어 myWords를 채운다.
        readWords("data/confucius.txt");
        System.out.println("starting");
        int RAND_SIZE = 100000;
        for(int k=0; k < RAND_SIZE; k++){
            String word = getRandomWord();            
            if (word.indexOf("*** NEVER") != -1){
                System.out.println(word);
            }
        }
        System.out.println("done with randoms");
        String[] words = new String[myWords.size()];
        int index = 0;
        for(String s : myWords.data()){
            words[index] = s;
            index += 1;
        }
        System.out.println("starting array");
        for(int k=0; k < RAND_SIZE; k++){
            String word = getRandomWord(words);
        }System.out.println("done with randoms");
    }
}
