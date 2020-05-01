import org.apache.commons.text.similarity.LevenshteinDistance;

public abstract class NewLevenshtein {
    public static double calculate(String s1, String s2){
        Integer difference = new LevenshteinDistance().apply(s1,s2);
        return 1-((double)difference/Math.max(s1.length(),s2.length()));
    }
}
