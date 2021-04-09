package paymentsystem.createfiles;


import paymentsystem.calculaterandomints.CalculateRandomInts;
import runner.Runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    Path paymentFilePath = Paths.get("./PaymentFile.txt"); //initialize File object
    Path balanceFilePath = Paths.get("./BalanceFile.txt");
    String line = null;

    String account;
    String accountNum;
    int amount;

    public MainClass(String account, String accountNum, int amount) {
        this.account = account;
        this.accountNum = accountNum;
        this.amount = amount;
    }

    public MainClass(String accountNum, int amount) {
        this.accountNum = accountNum;
        this.amount = amount;
    }

    public MainClass() {
    }

    @Override
    public String toString() {
        return accountNum + "\t" + amount;
    }

    public List<MainClass> readPaymentFile() {
        List<MainClass> list = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(paymentFilePath, StandardCharsets.UTF_8);
            while ((line = br.readLine()) != null) {
                try {
                    String[] parameters = line.split("\t");
                    if (parameters.length == 3) {
                        String account = parameters[0];
                        String accountNum = parameters[1];
                        Integer amount = Integer.parseInt(parameters[2]);
                        MainClass readPayment = new MainClass(account, accountNum, amount);
                        list.add(readPayment);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MainClass> readBalanceFile() {
        List<MainClass> list = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(balanceFilePath, StandardCharsets.UTF_8);
            while ((line = br.readLine()) != null) {
                try {
                    String[] parameters = line.split("\t");
                    if (parameters.length == 2) {
                        String accountNum = parameters[0];
                        Integer amount = Integer.parseInt(parameters[1]);
                        MainClass readBalance = new MainClass(accountNum, amount);
                        list.add(readBalance);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        CalculateRandomInts calculateRandomInts = new CalculateRandomInts();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        BalanceFile bf = new BalanceFile();
        bf.generateMojoodi();

        calculateRandomInts.readFileMojoodiBalance();
        calculateRandomInts.writeToFiles();

        List<MainClass> paymentList = mainClass.readPaymentFile();
        List<MainClass> balanceFile = mainClass.readBalanceFile();

        Runnable runner = new Runner(paymentList, balanceFile);
        executorService.execute(runner);
        executorService.shutdown();
    }
}