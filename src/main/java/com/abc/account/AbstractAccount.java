/*
 *
 *
 */
package com.abc.account;

import com.abc.account.transaction.Transaction;
import com.abc.account.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Define new interest ladder as follows
 * interest rate, starting amount, maximum amount for range
 * if maximum value is unknown use Long.MAX_VALUE. This would be for the last range in the ladder.
 * 
 * @see SavingsAccount for an example
 * 
 * @author hiecaxb
 */
public abstract class AbstractAccount implements Account {

    private final static AtomicInteger idGenerator = new AtomicInteger();
    
    private int id;
    private AccountType accountType = null;
    private List<Transaction> transactions = null;
    private List<InterestRange> interestRangeLadder = null;

    protected AbstractAccount(AccountType accountType, List<InterestRange> interestRangeLadder) {

        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        this.interestRangeLadder = interestRangeLadder;
        
        this.id = idGenerator.getAndIncrement();

    }

    public void deposit(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A deposit amount can not be zero or negative.");
        }

        Transaction transaction = new Transaction(amount);
        transactions.add(transaction);

    }

    public void withdraw(BigDecimal amount) throws InsufficientFundsException {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A withdrawal amount can not be zero or negative.");
        }

        BigDecimal negatedAmount = null;
        BigDecimal balanceAfterWithdrawal = null;

        negatedAmount = amount.negate();
        balanceAfterWithdrawal = balance().add(negatedAmount);

        if (balanceAfterWithdrawal.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException();
        }

        Transaction transaction = new Transaction(negatedAmount);
        transactions.add(transaction);
    }
 
    public BigDecimal interestEarned() {
        return interestEarned(interestRangeLadder);
    }
    
    protected BigDecimal interestEarned(List<InterestRange> interestRangeLadder) {
        
        BigDecimal interestRate = null;
        BigDecimal interestRatePct = null;
        BigDecimal minimumAmount = null;
        BigDecimal maximumAmount = null;
        
        BigDecimal _interestCalcHolder = null;
        BigDecimal _amountCalcHolder = null;
        BigDecimal _amountApplicable = null;
        
        BigDecimal hundred = new BigDecimal("100.00");
        BigDecimal totalInterest = BigDecimal.ZERO.setScale(2);
        BigDecimal accountBalance = balance();
        
        if (accountBalance.compareTo(BigDecimal.ZERO) == 0) {
            return totalInterest;
        }
        
        _amountCalcHolder = accountBalance;
        
        for (InterestRange interestRange : interestRangeLadder) {
            
            interestRate = interestRange.getInterest();
            minimumAmount = interestRange.getMinimumAmount();
            maximumAmount = interestRange.getMaximumAmount();
            
            interestRatePct = interestRate.divide(hundred);
            
            if (accountBalance.compareTo(minimumAmount) > 0 && accountBalance.compareTo(maximumAmount) <= 0) {
                
                _interestCalcHolder = _amountCalcHolder.multiply(interestRatePct);
                _interestCalcHolder = _interestCalcHolder.setScale(2, RoundingMode.HALF_EVEN);

                totalInterest = totalInterest.add(_interestCalcHolder);
                
                break;
                
            } else if (accountBalance.compareTo(minimumAmount) > 0 && accountBalance.compareTo(maximumAmount) > 0) {
               
                _amountApplicable = maximumAmount.subtract(minimumAmount);
                
                _interestCalcHolder = _amountApplicable.multiply(interestRatePct);
                _interestCalcHolder = _interestCalcHolder.setScale(2, RoundingMode.HALF_EVEN);

                totalInterest = totalInterest.add(_interestCalcHolder);
                _amountCalcHolder = _amountCalcHolder.subtract(_amountApplicable);
                
            } else {
                
                throw new IllegalStateException("Something is wrong with the Interest Rate Ladder setup or calc.");
                
            }

        }

        return totalInterest;
    }

    public BigDecimal balance() {

        BigDecimal balance = BigDecimal.ZERO.setScale(2);

        for (Transaction transaction : transactions) {
            balance = balance.add(transaction.getAmount());
        }

        return balance;
    }

    public String statement() {

        NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
        BigDecimal balance = BigDecimal.ZERO.setScale(2);
        StringBuilder statement = new StringBuilder();

        statement.append(accountType.getDescription());
        statement.append("\n");

        for (Transaction transaction : transactions) {
            statement.append(transaction.statement());
            //calculating to not double iterate and consistency.
            balance = balance.add(transaction.getAmount());
        }

        statement.append("Total ");
        statement.append(usdFormat.format(balance)); 
        statement.append("\n");

        return statement.toString();
    }

    public AccountType getAccountType() {
        return accountType;
    }
    
    protected List<Transaction> getTransactions() {
        return transactions;
    }

    public int getId() {
        return id;
    }
    
}
