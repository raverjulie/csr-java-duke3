
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (Larynx  ) 
 * @version (20151128)
 */
public class CaesarCipher {
    // fields
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        mainKey = key;
    }

    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for (int i=0; i<sb.length(); i++){
            char c = sb.charAt(i);
            int idx = alphabet.indexOf(c);
            if(idx !=-1){
                c=shiftedAlphabet.charAt(idx);
                sb.setCharAt(i,c);
            }
        }
        return sb.toString();
    }

    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
    
    public void quiz1(){
        CaesarCipher cc = new CaesarCipher(15);
        String s = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println(s);
    }
}

