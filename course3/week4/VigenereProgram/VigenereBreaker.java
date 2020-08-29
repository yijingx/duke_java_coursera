import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for (int i=whichSlice;i<message.length();i+=totalSlices){
            char c = message.charAt(i);
            sb.append(c);
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int start = 0;start<klength;start++){
            String temp = sliceString(encrypted,start,klength);
            //System.out.println(temp);
            int curKey = cc.getKey(temp);
            //System.out.println(curKey);
            key[start] = curKey;
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String,HashSet<String>> lanMap = new HashMap<String,HashSet<String>>();
        for(String lan:languages){
            FileResource dict = new FileResource("dictionaries/"+lan);
            HashSet<String> wordDict = readDictionary(dict);
            lanMap.put(lan,wordDict);
        }
        FileResource fr = new FileResource();
        String s = fr.asString();
        //v(s);
        breakForAllLangs(s,lanMap);
        //System.out.println(res);
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for (String s:fr.words()){
            s = s.toLowerCase();
            hs.add(s);
        }
        return hs;
    }
    
    public int countWords(String message,HashSet<String> dict){
        int count = 0;
        //System.out.println(message);
        for (String word:message.split("\\W")){
            word = word.toLowerCase();
            //System.out.println(word);
            if(dict.contains(word)){
                count+=1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dict){
        //int tryKeyLength = 100;
        int maxNum = 0;
        String maxS = "";
        int maxKeyLength = 0;
        char mostCommonChar = mostCommonCharIn(dict);
        for (int len=1;len<20;len++){
            int[] a = tryKeyLength(encrypted,len,mostCommonChar);
            //System.out.println(Arrays.toString(a));
            VigenereCipher vc = new VigenereCipher(a);
            String deS = vc.decrypt(encrypted);
            int countw = countWords(deS,dict);
            //System.out.println(countw);
            if(countw>maxNum){
                maxNum = countw;
                maxS = deS;
                maxKeyLength = a.length;
            }
        }
        System.out.println("keyLength:"+maxKeyLength);
        System.out.println("maxNum:"+maxNum);
        return maxS;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        char mostChar = ' ';
        int count = 0;
        for (String word:dictionary){
            char[] cs = word.toCharArray();
            for (char c:cs){
                if(!map.containsKey(c)){
                    map.put(c,1);
                }
                else{
                    map.put(c,map.get(c)+1);
                }
            }
        }
        for (Character c:map.keySet()){
            if(map.get(c)>count){
                mostChar = c;
                count = map.get(c);
            }
        }
        return mostChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int maxMatch = 0;
        String bestLan = "";
        String deCrypted = "";
        for(String language:languages.keySet()){
            HashSet<String> lanMap = languages.get(language);
            String res = breakForLanguage(encrypted, lanMap);
            int countNum = countWords(res,lanMap);
            if(countNum>maxMatch){
                maxMatch = countNum;
                bestLan = language;
                deCrypted = res;
            }
        }
        System.out.println("Best Language:"+bestLan);
        System.out.println("deCrypted message:"+deCrypted);
    }
}















