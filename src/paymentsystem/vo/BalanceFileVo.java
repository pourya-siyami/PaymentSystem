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
    private String debtorAccountBalance;
    String strLine = "";
    String str_data = "";
    static Path balanceFilePath = Paths.get("./BalanceFile.txt"); //initialize File object

    public BalanceFileVo() {
    }

    public BalanceFileVo(String debtorAccountBalance) {
        this.debtorAccountBalance = debtorAccountBalance;
    }

    @Override
    public String toString() {
        return "BalanceFileVo: {" + "debtorAccountBalance= " + debtorAccountBalance + '}';
    }

    void setFileToVo() {
        try {
            List<BalanceFileVo> list = new ArrayList<>();
            BufferedReader br = Files.newBufferedReader(balanceFilePath, StandardCharsets.UTF_8);

            while (strLine != null) {
                str_data += strLine;
                strLine = br.readLine();
            }
            debtorAccountBalance = str_data.substring(11, 14);
            BalanceFileVo balanceFileVo = new BalanceFileVo(debtorAccountBalance);
            list.add(balanceFileVo);
            System.out.println("Object created from the file");
            list.stream().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BalanceFileVo balanceFileVo = new BalanceFileVo();
        balanceFileVo.setFileToVo();
    }
}