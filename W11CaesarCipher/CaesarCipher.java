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

    
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}

