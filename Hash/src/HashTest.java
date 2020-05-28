import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class HashTest {
    public static void main(String args[]) throws IOException {

        Function fn = new Function() {
            @Override
            public Integer apply(Object o) {
                return (int)o;
            }
        };

        Hash<Integer, String> lista = new Hash<>(fn);

        /*File file = new File("C:\\Users\\maxim\\Documents\\EDA_new\\Hash\\src\\resources\\amazon-categories.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        String words[] = new String[50];
        while ((st = br.readLine()) != null) {
            words = st.split("#");
            lista.insert(words[0], st);
            lista.dump();
            System.out.println(lista.size());
            System.out.println("-------------------------------------------------");
        }*/

        lista.insert(3, "Dick");
        lista.dump();
        lista.insert(23, "Joe");
        lista.dump();
        lista.insert(4, "Sue");
        lista.dump();
        lista.insert(15, "Meg");
        lista.dump();
        lista.delete(23);
        lista.dump();
        lista.delete(15);
        lista.dump();
        lista.insert(4, "Sue");
        lista.dump();
        lista.insert(43, "Paul");
        lista.dump();
        System.out.println(lista.getValue(3));
    }
}
