package c.h
import javax.ejb.Stateless
import java.util.Date
import javax.ejb.Schedule
import javax.ejb.Local
import javax.ejb.LocalBean
import javax.persistence.PersistenceContext
import javax.persistence.EntityManager


@Stateless(name="ScalaService",mappedName="ScalaService")
@LocalBean
class ScalaService {
  @PersistenceContext
  protected var manager: EntityManager = _
  
  var factor = 10L;
  
  /**
   * return a random String
   */
  def myEjbAction : String = {
    println ("*********** ScalaService.myEjbAction called");
    val random : Double = Math.random
    val random2 : Long = (factor*random).toLong
    val res : String =  random2.toString()
    val prod : String = getNameOfProduct1
    "[S"+res+"-"+prod+"]"
  }
  
  
  @Schedule(persistent=false, second="0", minute="*", hour="*", dayOfMonth="*", month="*", year="*")
  def oncePerMinute() = {
	var now = new java.util.Date()
	println ("******************************* ScalaService.timer called just now  : "+(now.toString()))
	
	factor = 10*factor;
  }
  
  /* TODO
  def getAllProducts : String[] = {
    manager createNamedQuery ("findAll" + m.erasure.getSimpleName) getResultList
  }
  
  */
  
  def getNameOfProduct1 : String = {
    
    val id = 1L;
    val p : Product = manager.find(classOf[Product], id)
    p.name
    
    //"abc"
  }
  
}