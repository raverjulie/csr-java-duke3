import edu.duke.*;
import java.util.*; 

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedList;  //This should be here!
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        usedList = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        usedList = new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country","noun","animal","adjective","name","color","timeframe","verb","fruit"};
        for(String s: labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list); 
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }
    
    public String processWord(String w){
        // 중복되지 않도록 사용한 단어는 제외할 수 있도록 고치자.
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);   // this for period ('.')  
        String suffix = w.substring(last+1);    // this for period ('.')
        String temp = w.substring(first+1,last);
        String sub = getSubstitute(temp);

        if (usedList.contains(sub)) {   // without creating new usedList instance
            processWord(w);             // this cause only NullPointException!      
        }
        usedList.add(sub);
        return prefix+sub+suffix;               // this for period ('.')
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        System.out.println("");
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("total number of words in all the ArrayLists in the HashMap: "+totalWordsInMap());
        System.out.println("total number of words in the ArrayLists of the categories that were used: "+ totalWordsConsidered());
    }

    public int totalWordsInMap(){
        int n = 0;
        for(String c: myMap.keySet()){
            for(String w: myMap.get(c)){
                n += 1;
            }
        }
        return n;
    }

    public int totalWordsConsidered(){
        return usedList.size();
    }
}
