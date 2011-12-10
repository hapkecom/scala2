/*
 * Scala Enterprise test project.
 * Copyright (c) 2011 Christian Hapke (hapke.com). All rights reserved.
*/

package c.h.finance
import org.junit.Test
import org.junit.Test
import org.junit.Assert.assertNotNull
import org.junit.Assert.fail
import org.junit.Assert.assertEquals


class PlanLocalTest {
  @Test
  def testPlan( ) {
    //
    // initialize plan
    //
    val p = new Plan(0)
    
    // initialize account of the plan
    p.setAccount(100, "E100", 1, 0)
    p.setAccount(110, "E110", 1, 100)
    p.setAccount(111, "E111", 1, 110)
    p.setAccount(112, "E112", 1, 110)
    p.setAccount(113, "E113", 1, 110)
    p.setAccount(114, "E114", 1, 110)
    p.setAccount(120, "E120", 1, 100)
    p.setAccount(200, "A200", -1, 0)
    p.setAccount(210, "A210", -1, 200)
    p.setAccount(220, "A220", -1, 200)
    p.setAccount(221, "A221", -1, 220)
    p.setParentsOfAccount()
    println(p.rootAccount)
    
    // set data (month 1)
    var planDate = new PlanDate("Jan 2011")
    p.setValue(111, planDate, new Amount(1110))
    p.setValue(112, planDate, new Amount(1120))
    p.setValue(114, planDate, new Amount(1140))
    p.setValue(120, planDate, new Amount(1200))
    p.setValue(210, planDate, new Amount(2100))
    p.setValue(221, planDate, new Amount(2210))
    println("Values before calculation: "+p.planValues)
    p.updateCalculatedValues()
    println("Values after calculation: "+p.planValues)
    
    // check calculated values
    assertEquals("wrong E100", 4570, p.getValue(100, planDate).amountInCents)
    assertEquals("wrong A200", 4310, p.getValue(200, planDate).amountInCents)
    assertEquals("wrong Account 0", 260, p.getValue(0, planDate).amountInCents)
    
    // modify single value and recalculate
    p.setValue(210, planDate, new Amount(2101))
    p.updateCalculatedValues(planDate)

    // check calculated values
    assertEquals("wrong E100", 4570, p.getValue(100, planDate).amountInCents)
    assertEquals("wrong A200", 4311, p.getValue(200, planDate).amountInCents)
    assertEquals("wrong Account 0", 259, p.getValue(0, planDate).amountInCents)
  }
}
