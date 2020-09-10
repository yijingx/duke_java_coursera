
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
    private String gen;
    
    public GenreFilter(String genre) {
        gen = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        int idx = MovieDatabase.getGenres(id).indexOf(gen);
        if(idx==-1){
            return false;
        }
        return true;
    }
}
