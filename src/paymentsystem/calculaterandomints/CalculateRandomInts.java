package paymentsystem.calculaterandomints;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class CalculateRandomInts {
    public static String newLine = System.getProperty("line.separator");
    private final Logger logger = Logger.getLogger(this.getClass());
    String strLine = "";
    String str_data = "";
    static int mojoodiFileBalance = 0;
    static Random rand = new Random();
    static int paymentFileBalance = rand.nextInt(501) + 10000;
    static int paymentBalanceSaver = paymentFileBalance;
    String deptor = "1.10.100.1\t" + paymentFileBalance + newLine;

    static CalculateRandomInts calculateRandomInts = new CalculateRandomInts();

    static Path balanceFilePath = Paths.get("./BalanceFile.txt"); //initialize File object
    static Path transactionFilePath = Paths.get("./TransactionFile.txt"); //initialize File object
    static Path paymentFilePath = Paths.get("./PaymentFile.txt"); //initialize File object

    String deptorAccountNum = "1.10.100.1";

    public synchronized void readFileMojoodiBalance() {
        BasicConfigurator.configure();
        String message;
        try {
            BufferedReader br = Files.newBufferedReader(balanceFilePath, StandardCharsets.UTF_8);
            while (strLine != null) {
                str_data += strLine;
                strLine = br.readLine();
            }
            String balance = str_data.substring(str_data.length() - 5);
            try {
                mojoodiFileBalance = Integer.parseInt(balance);
            } catch (NumberFormatException nfe) {
                message = ("there was an error with BalanceFile.java");
                logger.debug(message);
            }
            br.close();
        } catch (FileNotFoundException e) {
            message = ("File not found");
            logger.error(message);
        } catch (IOException e) {
            message = ("Unable to read the file");
            logger.error(message);
        }
    }

    public synchronized void writeToFiles() {
        BasicConfigurator.configure();
        String message;
        try {
            BufferedWriter paymentFileWriter = Files.newBufferedWriter(paymentFilePath, StandardCharsets.UTF_8);
            BufferedWriter balanceFileWriter = Files.newBufferedWriter(balanceFilePath, StandardCharsets.UTF_8);
            if (mojoodiFileBalance >= paymentFileBalance) {
                calculateRandomInts.divider(paymentFileBalance, 1000);
            } else {
                message = ("Mojoodi kaafi nist");
                logger.error(message);
                balanceFileWriter.write(deptorAccountNum + "\t" + mojoodiFileBalance + newLine + "Mojoodi kaafi nist" + newLine);
                paymentFileWriter.write("Mojoodi kaafi nist" + "\t" + paymentFileBalance + newLine);
            }
            paymentFileWriter.close();
            balanceFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            message = ("File not found");
            logger.debug(message, e);
        }
    }

    public void divider(int number, int divisions) {
        Random rand = new Random();
        int[] container = new int[divisions];
        int value = 1;

        while (number > 0) {
            container[rand.nextInt(divisions)]++;
            number--;
        }
        try {
            BufferedWriter paymentFileWriter = Files.newBufferedWriter(paymentFilePath, StandardCharsets.UTF_8);
            BufferedWriter balanceFileWriter = Files.newBufferedWriter(balanceFilePath, StandardCharsets.UTF_8);
            BufferedWriter transactionFileWriter = Files.newBufferedWriter(transactionFilePath, StandardCharsets.UTF_8);
            balanceFileWriter.write("1.10.100.1" + "\t" + (mojoodiFileBalance - paymentBalanceSaver) + newLine);
            paymentFileWriter.write("deptor\t" + deptor);
            logger.info("Done");
            for (int i : container) {
                balanceFileWriter.write("1.20.100." + value + "\t" + i + newLine);
                paymentFileWriter.write("creditor" + "\t" + "1.20.100." + value + "\t" + i + newLine);
                transactionFileWriter.write("1.10.100." + value + "\t" + "1.20.100." + value + "\t" + i + newLine);
                value += 1;
            }
            balanceFileWriter.close();
            paymentFileWriter.close();
            transactionFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}