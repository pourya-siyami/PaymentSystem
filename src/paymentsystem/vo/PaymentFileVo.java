package paymentsystem.vo;

import java.math.BigDecimal;

public class PaymentFileVo {

    private String account;
    private String accountNum;
    private BigDecimal amount;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentFileVo : " + account + " : " + accountNum + " : " + amount;
    }
}