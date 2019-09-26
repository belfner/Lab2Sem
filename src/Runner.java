class Runner extends Thread {
    Monitor m1;
    Monitor m2;

    public Runner(Monitor m1, Monitor m2) {
        this.m1 = m1;
        this.m2 = m2;
    }

    public void run() {
        this.m1.ping(this.m2);
    }
}
