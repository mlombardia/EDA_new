import java.util.ArrayList;

public class LevenshteinImproved {
    public static void main(String args[]){
        String str1 = "exkusa";
        String str2 = "ex-amigo";

        char aux1[];
        aux1 = str1.toUpperCase().toCharArray();
        char aux2[];
        aux2 = str2.toUpperCase().toCharArray();

        int index=0;
        int a_column[] = new int[aux1.length+1];
        int other_column[] = new int[aux1.length+1];
        for(int i = 0; i <= aux1.length; i++){
            a_column[i] = i;
            //System.out.print(a_column[i] + " ");
        }
        //System.out.println();
        for(int i = 0; i < aux2.length;i++){
            other_column[0]=a_column[0]+1;
            //System.out.print(other_column[0] + " ");
            index++;
            for(int j = 0; j<=aux1.length && index<a_column.length;j++){
                other_column[index] = Math.min(Math.min(other_column[index-1]+1,a_column[index]+1),a_column[index-1]+(aux1[j]==aux2[i] && i == j? 0:1));
                //System.out.print(other_column[index] + " ");
                index++;
            }
            //System.out.println();
            System.arraycopy(other_column,0,a_column,0,other_column.length);
            index=0;
        }
        System.out.println(other_column[other_column.length-1]);
    }
}
//012345678
//101234567
//210123456
//321123456
//432223456
//543333456
//654444456

// e x k u s a
// e x - a m i g o
