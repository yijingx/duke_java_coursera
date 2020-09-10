
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        // this("ratedmoviesfull.csv", "ratings.csv");
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    
    public SecondRatings(String moviefile , String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        for(Movie rt:myMovies){
            String id = rt.getID();
            double avgRate = getAverageByID(id,minimalRaters);
            if(avgRate>0){
                Rating newRating = new Rating(getTitle(id), avgRate);
                avgList.add(newRating);
            }
        }
        return avgList;
    }
    
    public String getTitle(String id){
        for(Movie mv: myMovies){
            if (mv.getID().equals(id)){
                return mv.getTitle();
            }
        }
        return "Cannot found this movie.";
    }
    
    public String getID(String title){
        for(Movie mv: myMovies){
            if (mv.getTitle().equals(title)){
                return mv.getID();
            }
        }
        return "Cannot found this movie.";
    }
    
}


















