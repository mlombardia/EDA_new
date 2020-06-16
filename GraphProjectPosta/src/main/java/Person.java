import com.opencsv.bean.CsvBindByName;

public class Person {

    @CsvBindByName
    private String name;

    public String toString(){
        return String.format("name=%s", name);
    }

    public String getName(){
        return name;
    }
}
