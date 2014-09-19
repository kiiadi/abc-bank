package com.abc.account;

import java.math.BigDecimal;

/**
 * Created by Archana on 9/16/14.
 */
public class InSufficientFundsException extends Exception{
  public InSufficientFundsException(BigDecimal amount, BigDecimal requestAmount){
    super("Insufficient Funds.\n Total Amount in the Account="+amount.toString()+"\n"
            +"Withdrawl Amount Requested="+requestAmount.toString());
  }
}
