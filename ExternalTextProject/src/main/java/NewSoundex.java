import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;

public abstract class NewSoundex {
    public static double calculate(String s1, String s2)throws EncoderException {
        int difference = new Soundex().difference(s1,s2);
        return (double)difference*0.25;
    }
}
