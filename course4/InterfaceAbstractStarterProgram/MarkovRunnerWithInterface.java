
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setSeed(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 5;
        
        // MarkovZero mz = new MarkovZero();
        // runModel(mz, st, size, seed);
    
        // MarkovOne mOne = new MarkovOne();
        // runModel(mOne, st, size, seed);
        
        EfficientMarkovModel mThree = new EfficientMarkovModel(5);
        runModel(mThree, st, size, 531);
        
        // MarkovFour mFour = new MarkovFour();
        // runModel(mFour, st, size, seed);

    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    public void testHashMap(){
        EfficientMarkovModel mThree = new EfficientMarkovModel(5);
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 100;
        int seed = 615;
        runModel(mThree, st, size, seed);
    }
    
    public void compareMethods (){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 2000;
        int seed = 5;
        
        long startTime = System.nanoTime();
        EfficientMarkovModel mThree = new EfficientMarkovModel(2);
        runModel(mThree, st, size, seed);
        long endTime = System.nanoTime();
        System.out.println("Time for EfficientMarkovModel: " + ((endTime - startTime) / 1000000));
        
        startTime = System.nanoTime();
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo, st, size, seed);
        endTime = System.nanoTime();
        System.out.println("Time for EfficientMarkovModel: " + ((endTime - startTime) / 1000000));
    }
}














