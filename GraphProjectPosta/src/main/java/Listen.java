import com.opencsv.bean.CsvBindByName;

public class Listen {
    @CsvBindByName(column="userid")
    private String user;

    @CsvBindByName(column="artistname")
    private String artist;

    @CsvBindByName
    private Integer qty;

    public String toString(){
        return String.format("%s=>%s qty=%d", user,artist,qty);
    }

    public String getUser(){
        return user;
    }

    public String getArtist(){
        return artist;
    }

    public Integer getQty(){
        return qty;
    }
}
