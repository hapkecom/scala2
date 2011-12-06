package c.h.finance


class Account(accountId: Long, name: String) extends Serializable {
  def getAccountId: Long = accountId
  
  def getNameString: String = name+" ("+accountId+")"
  override def toString: String = name+" ("+accountId+")"
}
