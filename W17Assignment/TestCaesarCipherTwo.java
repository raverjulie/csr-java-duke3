
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
 
    public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for(int i=start; i<message.length(); i += 2){
            sb.append(""+message.charAt(i));
        }
        return sb.toString();
    }
    
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k<message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if(dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] ar){
        int maxId = 0;
        for (int i=0; i<ar.length; i++){
            if (ar[i] > ar[maxId]){
                maxId = i;
            }
        }
        return maxId;
    }   
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String enc = cc.encrypt(s);
        System.out.println(enc);
        String dec = cc.decrypt(enc);
        System.out.println(dec);
        String b = breakCaesarCipher(enc);
        System.out.println(b);
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    
    public String breakCaesarCipher(String input){
        String e1 = halfOfString(input,0);
        String e2 = halfOfString(input,1);
        int key1 = getKey(e1);
        int key2 = getKey(e2);
        System.out.println("key1: " + key1 + ", key2: " + key2);
        CaesarCipherTwo c = new CaesarCipherTwo(26-key1, 26-key2);
        String ans = c.encrypt(input);
        return ans; 
    } 
}
