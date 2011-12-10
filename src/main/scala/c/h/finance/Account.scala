/*
 * Scala Enterprise test project.
 * Copyright (c) 2011 Christian Hapke (hapke.com). All rights reserved.
*/
package c.h.finance
import scala.reflect.BeanProperty


class Account(accountId0: Long, name: String, sign0: Long) extends Serializable {
  @BeanProperty
  var accountId: Long = accountId0
  def sign: Long = sign0
  
  var children = Set[Account]()
  
  /** add this object to a parent */
  def setParent(parent: Account) {
    if (parent!=null) {
        parent.children += this
    }
  }
  
  //def getAccountAndChi
  
  def getNameString: String = name+" ("+accountId+")"
  override def toString: String =
    name+" ("+accountId+(if (!children.isEmpty) "+"+children else "")+")"
}
