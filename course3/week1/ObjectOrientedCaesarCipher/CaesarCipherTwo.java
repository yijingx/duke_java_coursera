
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    String alphabet;
    String shiftedAlphabet1;
    String shiftedAlphabet2;
    private int reverseKey1;
    private int reverseKey2;
    public CaesarCipherTwo(int key1,int key2){
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0,key2);
        reverseKey1 = key1;
        reverseKey2 = key2;
    }
    
    public String encrypt (String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i=0;i<input.length();i++){
            char c = input.charAt(i);
            char currChar = input.charAt(i);
            char temp = Character.toLowerCase(currChar);
            int idx =alphabet.indexOf(temp);
            if (idx!=-1){
                if (i%2==0){
                    char newC1 = shiftedAlphabet1.charAt(idx);
                    if (Character.isUpperCase(currChar)){
                        encrypted.setCharAt(i,Character.toUpperCase(newC1));
                    }
                    else{
                        encrypted.setCharAt(i,newC1);
                    }
                }
                else{
                    char newC2 = shiftedAlphabet2.charAt(idx);
                    if (Character.isUpperCase(currChar)){
                        encrypted.setCharAt(i,Character.toUpperCase(newC2));
                    }
                    else{
                        encrypted.setCharAt(i,newC2);
                    }
                }
            }
            
        }
        return encrypted.toString();
   }
    
    public String decrypt (String encrypted){
        // reverseKey1-=4;
        // reverseKey2-=4;
        // if (reverseKey1<0){
            // reverseKey1+=26;
        // }
        // if (reverseKey2<0){
            // reverseKey2+=26;
        // }
        // System.out.println("Two keys:"+reverseKey1+" "+reverseKey2);
        CaesarCipherTwo cc = new CaesarCipherTwo(reverseKey1,reverseKey2);
        String message = cc.encrypt(encrypted);
        System.out.println(message);
        return message;
    }
}




