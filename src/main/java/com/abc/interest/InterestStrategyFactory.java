package com.abc.interest;


import com.abc.AccountType;

import java.util.EnumMap;
import java.util.Map;

public class InterestStrategyFactory {
    private static final InterestStrategyFactory instance = new InterestStrategyFactory();
    private final Map<AccountType, InterestStrategy> strategies;

    public InterestStrategyFactory() {
        this.strategies = new EnumMap<>(AccountType.class);
        strategies.put(AccountType.Checking, new CheckingInterestStrategy());
        strategies.put(AccountType.Savings, new SavingsInterestStrategy());
        strategies.put(AccountType.MaxiSavings, new MaxiSavingsInterestStrategy());
    }

    public static InterestStrategyFactory getInstance() {
        return instance;
    }

    public InterestStrategy getStrategy(AccountType accountType) {
        InterestStrategy result = strategies.get(accountType);
        if (result == null) {
            throw new IllegalArgumentException("Unsupported accountType: " + accountType);
        }
        return result;
    }
}
