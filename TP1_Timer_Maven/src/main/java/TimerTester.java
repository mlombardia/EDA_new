import java.util.concurrent.TimeUnit;

public class TimerTester {
    public static void main(String args[]) throws InterruptedException {
        Timer t1 = new Timer();
        Timer t2 = new Timer(5);
        TimeUnit.SECONDS.sleep(1);
        t1.stop(60500);
        t2.stop();
        System.out.println(t1);
        System.out.println(t2);
    }
}
