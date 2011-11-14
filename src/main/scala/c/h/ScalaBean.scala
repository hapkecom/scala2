package c.h

import javax.faces.bean.ManagedBean
import javax.faces.bean.SessionScoped
import scala.reflect.BeanProperty
import javax.ejb.EJB

@ManagedBean
@SessionScoped
class ScalaBean {
  var myText = "initial myText value"
    
  @BeanProperty
  var myText2 = "initial myText2 value"
    
  @EJB
  private var scalaService : ScalaService = _
  
  @EJB
  private var javaService : JavaService = _
  
  
  def getMyText(): String = {
    val res2 = javaService.myEjbAction
    val res3 = scalaService.myEjbAction
    
    val random : Double = Math.random
    val random2 : Int = (100*random).toInt
    val res : String =  random2.toString()
    myText+res+res2+res3
  }

  def setMyText(myText: String) {
    this.myText=myText
  }

}
