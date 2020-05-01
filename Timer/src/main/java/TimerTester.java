import java.util.concurrent.TimeUnit;

public class TimerTester{
    public static void main(String args[]) throws InterruptedException {

        Timer timer = new Timer();
        System.out.println("HOLA");
        System.out.println("QUE");
        System.out.println("TAL");
        System.out.println("ESTOY");
        System.out.println("PONIENDO");
        System.out.println("COSAS");
        System.out.println("RANDOM");
        System.out.println("PARA");
        System.out.println("RELLENAR");
        Integer array[] = new Integer[10];
        array[0] = 2;
        array[1] = 3;
        array[2] = 1;
        TimeUnit.SECONDS.sleep(1);
        timer.stop();
        System.out.println(timer);
    }
}
