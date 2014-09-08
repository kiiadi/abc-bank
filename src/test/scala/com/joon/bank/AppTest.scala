package com.joon.bank

import org.junit._
import Assert._
import com.joon.bank.App._
import java.util.Calendar

@Test
class AppTest {
  val DOUBLE_DELTA = 1e-15;

    @Test
    def checkingMaxi() {
      var date = Calendar.getInstance();
      date.add(Calendar.YEAR, -1)
      val transactions = List(
          new Transaction(100,date.getTime()),
          new Transaction(1000,date.getTime()),
          new Transaction(5000,date.getTime()))
     val maxi= new MaxiSavings(transactions) 

     val total = maxi.total
     assertEquals(maxi.interestEarned,305.0,DOUBLE_DELTA)
    } 

    //test daily compound interest
    @Test
    def testInterest():Unit = {
      var cal = Calendar.getInstance()
      cal.add(Calendar.YEAR, -1)
      var list = List[Transaction]()

      for (a<- 1 to 10) {
        cal.add(Calendar.MONTH, 1);
        list = new Transaction(100,cal.getTime())::list
      }
      val retval = dailyCompoundinterest(list ,p=>if (p<500) 0.043 else 0.01)
      assertTrue(16> retval && retval > 15)
      
    }

}


