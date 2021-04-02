package runner;

import paymentsystem.calculaterandomints.CalculateRandomInts;

public class Runnable implements java.lang.Runnable {
    CalculateRandomInts calculateRandomInts = new CalculateRandomInts();

    @Override
    public void run() {
        calculateRandomInts.readFileMojoodiBalance();
        calculateRandomInts.writeToFiles();
    }
}