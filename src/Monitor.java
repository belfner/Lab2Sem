import java.util.concurrent.Semaphore;


class Monitor
{
    String name;
    Semaphore sem;

    public Monitor(final String name, Semaphore sem)
    {
        this.name = name;
        this.sem = sem;
    }

    public synchronized void ping(final Monitor m)
    {

        while (!sem.tryAcquire())
        {
            System.out.println(this.name + " (ping): waiting for permit at " + Main.getTime());
            try
            {
                this.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println(this.name + "(Semaphore): acquired permit at " + Main.getTime());

        System.out.println(this.name + "(ping): asking " + m.name + " to confirm at " + Main.getTime());
        m.confirm(this);
        System.out.println(this.name + "(ping): got confirmation at " + Main.getTime());
        sem.release();
        System.out.println(this.name + "(Semaphore): released permit at " + Main.getTime());
        m.release(this);

    }

    public synchronized void confirm(final Monitor m)
    {
        System.out.println(this.name + "(confirm): confirm to " + m.name + " at " + Main.getTime());
    }

    public synchronized void release(final Monitor m)
    {
        System.out.println(m.name + ": " + this.name + " released at " + Main.getTime());
        this.notify();
    }

}
