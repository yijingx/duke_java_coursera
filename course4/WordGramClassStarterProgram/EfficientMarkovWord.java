
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
import edu.duke.*;
public class EfficientMarkovWord implements IMarkovModel{
    private HashMap<WordGram,ArrayList<WordGram>> map;
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<WordGram,ArrayList<WordGram>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    public void buildMap (){
        for(int i=0;i<myText.length-myOrder;i++){
            WordGram sub = new WordGram(myText,i,myOrder);
            WordGram next = new WordGram(myText,i+myOrder,1);
            if(!map.containsKey(sub)){
                ArrayList<WordGram> list = new ArrayList<WordGram>();
                list.add(next);
                map.put(sub,list);
            }
            else{
                ArrayList<WordGram> list = map.get(sub);
                list.add(next);
                map.put(sub,list);
            }
        }
    }
    
    public ArrayList<WordGram> getFollows (WordGram wg){
        ArrayList<WordGram> follows = new ArrayList<WordGram>();
        if(map.containsKey(wg)) {
            follows = map.get(wg);
        }
        return follows;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram wg = new WordGram(myText,index,myOrder);
        sb.append(wg.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<WordGram> follows = getFollows(wg);
            //System.out.println(key + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            WordGram next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            wg = wg.shiftAdd(next.toString());
        }
        
        return sb.toString().trim();
    }
    
    public void printHashMapInfo (){
        if(map.size()<25){
            System.out.println(map);
        }
        System.out.println(map.size());
        int maxSize = 0;
        ArrayList<String> maxKey = new ArrayList<String>();
        for (WordGram key:map.keySet()){
            int size = map.get(key).size();
            if (size==maxSize){
                maxSize = size;
                maxKey.add(key.toString());
            }else if(size>maxSize){
                maxSize = size;
                maxKey = new ArrayList<String>();
                maxKey.add(key.toString());
            }
        }
        System.out.println("max size:"+maxSize+"  maxKey:"+maxKey);
    }
}

















