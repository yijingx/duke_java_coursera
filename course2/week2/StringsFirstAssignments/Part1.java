

public class Part1 {
    public String findSimpleGene (String dna){
        int startIdx = dna.indexOf("ATG");
        if (startIdx==-1){
            return "";
        }
        int endIdx = dna.indexOf("TAA");
        if(endIdx==-1){
            return "";
        }
        if ((endIdx-startIdx)%3==0){
            return dna.substring(startIdx,endIdx+3);
        }
        else{
            return "";
        }
    }
    public void testSimpleGene(){
        String dna = "ATGPPPYYYTAA";
        String simpleGene = findSimpleGene(dna);
        System.out.println("DNA:"+dna);
        System.out.println("Simple Gene:"+simpleGene);
        dna = "ATGPPPYYYTAAGGGWWW";
        simpleGene = findSimpleGene(dna);
        System.out.println("DNA:"+dna);
        System.out.println("Simple Gene:"+simpleGene);
    }
    public static void main (String[] args) {
        Part1 example = new Part1();
        example.testSimpleGene();
    }

}
