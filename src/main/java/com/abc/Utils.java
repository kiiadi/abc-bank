package com.abc;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

    public static String correctPluralOfWord(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public static void ensurePositive(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) != 1) {
            throw new NegativeOrZeroAmountException("amount must be greater than zero");
        }
    }

    public static boolean hasWithdrawalsForNdays(List<Transaction> transactions, int days) {
        if (days == 0) {
            return false;
        }

        Date dateNdaysAgo = getDateNdaysAgo(days);

        for (Transaction t : transactions) {
            if (t.isWithdrawal() && !t.getDate().before(dateNdaysAgo)) {
                return true;
            }
        }
        return false;
    }

    public static Date getDateNdaysAgo(Date from, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    public static Date getDateNdaysAgo(int days) {
        return getDateNdaysAgo(new Date(), days);
    }

    public static String toDollars(BigDecimal d) {
        return String.format("$%,.2f", d.abs());
    }
}
