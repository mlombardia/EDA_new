import org.apache.commons.codec.EncoderException;

import java.io.IOException;

public class CommerceImpl {
    public static void main(String args[]) throws IOException, EncoderException {
        CommerceBack back = new CommerceBack();
        back.generateSimilarity("tender");
    }
}
