
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
import edu.duke.*;
public class MarkovModel extends AbstractMarkovModel{
    
    private int cNum;
    
    public MarkovModel(int charNum) {
        //myRandom.setSeed(365);
        cNum = charNum;
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
            index = myRandom.nextInt(follow.size());
            sb.append(follow.get(index));
            key = key.substring(1)+follow.get(index);
        }
        
        return sb.toString();
    }
    
    
    public String toString(){
        return ("MarkovModel of order "+cNum);
    }
}
