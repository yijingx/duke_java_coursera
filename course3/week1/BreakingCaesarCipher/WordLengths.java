
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.Arrays;

public class WordLengths {
    public void countWordLengths (FileResource resource, int[] counts){
        for(String word : resource.words()) {
            if(!Character.isLetter(word.charAt(0))) {
                word = word.substring(1, word.length());
            }
            if(word.length() - 1>=0&&!Character.isLetter(word.charAt(word.length() - 1))) {
                word = word.substring(0, word.length() - 1);
            }
            int length = word.length();
            if (length>30){
                length=30;
            }
            counts[length] += 1;
        }
        System.out.println(Arrays.toString(counts));
        System.out.println("Max index: "+indexOfMax(counts));
    }
    
    public int indexOfMax(int[] values){
        int index = 0;
        for (int i=0;i<values.length;i++){
            if (values[i]>values[index]){
                index = i;
            }
        }
        return index;
    }
    
    public void testCountWordLengths (){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr,counts);
    }
}
