package com.abc;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
 * Run in Maven as Following on Command Line:
 *
 * mvn test -Dit.test=com.abc.BankAllTestRunner
 *
 * Created by Archana on 9/19/14.
 */
public class BankAllTestRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(BankAllTestsSuite.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
    System.out.println("\nTotal Tests            : "+result.getRunCount()+"\n"
        +"Successful Tests Count : "+(result.getRunCount() - result.getFailureCount())+"\n"
        +"Failure Test Count     : "+result.getFailureCount()+"\n");
  }
}
