
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel (char ch){
        String a = "aeiouAEIOU";
        int idx = a.indexOf(ch);
        if (idx==-1){
            return false;
        }
        return true;
    }
    
    public String replaceVowels (String phrase, char ch){
        StringBuilder a = new StringBuilder(phrase);
        for (int i = 0;i<phrase.length();i++){
            char curChar = phrase.charAt(i);
            if (isVowel(curChar)){
                a.setCharAt(i,'*');
            }
        }
        return a.toString();
    }
    
    public String emphasize (String phrase,char ch){
        StringBuilder a = new StringBuilder(phrase);
        for (int i = 0;i<phrase.length();i++){
            char curChar = phrase.charAt(i);
            if (curChar==ch){
                if (i%2==0){
                    a.setCharAt(i,'*');
                }
                else{
                    a.setCharAt(i,'+');
                }
            }
        }
        return a.toString();
    }
    
    public void test(){
        String a1 = replaceVowels ("daeioncsOI", '+');
        System.out.println(a1);
        a1 = emphasize ("dooaeioncosOI", 'o');
        System.out.println(a1);
    }
}













