
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    public int howMany (String a, String b){
        int count=0;
        int idx = b.indexOf(a);
        while (idx>=0){
            count+=1;
            idx = b.indexOf(a,idx+a.length());
        }
        return count;
    }
    public void testHowMany (){
        int num = howMany("GAA","ATGAACGAATTGAATC");
        System.out.println("Res:"+num);
        num = howMany("AA","ATAAAA");
        System.out.println("Res:"+num);
    }
}
