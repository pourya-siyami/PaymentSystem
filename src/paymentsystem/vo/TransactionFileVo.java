package paymentsystem.vo;

import java.math.BigDecimal;

public class TransactionFileVo {

    private String debtorAccountNum;
    private String creditorAccountNum;
    private BigDecimal amount;

    public String getDebtorAccountNum() {
        return debtorAccountNum;
    }

    public void setDebtorAccountNum(String debtorAccountNum) {
        this.debtorAccountNum = debtorAccountNum;
    }

    public String getCreditorAccountNum() {
        return creditorAccountNum;
    }

    public void setCreditorAccountNum(String creditorAccountNum) {
        this.creditorAccountNum = creditorAccountNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionFileVo : " + debtorAccountNum + " : " + creditorAccountNum + " : " + amount;
    }
}