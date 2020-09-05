
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows (){
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> res = markov.getFollows("es");
        System.out.println(res);
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
    	String st = fr.asString();
    	st = st.replace('\n', ' ');
    	MarkovOne markov = new MarkovOne();
    	markov.setTraining(st);
    	ArrayList<String> res = markov.getFollows("he");
    	//String res = markov.getRandomText(100);
        System.out.println(res.size());
    }
}
