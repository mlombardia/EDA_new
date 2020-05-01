import org.apache.commons.codec.language.Metaphone;

public abstract class NewMetaphone {
    public static double calculate(String s1, String s2){
        String encoding1 = new Metaphone().encode(s1);
        String encoding2 = new Metaphone().encode(s2);

        return NewLevenshtein.calculate(encoding1, encoding2);
    }
}
