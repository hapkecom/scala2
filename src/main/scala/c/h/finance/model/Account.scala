/*
 * Scala Enterprise test project.
 * Copyright (c) 2011 Christian Hapke (hapke.com). All rights reserved.
*/
package c.h.finance.model
import scala.reflect.BeanProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.NamedQuery
import javax.persistence.Id
import javax.persistence.Transient


@Entity
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
class Account extends Serializable {
  @Id
  @BeanProperty
  @Column(name="account_id")
  var accountId: Long = _
  
  @BeanProperty
  var name: String = _
  
  @BeanProperty
  var sign: Long = _
  
  @BeanProperty
  @Column(name="parent_account_id")
  var parentAccountId: Long = _

  /** created after read from DB: */
  @Transient 
  var children = Set[Account]()
  
  /** add this object to a parent */
  def setParent(parent: Account) {
    if (parent!=null) {
        parent.children += this
    }
  }
  
  def getNameString: String = name+" ("+accountId+")"

  override def toString: String =
    name+" ("+accountId+(if (!children.isEmpty) "+"+children else "")+")"
}
