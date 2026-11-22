CSA09 - Programming in Java | 
Assignment 3 (CO3)Student & Repository Information
Name: Surya Narayanan Muthu Kumar
Register Number: 192372007
Repository Name: CSA09-192372007-Assignment3
Course: CSA09 – Programming in Java (CO3: Exception Handling, Multithreading & Concurrency)
Java Version: Java 21.0.1 (openjdk 21.0.1 / Oracle JDK 21.0.1)
Repository StructureThis repository is organized into individual task folders (Task01 through Task07), each containing the Java source files, required sample input files, and execution output screenshots.CSA09-192372007-Assignment3/
├── README.md
├── Task01/
│   ├── ATMSimulation.java
│   ├── card_config.txt
│   └── output_screenshot.png
├── Task02/
│   ├── StorageDemo.java
│   └── output_screenshot.png
├── Task03/
│   ├── ReportGenerator.java
│   ├── store1.txt
│   └── output_screenshot.png
├── Task04/
│   ├── ScriptScanner.java
│   └── output_screenshot.png
├── Task05/
│   ├── SeatBooking.java
│   └── output_screenshot.png
├── Task06/
│   ├── TokenCounter.java
│   └── output_screenshot.png
└── Task07/
    ├── ResultProcessor.java
    └── output_screenshot.png
Prerequisites & Environment SetupEnsure that JDK 21.0.1 is installed and properly configured in your system PATH. You can verify your installation by running:java -version
javac -version
Compilation & Execution InstructionsOpen a terminal in the root directory (CSA09-192372007-Assignment3) and follow the steps below for each task.Task 01: Handling Failure in a Bank ATM SimulationNavigate to the folder and create the sample card configuration file (if not already present):cd Task01
echo "CARD_READER_OK" > card_config.txt
Compile and run:javac ATMSimulation.java
java ATMSimulation
Task 02: User-Defined Exceptions and Propagation (StorageDemo.java)Navigate to the folder:cd ../Task02
Compile and run:javac StorageDemo.java
java StorageDemo
Task 03: Resource Safety, Debugging and Robust Code (ReportGenerator.java)Navigate to the folder:cd ../Task03
Compile and run:javac ReportGenerator.java
java ReportGenerator
Task 04: Thread Creation and the Thread Life Cycle (ScriptScanner.java)Navigate to the folder:cd ../Task04
Compile and run:javac ScriptScanner.java
java ScriptScanner
Task 05: Race Conditions in a Cinema Seat-Booking System (SeatBooking.java)Navigate to the folder:cd ../Task05
Compile and run:javac SeatBooking.java
java SeatBooking
Task 06: Deadlock and Inter-Thread Communication (TokenCounter.java)Navigate to the folder:cd ../Task06
Compile and run:javac TokenCounter.java
java TokenCounter
Task 07: Concurrent, Fault-Tolerant Result Processor (ResultProcessor.java)Navigate to the folder:cd ../Task07
Compile and run:javac ResultProcessor.java
java ResultProcessor
Summary of Task Results & ObservationsTaskTopic & Core ConceptsObserved Execution ResultsTask 01Exception Hierarchy, try-catch-finally, Multi-catchTested 5 distinct execution paths (valid input, non-numeric string, negative amount, overdraft, missing file). Verified that specific exceptions are caught in order from narrowest to broadest and that the finally block executes unconditionally across all paths.Task 02User-Defined Exceptions & Exception ChainingCompleted StorageDemo.java by wrapping low-level IOException instances inside SubmissionStorageException. Verified that e.getCause().getMessage() preserves the root cause (Disk quota exceeded) while presenting a clean domain error to the caller.Task 03Resource Safety (try-with-resources vs Manual Close)Demonstrated how manual resource cleanup in loops can leak file descriptors when an IOException occurs mid-stream, whereas try-with-resources guarantees deterministic reverse-order closing of BufferedReader and BufferedWriter handles.Task 04Thread Creation (Thread vs Runnable) & Life CycleImplemented parallel script-scanning stations using both extends Thread and implements Runnable. Used join() to ensure the main thread waits for all worker threads (NEW $\rightarrow$ RUNNABLE $\rightarrow$ TIMED_WAITING $\rightarrow$ TERMINATED) before printing final totals.Task 05Race Conditions & Intrinsic SynchronizationObserved over-booking (selling $>10$ seats) in the unsynchronized check-then-act implementation under 100 concurrent threads. Applying the synchronized modifier eliminated the race condition, consistently yielding Seats sold: 10.Task 06Deadlock Prevention & Producer-Consumer (wait/notifyAll)Completed TokenCounter.java using a bounded circular buffer of size 5 with while loop guards around wait() and notifyAll(). Verified that the cook producer and two student consumers accurately transfer all 20 tokens (Sum of tokens collected: 210).Task 07Fault-Tolerant Concurrent Processing (ExecutorService)Built a parallel batch processor using ExecutorService, Callable<ProcessingResult>, and Future. Injected corrupt and missing files and benchmarked execution times across fixed thread pools of sizes 1, 2, 4, 8, confirming zero batch crashes and accurate success/failure counts.Git Commit Progression NoteIn accordance with the assignment guidelines, each task (Task01 through Task07) was developed, tested, and committed progressively with dedicated commit messages documenting each milestone.
