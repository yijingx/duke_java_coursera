
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import java.util.ArrayList;
import edu.duke.*;
import java.io.*;

public class WordsInFiles {
    
    private HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    private void addWordsFromFile (File f){
        FileResource fr = new FileResource(f);
        for(String word : fr.words()) {
            if(!map.containsKey(word)) {
                ArrayList<String> fileNames = new ArrayList<String>();
                fileNames.add(f.getName());
                map.put(word, fileNames);
            } 
            else {
                ArrayList<String> fileNames = map.get(word);
                if(!fileNames.contains(f.getName())) {
                    fileNames.add(f.getName());
                    map.put(word, fileNames);
                }
            }
        }
    }
    public void buildWordFileMap (){
        map = new HashMap<String,ArrayList<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    public int maxNumber (){
        int maxNum = 0;
        String maxS = "";
        for (String s:map.keySet()){
            int curNum = map.get(s).size();
            if (curNum>maxNum){
                maxNum = curNum;
                maxS = s;
            }
        }
        System.out.println("naxNumber:"+maxNum+" maxString:"+maxS);
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles (int number){
        ArrayList<String> lword = new ArrayList<String>();
        for (String s:map.keySet()){
            int curNum = map.get(s).size();
            if(curNum==number){
                lword.add(s);
            }
        }
        return lword;
    }
    
    public void printFilesIn (String word){
        ArrayList<String> wordlist = map.get(word);
        System.out.println("printFilesIn:"+word);
        for (int i=0;i<wordlist.size();i++){
            System.out.println(wordlist.get(i));
        }
    }
    
    public void test(){
        buildWordFileMap();
        // System.out.println("total words number:"+map.size());
        // ArrayList<String> number = wordsInNumFiles(4);
        // System.out.println("Appear "+4+" times "+number.size());
        // number = wordsInNumFiles(5);
        // System.out.println("Appear "+5+" times "+number.size());
        // System.out.println("sad:");
        // printFilesIn ("sad");
        // System.out.println("red:");
        // printFilesIn ("red");
        //printFilesIn("cats");
        // ArrayList<String> number = wordsInNumFiles(7);
        // System.out.println("Appear "+7+" times "+number.size());
        // ArrayList<String> number = wordsInNumFiles(4);
        // System.out.println("Appear "+4+" times "+number.size());
        // maxNumber();
        System.out.println("sea:");
        printFilesIn ("sea");
        System.out.println("tree:");
        printFilesIn ("tree");
    }
}















