package paymentsystem.vo;

import java.math.BigDecimal;

public class BalanceFileVo {

    private String AccountNum;
    private BigDecimal AccountBalance;

    public String getAccountNum() {
        return AccountNum;
    }

    public void setAccountNum(String accountNum) {
        AccountNum = accountNum;
    }

    public BigDecimal getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        AccountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "BalanceFileVo: {" + "AccountNumber= " + AccountNum + " : " + "AccountBalance= "
                + AccountBalance + '}';
    }
}