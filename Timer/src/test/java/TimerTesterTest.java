import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TimerTesterTest {

    @Test
    void main() throws InterruptedException {
        Timer t = new Timer();
        TimeUnit.SECONDS.sleep(1);
        t.stop();
        System.out.println(t);
        assertNotEquals("(0 ms) 0 dias 0 hs 0 mins 0 s", t);
    }

    @Test
    void checkLimits() throws InterruptedException{
        Timer t = new Timer();
        TimeUnit.SECONDS.sleep(1);
        assertThrows(RuntimeException.class, ()->{
           System.out.println(t);
        });
        t.stop();
    }

    @Test
    void checkTimeUnits() throws InterruptedException{
        Timer t = new Timer();
        TimeUnit.SECONDS.sleep(1);
        t.stop();
        System.out.println(t);
        assertTrue((t.getDays() >= 0 && t.getDays() < 365) && (t.getHours() >= 0 && t.getHours() < 24) &&
                (t.getMinutes() >= 0 && t.getMinutes() < 60) && (t.getSeconds() >= 0 && t.getSeconds() < 60));
    }

    @Test
    void checkStillGoing() throws InterruptedException{
        Timer t = new Timer();
        TimeUnit.SECONDS.sleep(1);
        t.stop();
        long aux = t.getEnd();
        TimeUnit.SECONDS.sleep(1);
        t.stop();
        assertEquals(t.getEnd(), aux);
    }
}