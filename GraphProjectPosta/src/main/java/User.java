import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class User extends Person {

    @CsvBindByName
    private Character gender;

    @CsvBindByName
    private Integer age;

    @CsvBindByName(column="country")
    private String theCountry;

    @CsvBindByName(column = "registered", locale = "en-US")
    @CsvDate("MMM dd, yyyy")
    private Date signedUp;

    public String toString(){
        return String.format("#id=%s, gender=%c, age=%d, country=%s, registered=%tc",
                getName(), gender, age, theCountry, signedUp);
    }

    public Character getGender(){
        return gender;
    }

    public Integer getAge(){
        return age;
    }

    public String getCountry(){
        return theCountry;
    }

    public Date getSignedUp(){
        return signedUp;
    }

}
