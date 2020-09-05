
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;

public class MarkovOne extends AbstractMarkovModel{
    
    public MarkovOne() {
        myRandom = new Random();
        //myRandom.setSeed(273);
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
    
    public String toString(){
        return ("MarkovModel of order 1");
    }
}
















