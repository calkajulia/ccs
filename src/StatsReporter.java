public class StatsReporter implements Runnable {

    public static final int INTERVAL = 10000;

    private Stats stats;

    public StatsReporter(Stats stats) {
        this.stats = stats;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(INTERVAL);
                synchronized(stats) {
                    System.out.println(stats.getStats());
                }
            } catch (InterruptedException e) {
                Log.log("Program failed on logging." + e.getMessage());
            }
        }
    }
}
