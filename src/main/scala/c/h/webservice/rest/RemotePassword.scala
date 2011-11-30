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
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAccessType

/**
 * In this class it is explicitly defined
 * which fields or getters/setters are used for XML/JSON (de)serialization.
 */
@XmlAccessorType(XmlAccessType.NONE)
class RemotePassword(seedInitial: Long, valueInitial: String) {
    // standard field "seed"
  	@BeanProperty
  	@XmlElement
	var seed: Long = seedInitial
	
	// special field "value"
  	private var _value: String = valueInitial
	def value: String = "get"+_value;
  	def value_= (value: String) {_value = "set"+value}

  	@XmlElement
  	def getValue() = value
  	def setValue(value: String) = {this.value = value}
  	
  	// constructors
	def this(value: String) = this((1000000000L*Math.random).toLong, value)
	def this() = this("<no value>")

	// other stuff
	override def toString = "RemotePassword(seed="+seed+", value="+value+")";
}
