
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    
    private int cNum;
    private HashMap<String,ArrayList<String>> map;
    
    public EfficientMarkovModel(int charNum) {
        myRandom = new Random();
        //myRandom.setSeed(365);
        cNum = charNum;
        map = new HashMap<String,ArrayList<String>>();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public void buildMap (){
        for(int i=0;i<myText.length()-cNum;i++){
            String sub = myText.substring(i,i+cNum);
            String next = myText.substring(i+cNum,i+cNum+1);
            if(!map.containsKey(sub)){
                ArrayList<String> list = new ArrayList<String>();
                list.add(next);
                map.put(sub,list);
            }
            else{
                ArrayList<String> list = map.get(sub);
                list.add(next);
                map.put(sub,list);
            }
        }
    }
    
    public ArrayList<String> getFollows (String parameter){
        ArrayList<String> follows = new ArrayList<String>();
        if(map.containsKey(parameter)) {
            follows = map.get(parameter);
        }
        return follows;
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - cNum);
        String key = myText.substring(index, index + cNum);
        sb.append(key);
        for(int k=0; k < numChars-cNum; k++){
            ArrayList<String> follow = getFollows(key);
            if(follow.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follow.size());
            sb.append(follow.get(index));
            key = key.substring(1)+follow.get(index);
        }
        
        return sb.toString();
    }
    
    public String toString(){
        return ("MarkovModel of order "+cNum);
    }
    
    public void printHashMapInfo (){
        if(map.size()<25){
            System.out.println(map);
        }
        System.out.println(map.size());
        int maxSize = 0;
        ArrayList<String> maxKey = new ArrayList<String>();
        for (String key:map.keySet()){
            int size = map.get(key).size();
            if (size>=maxSize){
                maxSize = size;
                maxKey.add(key);
            }
        }
        System.out.println("max size:"+maxSize+"  maxKey:"+maxKey);
    }
}




















