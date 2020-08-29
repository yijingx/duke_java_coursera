
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.Arrays;

public class CaesarCipher {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String shiftedAlphabet;
    private int mainkey;
    
    public CaesarCipher(int key){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
    }
    
    public String encrypt (String input){
        StringBuilder encr = new StringBuilder(input);
        for (int i=0;i<input.length();i++){
            char currChar = input.charAt(i);
            char temp = Character.toLowerCase(currChar);
            int idx =alphabet.indexOf(temp);
            if (idx!=-1){
                char newC = shiftedAlphabet.charAt(idx);
                if (Character.isUpperCase(currChar)){
                    encr.setCharAt(i,Character.toUpperCase(newC));
                }
                else{
                    encr.setCharAt(i,newC);
                }
            }
        }
        return encr.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainkey);
        return cc.encrypt(input);
    }
}














