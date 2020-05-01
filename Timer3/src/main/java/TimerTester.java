

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TimerTester {
    public static void main(String args[]) throws InterruptedException {
        Timer timer = new Timer();
        TimeUnit.SECONDS.sleep(1);
        timer.stop();
        System.out.println(timer);
    }
}
