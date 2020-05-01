import java.util.ArrayList;

public class ExactSearch {
    public static void main(String args[]){
        KMP kmp = new KMP();
        ArrayList<Integer> all = kmp.findAll("abra".toCharArray(),"abracadabra".toCharArray());
        System.out.println(all);
    }
}
