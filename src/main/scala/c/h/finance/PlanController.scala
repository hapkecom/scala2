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

@Named
//@ConversationScoped
@SessionScoped
class PlanController extends Serializable {
  
  private var root: TreeNode = createRoot  
 
  @EJB
  private var dbService: DBService = _

  //@Inject
  //private var conversation: Conversation = _
  
  
  def getRoot(): TreeNode = {
    root
  }
  
  def createRoot: TreeNode = {
    // create account tree
    val p = new Plan(0)
    
    // initialize account of the plan
    p.setAccount(1000, "Account E*", 1, 0)
    p.setAccount(1010, "Account EA", 1, 1000)
    p.setAccount(1011, "Account EAA",1, 1010)
    p.setAccount(1020, "Account EB", 1, 1000)
    p.setAccount(2000, "Account A+", -1, 0)
    p.setAccount(2010, "Account AA", -1, 2000)
    p.setAccount(2020, "Account AB", -1, 2000)
    p.setParentsOfAccount()
    println(p.rootAccount)
    
    // convert to TreeNodes
    val root = createTreeNodeFromPlan(p)
    println("root.childs="+root.getChildren())
    
    root
  }
  
  def createTreeNodeFromPlan(plan: Plan): TreeNode = {
    val rootTreeNode = new DefaultTreeNode("root", null)
    plan.rootAccount.children.foreach(a=>createTreeNodeFromAccount(a,rootTreeNode))
    // sepcial handling of saldo
    new DefaultTreeNode(plan.rootAccount, rootTreeNode)
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
