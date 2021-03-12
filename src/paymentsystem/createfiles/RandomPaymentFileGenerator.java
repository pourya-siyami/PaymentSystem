package paymentsystem.createfiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.*;
import paymentsystem.calculaterandomints.CalculateRandomInts;

public class RandomPaymentFileGenerator {
    private final Logger logger = Logger.getLogger(this.getClass());
    Path transactionFilePath = Paths.get("./TransactionFile.txt"); //initialize File object
    Path paymentFilePath = Paths.get("./PaymentFile.txt"); //initialize File object
    boolean result;

    public void createTransactionFilePath() {
        BasicConfigurator.configure();
        String message;
        try {
            result = Files.deleteIfExists(transactionFilePath);
            // test if successfully created a new file
            if (!result) {
                message = ("file created " + transactionFilePath.toAbsolutePath().normalize());
                logger.info(message);
            } else {
                message = ("File already exist at location " + transactionFilePath.toAbsolutePath().normalize());
                logger.info(message);
            }
        } catch (IOException e) {
            message = ("address not found");
            logger.error(message, e);
        }
    }

    public void createPaymentFile() {
        BasicConfigurator.configure();
        String message;
        try {
            result = Files.deleteIfExists(paymentFilePath);
            // test if successfully created a new file
            if (!result) {
                message = ("file created " + paymentFilePath.toAbsolutePath().normalize());
                logger.info(message);
            } else {
                message = ("File already exist at location " + paymentFilePath.toAbsolutePath().normalize());
                logger.info(message);
            }
        } catch (IOException e) {
            message = ("address not found");
            logger.error(message, e);
        }
    }

    public static void main(String[] args) {
        CalculateRandomInts calculateRandomInts = new CalculateRandomInts();
        BalanceFile balanceFile = new BalanceFile();
        balanceFile.generateMojoodi();
        RandomPaymentFileGenerator rpfg = new RandomPaymentFileGenerator();
        calculateRandomInts.readFileMojoodiBalance();
        rpfg.createTransactionFilePath();
        rpfg.createPaymentFile();
        calculateRandomInts.writeToFiles();
    }
}