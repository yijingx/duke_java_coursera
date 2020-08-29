
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique (){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word:fr.words()){
            word = word.toLowerCase();
            int idx = myWords.indexOf(word);
            if (idx==-1){
                myWords.add(word);
                myFreqs.add(1);
            }
            else{
                int count = myFreqs.get(idx);
                myFreqs.set(idx,count+1);
            }
        }
    }
    public void tester(){
        findUnique();
        System.out.println("Number of unique Words:"+myWords.size());
        for (int i=0;i<myWords.size();i++){
            int count = myFreqs.get(i);
            //System.out.println(count+" "+myWords.get(i));
        }
        findIndexOfMax();
    }
    
    public int findIndexOfMax (){
        int maxnum = 0;
        String maxString = "";
        int maxidx = -1;
        for (int i=0;i<myWords.size();i++){
            int count = myFreqs.get(i);
            if (count>maxnum){
                maxnum = count;
                maxString = myWords.get(i);
                maxidx = i;
            }
        }
        System.out.println("The word that occurs most often and its ");
        System.out.println("count are:"+maxString+" "+maxnum);
        return maxidx;
    }

}











