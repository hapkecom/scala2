package c.h
import javax.persistence.Entity
import javax.persistence.NamedQuery
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.GenerationType
import scala.reflect.BeanProperty
import javax.persistence.Column
import javax.persistence.SequenceGenerator

@Entity
@NamedQuery(name = "Item.findAllByGroupId", query = "SELECT i FROM Item i WHERE i.groupId = :groupId ORDER BY i.itemId")
class Item {
	@Id
	@GeneratedValue(generator="ItemIdSeq")
	@SequenceGenerator(name="ItemIdSeq",sequenceName="ITEM_ID_SEQ", allocationSize=1)
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
	
	override def toString = "Item(item_id="+itemId+", encrypted_value="+encryptedValue+")";
}
