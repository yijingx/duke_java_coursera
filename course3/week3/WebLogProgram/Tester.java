
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer logObj = new LogAnalyzer();
        logObj.readFile("test.txt");
        // logObj.printAll();
        HashMap<String, Integer> map = logObj.countVisitsPerIP();
        // int num = logObj.mostNumberVisitsByIP(map);
        // System.out.println(num);
        ArrayList<String> list = logObj.iPsMostVisits(map);
        System.out.println(list);
        HashMap<String, ArrayList<String>> dayMap = logObj.iPsForDays();
        String s = logObj.dayWithMostIPVisits(dayMap);
        System.out.println(s);
        list = logObj.iPsWithMostVisitsOnDay(dayMap,"Sep 29");
        System.out.println(list);
        // int num = logObj.countUniqueIPsInRange(400,499);
        // System.out.println(num);
        // ArrayList<String> l = logObj.uniqueIPVisitsOnDay("Sep 27");
        // System.out.println(l.size());
    }
    
    public void testUniqueIP(){
        LogAnalyzer loga = new LogAnalyzer();
        loga.readFile("test.txt");
        // int num = loga.countUniqueIPs();
        // System.out.println("Unique IP:"+num);
        ArrayList<String> uniqueIPOnDay = loga.uniqueIPVisitsOnDay("Sep 30");
        System.out.println(uniqueIPOnDay);
    }
}
















