package com.abc;

import com.abc.utilities.DateUtility;

import java.util.*;

/**
 * A high level account object with methods to calculate balance and interest.
 */
public abstract class Account {

    /**
     * Provides a hook for specific implementations to calculate daily accrual according to business rules.
     *
     * @param balance         - the amount to base accrual calculations on
     * @param calculationDate - the date from which calculations should be made
     * @return - the calculated daily interest accrual.
     */
    protected abstract double calculateDailyAccrual(double balance, Date calculationDate);

    /**
     * Exhaustive enumeration of eligible account types.
     */
    public static enum AccountType {
        CHECKING(0, "Checking"),
        SAVINGS(1, "Savings"),
        MAXI_SAVINGS(2, "Maxi-Savings"),
        UNKNOWN(-1, "Unknown");

        private int value;
        private String desc;

        AccountType(final int value, final String desc) {
            this.value = value;
            this.desc = desc;
        }

        int getValue() {
            return value;
        }

        String getDesc() {
            return desc;
        }

        public static AccountType fromValue(final int value) {
            for (final AccountType accountType : AccountType.values()) {
                if (accountType.getValue() == value) {
                    return accountType;
                }
            }
            return AccountType.UNKNOWN;
        }

    }

    private final AccountType accountType;
    private List<Transaction> transactions;
    private Date openDate;

    public Account() {
        accountType = AccountType.UNKNOWN;
    }

    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public Account(AccountType accountType, double initialDeposit) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();

        deposit(initialDeposit);
    }

    public Account(AccountType accountType, double initialDeposit, Date openDate) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        this.openDate = openDate;

        deposit(initialDeposit);
    }

    public Account(double initialDeposit, Date openDate) {
        this();
        this.transactions = new ArrayList<Transaction>();
        this.openDate = openDate;

        deposit(initialDeposit, openDate);
    }

    public Account(double initialDeposit) {
        this();
        this.transactions = new ArrayList<Transaction>();
        this.openDate = new Date();

        deposit(initialDeposit);
    }

    /**
     * A transaction representing a debit to the asset balance of this account.
     *
     * @param amount - the monetary value of the debit.
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    /**
     * A transaction representing a debit to the asset balance of this account.
     *
     * @param amount          - the monetary value of the debit.
     * @param transactionDate - the posting date of the debit.
     */
    public void deposit(double amount, Date transactionDate) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount, transactionDate));
        }
    }

    /**
     * A transaction representing a credit to the asset balance of this account.
     *
     * @param amount - the monetary value of the credit.
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else if (amount > this.availableBalance()) {
            throw new InsufficientFundsException("amount exceeds available balance");
        } else {
            transactions.add(new Transaction(-amount));
        }
    }

    /**
     * A transaction representing a credit to the asset balance of this account.
     *
     * @param amount - the monetary value of the credit.
     * @param transactionDate - the posting date of the credit.
     */
    public void withdraw(double amount, Date transactionDate) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else if (amount > this.availableBalance()) {
            throw new InsufficientFundsException("amount exceeds available balance");
        } else {
            transactions.add(new Transaction(-amount, transactionDate));
        }
    }

    /**
     * A transaction representing a credit from the specified account debited into this account.
     *
     * @param fromAccount - the account from which the credit is executed.
     * @param amount - the amount of the transaction.
     */
    public void transferFrom(Account fromAccount, double amount) {
        fromAccount.withdraw(amount);
        deposit(amount);
    }

    /**
     * The sum of all posted transactions.
     * @return
     */
    public double sumTransactions() {
        double amount = 0.0;
        if (transactions != null) {
            for (Transaction t : transactions)
                amount += t.amount;
        }
        return amount;
    }

    /**
     * The most recently posted transaction.
     *
     * @return - the {@link Transaction} with the most recent transactionDate.
     */
    public Transaction latestTransaction() {
        Transaction latest = null;

        for (Transaction transaction : transactions) {
            if (latest == null || transaction.getTransactionDate().compareTo(latest.getTransactionDate()) > 0) {
                latest = transaction;
            }
        }

        return latest;
    }

    /**
     * The most recent credit transaction.
     *
     * @return - the credit {@link Transaction} with the most recent transactionDate.
     */
    public Transaction latestWithdrawal() {
        Transaction latestWithdrawal = null;

        for (Transaction transaction : transactions) {
            if ((transaction.amount < 0 && latestWithdrawal == null) ||
                    (transaction.amount < 0 && transaction.getTransactionDate().compareTo(
                            latestWithdrawal.getTransactionDate()) > 0)) {
                latestWithdrawal = transaction;
            }
        }

        return latestWithdrawal;
    }

    /**
     * Sum of all transaction between the open date of this account and the specified end date.
     * @param specifiedDate - the latest date before which transactions should be considered for summary.
     *
     * @return - the sum total of all transactions before the specified date.
     */
    public double transactionSumForDate(Date specifiedDate) {
        if (specifiedDate.compareTo(openDate) < 0) {
            throw new IllegalArgumentException("specified date precedes account open date");
        }
        double balance = 0.0;
        for (Transaction transaction : transactions) {
            //System.out.println("comparing "+transaction.getTransactionDate()+" --> "+specifiedDate);
            if (transaction.getTransactionDate().before(specifiedDate) ||
                    transaction.getTransactionDate().equals(specifiedDate)) {
                //System.out.println("adding transaction amount : "+transaction.amount);
                balance += transaction.amount;
            }
        }

        return balance;
    }

    /**
     * Calculates the balance consisting of the sum of all transactions plus any accrued daily interest.
     *
     * @return - the sum of all transactions plus any accrued daily interest.
     */
    public double availableBalance() {
        //create a calendar initialized to the open date of the account
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(openDate());
        Date currentDate = DateUtility.now();

        //ensure all transactions are sorted chronologically from the open date of the account
        Collections.sort(transactions, new Comparator<Transaction>() {
            public int compare(Transaction a, Transaction b) {
                return a.getTransactionDate().compareTo(b.getTransactionDate());
            }
        });

        Iterator<Transaction> iterator = transactions.iterator();
        Transaction next = iterator.next();
        double balance = 0.0;
        //starting with the open date and ending today, add each days calculated sum and interest to our total
        while (calendar.getTime().before(currentDate)) {
            while (DateUtility.sameDay(next.getTransactionDate(),calendar.getTime())) {//if we're on a day with transactions...
                balance += next.amount;//adjust our balance

                if (iterator.hasNext()) {
                    next = iterator.next();//if there are more transactions, move to the next
                } else {
                    break;//otherwise, we're done
                }
            }
            //add our daily accrual, provided by subclasses, to our current balance
            balance += calculateDailyAccrual(balance, calendar.getTime());

            //move to the next day and repeat
            calendar.roll(Calendar.DAY_OF_YEAR, 1);
        }

        //all done
        return balance;

    }

    /**
     * Amount of interest earned on our account since opening.
     * @return
     */
    public double interestEarned() {
        return availableBalance() - sumTransactions();
    }


    public AccountType accountType() {
        return accountType;
    }

    public List<Transaction> transactions() {
        return transactions;
    }

    public Date openDate() {
        return openDate;
    }

}
