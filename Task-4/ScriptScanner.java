// Approach 1: Extending Thread class
class ScannerStationThread extends Thread {
    private final String stationName;

    public ScannerStationThread(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public void run() {
        try {
            System.out.println(stationName + " is [RUNNABLE] and starting scan batch.");
            Thread.sleep(500); // Simulate processing time
            System.out.println(stationName + " has finished scanning scripts.");
        } catch (InterruptedException e) {
            System.out.println(stationName + " was interrupted.");
        }
    }
}

// Approach 2: Implementing Runnable interface
class ScannerStationRunnable implements Runnable {
    private final String stationName;

    public ScannerStationRunnable(String stationName) {
        this.stationName = stationName;
    }

    @Override
    public void run() {
        try {
            System.out.println(stationName + " (Runnable) is [RUNNABLE] processing scripts.");
            Thread.sleep(400);
            System.out.println(stationName + " (Runnable) finished.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ScriptScanner {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Starting Scanner Stations using Thread Subclass ===");
        ScannerStationThread s1 = new ScannerStationThread("Station-1");
        ScannerStationThread s2 = new ScannerStationThread("Station-2");

        s1.start();
        s2.start();

        // Ensure main waits for both stations to complete using join()
        s1.join();
        s2.join();

        System.out.println("All Thread subclass stations completed. Total final check verified.\n");

        System.out.println("=== Starting Scanner Stations using Runnable Interface ===");
        Thread r1 = new Thread(new ScannerStationRunnable("Station-Alpha"));
        Thread r2 = new Thread(new ScannerStationRunnable("Station-Beta"));

        r1.start();
        r2.start();

        r1.join();
        r2.join();

        System.out.println("All Runnable stations execution finished successfully.");
    }
}
