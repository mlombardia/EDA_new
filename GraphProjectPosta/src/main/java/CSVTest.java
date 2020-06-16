import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CSVTest {
    public static void main(String args[]) throws FileNotFoundException {
        List<Person> artists = new CsvToBeanBuilder<Person>(new FileReader("C:\\Users\\maxim\\Documents\\EDA_new\\GraphProjectPosta\\src\\main\\resources\\artistssmall.tsv"))
                .withType(Person.class)
                .build()
                .parse();

        for (Person artist : artists){
            System.out.println(artist);
        }

        List<User> users = new CsvToBeanBuilder<User>(new FileReader("C:\\Users\\maxim\\Documents\\EDA_new\\GraphProjectPosta\\src\\main\\resources\\userssmall.tsv"))
                .withType(User.class)
                .withSeparator('\t')
                .build()
                .parse();

        for (User user : users){
            System.out.println(user);
        }
    }
}
