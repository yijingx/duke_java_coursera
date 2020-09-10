
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies (String filename){
        FileResource csvData = new FileResource(filename);
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        for(CSVRecord rec:csvData.getCSVParser(true)){
            Movie movie = new Movie(rec.get(0), rec.get(1), rec.get(2), rec.get(4), rec.get(5),
  rec.get(3), rec.get(7), Integer.parseInt(rec.get(6)));
            movieList.add(movie);
        }
        return movieList;
    }
    
    public ArrayList<EfficientRater> loadRaters (String filename){
        FileResource csvData = new FileResource(filename);
        ArrayList<EfficientRater> raterList = new ArrayList<EfficientRater>();
        for(CSVRecord rec:csvData.getCSVParser(true)){
            String rater_id = rec.get(0);
            String movie_id = rec.get(1);
            double rating = Double.parseDouble(rec.get(2));
            EfficientRater rater = new EfficientRater(rater_id);
            rater.addRating(movie_id, rating);
            raterList.add(rater);
        }
        return raterList;
    }
    
    public void testLoadMovies (){
        ArrayList<Movie> mlist = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Total number of movies:"+mlist.size());
        // for (Movie m:mlist){
            // System.out.println(m);
        // }
        certainGenre(mlist,"Comedy");
        greaterTime(mlist,150);
        directorMovies(mlist);
    }
    
    public void testLoadRaters (){
        FirstRatings fr = new FirstRatings();
        ArrayList<EfficientRater> raterList = fr.loadRaters("data/ratings.csv");
        System.out.println("Total number of ratings:"+raterList.size());
        for (EfficientRater m:raterList){
            System.out.println(m);
        }
        rateNum(raterList,"193");
        maxRater(raterList);
        rateNumMv(raterList,"1798709");
        rateByAll(raterList);
    }
    
    public void rateNum(ArrayList<EfficientRater> raterList,String id){
        int count = 0;
        for (EfficientRater m:raterList){
            String rid = m.getID();
            if(rid.equals(id)){
                count+=1;
            }
        }
        System.out.println("rateNum:"+count);
    }
    
    public void maxRater(ArrayList<EfficientRater> raterList){
        HashMap<String,Integer> ratemap = new HashMap<String,Integer>();
        ArrayList<String> maxRater = new ArrayList<String>();
        int maxNum = 0;
        for (EfficientRater m:raterList){
            String d = m.getID();
            if(ratemap.containsKey(d)){
                ratemap.put(d,ratemap.get(d)+1);
            }
            else{
                ratemap.put(d,1);
            }
            if(ratemap.get(d)>maxNum){
                maxRater = new ArrayList<String>();
                maxRater.add(d);
                maxNum = ratemap.get(d);
            }else if (ratemap.get(d)==maxNum){
                maxRater.add(d);
            }
        }
        System.out.println("MaxNum rater number:"+maxNum);
        System.out.println("MaxNum Rater:"+maxRater);
    }
    
    public int rateNumMv(ArrayList<EfficientRater> raterList,String id){
        int count = 0;
        for (EfficientRater m:raterList){
            if(m.hasRating(id)){
                count+=1;
            }
        }
        // System.out.println("rateNumMv:"+count);
        return count;
    }
    
    public void rateByAll(ArrayList<EfficientRater> raterList){
        int count = 0;
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        // Rater preRater = raterList.get(0);
        // int totalRater = 1;
        for (EfficientRater m:raterList){
            ArrayList<String> myRatings = m.getItemsRated();
            for(String mv:myRatings){
                if(map.containsKey(mv)){
                    map.put(mv,map.get(mv)+1);
                }
                else{
                    map.put(mv,1);
                }
            }
            // if(!m.getID().equals(preRater.getID())){
                // preRater = m;
                // totalRater += 1;
            // }
        }
        System.out.println("total movies:"+map.size());
        // System.out.println(map);
        // System.out.println(totalRater);
        // for(String s:map.keySet()){
            // if(map.get(s)==raterList.size()){
                // count+=1;
            // }
        // }
        // System.out.println("movies have been rated by all these raters:"+count);
    }
    
    public void certainGenre(ArrayList<Movie> mlist,String genre){
        int count = 0;
        for (Movie m:mlist){
            if(m.getGenres().contains(genre)){
                count+=1;
            }
        }
        System.out.println("certainGenre "+genre+" :"+count);
    }
    
    public void greaterTime(ArrayList<Movie> mlist,int num){
        int count = 0;
        for (Movie m:mlist){
            if(m.getMinutes()>num){
                count+=1;
            }
        }
        System.out.println("greaterTime:"+count);
    }
    
    public void directorMovies(ArrayList<Movie> mlist){
        HashMap<String,Integer> dirmap = new HashMap<String,Integer>();
        ArrayList<String> maxDir = new ArrayList<String>();
        int maxNum = 0;
        for (Movie m:mlist){
            String d = m.getDirector();
            if(dirmap.containsKey(d)){
                dirmap.put(d,dirmap.get(d)+1);
            }
            else{
                dirmap.put(d,1);
            }
            if(dirmap.get(d)>maxNum){
                maxDir = new ArrayList<String>();
                maxDir.add(d);
                maxNum = dirmap.get(d);
            }else if (dirmap.get(d)==maxNum){
                maxDir.add(d);
            }
        }
        System.out.println("MaxNum director:"+maxNum);
        System.out.println(maxDir);
    }
}














