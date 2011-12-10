/*
 * Scala Enterprise test project.
 * Copyright (c) 2011 Christian Hapke (hapke.com). All rights reserved.
*/
package c.h.finance
import scala.collection.immutable.HashMap
import c.h.finance.model.Account
import javax.ejb.EJB
import c.h.finance.model.AccountService
import javax.inject.Named
import javax.enterprise.context.SessionScoped


class Plan(versionId: Long, accountServiceInit: AccountService) extends Serializable {
  var months: Array[String] = Array("Jan 2011", "Feb 2011", "Maerz 2011")
  
  var amountInCents: Long = (10000L * Math.random).toLong
  
  
  def getMonths: Array[String] = months
  

  private var accountService = accountServiceInit
  
  /** store all single values depending on planDate and accountId */
  var planValues = Map[PlanDate,PlanOfOneDate]()
  
  
  /** store a new single value, do not update calculated values */
  def setValue(accountId: Long, planDate: PlanDate, value: Amount) {
    // store the value at the correct place
    var planOfOneDate = if (planValues.contains(planDate)) planValues(planDate) else null
    if (planOfOneDate==null) {
      planOfOneDate = new PlanOfOneDate()
      planValues += planDate -> planOfOneDate
    }
    planOfOneDate.addValue(accountId, value)
  }
 
  /** get a single value */
  def getValue(accountId: Long, planDate: PlanDate): Amount = {
    val planOfOneDate = planValues(planDate)
    if (planOfOneDate!=null) {
      planOfOneDate.getValue(accountId)
    } else {
      null
    }
  }
  
  /** provide an ordered array of all planDates */
  def allPlanDates(): Array[PlanDate] = {
    planValues.keySet.toList.sort((p1,p2) => p1.startdate<p2.startdate).toArray
  }
  
  /** update all calculated values of the plan */
  def updateCalculatedValues() {
    allPlanDates foreach(updateCalculatedValues)
  }

  /** update for a single planDate */
  def updateCalculatedValues(planDate: PlanDate) {
    updateCalculatedValues(accountService.rootAccount, planDate, true);
  }

  /** update for planDate and account and its sub/child-accounts : returns updated amount */
  def updateCalculatedValues(account: Account, planDate: PlanDate, withSign: Boolean): Amount = {
     val res: Long =
       if (account.children.isEmpty) {
    	   // don't need to re-calculate because no children: just 
    	   getValue(account.accountId, planDate).amountInCents
       } else {
	       // need to calculate sum of child values
	       val childsWithSign = (account==accountService.rootAccount)
	       val amounts: Set[Amount] = account.children.map(a => updateCalculatedValues(a, planDate, childsWithSign))
	       val value = amounts.foldLeft(0L)((sum,a) => sum+a.amountInCents)
	       setValue(account.accountId, planDate, new Amount(value))
	       value
       }
     val sign = if (withSign) account.sign else 1
     new Amount(sign*res)
  }
}
