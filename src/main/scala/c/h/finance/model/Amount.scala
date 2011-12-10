/*
 * Scala Enterprise test project.
 * Copyright (c) 2011 Christian Hapke (hapke.com). All rights reserved.
*/
package c.h.finance


class Amount(initialAmount: Long) extends Serializable {
  var amountInCents: Long = initialAmount
  
  def getAmountString(): String = {
    ""+(amountInCents)+" EUR cents"
  }
  
  override def toString = getAmountString()
}
