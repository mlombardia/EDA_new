import org.apache.commons.codec.EncoderException;


public class APITester {
    public static void main(String args[]) throws EncoderException {

//-------------------------------------------- LEVENSHTEIN -------------------------------------------------------------

        System.out.println("Levenshtein:");
        System.out.println(NewLevenshtein.calculate("salesal","alale"));

//-------------------------------------------- SOUNDEX -----------------------------------------------------------------

        System.out.println("Soundex:");
        System.out.println(NewSoundex.calculate("salesal","alale"));

//-------------------------------------------- METAPHONE ---------------------------------------------------------------

        System.out.println("Metaphone:");
        System.out.println(NewMetaphone.calculate("salesal","alale"));

//-------------------------------------------- Q-Gram(Q=3) -------------------------------------------------------------

        System.out.println("Q-Gram (Q=3): ");
        System.out.println(NewTriGram.calculate("salesal", "alale"));

    }
}

