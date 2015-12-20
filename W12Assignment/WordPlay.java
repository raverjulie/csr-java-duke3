/**
 * Write a description of WordPlay here.
 * 
 * @author (Larynx) 
 * @version (20151119)
 */
import edu.duke.*;

public class WordPlay {
    public boolean isVowel(Character ch) {
        String vs = "aeiouAEIOU";
        return vs.contains(""+ch);
    }
    
    public void test_isVowel(){
        System.out.println("g is vowel: "+isVowel('g'));
        System.out.println("e is vowel: "+isVowel('e'));
    }
    
    public String replaceVowels(String phrase, Character ch) {
        StringBuilder sb = new StringBuilder();
        for (char c: phrase.toCharArray()){
            if (isVowel(c)) {
                sb.append(""+ch);
            } else {
                sb.append(""+c);
            }
        }
        return sb.toString();
    }
    
    public void test_replaceVowels(){
        System.out.println(replaceVowels("Hello World", '*'));
    }
    
    public String emphasize(String phrase, Character ch){
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<phrase.length();i++){
            if (i%2==0 && phrase.charAt(i)==ch){
                sb.append(""+'*');
            }else if (phrase.charAt(i)==ch){
                sb.append(""+'+');
            }else{
                sb.append(""+phrase.charAt(i));
            }
        }
        return sb.toString();
    }
    
    public void test_emphasize(){
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
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
    
    
    public String encrypt(String input, int key){
        StringBuilder sb = new StringBuilder();
        for (char c:input.toCharArray()){
            if (Character.isLetter(c)){
                sb.append(""+changeChar(c,key));
            }else{
                sb.append(""+c);
            }
        }
        return sb.toString(); 
    }
    
    public void test_encrypt(){
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        System.out.println(encrypt("First Legion", 23));
        System.out.println(encrypt("First Legion", 17));
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<input.length(); i++) {
            if (i%2 == 0 && Character.isLetter(input.charAt(i))){
                sb.append(""+changeChar(input.charAt(i),key1));
            }else if (Character.isLetter(input.charAt(i))){
                sb.append(""+changeChar(input.charAt(i),key2));
            }else{
                sb.append(""+input.charAt(i));
            }                 
        }
        return sb.toString();
    }
    
    public void test_encryptTwoKeys(){
        System.out.println(encryptTwoKeys("First Legion",23,17));
    }
}