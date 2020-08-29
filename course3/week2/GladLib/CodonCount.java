
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import edu.duke.*;
public class CodonCount {
    private HashMap<String,Integer> map;
    public CodonCount(){
        map = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap (int start,String dna){
        map = new HashMap<String,Integer>();
        for (int i = start;i<dna.length();i+=3){
            if (i+2<dna.length()){
                String sub = dna.substring(i,i+3);
                if (map.containsKey(sub)){
                    map.put(sub,map.get(sub)+1);
                }
                else{
                    map.put(sub,1);
                }
            }
        }
    }
    
    public String getMostCommonCodon(){
        int maxNum = 0;
        String maxs = "";
        for (String s:map.keySet()){
            int curNum = map.get(s);
            if (curNum>maxNum){
                maxNum = curNum;
                maxs = s;
            }
        }
        System.out.println("Most Common Number:"+maxNum);
        return maxs;
    }
    
    public void printCodonCounts (int start,int end){
        for (String s:map.keySet()){
            int curNum = map.get(s);
            if (curNum>=start&&curNum<=end){
                System.out.println("count:"+map.get(s)+" dna:"+s);
            }
        }
    }
    
    public void getNumber(int number,int frame){
        FileResource fr = new FileResource();
        String s = fr.asString();
        String mostCommon;
        s = s.toUpperCase();
        s = s.trim();
        buildCodonMap(frame,s);
        for (String s2:map.keySet()){
            if (map.get(s2)==number){
                System.out.println("Appear "+number+" times:"+s2);
            }
        }
    }
    
    public void tester(){
        System.out.println();
        FileResource fr = new FileResource();
        String s = fr.asString();
        String mostCommon;
        s = s.toUpperCase();
        s = s.trim();
        // buildCodonMap(0,s);
        // mostCommon = getMostCommonCodon();
        // printCodonCounts(0,100);
        // System.out.println("Most Common:"+mostCommon);
        buildCodonMap(1,s);
        mostCommon = getMostCommonCodon();
        System.out.println("Most Common:"+mostCommon);
        System.out.println("total number:"+map.size());
        buildCodonMap(2,s);
        mostCommon = getMostCommonCodon();
        System.out.println("Most Common:"+mostCommon);
        System.out.println("total number:"+map.size());
    }
}

















