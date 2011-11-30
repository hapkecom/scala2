package c.h.webservice.rest

import javax.faces.bean.ManagedBean
import javax.faces.bean.SessionScoped
import scala.reflect.BeanProperty
import javax.ejb.EJB
import javax.ejb.Stateless
import javax.ws.rs.Path
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAccessType

/**
 * In this class we use default JAXB mapping for XML/JSON (de)serialization.
 *
 * Hint: the @XmlRootElement annotation is not needed for JSON but for XML
 */
@XmlRootElement
class RemoteUser {
  	@BeanProperty
	var firstName : String = _
	
  	@BeanProperty
	var lastName : String = _
	
  	@BeanProperty
	var password : RemotePassword = new RemotePassword

	override def toString = "RemoteUser(firstName="+firstName+", lastName="+lastName+", password="+password+")";
}
