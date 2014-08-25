package com.abc.infra;

import com.abc.api.AccountType;
import com.abc.api.Bank;
import com.abc.impl.BankImpl;
import com.abc.impl.calculators.FlatInterestCalculator;
import com.abc.impl.calculators.StepFlatInterestCalculator;
import com.abc.impl.calculators.TwoStepFlatInterestCalculator;
import com.abc.services.AccountIdGeneratorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class Container {

    private static final Logger logger = LoggerFactory.getLogger(Container.class);
    private static Bank bank;

    public static void main(String[] args){
        setup();
    }

    public static void setup() {
        BankImpl bankImpl = new BankImpl();
        bankImpl.setAccountIdGenerator(new AccountIdGeneratorImpl());
        bankImpl.addAccountType(AccountType.checking, new FlatInterestCalculator(new BigDecimal("0.1")));
        bankImpl.addAccountType(AccountType.savings, new StepFlatInterestCalculator(new BigDecimal("0.1"), new BigDecimal("1000"), new BigDecimal("0.2")));
        bankImpl.addAccountType(AccountType.maxi, new TwoStepFlatInterestCalculator(new BigDecimal("2"), new BigDecimal("1000"), new BigDecimal("5"), new BigDecimal("1000"), new BigDecimal("10")));
        bank = bankImpl;
    }

    public static Bank getBank(){
        return bank;
    }

}
