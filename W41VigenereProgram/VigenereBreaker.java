import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i=whichSlice;i<message.length(); i+=totalSlices){
            sb.append(""+message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        String[] ls = new String[klength];
        for(int i=0;i<klength;i++){
            ls[i] = sliceString(encrypted,i,klength);
        }
        for(int i=0;i<klength;i++){
            key[i] = cc.getKey(ls[i]);
        }
        return key;
    }

    public void breakVigenere () {
        // read an encrypted file
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        // HashMap<String,HashSet<String>> dictionaries
        HashMap<String,HashSet<String>> dictionaries = new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fdic = new FileResource(f);
            HashSet<String> dic = readDictionary(fdic);
            dictionaries.put(f.getName(), dic);
        }

        breakForAllLanguages(encrypted, dictionaries);
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for(String word: fr.words()){
            word = word.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        message = message.toLowerCase();
        int count = 0;
        for(String w: message.split("\\W")){
            if(!w.isEmpty()){
                if(dictionary.contains(w)){
                    count += 1;
                }
            }
        }
        return count;
    } 

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        encrypted = encrypted.toLowerCase();
        String decrypted = "";
        int maxCount = 0;
        int klength = 0;
        for(int i=1;i<101;i++){
            int[] key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(key);
            String temp = vc.decrypt(encrypted);            
            int count = countWords(temp,dictionary);
            if(count>maxCount){
                maxCount = count;
                decrypted = temp;
                klength = i;
            }
        }
        System.out.println("counts: " + maxCount);
        System.out.println("klength: " + klength);
        return decrypted;
    }

    public Character mostCommonCharIn(HashSet<String> dictionary){
        int maxFreq = 0;
        char maxChar = 0;
        HashMap<Character,Integer> m = new HashMap<Character,Integer>();
        for(String word: dictionary){
            for(char c: word.toCharArray()){
                // build a map
                if(!m.containsKey(c)){
                    m.put(c,1);
                }else{
                    m.put(c,m.get(c)+1);
                }
                // check if max value
                if(m.get(c)>maxFreq){
                    maxFreq = m.get(c);
                    maxChar = c;
                } 
            }
        }
        return maxChar;
    }

    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
        int finalMaxCount = 0;
        int finalMostCommonChr = 0;
        String finalDecrypted = "";
        for(String lang: languages.keySet()){
            HashSet<String> dictionary = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int cw = countWords(decrypted, dictionary);
            char mcc = mostCommonCharIn(dictionary);
            System.out.println(lang + " : " + mcc + ", count: " + cw);
            if(cw>finalMaxCount){
                finalMaxCount = cw;
                finalMostCommonChr = mcc;
                finalDecrypted = decrypted;
            }
        }
        String[] lst = finalDecrypted.split("\\n");
        System.out.println("Final Result --------------------");
        System.out.println("Most Common Char: " + finalMostCommonChr);
        System.out.println("Counts: " + finalMaxCount);
        System.out.println("Decrypted (First line): " + lst[0]);
    }
}
