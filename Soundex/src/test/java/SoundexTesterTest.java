import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoundexTesterTest {

    @Test
    void mainTest() {
        String word = Soundex.encode("INTELLIJ");
        System.out.println(word);
        assertEquals("I534",word);
    }

    @Test
    void checkSimilarity(){
        String word = Soundex.encode("INTELLIJ");
        System.out.println(word);

        String another_word = Soundex.encode("INTACTO");
        System.out.println(another_word);

        double result = Soundex.checkSimilarity(word, another_word);
        System.out.println(result);

        assertEquals(0.75, result);
    }

    @Test
    void checkSpaces(){
        String word = Soundex.encode("INT                ELLIJ");
        System.out.println(word);
        assertEquals("I534", word);
    }

    @Test
    void checkWeirdCharacters(){
        String word = Soundex.encode(";;;;;;INTELLIJ");
        System.out.println(word);
        assertEquals("I534", word);
    }

    @Test
    void checkAlternated(){
        String word = Soundex.encode("InTElLIj");
        System.out.println(word);
        assertEquals("I534", word);
    }

    @Test
    void checkDoubleEncoding(){
        String word = Soundex.encode("INTELLIJ");
        word = Soundex.encode("INTACTO");
        System.out.println(word);
        assertEquals("I532", word);
    }
}