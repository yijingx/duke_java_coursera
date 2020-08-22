
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class Part1 {
 public int findStopCodon(String dna,int startIdx, String stopCodon){
     int curIdx = dna.indexOf(stopCodon,startIdx+3);
     while (curIdx>=0){
         int len = curIdx-startIdx;
         //System.out.println("len "+stopCodon+":"+len%3);
         if (len%3==0){
            //System.out.println("curIdx:"+stopCodon+":"+curIdx);
            return curIdx;
        }
        else{
            curIdx = dna.indexOf(stopCodon,curIdx+1);
        }
     }
     return -1;
 }
 public void testFindStopCodon(){
     int res = findStopCodon("ATGGYYTGA",0,"TGA");
     System.out.println(res);
    
 }
 
 public String findGene(String dna,int startIdx){
     //int startIdx = dna.indexOf("ATG");
     int begIdx = dna.indexOf("ATG",startIdx);
     //System.out.println("begIdx:"+begIdx);
     if (begIdx==-1)
        return "";
     int taaIdx = findStopCodon(dna,begIdx, "TAA");
     int tagIdx = findStopCodon(dna,begIdx, "TAG");
     int tgaIdx = findStopCodon(dna,begIdx, "TGA");
     // System.out.println("taaIdx:"+taaIdx);
     // System.out.println("tagIdx:"+tagIdx);
     // System.out.println("tgaIdx:"+tgaIdx);
     int curIdx = taaIdx;
     if (taaIdx==-1||(tagIdx>-1&&tagIdx<taaIdx)){
         curIdx = tagIdx;
        }
     if (curIdx==-1||(tgaIdx>-1&&tgaIdx<curIdx)){
         curIdx = tgaIdx;
        }
     if (curIdx==-1){
         return "";
        }
        //System.out.println("res:"+dna.substring(begIdx,curIdx+3));
        return dna.substring(begIdx,curIdx+3);
 }
 public void testFindGene(){
     // String res = findGene("ATNDGKS",0);
     // System.out.println("res:"+res);
     // res = findGene("ATGDGKTAA",0);
     // System.out.println("res:"+res);
     // res = findGene("ATGDGKTAGTAGATG",0);
     // System.out.println("res:"+res);
     // res = findGene("ATGDGKKNSDH",0);
     // System.out.println("res:"+res);
     
     //printAllGenes("ATGATGTAAKKKATGDGKTAGTAGATGTAATGA");
     // System.out.println("res:"+res);
    }
 public void printAllGenes (String dna){
    String s = "";
    int startIdx = 0;
    while (true){
        s = findGene(dna,startIdx);
        if (s.isEmpty()){
            break;
        }
        System.out.println("printAllGenes:"+s);
        startIdx = dna.indexOf(s,startIdx)+s.length();
    }
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource st = new StorageResource();
        String s = "";
        int startIdx = 0;
        while (true){
            s = findGene(dna,startIdx);
            if (s.isEmpty()){
                break;
            }
            //System.out.println("Genes:"+s);
            startIdx = dna.indexOf(s,startIdx)+s.length();
            st.add(s);
        }
        return st;
    }
    
    public void testFindAllGene(){
     StorageResource res = getAllGenes("ATGATCTAATTTATGCTGCAACQGTGAAGA");
     for (String s:res.data()){
         System.out.println("res:"+s);
        }
    }
    
    public float cgRatio(String dna){
        int num = 0;
        for (int i=0;i<dna.length();i++){
            char c = dna.charAt(i);
            if(c=='C'||c=='G'){
                num+=1;
                //System.out.println("yes");
            }
        }
        int len = dna.length();
        float ratio = (float)num/len;
        return ratio;
    }
    
    public void testRatio(){
        String a = "CCGGGTTTTT";
        float num = cgRatio(a);
        System.out.println(num);
    }
    
    public int howMany (String a, String b){
        int count=0;
        int idx = b.indexOf(a);
        while (idx>=0){
            count+=1;
            idx = b.indexOf(a,idx+a.length());
        }
        return count;
    }
    
    public void processGenes(StorageResource sr){
        String longer9 = "";
        int num_longer9 = 0;
        String higher35 = "";
        int num_higher35 = 0;
        int num_longest = 0;
        int num_gene = 0;
        for (String dna:sr.data()){
            num_gene+=1;
            if (dna.length()>60){
                longer9+=dna;
                longer9+="\n";
                num_longer9+=1;
            }
            if(cgRatio(dna)>0.35){
                higher35+=dna;
                higher35+="\n";
                num_higher35+=1;
            }
            if (dna.length()>num_longest){
                num_longest = dna.length();
            }
        }
        System.out.println("Strings :"+num_gene);
        System.out.println("Strings in sr that are longer than 9 characters:"+longer9);
        System.out.println("number of Strings in sr that are longer than 9 characters:"+num_longer9);
        System.out.println("Strings in sr whose C-G-ratio is higher than 0.35:"+higher35);
        System.out.println("number of strings in sr whose C-G-ratio is higher than 0.35:"+num_higher35);
        System.out.println("length of the longest gene in sr:"+num_longest);
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        //System.out.println(dna);
        //printAllGenes(dna);
        // StorageResource sr = getAllGenes(dna);
        // processGenes(sr);
        int num = howMany("CTG",dna);
        System.out.println(num);
    }
}

























