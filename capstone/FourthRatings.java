
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    public double getAverageByID (String id,int minimalRaters){
        int numRater = 0;
        double totalRater = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters(); 
        for (Rater rt:myRaters){
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
    
    public double dotProduct(Rater me,Rater r){
        ArrayList<String> melist = me.getItemsRated();
        double res = 0;
        for(String movie:melist){
            if(r.hasRating(movie)){
                double rate1 = me.getRating(movie)-5;
                double rate2 = r.getRating(movie)-5;
                res+=rate1*rate2;
            }
        }
        return res;
    }
    
    public ArrayList<Rating> getSimilarities(String id){
        Rater me = RaterDatabase.getRater(id);
        // System.out.println(me);
        // System.out.println(me.getItemsRated());
        ArrayList<Rater> allRater =  RaterDatabase.getRaters();
        ArrayList<Rating> resList = new ArrayList<Rating>();
        for(Rater r:allRater){
            if(r.getID().equals(id)){
                continue;
            }
            double simi = dotProduct(me,r);
            if(simi>0){
                Rating newRating = new Rating(r.getID(),simi);
                resList.add(newRating);
            }
        }
        Collections.sort(resList,Collections.reverseOrder());
        return resList;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id,int minimalRaters,int numSimilarRaters){
        ArrayList<Rating> allSimi = getSimilarities(id);
        HashMap<Rater,Double> topRaterSimi = new HashMap<Rater,Double>();
        Rater hostRater = RaterDatabase.getRater(id);
        if (allSimi.size() < numSimilarRaters){
            numSimilarRaters = allSimi.size();
        }
        for(int i=0;i<numSimilarRaters;i++){
            Rating curRating = allSimi.get(i);
            String raterID = curRating.getItem();
            Rater rater2 = RaterDatabase.getRater(raterID);
            double weight = curRating.getValue();
            topRaterSimi.put(rater2,weight);
        }
        HashMap<String,Double> totalRatings = new HashMap<String,Double>();
        HashMap<String,Double> weight = new HashMap<String,Double>();
        HashMap<String,Integer> number = new HashMap<String,Integer>();
        for(Rater rater:topRaterSimi.keySet()){
            ArrayList<String> raterMovies = rater.getItemsRated();
            double simi = topRaterSimi.get(rater);
            for(String movie:raterMovies){
                double score = rater.getRating(movie);
                if(totalRatings.containsKey(movie)){
                    totalRatings.put(movie,totalRatings.get(movie)+score*simi);
                    weight.put(movie,weight.get(movie)+simi);
                    number.put(movie,number.get(movie)+1);
                }
                else{
                    totalRatings.put(movie,score*simi);
                    weight.put(movie,simi);
                    number.put(movie,1);
                }
            }
        }
        ArrayList<Rating> resMovies = new ArrayList<Rating>();
        for(String movie:totalRatings.keySet()){
            double total = totalRatings.get(movie);
            double w = weight.get(movie);
            int num = number.get(movie);
            double average = total/num;
            if(num<minimalRaters){
                continue;
            }
            Rating r = new Rating(movie,average);
            resMovies.add(r);
        }
        Collections.sort(resMovies,Collections.reverseOrder());
        return resMovies;
    }
    
    // public ArrayList<Rating> getSimilarRatings(String raterID,int minimalRaters, int numSimilarRaters){
        // ArrayList<Rating> similarRatings = getSimilarities(raterID);
        // ArrayList<Rating> ratings = new ArrayList<Rating>();
       
        // for(String  movieID : MovieDatabase.filterBy(new TrueFilter())){            
            // double avg = 0;
            // int numRaters = 0;
            // for(int i=0; i<numSimilarRaters; i++){                
                // //System.out.println("similar rating = " + rating.getValue());                
                // Rater curRater = RaterDatabase.getRater(similarRatings.get(i).getItem()); 
                // Rating rating = similarRatings.get(i);
                // if(curRater.hasRating(movieID)){
                    // avg = avg + rating.getValue() * curRater.getRating(movieID);         
                    // numRaters +=1;
                // }                
            // }  
            // if(numRaters >= minimalRaters){
                // ratings.add(new Rating(movieID, avg/numRaters));               
            // }

       // }
       // Collections.sort(ratings, Collections.reverseOrder());
       // return ratings;
    // }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int minimalRaters,int numSimilarRaters,Filter filterCriteria){
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<Rating> simi = getSimilarities(id);
        ArrayList<String> mvList = MovieDatabase.filterBy(filterCriteria);
        if (simi.size() < numSimilarRaters){
            numSimilarRaters = simi.size();
        }
        for(String movie_id:mvList){
            double total = 0;
            int num = 0;
            for(int i=0;i<numSimilarRaters;i++){
                Rating rating = simi.get(i);
                String raterID= rating.getItem();
                double curSimi = rating.getValue();
                Rater r = RaterDatabase.getRater(raterID);
                ArrayList<String> allMovieCurRater = r.getItemsRated();
                if(allMovieCurRater.contains(movie_id)){
                    double curRating = r.getRating(movie_id);
                    total+=curRating*curSimi;
                    num+=1;
                }
            }
            if(num>=minimalRaters){
                double avg = total/num;
                Rating oneMovie = new Rating(movie_id,avg);
                list.add(oneMovie);
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
}









