package paymentsystem.vo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileVo {
    private String debtorAccountNum;
    private String creditorAccountNum;
    private int amount;
    static Path transactionFilePath = Paths.get("./TransactionFile.txt"); //initialize File object
    String line = null;

    public TransactionFileVo() {
    }

    public TransactionFileVo(String debtorAccountNum, String creditorAccountNum, int amount) {
        this.debtorAccountNum = debtorAccountNum;
        this.creditorAccountNum = creditorAccountNum;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionFileVo : " + debtorAccountNum + " : " + creditorAccountNum + " : " + amount;
    }

    void setFileToVo() {
        try {
            List<TransactionFileVo> list = new ArrayList<>();
            BufferedReader br = Files.newBufferedReader(transactionFilePath, StandardCharsets.UTF_8);

            while ((line = br.readLine()) != null) {
                try {
                    String[] parameters = line.split("\t");
                    if (parameters.length == 3) {
                        String debtorAccountNum = parameters[0];
                        String creditorAccountNum = parameters[1];
                        Integer amount = Integer.parseInt(parameters[2]);
                        TransactionFileVo transactionFileVo = new TransactionFileVo(debtorAccountNum, creditorAccountNum, amount);
                        list.add(transactionFileVo);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Object created from the file");
            list.stream().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TransactionFileVo transactionFileVo = new TransactionFileVo();
        transactionFileVo.setFileToVo();
    }
}
