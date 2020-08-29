
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.Arrays;

public class TestCaesarCipher {
    public int[] countLetters (String resource){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i=0;i<resource.length();i++){
            char c = Character.toLowerCase(resource.charAt(i));
            int idx =alphabet.indexOf(c);
            if (idx!=-1){
                counts[idx]+=1;
            }
        }
        // System.out.println(resource);
        // System.out.println(Arrays.toString(counts));
        return counts;
    }
    
    public int maxIndex(int[] values){
        int index = 0;
        for (int i=0;i<values.length;i++){
            if (values[i]>values[index]){
                index = i;
            }
        }
        return index;
    }
    
    public int getKey(String s){
        int[] a = countLetters (s);
        int num = maxIndex(a);
        return num;
    }
    
    public void simpleTests (){
        // FileResource fr = new FileResource();
        // String s = fr.asString();
        String s = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        String enc = cc.encrypt(s);
        System.out.println(enc);
        String dec = cc.decrypt(s);
        System.out.println(dec);
    }
    
    public String breakCaesarCipher(String input){
        int key = getKey(input);
        CaesarCipher cc = new CaesarCipher(key);
        String message = cc.decrypt(input);
        //System.out.println(message);
        return message;
    }
    
}















