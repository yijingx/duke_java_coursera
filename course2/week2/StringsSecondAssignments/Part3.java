
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {
public int findStopCodon(String dna,int startIdx, String stopCodon){
     int curIdx = dna.indexOf(stopCodon,startIdx+1);
     while (curIdx>=0){
         int len = curIdx-startIdx;
         if (len%3==0){
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
     if (begIdx==-1)
        return "";
     int taaIdx = findStopCodon(dna,begIdx, "TAA");
     int tagIdx = findStopCodon(dna,begIdx, "TAG");
     int tgaIdx = findStopCodon(dna,begIdx, "TGA");
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
        return dna.substring(begIdx,curIdx+3);
 }
 public void printAllGenes (String dna){
    String s = "";
    int startIdx = 0;
    while (true){
        s = findGene(dna,startIdx);
        if (s.isEmpty()){
            break;
        }
        System.out.println("Genes:"+s);
        startIdx = dna.indexOf(s,startIdx)+s.length();
    }
    }
 public int countGenes(String dna){
     String s = "";
    int startIdx = 0;
    int count = 0;
    while (true){
        s = findGene(dna,startIdx);
        if (s.isEmpty()){
            break;
        }
        System.out.println("Genes:"+s);
        startIdx = dna.indexOf(s,startIdx)+s.length();
        count+=1;
    }
    return count;
}
    public void testCountGenes (){
        int count = countGenes("ATGATCTAATTTATGCTGCAACQGTGAAGA");
        System.out.println("Count:"+count);
    }
}
