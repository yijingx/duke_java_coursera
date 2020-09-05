
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovWordTwo implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        sb.append(key1);
        sb.append(" ");
        String key2 = myText[index+1];
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1,key2);
            //System.out.println(key + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1,String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int idx = indexOf(myText,key1,key2,0);
        while (idx!=-1&&idx<myText.length-2){
            follows.add(myText[idx+2]);
            idx = indexOf(myText,key1,key2,idx+2);
        }
        return follows;
    }

    private int indexOf(String[] words, String target1, String target2,int start){
        for(int i=start;i<words.length-1;i++){
            
            if(words[i].equals(target1)&&words[i+1].equals(target2)){
                return i;
            }
        }
        return -1;
    }
    
    
}
