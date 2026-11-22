import java.io.*;

public class ReportGenerator {

    // Version 1: Manual close using try-catch-finally
    public static void generateReportManual(String[] storeFiles, String summaryFile) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(summaryFile));
            for (String storeFile : storeFiles) {
                reader = new BufferedReader(new FileReader(storeFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
                reader.close(); // Closed per iteration, but leaks if an exception occurs before this line!
            }
            System.out.println("Manual report generation completed successfully.");
        } catch (IOException e) {
            System.out.println("Manual Version Error (potential file handle leak): " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Version 2: Safe try-with-resources implementation
    public static void generateReportTryWithResources(String[] storeFiles, String summaryFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(summaryFile))) {
            for (String storeFile : storeFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(storeFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                } // reader automatically closed here safely, even if exception occurs
            }
            System.out.println("Try-with-resources report generation completed successfully.");
        } catch (IOException e) {
            System.out.println("Try-with-Resources Error handled safely: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Create dummy files for testing
        String[] files = {"store1.txt", "non_existent_store.txt"};
        try {
            new File("store1.txt").createNewFile();
            FileWriter fw = new FileWriter("store1.txt");
            fw.write("Store 1 Sales: $4500\n");
            fw.close();
        } catch (IOException ignored) {}

        System.out.println("--- Running Manual Version ---");
        generateReportManual(files, "summary_manual.txt");

        System.out.println("\n--- Running Try-With-Resources Version ---");
        generateReportTryWithResources(files, "summary_twr.txt");
    }
}
