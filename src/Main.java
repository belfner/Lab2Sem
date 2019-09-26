import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class Main {

        public static long startTime;
        public static DecimalFormat df = new DecimalFormat("#.###");

    public static void main (String args[]) {
        System.out.println("Starting...");
        Semaphore sem = new Semaphore(1);
        Monitor a = new Monitor("Girl",sem);
        Monitor b = new Monitor("Boy",sem);
        startTime = System.currentTimeMillis();
        (new Runner(a, b)).start();
        (new Runner(b, a)).start();

    }


    public static String getTime()
    {
        return "Time: " + df.format((double) (System.currentTimeMillis() - startTime) / 1000.0d)+"s.";
    }
}