
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (Larynx) 
 * @version (20151128)
 */

import edu.duke.*;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public Character changeChar(Character ch, int k) {
        char ch2;
        if (Character.isUpperCase(ch)) {
            ch2 = (char) ('A' + (ch - 'A' + k) % 26);
        } else {
            ch2 = (char) ('a' + (ch - 'a' + k) % 26);
        }        
        return ch2;
    }
    
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<input.length(); i++) {
            if (i%2 == 0 && Character.isLetter(input.charAt(i))){
                sb.append(""+changeChar(input.charAt(i),mainKey1));
            }else if (Character.isLetter(input.charAt(i))){
                sb.append(""+changeChar(input.charAt(i),mainKey2));
            }else{
                sb.append(""+input.charAt(i));
            }                 
        }
        return sb.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        return cc.encrypt(input);
    }
}
