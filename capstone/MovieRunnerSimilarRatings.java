
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        
        TrueFilter yf = new TrueFilter();
        ArrayList<Rating> avgRatings = sr.getAverageRatingsByFilter(1,yf);
        Collections.sort(avgRatings);
        System.out.println("found "+avgRatings.size()+" movies");
        for(Rating r:avgRatings){
            System.out.print(r.getValue()+" ");
            System.out.println(MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        // Rater me = RaterDatabase.getRater("65");
        // System.out.println(me);
        // System.out.println(me.getItemsRated());
        AllFilters allF = new AllFilters();
        YearAfterFilter f1 = new YearAfterFilter(1980);
        GenreFilter f2 = new GenreFilter("Romance");
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> avgRatings = sr.getAverageRatingsByFilter(1,allF);
        Collections.sort(avgRatings);
        System.out.println("found "+avgRatings.size()+" movies");
        // for(Rating r:avgRatings){
            // System.out.print(r.getValue()+" ");
            // System.out.println(MovieDatabase.getTitle(r.getItem())+" ");
            // System.out.println(MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getGenres(r.getItem()));
        // }
    }
    
    public void printSimilarRatings (){
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        ArrayList<Rating> list = sr.getSimilarRatings("71",5,20);
        for (Rating r: list){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " : " + r.getValue());
        }
        // System.out.println(list);
    }
    
    public void printSimilarRatingsByGenre (){
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        Filter f = new GenreFilter("Mystery");
        ArrayList<Rating> list = sr.getSimilarRatingsByFilter("964",5,20,f);
        for (Rating r: list){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " : " + r.getValue());
        }
    }
    
    public void printSimilarRatingsByDirector  (){
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> list = sr.getSimilarRatingsByFilter("120",2,10,f);
        for (Rating r: list){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " : " + r.getValue());
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        AllFilters allF = new AllFilters();
        MinutesFilter f1 = new MinutesFilter(80,160);
        GenreFilter f2 = new GenreFilter("Drama");
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> list = sr.getSimilarRatingsByFilter("168",3,10,allF);
        for (Rating r: list){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " : " + r.getValue());
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        AllFilters allF = new AllFilters();
        YearAfterFilter f1 = new YearAfterFilter(1975);
        MinutesFilter f2 = new MinutesFilter(70,200);
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> list = sr.getSimilarRatingsByFilter("314",5,10,allF);
        for (Rating r: list){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " : " + r.getValue());
        }
    }
}


















