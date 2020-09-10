
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        // this("ratedmoviesfull.csv", "ratings.csv");
        this("data/ratings.csv");
    }
    
    
    public ThirdRatings( String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID (String id,int minimalRaters){
        int numRater = 0;
        double totalRater = 0;
        for (EfficientRater rt:myRaters){
            // ArrayList<String> mvlist = rt.getItemsRated();
            // System.out.println(mvlist);
            if (rt.hasRating(id)==true){
               numRater+=1;
               totalRater+=rt.getRating(id);
               // System.out.println("Y");
            }
        }
        if(numRater>=minimalRaters){
            return totalRater/numRater;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgList = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String id:movies){
            double avgRate = getAverageByID(id,minimalRaters);
            if(avgRate>0){
                Rating newRating = new Rating(id, avgRate);
                avgList.add(newRating);
            }
        }
        return avgList;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> avgList = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String id:movies){
            double avgRate = getAverageByID(id,minimalRaters);
            if(avgRate>0){
                Rating newRating = new Rating(id, avgRate);
                avgList.add(newRating);
            }
        }
        return avgList;
    }
}



















