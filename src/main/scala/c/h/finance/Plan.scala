/*
 * Scala Enterprise test project.
 * Copyright (c) 2011 Christian Hapke (hapke.com). All rights reserved.
*/
package c.h.finance
import scala.collection.immutable.HashMap


class Plan(versionId: Long) extends Serializable {
  var months: Array[String] = Array("Jan 2011", "Feb 2011", "Maerz 2011")
  
  var amountInCents: Long = (10000L * Math.random).toLong
  
  
  def getMonths: Array[String] = months
  
 
  
  /** store all single values depending on planDate and accountId */
  var planValues = Map[PlanDate,PlanOfOneDate]()
  
  var rootAccount: Account = new Account(0, "Saldo Account(0)", 1)

  /** store all different accountIds */
  private var allAccountIds = Set[Long]()
  /** store all different accountIds */
  private var accountOfAccountIds = Map[Long,Account]()
  
  /** key=parent accountId, >0, value=account */
  private var parentAccountIdsOfAccounts = Map[Account,Long]()
  
  /** create the Account structure */
  def setAccount(accountId: Long, name: String, typeOrSign: Long, parentAccountId: Long): Account = {
    // create account
    val sign = if (parentAccountId<=0) typeOrSign else 1
    val account = new Account(accountId, name, sign)
    accountOfAccountIds += accountId -> account
    
    // handle parent
    if (parentAccountId>0) {
      // save parent for later linking
      parentAccountIdsOfAccounts += account -> parentAccountId
    } else {
      // there is no parent: add it to the rootAccount
      account.setParent(rootAccount)
    }
    
    account
  }
  /**
   * Add the links to the parents - call this after setAccount() was called for all accounts;
   * If called too early then needed parent account are missed.
   */
  def setParentsOfAccount() {
    parentAccountIdsOfAccounts.foreach{ case (account, parentId) => account.setParent(accountOfAccountIds(parentId)) }
  }
  
  
  
  /** store a new single value, do not update calculated values */
  def setValue(accountId: Long, planDate: PlanDate, value: Amount) {
    // keep track of all used accountIds
    allAccountIds += accountId
    
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
    updateCalculatedValues(rootAccount, planDate, true);
  }

  /** update for planDate and account and its sub/child-accounts : returns updated amount */
  def updateCalculatedValues(account: Account, planDate: PlanDate, withSign: Boolean): Amount = {
     val res: Long =
       if (account.children.isEmpty) {
    	   // don't need to re-calculate because no children: just 
    	   getValue(account.accountId, planDate).amountInCents
       } else {
	       // need to calculate sum of child values
	       val childsWithSign = (account==rootAccount)
	       val amounts: Set[Amount] = account.children.map(a => updateCalculatedValues(a, planDate, childsWithSign))
	       val value = amounts.foldLeft(0L)((sum,a) => sum+a.amountInCents)
	       setValue(account.accountId, planDate, new Amount(value))
	       value
       }
     val sign = if (withSign) account.sign else 1
     new Amount(sign*res)
  }
}
