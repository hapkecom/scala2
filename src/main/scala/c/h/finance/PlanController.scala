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
    root = new DefaultTreeNode("root", null) 
    
    /* TODO
    val einnahmen1000 = new DefaultTreeNode(new Account(1000, "Account E*"), root)
    val einnahmen1010 = new DefaultTreeNode(new Account(1010, "Account EA"), einnahmen1000)
    val einnahmen1011 = new DefaultTreeNode(new Account(1011, "Account EAA"), einnahmen1010)
    val einnahmen1020 = new DefaultTreeNode(new Account(1020, "Account EB"), einnahmen1000)

    val ausgaben2000 = new DefaultTreeNode(new Account(2000, "Account A+"), root)
    val ausgaben2010 = new DefaultTreeNode(new Account(2010, "Account AA"), ausgaben2000)
    val ausgaben2020 = new DefaultTreeNode(new Account(2020, "Account AB"), ausgaben2010)

    val gewinn = new DefaultTreeNode(new Account(0, "Gewinn(+)/Verlust(-)"), root)
    new DefaultTreeNode(new Account(10, "Gewinn(+)/Verlust(-)"), gewinn)
    println("root.childs="+root.getChildren())
    println("gewinn.childs="+gewinn.getChildren())
    */
    
    root
  }
  
  def amount(versionId: Long, accountId: Long, month/*startdate*/: String): String = {
    (new Amount(999).getAmountString())+"("+accountId+","+month+")"
  }

  var months: Array[String] = Array("Jan 2011", "Feb 2011", "Maerz 2011")
  
  def getMonths: Array[String] = months

}
