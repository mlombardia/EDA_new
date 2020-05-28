public class SortedLinkedListTester {
    public static void main(String[] args){
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(2);
        list.add(4);
        list.add(3);
        for(Integer s:list){
            System.out.println(s);
        }
        System.out.println(list.getMax());
        System.out.println(list.getMin());
        System.out.println(list.size());
        System.out.println("--------------------------------------------------");
        list.delete(2);
        for(Integer s:list){
            System.out.println(s);
        }
        System.out.println(list.getMax());
        System.out.println(list.getMin());
        System.out.println(list.size());
        System.out.println("--------------------------------------------------");
        list.add(5);
        for(Integer s:list){
            System.out.println(s);
        }
        System.out.println(list.getMax());
        System.out.println(list.getMin());
        System.out.println(list.size());
        System.out.println("--------------------------------------------------");
        list.delete(4);
        for(Integer s:list){
            System.out.println(s);
        }
        System.out.println(list.getMax());
        System.out.println(list.getMin());
        System.out.println(list.size());
        System.out.println("--------------------------------------------------");
        list.add(6);
        for(Integer s:list){
            System.out.println(s);
        }
        System.out.println(list.getMax());
        System.out.println(list.getMin());
        System.out.println(list.size());
        System.out.println("--------------------------------------------------");
        list.delete(6);
        for(Integer s:list){
            System.out.println(s);
        }
        System.out.println(list.getMax());
        System.out.println(list.getMin());
        System.out.println(list.size());
        list.delete(7);

        SortedLinkedList<Integer> list2 = new SortedLinkedList<>();
        list2.delete(7);
        System.out.println(list2.getMin());
        System.out.println(list2.getMax());
        System.out.println(list2.size());
    }
}
