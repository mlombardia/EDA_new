import java.util.LinkedList;

public class Sandbox {
    public static void main(String[] args) {
        System.out.println(32%10);
        System.out.println(32%20);
        System.out.println(32%30);
        LinkedList<LinkedList<Integer>> list = new LinkedList<>();
        int i = 1;
        list.add(new LinkedList<>());
        list.get(0).add(3);
        System.out.println(list.get(i));
    }
}
