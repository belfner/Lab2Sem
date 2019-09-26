import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Monitor
{
    String name;
    boolean waiting;
    Semaphore sem;

    public Monitor(final String name,Semaphore sem)
    {
        this.name = name;
        this.sem = sem;
    }

    public synchronized void ping(final Monitor Monitor)
    {
        if(!sem.tryAcquire())
        {

            try
            {
                System.out.println(this.name + " (ping): wait to ask to confirm at "+ Main.getTime());
                this.wait();
                sem.acquire();
            } catch (InterruptedException exc)
            {
                System.out.println(exc);
            }
        }
        else
        {
            System.out.println(this.name + "(Semaphore): acquired permit at "+ Main.getTime());
        }

        System.out.println(this.name + "(ping): asking " + Monitor.name + " to confirm at "+ Main.getTime());
        Monitor.confirm(this);
        System.out.println(this.name + "(ping): got confirmation at "+ Main.getTime());
        sem.release();
        System.out.println(this.name + "(Semaphore): released permit at "+ Main.getTime());
        Monitor.release(this);

    }

    public synchronized void confirm(final Monitor Monitor)
    {
        System.out.println(this.name + "(confirm): confirm to " + Monitor.name+ " at "+ Main.getTime());
    }

    public synchronized void release(final Monitor Monitor)
    {
        System.out.println(Monitor.name + ": " + this.name + " released at "+ Main.getTime());
        this.notify();
    }

}
