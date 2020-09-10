
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int min;
    private int max;
    
    public MinutesFilter(int minute1,int minute2){
        min = minute1;
        max = minute2;
    }
    
    public boolean satisfies(String id){
        int movieMin = MovieDatabase.getMinutes(id);
        if(movieMin>=min&&movieMin<=max){
            return true;
        }
        return false;
    }
}
