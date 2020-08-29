import java.util.HashMap;
import java.util.ArrayList;
import edu.duke.*;
import java.io.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,String> mapSource;
    private HashMap<String,ArrayList<String>> myMap;
    private int totalWords;
    private ArrayList<String> allWordsList;
    private ArrayList<String> usedCateList;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        myMap = new HashMap<String,ArrayList<String>>();
        mapSource = new HashMap<String,String>();
        usedCateList = new ArrayList<String>();
        allWordsList = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
        myMap = new HashMap<String,ArrayList<String>>();
        mapSource = new HashMap<String,String>();
        usedCateList = new ArrayList<String>();
        allWordsList = new ArrayList<String>();
    }
    
    private void initializeFromSource(String source) {
        String[] catagories = {"adjective","noun", "color",
        "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String s:catagories){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s,list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (myMap.containsKey(label)){
            
            return randomFrom(myMap.get(label));
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (allWordsList.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
        totalWords += 1;
        allWordsList.add(sub);
        if (!usedCateList.contains(sub)){
            usedCateList.add(sub);
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap() {
        int totalNumber = 0;
        for(String key : myMap.keySet()) {
            //ArrayList<String> words  = myMap.get(key);
            //totalNumber += words.size();
           totalNumber += myMap.get(key).size();
        }
        return totalNumber;
    }
    
    public int totalWordsConsidered (){
        int totalWords = 0;
        for (int i=0;i<usedCateList.size();i++){
            totalWords+=myMap.get(usedCateList.get(i)).size();
        }
        return totalWords;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Total Words: " + totalWordsInMap());
        System.out.println("Total Words Considered: " + totalWordsConsidered());
    }
    


}
