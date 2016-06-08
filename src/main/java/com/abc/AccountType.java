package com.abc;

public enum AccountType {
    CHECKING_ACCOUNT("0", 5000 , 0.1),
    SAVINGS_ACCOUNT("1", 5000 , 0.1),
    MAXI_SAVINGS("2",5000 , 0.1);
    private final String text;
    private final double interestRate;
    private final double withdrawalLimit;


    private AccountType(String text, double withLimit, double ir) {
        this.text = text;
        this.interestRate = ir;
        this.withdrawalLimit = withLimit;
       
    }


    @Override
    public String toString() {
        return text;
    }

    public double getInterestRate() {
        return interestRate;
    }
    

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }


    
    

}