/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int boysName = 0;
        int girlsName = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                boysName+=1;
            }
            else {
                totalGirls += numBorn;
                girlsName+=1;
            }
        }
        int totalNames = girlsName+boysName;
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("total names = " + totalNames);
        System.out.println("female girls Names = " + girlsName);
        System.out.println("male boys Name = " + boysName);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob1905.csv");
        totalBirths(fr);
    }
    
    public int genderNumber(FileResource fr,String gender){
        int num = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                num+=1;
            }
        }
        return num;
    }
    
    public int getRank(int year,String name,String gender){
        String syear = Integer.toString(year);
        String filename = "data/yob"+syear+".csv";
        FileResource fr = new FileResource(filename);
        int rank = -1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(0).equals(name)&&rec.get(1).equals(gender)){
                rank = (int)rec.getRecordNumber();
            }
        }
        if (rank==-1){
            return rank;
        }
        if (gender.equals("M")){
            int fNum = genderNumber(fr,"F");
            rank-=fNum;
        }
        return rank;
    }
    
    public String getName(int year,int rank,String gender){
        String syear = Integer.toString(year);
        String filename = "data/yob"+syear+".csv";
        FileResource fr = new FileResource(filename);
        int curRank=1;
        String name = "";
        if (gender=="M"){
            int fNum = genderNumber(fr,"F");
            rank+=fNum;
        }
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (curRank==rank&&rec.get(1).equals(gender)){
                name = rec.get(0);
                break;
            }
            curRank+=1;
        }
        if (name.length()==0){
            return "NO NAME";
        }
        return name;
    }
    
    public void whatIsNameInYear (String name,int year,int newYear,String gender){
        String syear1 = Integer.toString(year);
        String filename = "data/yob"+syear1+".csv";
        FileResource fr1 = new FileResource(filename);
        String syear2 = Integer.toString(newYear);
        String filename2 = "data/yob"+syear2+".csv";
        FileResource fr2 = new FileResource(filename2);
        int rank = getRank(year,name,gender);
        //System.out.println(rank);
        String newName = getName(newYear,rank,gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if "+ "the person is born in "+newYear);
    }
    // public void test(){
        // whatIsNameInYear ("Isabella",2012,2014,"F");
    // }
    
    public int yearOfHighestRank(String name,String gender){
        int recHighest=-1;
        int curYear = 1880;
        int endYear = 2014;
        int heighestYear = -1;
        while (curYear<=endYear){
            String scurYear = Integer.toString(curYear);
            String filename = "data/yob"+scurYear+".csv";
            FileResource fr = new FileResource(filename);
            int curRank = getRank(curYear,name,gender);
            if (curRank==-1){
                curYear+=1;
                continue;
            }
            if (recHighest==-1||curRank<recHighest){
                recHighest = curRank;
                heighestYear = curYear;
            }
            curYear+=1;
        }
        if (recHighest==-1){
            return -1;
        }
        return heighestYear;
    }
    
    public double getAverageRank (String name, String gender){
        int curYear = 1980;
        int endYear = 2014;
        double sumRank = 0;
        int numYear = 0;
        while (curYear<=endYear){
            String scurYear = Integer.toString(curYear);
            String filename = "data/yob"+scurYear+".csv";
            FileResource fr = new FileResource(filename);
            int curRank = getRank(curYear,name,gender);
            System.out.println(curRank);
            if (curRank==-1){
                curYear+=1;
                continue;
            }
            sumRank+=curRank;
            numYear+=1;
            curYear+=1;
        }
        if (numYear==0){
            return -1;
        }
        return sumRank/numYear;
    }
    
    public int getTotalBirthsRankedHigher (int year,String name,String gender){
        String scurYear = Integer.toString(year);
        String filename = "data/yob"+scurYear+".csv";
        FileResource fr = new FileResource(filename);
        int birthCount = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (!rec.get(0).equals(name)&&rec.get(1).equals(gender)){
                int numbirth = Integer.parseInt(rec.get(2));
                birthCount+=numbirth;
            }
            if (rec.get(0).equals(name)&&rec.get(1).equals(gender)){
                break;
            }
        }
        return birthCount;
    }
}























