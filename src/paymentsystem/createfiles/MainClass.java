package paymentsystem.createfiles;


import runner.Runner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        BalanceFile balanceFile = new BalanceFile();
        balanceFile.generateMojoodi();
        Runnable runner = new Runner();
        executorService.execute(runner);
        executorService.shutdown();
    }
}