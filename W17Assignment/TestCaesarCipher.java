
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (Larynx) 
 * @version (20151128)
 */

import edu.duke.*;

public class TestCaesarCipher {
    public void simpleTests(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String enc = cc.encrypt(s);
        System.out.println(enc);
        System.out.println(cc.decrypt(enc));
        System.out.println(breakCaesarCipher(s));
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

    
    public String breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if(maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(26-dkey);
        return cc.encrypt(input);
    }
}
