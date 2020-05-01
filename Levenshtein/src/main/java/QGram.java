import java.util.ArrayList;

public class QGram {
    public static void main(String args[]){
        String str1 = ("#").concat("salesal").concat("#");
        String str2 = ("#").concat("alale").concat("#");

        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        ArrayList<String> match = new ArrayList<String>();
        int matches = 0;
        for(int i = 0;i < str1.length()-1;i++){
            list1.add(str1.substring(i,i+2));
        }
        for(int i = 0; i < str2.length()-1;i++){
            list2.add(str2.substring(i,i+2));
        }
        System.out.println(list1);
        System.out.println(list2);
        for(int i= 0;i<list1.size();i++) {
            if (!match.contains(list1.get(i))) {
                for (int j = 0; j < list2.size(); j++) {
                    if (list1.get(i).matches(list2.get(j))) {
                        if (!match.contains(list1.get(i))) {
                            match.add(list1.get(i));
                        }
                        matches++;
                    }
                }
            }
        }
        System.out.println(match);
        double similarity = ((double)(list1.size()+list2.size()-(list1.size()+list2.size()-2*matches))/(list1.size()+list2.size()));
        System.out.println(similarity);

        String str = ("#").concat("alal").concat("#");
        ArrayList<String> list3 = new ArrayList<String>();
        ArrayList<String> unique = new ArrayList<String>();
        for(int i = 0;i < str.length()-1;i++){
            list3.add(str.substring(i,i+2));
        }
        System.out.println(list3);
        int index=-1;
        for(int i=0;i < list3.size(); i++){
            if(!unique.contains(list3.get(i))) {
                unique.add(list3.get(i));
                index++;
                int start = 1;
                for (int j = 0; j < list3.size(); j++) {
                    if (i != j) {
                        if (list3.get(i).matches(list3.get(j))) {
                            start++;
                        }
                    }
                }
                System.out.println(unique.get(index) + " " + start);
            }
        }
        System.out.println(unique);
    }
}
