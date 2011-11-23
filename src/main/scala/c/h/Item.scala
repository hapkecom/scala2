package c.h
import javax.persistence.Entity
import javax.persistence.NamedQuery
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.GenerationType
import scala.reflect.BeanProperty
import javax.persistence.Column

@Entity
@NamedQuery(name = "Item.findAllByGroupId", query = "SELECT i FROM Item i WHERE i.groupId = :groupId")
class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@BeanProperty
	@Column(name="item_id")
	var itemId : Long = _
	
	@BeanProperty
	@Column(name="itemtype_id")
	var itemtypeId : Long = _

	@BeanProperty
	@Column(name="group_id")
	var groupId : Long = _

	@BeanProperty
	var encryption : String = _

	@BeanProperty
	@Column(name="encrypted_value")
	var encryptedValue : String = _

	@BeanProperty
	@Column(name="encrypted_comment")
	var encryptedComment : String = _
}
