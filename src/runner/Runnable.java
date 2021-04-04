package runner;

import paymentsystem.calculaterandomints.CalculateRandomInts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runnable implements java.lang.Runnable {
    CalculateRandomInts calculateRandomInts = new CalculateRandomInts();
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.execute(() -> {
            calculateRandomInts.readFileMojoodiBalance();
            calculateRandomInts.writeToFiles();
        });
        executorService.shutdown();
    }
}