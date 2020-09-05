
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setSeed(int seed) {
        myRandom.setSeed(seed);
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows (String parameter){
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
