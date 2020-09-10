
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String dirs;
    
    public DirectorsFilter(String directors) {
        dirs = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String movieDir = MovieDatabase.getDirector(id);
        String[] dArray = dirs.split(",");
        for (int i = 0;i<dArray.length;i++){
            String d = dArray[i];
            int idx = movieDir.indexOf(d);
            if(idx!=-1){
                return true;
            }
        }
        return false;
    }
}
