
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String toString() {
        return "MarkovWord of order " + myOrder;
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram wg = new WordGram(myText,index,myOrder);
        sb.append(wg.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(wg);
            //System.out.println(key + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            wg = wg.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int idx = indexOf(myText,kGram,0);
        while (idx!=-1&&idx<myText.length-myOrder){
            follows.add(myText[idx+myOrder]);
            idx = indexOf(myText,kGram,idx+myOrder);
        }
        return follows;
    }

    private int indexOf(String[] words, WordGram target, int start){
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        for (int i = 0;i<words.length-target.length();i++){
            WordGram wg = new WordGram(words,i,myOrder);
            list.add(wg);
        }
        for(int i=start;i<list.size();i++){
            WordGram cur = list.get(i);
            if(target.equals(cur)){
                return i;
            }
        }
        return -1;
    }
}
