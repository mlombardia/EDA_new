import java.util.Iterator;

public interface QueueService<T extends Comparable<? super T>> extends Iterable<T>{
    void add(T elem);

    T remove();

    T peek();

    boolean isEmpty();

    void dump();

    int size();

    Iterator<T> iterator();
}
