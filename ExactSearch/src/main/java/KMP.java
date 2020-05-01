import java.util.ArrayList;
import java.util.List;

public class KMP {
    private static int[] nextComputation(char[] query) {
        int[] next = new int[query.length];
        next[0] = 0;     // Always. There's no proper border.
        int border = 0;  // Length of the current border
        for (int rec = 1; rec < query.length; rec++) {
            while ((border > 0) && (query[border] != query[rec]))
                border = next[border - 1];     // Improving previous computation
            if (query[border] == query[rec])
                border++;
            // else border = 0;  // redundant
            next[rec] = border;
        }
        return next;
    }


    public static int indexOf(char[] query, char[] target) {
        if (query == null || query.length == 0)
            throw new RuntimeException("Bad query string");
        if (target == null || target.length == 0)
            throw new RuntimeException("Bad target string");
        int[] next = nextComputation(query);
        int rec = 0;
        int pquery = 0;
        while (rec < target.length) {
            if (target[rec] == query[pquery]) {
                rec++;
                pquery++;
            }
            if (pquery == query.length) {  // Found!!!
                break;
            } else // mismatch?
                if (rec < target.length && target[rec] != query[pquery]) {
                    // no machea los i-1
                    if (pquery != 0)
                        pquery = next[pquery - 1];
                    else
                        rec++;
                }
        }
        if (pquery == query.length)
            return rec - pquery;
        return -1;
    }

    ArrayList<Integer> findAll(char[] query, char[] target){
        ArrayList<Integer> matches = new ArrayList<Integer>();
        if (query == null || query.length == 0)
            throw new RuntimeException("Bad query string");
        if (target == null || target.length == 0)
            throw new RuntimeException("Bad target string");
        int[] next = nextComputation(query);
        int rec = 0;
        int pquery = 0;
        while (rec < target.length) {
            if (target[rec] == query[pquery]) {
                rec++;
                pquery++;
            }
            if (pquery == query.length) {  // Found!!!
                matches.add(rec-pquery);
                pquery=next[pquery-1];
            } else // mismatch?
                if (rec < target.length && target[rec] != query[pquery]) {
                    // no machea los i-1
                    if (pquery != 0)
                        pquery = next[pquery - 1];
                    else
                        rec++;
                }
        }
        if (pquery == query.length)
            matches.add(rec-pquery);
        return matches;
    }
}
