
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings();
        // int mvSize = sr.getMovieSize();
        // int rtSize = sr.getRaterSize();
        // System.out.println("Movie size:"+mvSize);
        // System.out.println("Rater size"+rtSize);
        ArrayList<Rating> avgRatings = sr.getAverageRatings(12);
        Collections.sort(avgRatings);
        System.out.println(avgRatings.size());
        for(Rating r:avgRatings){
            System.out.println(r);
        }
        
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings();
        String id = sr.getID("Vacation");
        double avgRate = sr.getAverageByID(id,1);
        System.out.println(avgRate);
    }
}
