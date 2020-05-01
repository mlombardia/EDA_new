import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.concurrent.TimeUnit;

public class TimerTester {
    public static void main(String args[]) throws InterruptedException {
        DateTime.Property milis = DateTime.now().millisOfDay();
        System.out.println(milis.getAsString());
        System.out.println("HOLA");
        System.out.println("TOY");
        System.out.println("ACA");
        System.out.println("HACIENDO");
        System.out.println("COSA");
        TimeUnit.SECONDS.sleep(1);
        DateTime.Property milis2 = DateTime.now().millisOfDay();
        System.out.println(milis2.getAsString());
        //Period period = new Period(milis, milis2);
    }
}
