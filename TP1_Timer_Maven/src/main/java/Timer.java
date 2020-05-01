public class Timer {
    public long start;
    public long result;
    public long starting_millis;
    public Timer(){
       this.start=System.currentTimeMillis();
    }

    public Timer(long ms){
        this.start = System.currentTimeMillis() + ms;
        this.starting_millis = ms;
    }

    public void stop(){
        if(System.currentTimeMillis()<this.start){
            throw new RuntimeException();
        }
        this.result = System.currentTimeMillis()-this.start;
    }

    public void stop(long fin){
        fin+=(System.currentTimeMillis()-(System.currentTimeMillis()-this.start));
        if(fin<this.start) {
            throw new RuntimeException();
        }
        this.result = fin-this.start-this.starting_millis;
    }
//System + fin - System - start = delta + fin - start
    public String toString(){
        long remaining = this.result;
        long days = remaining/(1000*60*60*24);
        remaining = remaining%(1000*60*60*24);
        long hours = remaining/(1000*60*60);
        remaining = remaining%(1000*60*60);
        long minutes = remaining/(1000*60);
        remaining = remaining%(1000*60);
        double seconds = (double)remaining/1000;
        return String.format("(%d ms.) %d dia/s %d hs %d min %.3f segs.",this.result,days,hours,minutes,seconds);
    }
}
