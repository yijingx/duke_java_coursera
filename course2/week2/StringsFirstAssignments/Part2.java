
public class Part2 {
    public String findSimpleGene (String dna,String start,String end){
        boolean isUpper = true;
        char c = dna.charAt(0);
        if (Character.isLowerCase(c)){
            isUpper=false;
            dna.toUpperCase();
        }
        int startIdx = dna.indexOf(start);
        if (startIdx==-1){
            return "";
        }
        int endIdx = dna.indexOf(end);
        if(endIdx==-1){
            return "";
        }
        if ((endIdx-startIdx)%3==0){
            if (isUpper==true){
                return dna.substring(startIdx,endIdx+3);
            }
             else{
                 return dna.substring(startIdx,endIdx+3).toLowerCase();
                }
      
        }
        else{
            return "";
        }
    }
    public void testSimpleGene(){
        String dna = "ATGPPPYYYTAA";
        String start = "ATG";
        String end = "TAA";
        String simpleGene = findSimpleGene(dna,start,end);
        System.out.println("DNA:"+dna);
        System.out.println("Simple Gene:"+simpleGene);
        dna = "ATGPPPYYYTAAGGGWWW";
        simpleGene = findSimpleGene(dna,start,end);
        System.out.println("DNA:"+dna);
        System.out.println("Simple Gene:"+simpleGene);
    }
    public static void main (String[] args) {
        Part2 example = new Part2();
        example.testSimpleGene();
    }
}
