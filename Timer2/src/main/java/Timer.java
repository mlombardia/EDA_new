import org.joda.time.Period;

public class Timer {
    private long start;
    private long end;

    public Timer(){
        this.start = System.currentTimeMillis();
        System.out.println(String.valueOf(start));
    }

    void stop(){
        this.end = System.currentTimeMillis();
        System.out.println(String.valueOf(end));
    }

    public String toString(){
        long days = (end-start)/86400000;
        long hours = ((end-start)%86400000)/3600000;
        long minutes = (((end-start)%86400000)%3600000)/60000;
        long seconds = ((((end-start)%86400000)%3600000)%60000)/1000;
        String result =  "(" + String.valueOf(end-start) + " ms)";
        result = result + " " + String.valueOf(days) + " dias " + String.valueOf(hours) + " hs " + String.valueOf(minutes) + " mins " + String.valueOf(seconds) + " s ";
        return result;
    }
}
