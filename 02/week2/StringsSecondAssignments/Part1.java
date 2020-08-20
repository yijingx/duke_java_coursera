
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {
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
 
 public String findGene(String dna){
     int startIdx = dna.indexOf("ATG");
     if (startIdx<0)
        return "";
     int taaIdx = findStopCodon(dna,startIdx, "TAA");
     int atgIdx = findStopCodon(dna,startIdx, "ATG");
     int tagIdx = findStopCodon(dna,startIdx, "TAG");
     int curIdx = taaIdx;
     if (taaIdx==-1||(atgIdx>-1&&atgIdx<taaIdx)){
         curIdx = atgIdx;
        }
     if (curIdx==-1||(tagIdx>-1&&tagIdx<curIdx)){
         curIdx = tagIdx;
        }
     if (curIdx==-1){
         return "";
        }
        return dna.substring(startIdx,curIdx+3);
 }
 public void testFindGene(){
     String res = findGene("ATNDGKS");
     System.out.println("res:"+res);
     res = findGene("ATGDGKTAA");
     System.out.println("res:"+res);
     res = findGene("ATGDGKTAGTAGATG");
     System.out.println("res:"+res);
     res = findGene("ATGDGKKNSDH");
     System.out.println("res:"+res);
    }
}
