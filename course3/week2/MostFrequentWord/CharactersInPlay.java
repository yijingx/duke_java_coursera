
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> myWords = new ArrayList<String>();
    private ArrayList<Integer> myFreqs = new ArrayList<Integer>();
    public void update (String word){
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
    public void findAllCharacters (){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String line:fr.lines()){
            if (line.contains(".")){
                int idx = line.indexOf(".");
                String sub = line.substring(0,idx);
                update(sub);
            }
        }
    }
    
    public void tester (){
        findAllCharacters();
        //charactersWithNumParts(10,15);
        charactersWithNumParts(10,15);
    }
    
    public void charactersWithNumParts (int num1,int num2){
        for (int i=0;i<myWords.size();i++){
            int count = myFreqs.get(i);
            if (count>=num1&&count<=num2){
                System.out.println(count+" "+myWords.get(i));
            }
        }
    }
}















