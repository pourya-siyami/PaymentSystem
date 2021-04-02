package paymentsystem.createfiles;

import runner.Runnable;

public class MainClass {

    public static void main(String[] args) {
        Thread th1 = new Thread(new Runnable());
        BalanceFile balanceFile = new BalanceFile();
        balanceFile.generateMojoodi();
        th1.start();
    }
}