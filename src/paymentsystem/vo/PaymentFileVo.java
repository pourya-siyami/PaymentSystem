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
    private String debtorAccountBalance;
    String strLine = "";
    String str_data = "";
    static Path paymentFilePath = Paths.get("./PaymentFile.txt"); //initialize File object

    public PaymentFileVo() {
    }

    public PaymentFileVo(String debtorAccountBalance) {
        this.debtorAccountBalance = debtorAccountBalance;
    }

    @Override
    public String toString() {
        return "PaymentFileVo: {" + "debtorAccountBalance= " + debtorAccountBalance + '}';
    }

    void setFileToVo() {
        try {
            List<PaymentFileVo> list = new ArrayList<>();
            BufferedReader br = Files.newBufferedReader(paymentFilePath, StandardCharsets.UTF_8);

            while (strLine != null) {
                str_data += strLine;
                strLine = br.readLine();
            }
            debtorAccountBalance = str_data.substring(19, 24);
            PaymentFileVo paymentFileVo = new PaymentFileVo(debtorAccountBalance);
            list.add(paymentFileVo);
            System.out.println("Object created from the file");
            list.stream().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PaymentFileVo paymentFileVo = new PaymentFileVo();
        paymentFileVo.setFileToVo();
    }

}
