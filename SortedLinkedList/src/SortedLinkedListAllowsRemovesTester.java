import java.util.Iterator;

public class SortedLinkedListAllowsRemovesTester {
    public static void main(String[] args){
        SortedListService<Integer> a = new SortedLinkedListAllowsRemoves<>();
        a.add(50); a.add(30); a.add(40); a.add(10);
        a.add(20); a.add(60); a.add(70); a.add(80);
        a.dump();

        System.out.println("tamanio = " + a.size());
        System.out.println("min = " + a.getMin());
        System.out.println("max = " + a.getMax());

        System.out.println("con iterador...");

        for (Iterator<Integer> iter = a.iterator(); iter.hasNext();){
            Integer nro = iter.next();
            if (nro.equals(80) || nro.equals(10) || nro.equals(40)){
                System.out.println(String.format("deleting %s", nro));
                iter.remove();
            }
            else{
                System.out.println(String.format("intacto %s", nro));
            }
        }
        a.dump();
        System.out.println("tamanio = " + a.size());
        System.out.println("min = " + a.getMin());
        System.out.println("max = " + a.getMax());
    }
}
