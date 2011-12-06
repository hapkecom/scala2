package c.h.finance


class Plan extends Serializable {
  var months: Array[String] = Array("Jan 2011", "Feb 2011", "Maerz 2011")
  
  var amountInCents: Long = (10000L * Math.random).toLong
  
  
  def getMonths: Array[String] = months
  
  def getAmountString(): String = {
    ""+(amountInCents/100)+" EUR"
  }
  
 
  def data(versionId: Long, accountId: Long, month: String, startdate: String): Amount = {
    new Amount
  }

}
