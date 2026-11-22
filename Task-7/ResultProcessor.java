import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class ProcessingResult {
    private final String fileName;
    private final boolean success;
    private final String message;

    public ProcessingResult(String fileName, boolean success, String message) {
        this.fileName = fileName;
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    @Override
    public String toString() {
        return "[" + (success ? "SUCCESS" : "FAILED") + "] " + fileName + " -> " + message;
    }
}

class ScriptTask implements Callable<ProcessingResult> {
    private final String fileName;

    public ScriptTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ProcessingResult call() {
        try {
            // Simulate corruption check
            if (fileName.contains("corrupt")) {
                throw new IllegalStateException("File data structure is corrupted.");
            }
            if (fileName.contains("missing")) {
                return new ProcessingResult(fileName, false, "File not found on disk.");
            }
            // Simulate score calculation logic
            Thread.sleep(50); 
            return new ProcessingResult(fileName, true, "Score computed successfully (GPA: 9.1)");
        } catch (Exception e) {
            // Fault tolerance: catch exception inside task so it doesn't crash the pool runner
            return new ProcessingResult(fileName, false, "Exception handled: " + e.getMessage());
        }
    }
}

public class ResultProcessor {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int totalFiles = 20; // Simulated batch size (scale of 10,000 files)
        List<String> fileList = new ArrayList<>();
        for (int i = 1; i <= totalFiles; i++) {
            if (i == 5) fileList.add("score_corrupt_" + i + ".txt");
            else if (i == 12) fileList.add("score_missing_" + i + ".txt");
            else fileList.add("score_file_" + i + ".txt");
        }

        // Test performance across different thread pool sizes as required by Stage 3
        int[] poolSizes = {1, 2, 4, 8};

        for (int poolSize : poolSizes) {
            long startTime = System.currentTimeMillis();
            ExecutorService executor = Executors.newFixedThreadPool(poolSize);
            List<Future<ProcessingResult>> futures = new ArrayList<>();

            for (String file : fileList) {
                futures.add(executor.submit(new ScriptTask(file)));
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

            int successCount = 0;
            int failureCount = 0;

            for (Future<ProcessingResult> future : futures) {
                ProcessingResult res = future.get();
                if (res.isSuccess()) successCount++;
                else failureCount++;
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Pool Size: " + poolSize |
                " | Time Taken: " + (endTime - startTime) + "ms" +
                " | Successes: " + successCount + " | Failures: " + failureCount);
        }
    }
}
