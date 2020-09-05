
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
        myRandom.setSeed(365);
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);
        sb.append(key);
        for(int k=0; k < numChars; k++){
            ArrayList<String> follow = getFollows(key);
            index = myRandom.nextInt(follow.size());
            sb.append(follow.get(index));
            key = key.substring(1)+follow.get(index);
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows (String parameter){
        int idx = myText.indexOf(parameter);
        ArrayList<String> ans = new ArrayList<String>();
        while(idx>-1&&idx<myText.length()-parameter.length()){
            String sub = myText.substring(idx+parameter.length(),idx+parameter.length()+1);
            ans.add(sub);
            idx = myText.indexOf(parameter,idx+parameter.length());
        }
        return ans;
    }
}
















