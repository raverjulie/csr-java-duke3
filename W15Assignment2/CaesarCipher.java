import edu.duke.*;

public class CaesarCipher {
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

    public void test_encrypt(int key){
        String q1 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println(encrypt(q1,key));
        
        FileResource fr = new FileResource();
        String s = fr.asString();
        System.out.println(encrypt(s,key));
        
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
    
    public void test_encryptTwoKeys(int key1, int key2){
        // quiz 8. solution
        String s8 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        System.out.println((encryptTwoKeys(s8,key1,key2)));
        
        //FileResource fr = new FileResource();
        //String s = fr.asString();
        //System.out.print(encryptTwoKeys(s, key1, key2));
    }
    
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        
       System.out.println(encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx",2,20));
    }
}

