
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor、
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fileName = new FileResource(filename);
         for(String line:fileName.lines()){
             // WebLogParser webParser = new WebLogParser();
             LogEntry log = WebLogParser.parseEntry(line);
             records.add(log);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le:records){
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
                }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum (int num){
         System.out.println("printAllHigherThanNum");
         for (LogEntry le:records){
             int status = le.getStatusCode();
             if (status>num){
                System.out.println(le);
                }
         }
        }
        
     public ArrayList<String> uniqueIPVisitsOnDay (String someday){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le:records){
            String date = le.getAccessTime().toString();
            String ipAddr = le.getIpAddress();
            if (date.contains(someday)&&!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs;
        }
        
        public int countUniqueIPsInRange (int low,int high){
            int count = 0;
            HashMap<String,String> map = new HashMap<String,String>();
            for (LogEntry le:records){
                int status = le.getStatusCode();
                String ipAddr = le.getIpAddress();
                if (status>low&&status<high &&!map.containsKey(ipAddr)){
                    count+=1;
                    map.put(ipAddr,"add");
                }
            }
            return count;
        }
        
        public HashMap<String, Integer> countVisitsPerIP(){
            HashMap<String,Integer> map = new HashMap<String,Integer>();
            for (LogEntry le:records){
                String ipAddr = le.getIpAddress();
                if (map.containsKey(ipAddr)){
                     int num = map.get(ipAddr)+1;
                     map.put(ipAddr,num);
                }
                else{
                    map.put(ipAddr,1);
                }
            }
            return map;
        }
        
        public int mostNumberVisitsByIP(HashMap<String, Integer> map){
            int maxNum = 0;
            for (Integer value:map.values()){
                if (value>maxNum){
                    maxNum = value;
                }
            }
            return maxNum;
        }
        
        public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
            int maxNum = 0;
            ArrayList<String> list = new ArrayList<String>();
            for (String s:map.keySet()){
                int visit = map.get(s);
                if (visit == maxNum){
                    list.add(s);
                }
                if (visit > maxNum){
                    list = new ArrayList<String>();
                    list.add(s);
                    maxNum = visit;
                }
            }
            return list;
        }
        
        public HashMap<String, ArrayList<String>> iPsForDays(){
            HashMap<String, ArrayList<String>> dayMap = new HashMap<String, ArrayList<String>>();
            for (LogEntry le:records){
                String ipAddr = le.getIpAddress();
                String date = le.getAccessTime().toString().substring(4,10);
                ArrayList<String> listIp = new ArrayList<String>();
                if (dayMap.containsKey(date)){
                     listIp = dayMap.get(date);
                     listIp.add(ipAddr);
                     dayMap.put(date,listIp);
                }
                else{
                    listIp.add(ipAddr);
                    dayMap.put(date,listIp);
                }
            }
            return dayMap;
        }
        
        public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayMap){
            String date = "";
            int maxNum = 0;
            for (String s:dayMap.keySet()){
                ArrayList<String> listIp = dayMap.get(s);
                int num = listIp.size();
                if(num>maxNum){
                    maxNum = num;
                    date = s;
                }
            }
            return date;
        }
        
        public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> myMap,String day){
            ArrayList<String> mostIp = new ArrayList<String>();
            HashMap<String,Integer> ips = new HashMap<String,Integer>();
            for (String key:myMap.keySet()){
                if(key.equals(day)){
                    for(int i=0;i<myMap.get(key).size();i++){
                        String ipAd = myMap.get(key).get(i);
                        if(!ips.containsKey(ipAd)){
                            ips.put(ipAd,1);
                        }
                        else{
                            ips.put(ipAd,ips.get(ipAd)+1);
                        }
                    }
                }
            }
            mostIp = iPsMostVisits(ips);
            return mostIp;
        }
}















