/*
 * Scala Enterprise test project.
 * Copyright (c) 2011 Christian Hapke (hapke.com). All rights reserved.
*/
package c.h.finance

import scala.reflect.BeanProperty
import javax.ejb.EJB
import scala.collection.mutable.Buffer
import javax.annotation.PostConstruct
import javax.faces.model.ListDataModel
import javax.faces.model.DataModel
import javax.inject.Named
import javax.enterprise.context.SessionScoped
import javax.enterprise.context.ConversationScoped
import javax.enterprise.context.Conversation
import javax.inject.Inject
import javax.enterprise.context.SessionScoped
import c.h.DBService
import org.primefaces.model.TreeNode
import org.primefaces.model.DefaultTreeNode
import c.h.finance.model.AccountService
import c.h.finance.model.Account

@Named
//@ConversationScoped
@SessionScoped
class PlanController extends Serializable {
  
  private var root: TreeNode = _ 
 
  @EJB
  var accountService: AccountService = _

  @EJB
  private var dbService: DBService = _

  //@Inject
  //private var conversation: Conversation = _
  
  
  def getRoot(): TreeNode = {
    root
  }
  
  @PostConstruct
  def createRoot {
    // initialize account tree
    val p = new Plan(0, accountService)
    println(accountService.rootAccount)
    
    // convert to TreeNodes
    root = createTreeNodeFromPlan(p)
    println("root.childs="+root.getChildren())
  }
  
  def createTreeNodeFromPlan(plan: Plan): TreeNode = {
    val rootTreeNode = new DefaultTreeNode("root", null)
    accountService.rootAccount.children.foreach(a=>createTreeNodeFromAccount(a,rootTreeNode))
    // special handling of saldo
    new DefaultTreeNode(accountService.rootAccount, rootTreeNode)
    rootTreeNode
  }
  def createTreeNodeFromAccount(account: Account, parentTreeNode: TreeNode): TreeNode = {
    val treeNode = new DefaultTreeNode(account, parentTreeNode)
    account.children.foreach(a=>createTreeNodeFromAccount(a,treeNode))
    treeNode
  }
  
  def amount(versionId: Long, accountId: Long, month/*startdate*/: String): String = {
    (new Amount(999).getAmountString())+"("+accountId+","+month+")"
  }

  var months: Array[String] = Array("Jan 2011", "Feb 2011", "Maerz 2011")
  
  def getMonths: Array[String] = months

}
