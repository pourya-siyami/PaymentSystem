package paymentsystem.vo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PaymentFileVo {

    private String account;
    private String accountNum;
    private int amount;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    static Path paymentFilePath = Paths.get("./PaymentFile.txt"); //initialize File object
    String line = null;

    public PaymentFileVo(String account, String accountNum, int amount) {
        this.account = account;
        this.accountNum = accountNum;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentFileVo : " + account + " : " + accountNum + " : " + amount;
    }

    void setFileToVo() {
        try {
            List<PaymentFileVo> list = new ArrayList<>();
            BufferedReader br = Files.newBufferedReader(paymentFilePath, StandardCharsets.UTF_8);

            while ((line = br.readLine()) != null) {
                try {
                    String[] parameters = line.split("\t");
                    if (parameters.length == 3) {
                        String account = parameters[0];
                        String accountNum = parameters[1];
                        Integer amount = Integer.parseInt(parameters[2]);
                        PaymentFileVo paymentFileVo = new PaymentFileVo(account, accountNum, amount);
                        list.add(paymentFileVo);
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
}