package c.h.finance


class PlanDate(initialStartdate: String) {
  val startdate = initialStartdate
  
  override def toString(): String = startdate
}
