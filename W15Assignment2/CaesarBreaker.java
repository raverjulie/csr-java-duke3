
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (Larynx) 
 * @version (20151123)
 */

import edu.duke.*;

public class CaesarBreaker {
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
    
    public void test_countLetters(){
        String s = "aaabbccccdeeefgggg";
        int[] c = countLetters(s);
        for(int i: c){
            System.out.println(i);
        }
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
    
    public void test_maxIndex(){
        int[] ar = {1,3,2,45,0};
        System.out.println(maxIndex(ar));
    }
    
    // decrypt method (under the assumption that 'e' is the most frequent character)
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        //System.out.println(maxDex);
        int dkey = maxDex - 4;
        //System.out.println(dkey);
        if(maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    public void test_decrypt(){
        System.out.println(decrypt("Top ncmy qkff vi vguv vbg ycpx"));
        System.out.println(decrypt("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        //FileResource fr = new FileResource();
        //String s = fr.asString();
        //System.out.println(decrypt(s));
    }
    
    public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for(int i=start; i<message.length(); i += 2){
            sb.append(""+message.charAt(i));
        }
        return sb.toString();
    }
    
    public void test_halfOfString(){
        System.out.println(halfOfString("Qbkm Zgis",0));
        System.out.println(halfOfString("Qbkm Zgis",1));
        
        System.out.println(halfOfString("Akag tjw Xibhr awoa aoee xakex znxag xwko",0));
        System.out.println(halfOfString("Akag tjw Xibhr awoa aoee xakex znxag xwko",1));
        
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
    
    public void test_getKey(){
        String s1 = halfOfString("Akag tjw Xibhr awoa aoee xakex znxag xwko",0);
        String s2 = halfOfString("Akag tjw Xibhr awoa aoee xakex znxag xwko",1);
        System.out.println(getKey(s1));
        System.out.println(getKey(s2));
    }
    
    public void decryptTwoKeys(String encrypted){
        CaesarCipher c = new CaesarCipher();
        String e1 = halfOfString(encrypted,0);
        String e2 = halfOfString(encrypted,1);
        int key1 = getKey(e1);
        int key2 = getKey(e2);
        System.out.println("key1: " + key1 + ", key2: " + key2);
        String d1 = decrypt(e1);
        String d2 = decrypt(e2);
        String ans = c.encryptTwoKeys(encrypted, 26-key1, 26-key2);
        System.out.println(ans);
        
    }
    
    public void test_decryptTwoKeys(){
        //quiz 9,10. solution
        String s9 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        decryptTwoKeys(s9);
        
        FileResource fr = new FileResource();
        String s10 = fr.asString();
        decryptTwoKeys(s10);
    }
}
