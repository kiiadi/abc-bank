package com.abc;

/**
 * User: vachaspathy
 * Date: 6/16/14
 * Code to Generate a Unique Id to Customers in a bank.
 */
public class CustomerId {
    private static CustomerId customerId = null;
    private long id =0;

    public static CustomerId getInstance(){
       if(customerId == null){
           customerId = new CustomerId();
       }
       return customerId;
    }

    /**
     * Provides Next available Id
     * @return
     */
     public long getNextId(){
         return id ++;
     }
}
