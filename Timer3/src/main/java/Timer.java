import java.time.Duration;
import java.time.Instant;

public class Timer {
    private final Instant start;

    private Duration duration;
    public Timer(){
        this.start = Instant.now();
    }
    public void stop(){
        this.duration = Duration.between(start, Instant.now());
    }

    public String toString(){
        return String.valueOf(duration.toMillis());
    }
}
