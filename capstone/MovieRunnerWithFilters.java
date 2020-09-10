
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        int rSize = sr.getRaterSize();
        System.out.println("Movie size:"+rSize);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        // TrueFilter yf = new TrueFilter();
        // ArrayList<Rating> avgRatings = sr.getAverageRatingsByFilter(35,yf);
        // Collections.sort(avgRatings);
        // System.out.println("found "+avgRatings.size()+" movies");
        // for(Rating r:avgRatings){
            // System.out.print(r.getValue()+" ");
            // System.out.println(MovieDatabase.getTitle(r.getItem()));
        // }
        
        // YearAfterFilter yf = new YearAfterFilter(2000);
        // ArrayList<Rating> avgRatings = sr.getAverageRatingsByFilter(20,yf);
        // Collections.sort(avgRatings);
        // System.out.println("found "+avgRatings.size()+" movies");
        // for(Rating r:avgRatings){
            // System.out.print(r.getValue()+" ");
            // System.out.println(MovieDatabase.getTitle(r.getItem()));
        // }
        
        // GenreFilter yf = new GenreFilter("Crime");
        // ArrayList<Rating> avgRatings = sr.getAverageRatingsByFilter(1,yf);
        // Collections.sort(avgRatings);
        // System.out.println("found "+avgRatings.size()+" movies");
        // for(Rating r:avgRatings){
            // System.out.print(r.getValue()+" ");
            // System.out.print(MovieDatabase.getTitle(r.getItem())+" ");
            // System.out.println(MovieDatabase.getGenres(r.getItem()));
        // }
        
        // MinutesFilter yf = new MinutesFilter(105,135);
        // ArrayList<Rating> avgRatings = sr.getAverageRatingsByFilter(5,yf);
        // Collections.sort(avgRatings);
        // System.out.println("found "+avgRatings.size()+" movies");
        // for(Rating r:avgRatings){
            // System.out.print(r.getValue()+" ");
            // System.out.print(MovieDatabase.getTitle(r.getItem())+" ");
            // System.out.println(MovieDatabase.getMinutes(r.getItem()));
        // }
        
        DirectorsFilter yf = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> avgRatings = sr.getAverageRatingsByFilter(4,yf);
        Collections.sort(avgRatings);
        System.out.println("found "+avgRatings.size()+" movies");
        for(Rating r:avgRatings){
            System.out.print(r.getValue()+" ");
            System.out.print(MovieDatabase.getTitle(r.getItem())+" ");
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        int rSize = sr.getRaterSize();
        System.out.println("Movie size:"+rSize);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        AllFilters allF = new AllFilters();
        YearAfterFilter f1 = new YearAfterFilter(1990);
        GenreFilter f2 = new GenreFilter("Drama");
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> avgRatings = sr.getAverageRatingsByFilter(8,allF);
        Collections.sort(avgRatings);
        System.out.println("found "+avgRatings.size()+" movies");
        // for(Rating r:avgRatings){
            // System.out.print(r.getValue()+" ");
            // System.out.println(MovieDatabase.getTitle(r.getItem())+" ");
            // System.out.println(MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getGenres(r.getItem()));
        // }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        int rSize = sr.getRaterSize();
        System.out.println("Movie size:"+rSize);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        AllFilters allF = new AllFilters();
        MinutesFilter f1 = new MinutesFilter(90,180);
        DirectorsFilter f2 = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> avgRatings = sr.getAverageRatingsByFilter(3,allF);
        Collections.sort(avgRatings);
        System.out.println("found "+avgRatings.size()+" movies");
        // for(Rating r:avgRatings){
            // System.out.print(r.getValue()+" ");
            // System.out.println(MovieDatabase.getTitle(r.getItem())+" ");
            // System.out.println(MovieDatabase.getDirector(r.getItem())+" "+MovieDatabase.getMinutes(r.getItem()));
        // }
    }
}













