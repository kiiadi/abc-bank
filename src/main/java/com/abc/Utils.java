/**
 * Utils.java
 * 
 * Static utilities class
 * Used to refactor the toDollars method
 * 
 * @author Martin Aydin
 *
 */

import java.lang.Math.abs;

public final class Utils {
	 
	// toDollars method, originally in Account.java
	public static final String toDollars(double d) {
		return String.format("$%,.2f", abs(d)); 
	}

	// format method, originally in Bank.java
    public static final String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

}
