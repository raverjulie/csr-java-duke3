import edu.duke.*;
import java.util.*;

public class Tester {

    public void test_CaesarCipher(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        CaesarCipher c = new CaesarCipher(2);
        String enc = c.encrypt(s);
        System.out.println(enc);
        String dec = c.decrypt(enc);
        System.out.println(dec);
    }

    public void test_CaesarCracker(){
        FileResource fr = new FileResource();
        CaesarCracker cc = new CaesarCracker();
        String s = fr.asString();
        String crk = cc.decrypt(s);
        System.out.println(crk);
    }

    public void test_VigenereCipher(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        int[] key = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(key);
        String enc = vc.encrypt(s);
        System.out.println(enc);
        String dec = vc.decrypt(enc);
        System.out.println(dec);
    }

    public void test_sliceString(){
        FileResource fr = new FileResource();
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3)); //"adgjm"
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3)); //"behk"
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3)); //"cfil"
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4)); //"aeim"
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4)); //"bfj"
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4)); //"cgk"
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4)); //"dhl"
        System.out.println(vb.sliceString("abcdefghijklm", 0, 5)); //"afk"
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5)); //"bgl"
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5)); //"chm"
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5)); //"di"
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5)); //"ej"
    }

    public void test_VigenereBreaker(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }

    public void test_readDictionary(){
        FileResource fr = new FileResource();
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> hs = vb.readDictionary(fr);
        for(String w: hs){
            System.out.println(w);
        }
    }

    public void test_countWords(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource frDic = new FileResource("dictionaries/English");
        HashSet<String> hs = vb.readDictionary(frDic);
        FileResource frMessage = new FileResource("messages/secretmessage2.txt");
        String encrypted = frMessage.asString();
        String decrypted = vb.breakForLanguage(encrypted,hs);
        System.out.println(decrypted);
        int count = vb.countWords(decrypted,hs);
        System.out.println(count);
    }

    public void test_breakForLanguage(){
        VigenereBreaker vb = new VigenereBreaker();
        FileResource frDic = new FileResource("dictionaries/English");
        HashSet<String> dic = vb.readDictionary(frDic);
        FileResource frMessage = new FileResource("messages/secretmessage2.txt");
        String message = frMessage.asString();
        String decrypted = vb.breakForLanguage(message,dic);
        //System.out.println(decrypted);

        // print the first line
        String[] lst = decrypted.split("\\n");
        System.out.println(lst[0]);
    }

    public void quiz4(){   
        VigenereBreaker vb = new VigenereBreaker();     
        FileResource frDic = new FileResource("dictionaries/English");
        HashSet<String> dic = vb.readDictionary(frDic);
        FileResource frenc = new FileResource("messages/secretmessage2.txt");
        String enc = frenc.asString();        
        String dec = vb.breakForLanguage(enc,dic);
        int n = vb.countWords(dec,dic);
        System.out.println(n);
    }

    public void test_mostCommonCharIn(){
        VigenereBreaker vb = new VigenereBreaker();     
        FileResource frDic = new FileResource("dictionaries/English");
        HashSet<String> dic = vb.readDictionary(frDic);
        char mcc = vb.mostCommonCharIn(dic);
        System.out.println(mcc);
    }

    public void test_breakVigenere(){
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
}
