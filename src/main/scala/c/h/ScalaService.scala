package c.h
import javax.ejb.Stateless
import java.util.Date
import javax.ejb.Schedule
import javax.ejb.Local
import javax.ejb.LocalBean


@Stateless(name="ScalaService",mappedName="ScalaService")
@LocalBean
class ScalaService {
  var factor = 10L;
  
  /**
   * return a random String
   */
  def myEjbAction : String = {
    println ("*********** ScalaService.myEjbAction called");
    val random : Double = Math.random
    val random2 : Long = (factor*random).toLong
    val res : String =  random2.toString()
    "[S"+res+"]"
  }
  
  
  @Schedule(persistent=false, second="0", minute="*", hour="*", dayOfMonth="*", month="*", year="*")
  def oncePerMinute() = {
	var now = new java.util.Date()
	println ("******************************* ScalaService.timer called just now  : "+(now.toString()))
	
	factor = 10*factor;
  }
}