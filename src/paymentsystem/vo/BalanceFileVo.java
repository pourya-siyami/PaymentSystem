package paymentsystem.vo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BalanceFileVo {

    private String AccountNum;
    private int AccountBalance;

    public String getAccountNum() {
        return AccountNum;
    }

    public void setAccountNum(String accountNum) {
        AccountNum = accountNum;
    }

    public int getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        AccountBalance = accountBalance;
    }

    static Path balanceFilePath = Paths.get("./BalanceFile.txt"); //initialize File object
    String line = null;

    public BalanceFileVo(String AccountNum, int AccountBalance) {
        this.AccountNum = AccountNum;
        this.AccountBalance = AccountBalance;
    }

    @Override
    public String toString() {
        return "BalanceFileVo: {" + "AccountNumber= " + AccountNum + " : " + "AccountBalance= "
                + AccountBalance + '}';
    }

    void setFileToVo() {
        try {
            List<BalanceFileVo> list = new ArrayList<>();
            BufferedReader br = Files.newBufferedReader(balanceFilePath, StandardCharsets.UTF_8);

            while ((line = br.readLine()) != null) {
                try {
                    String[] parameters = line.split("\t");
                    if (parameters.length == 2) {
                        String debtorAccountNum = parameters[0];
                        Integer debtorAccountBalance = Integer.parseInt(parameters[1]);
                        BalanceFileVo balanceFileVo = new BalanceFileVo(debtorAccountNum, debtorAccountBalance);
                        list.add(balanceFileVo);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            list.stream().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}