import org.apache.commons.collections4.bag.HashBag;

public class Bag {
    public static void main(String[] args) {
        HashBagImpl<Integer> hashBag = new HashBagImpl<>();
        hashBag.add(23);
        hashBag.add(32);
        hashBag.add(23);
        System.out.println(hashBag.getCount(12));
        System.out.println(hashBag.getCount(23));
        System.out.println(hashBag.getCount(32));
    }
}
