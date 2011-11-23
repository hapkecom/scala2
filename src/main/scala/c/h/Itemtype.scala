package c.h
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GenerationType
import scala.reflect.BeanProperty
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.NamedQuery

@Entity
@NamedQuery(name = "Itemtype.findAll", query = "SELECT it FROM Itemtype it")
class Itemtype {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@BeanProperty
	@Column(name="itemtype_id")
	var itemtypeId : Long = _
	
	@BeanProperty
	var sort : Long = _

	@BeanProperty
	var name : String = _
}
