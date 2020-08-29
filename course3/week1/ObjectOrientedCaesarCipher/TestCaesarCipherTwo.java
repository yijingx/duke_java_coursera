
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.Arrays;

public class TestCaesarCipherTwo {
    public String halfOfString(String message,int start){
        StringBuilder a = new StringBuilder();
        for(int i=start;i<message.length();i+=2){
            char c = message.charAt(i);
            a.append(c);
        }
        return a.toString();
    }
    
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
    
    public void breakCaesarCipher (String input){
        // CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        // String enc = cc.encrypt(input);
        // System.out.println();
        String enc = input;
        String s1 = halfOfString(enc,0);
        String s2 = halfOfString(enc,1);
        int reverseKey1 = getKey(s1)-4;
        int reverseKey2 = getKey(s2)-4;
        if (reverseKey1<0){
            reverseKey1+=26;
        }
        if (reverseKey2<0){
            reverseKey2+=26;
        }
        System.out.println("Two keys:"+reverseKey1+" "+reverseKey1);
        CaesarCipherTwo dcc = new CaesarCipherTwo(26-reverseKey1,26-reverseKey2);
        String d = dcc.decrypt(input);
        System.out.println(d);
    }
    
    public void test(){
        CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
        String a = cc.encrypt("Hfs cpwewloj loks cd Hoto kyg Cyy.");
        System.out.println(a);
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        breakCaesarCipher(s);
    }
}
