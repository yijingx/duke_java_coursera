
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientRater implements Rater{
    private String myID;
    // private ArrayList<Rating> myRatings;
    // The key in the HashMap is a movie ID, and its value is a rating associated with this movie.
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        // myRatings.add(new Rating(item,rating));
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)){
            return true;
        }
        return false;
    }
    
    public HashMap<String,Rating> getMyRatings(){
        return myRatings;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for(String s:myRatings.keySet()){
            if(myRatings.get(s).getItem().equals(item)){
                return myRatings.get(s).getValue();
            }
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        // for(int k=0; k < myRatings.size(); k++){
            // list.add(myRatings.get(k).getItem());
        // }
        for(String k:myRatings.keySet()){
            list.add(myRatings.get(k).getItem());
        }
        return list;
    }
}
