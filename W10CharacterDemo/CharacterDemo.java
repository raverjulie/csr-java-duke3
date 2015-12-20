
/**
 * Illustrate Character methods
 * 
 * @author Duke Software Team 
 * @version (a version number or a date)
 * 
 * This is very simple examples of string- and character-methods.
 *      <String>.charAt(<index>)
 *      Character.isDigit(<Character>)
 *      Character.isAlphabetic(<Character>)
 *      <StringBuilder>.toString()
 *      <String>.toCharArray()
 */
public class CharacterDemo {
    public void digitTest() {
        // String methods
        String test = "ABCabc0123456789';#!";
        for(int k=0; k < test.length(); k++){
            char ch = test.charAt(k);
            if (Character.isDigit(ch)){
               System.out.println(ch+" is a digit"); 
            }
            if (Character.isAlphabetic(ch)){
                System.out.println(ch+" is alphabetic");
            }
            if (ch == '#'){
                System.out.println(ch +" #hashtag");
            }
        }
    }
    
    public void sbTest() {
        // practicing StringBuilder methods
        StringBuilder sb = new StringBuilder();
        sb.append("first-");
        sb.append("second-");
        sb.append("third");
        System.out.println("Built string is "+sb);
        sb.insert(sb.length(), " : was built by me.");
        System.out.println(sb);
        sb.setCharAt(sb.length()-1, '!');
        System.out.println(sb);
        System.out.println(sb.charAt(sb.length()-1));
    }
    
    public void alphabets(Character ch, int k) {
        char ch2;
        if (Character.isUpperCase(ch)) {
            ch2 = (char) ('A' + (ch - 'A' + k)%26);
        } else {
            ch2 = (char) ('a' + (ch - 'a' + k)%26);
        }        
        System.out.println(ch2);
    }
    
    public String reverse(String s) {
        // reversing String
        String ret = "";
        for(int k = 0; k < s.length(); k += 1) {
            ret = s.charAt(k) + ret;
        }
        return ret;
    }
    
    public void conversionTest(){
        // Conversion character into another cahracter
        String test = "ABCDEFabcdef123!#";
        for(int k=0; k < test.length(); k++){
            char ch = test.charAt(k);
            char uch = Character.toUpperCase(ch);
            char lch = Character.toLowerCase(ch);
            System.out.println(ch+" "+uch+" "+lch);
        }
    }
}
