package org.example;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
   static List<Loan> loanList=new ArrayList<>();
   static List<Loan> loanList1=new ArrayList<>();
    /**
     * Rigorous Test :-)
     */
//    @Test
//    public void shouldAnswerWithTrue()
//    {
//        assertTrue( true );
//    }
    @BeforeClass
    public static void initialize(){
        loanList.add(new Loan(1234567L,250000,"2/10/2023","closed","Vanitha",9876543210L));
        loanList.add(new Loan(1234568L,300000,"9/3/2023","closed","Sunitha",9253758626L));
        loanList.add(new Loan(1234569L,350000,"12/14/2023","closed","Anitha",8184526489L));
        loanList.add(new Loan(1234570L,400000,"1/27/2024","open","Vanditha",8125384901L));
        loanList.add(new Loan(1234571L,450000,"2/9/2024","open","Kavitha",9102836475L));
        loanList.add(new Loan(1234572L,500000,"2/19/2024","open","Savitha",9173570236L));

    }
    @Test
    public void testAvailable(){
         String expectedLoanStatus="open";
         assertNotEquals("Expect Test to pass",expectedLoanStatus,loanList.get(0).getLoanStatus());
         assertEquals(expectedLoanStatus,loanList.get(1).getLoanStatus());
         assertEquals(expectedLoanStatus,loanList.get(2).getLoanStatus());
         assertEquals(expectedLoanStatus,loanList.get(3).getLoanStatus());
         assertEquals(expectedLoanStatus,loanList.get(4).getLoanStatus());
         assertEquals(expectedLoanStatus,loanList.get(5).getLoanStatus());

    }
  @Test
    public void testClosed() {
      String expectedLoanStatus = "closed";
      assertEquals(expectedLoanStatus, loanList.get(0).getLoanStatus());
      assertEquals(expectedLoanStatus, loanList.get(1).getLoanStatus());
      assertEquals(expectedLoanStatus, loanList.get(2).getLoanStatus());
      assertNotEquals(expectedLoanStatus, loanList.get(3).getLoanStatus());
      assertEquals(expectedLoanStatus, loanList.get(4).getLoanStatus());//test case fails
      assertEquals(expectedLoanStatus, loanList.get(5).getLoanStatus());
    }

    @Test(timeout = 900)
    public void testTime() throws InterruptedException {
        Thread.sleep(100);
      assertTrue(loanList.size()>0);
    }

    @Test
    public void checkNull(){
        assertNull(loanList1);
        assertNotNull(loanList);
    }
   @Test
    public void testSize(){
        assertFalse(loanList.isEmpty());
        assertTrue(loanList1.isEmpty());
    }

    @AfterClass
    public static void clear(){
        loanList.clear();
        loanList1.clear();
    }
}
