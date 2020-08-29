
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftUpper = alphabetUpper.substring(key)+alphabetUpper.substring(0,key);
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String shiftLower = alphabetLower.substring(key)+alphabetLower.substring(0,key);
        for (int i=0;i<input.length();i++){
            char currChar = input.charAt(i);
            int idx = alphabetUpper.indexOf(currChar);
            if (idx!=-1){
                char newC = shiftUpper.charAt(idx);
                encrypted.setCharAt(i,newC);
            }
            if (idx == -1){
                idx = alphabetLower.indexOf(currChar);
                if (idx!=-1){
                    char newC = shiftLower.charAt(idx);
                    encrypted.setCharAt(i,newC);
                }
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar (int key){
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(message);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys (String input,int key1,int key2){
        StringBuilder encrypted = new StringBuilder();
        for (int i=0;i<input.length();i++){
            char c = input.charAt(i);
            String s = Character.toString(c);
            if (i%2==0){
                encrypted.append(encrypt(s, key1));
            }
            else{
                encrypted.append(encrypt(s, key2));
            }
        }
        return encrypted.toString();
    }
    
    public void test2(int key1,int key2){
        String a = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String b = encryptTwoKeys(a,8,21);
        System.out.println(b);
    }
}












