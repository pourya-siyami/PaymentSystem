package paymentsystem.createfiles;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class BalanceFile {
    public static String newLine = System.getProperty("line.separator");
    private final Logger logger = Logger.getLogger(this.getClass());

    public void generateMojoodi() {
        BasicConfigurator.configure();
        String message;
        Random rand = new Random();
        int n = rand.nextInt(501) + 10100;
        Path balanceFilePath = Paths.get("./BalanceFile.txt");
        boolean result;
        try {
            result = Files.deleteIfExists(balanceFilePath);
            // test if successfully created a new file
            if (!result) {
                message = ("file created " + balanceFilePath.toAbsolutePath().normalize());
                logger.info(message);
            } else {
                // System.out.println("File already exist at location: " + balanceFilePath.toAbsolutePath().normalize());
                message = ("File already exist at location " + balanceFilePath.toAbsolutePath().normalize());
                logger.info(message);
            }
            BufferedWriter writer = Files.newBufferedWriter(balanceFilePath, StandardCharsets.UTF_8);
            String depositNumber = "1.10.100.1";
            writer.write(depositNumber + "\t" + n + newLine);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}