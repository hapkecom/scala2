package c.h.finance


class Amount extends Serializable {
  var amountInCents: Long = (10000L * Math.random).toLong
  
  def getAmountString(): String = {
    ""+(amountInCents/100)+" EUR"
  }
}
