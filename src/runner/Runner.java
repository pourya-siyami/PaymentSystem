package runner;

import paymentsystem.calculaterandomints.CalculateRandomInts;
import paymentsystem.createfiles.MainClass;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Runner implements java.lang.Runnable {
    List<MainClass> paymentList;
    List<MainClass> balanceList;

    Path balanceFilePath = Paths.get("./BalanceFile.txt");
    static Path transactionFilePath = Paths.get("./TransactionFile.txt"); //initialize File object
    public static String newLine = System.getProperty("line.separator");

    BufferedWriter balanceFileWriter;
    BufferedWriter transactionFileWriter;

    {
        try {
            balanceFileWriter = Files.newBufferedWriter(balanceFilePath, StandardCharsets.UTF_8);
            transactionFileWriter = Files.newBufferedWriter(transactionFilePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Runner(List<MainClass> paymentList, List<MainClass> balanceList) {
        this.paymentList = paymentList;
        this.balanceList = balanceList;
    }

    ReentrantReadWriteLock lock;

    @Override
    public void run() {
        try {
            int value = 1;
            balanceFileWriter.write("1.10.100.1" + "\t" + (CalculateRandomInts.mojoodiFileBalance -
                    CalculateRandomInts.paymentBalanceSaver) + newLine);
            if (paymentList.size() > 1) {
                for (int i = 1; i < paymentList.size(); i++) {
                    Object rowOfPaymentFile = paymentList.get(i);
                    balanceFileWriter.write(rowOfPaymentFile + newLine);
                    transactionFileWriter.write("1.10.100." + value + "\t" + rowOfPaymentFile + newLine);
                    value += 1;
                }
                for (int i = 1; i < balanceList.size(); i++) {
                    Object rowOfBalanceFile = balanceList.get(i);
                    balanceFileWriter.write(rowOfBalanceFile + newLine);
                }
            } else {
                System.out.println("Mojoodi kafi nist");
            }
            balanceFileWriter.close();
            transactionFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}