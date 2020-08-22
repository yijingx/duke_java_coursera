public class Part4 {
    public boolean twoOccurrences (String a,String b){
        int idx = b.indexOf(a);
        if (idx==-1){
        return false;
    }
        int idx2 = b.indexOf(a,idx+1);
        if (idx2==-1){
            return false;
        }
        else{
        return true;
    }
    }
    
    public void testing (){
        boolean test = twoOccurrences("atg","atgctatg");
        System.out.println("two oocurrences: "+test);
        test = twoOccurrences("atg","atgctat");
        System.out.println("two oocurrences: "+test);
        String testLast = lastPart("an","bananat");
        System.out.println("last part:"+testLast);
        testLast = lastPart("zoo","forest");
        System.out.println("last part:"+testLast);
    }
    public String lastPart(String a,String b){
        int idx = b.indexOf(a);
        if (idx==-1){
            return b;
        }
        else
            return b.substring(idx+a.length(),b.length());
    }

    
    public static void main (String[] args) {
        Part3 example = new Part3();
        example.testing();
    }

}