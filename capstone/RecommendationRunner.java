
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender{
    private Random myRandom;
    private int movieNum;
    public RecommendationRunner(){
        myRandom = new Random();
        movieNum = 15;
    }
    
    public ArrayList<String> getItemsToRate(){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        ArrayList<String> toRate = new ArrayList<String>();
        Filter f = new TrueFilter();
        ArrayList<String> allMovies = MovieDatabase.filterBy(f);
        for (int k=0; k<movieNum; k++){
            int currIdx = myRandom.nextInt(MovieDatabase.size());;
            String currMovieID = allMovies.get(currIdx);
            // String currMovieTitle = MovieDatabase.getTitle(currMovieID);
            toRate.add(currMovieID);
        }
        return toRate;
    }
    public void printRecommendationsFor(String webRaterID){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fourth = new FourthRatings();
        ArrayList<Rating> result = fourth.getSimilarRatings(webRaterID, 5, 20);
        int num = result.size();
        if (num == 0){
            System.out.println("Recommendation List:");
            System.out.println("Not Found");
        } else {
            if (num > 30){
                num = 30;
            }
            String header = ("<table> <tr> <th>Title</th> <th>Rating Value</th>+<th>Genre</th> </tr>");
            String body = "";
            for (int k=0; k<num; k++){
                Rating currRating = result.get(k);
                String currMovieID = currRating.getItem();
                // System.out.println(MovieDatabase.getTitle(currMovieID) + " : " + currRating.getValue());
                String currMovieTitle = MovieDatabase.getTitle(currMovieID);
                double currRatingValue = currRating.getValue();
                String currGenre = MovieDatabase.getGenres(currMovieID);
                body += ("<tr> <td>" + currMovieTitle + "</td> <td>" + Double.toString(currRatingValue) + "</td> <td>"+currGenre+"</td> </tr>");
            }
            System.out.println(header + body + "</table>");
        }
    }
}
