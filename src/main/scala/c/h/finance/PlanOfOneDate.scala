package c.h.finance
import scala.collection.mutable.ListBuffer
import scala.collection.immutable.HashMap


class PlanOfOneDate {
  /** map with key=accountId, value=amount */
  private var values = Map[Long,Amount]()
  
  def addValue(accountId: Long, value: Amount) {
    values += accountId -> value
  }
  
  def getValue(accountId: Long) = {
    if (values.contains(accountId)) values(accountId) else new Amount(0)
  }
  
  override def toString(): String = values.toString
}
