
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.Arrays;

public class CaesarBreaker {
    public String decrypt (String encrypt,int key){
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypt, 26 - key);
        return message;
    }
    
    public void testDecrypt(){
        String a = decrypt("abcdef",3);
        System.out.println(a);
    }
    
    public String halfOfString(String message,int start){
        StringBuilder a = new StringBuilder();
        for(int i=start;i<message.length();i+=2){
            char c = message.charAt(i);
            a.append(c);
        }
        return a.toString();
    }
    
    public void testHalf(){
        String test = halfOfString("Qbkm Zgis", 0);
        System.out.println(test);
        test = halfOfString("Qbkm Zgis", 1);
        System.out.println(test);
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
    
    public String decryptTwoKeys (String encrypted){
        String s1 = halfOfString(encrypted,0);
        String s2 = halfOfString(encrypted,1);
        int key1 = getKey(s1)-4;
        int key2 = getKey(s2)-4;
        key1 = 14;
        key2 = 24;
        System.out.println("Two keys:"+key1+" "+key2);
        if (key1<0){
            key1+=26;
        }
        if (key2<0){
            key2+=26;
        }
        System.out.println("Two keys:"+key1+" "+key2);
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encryptTwoKeys(encrypted,26-key1,26-key2);
        System.out.println(message);
        return message;
    }
    
    public void testDecryptTwoKeys() {
        // FileResource fr = new FileResource();
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        decryptTwoKeys(message);
    }
}














