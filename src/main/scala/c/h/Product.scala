package c.h
import javax.persistence.Entity
import javax.persistence.NamedQuery
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.GenerationType
import scala.reflect.BeanProperty
import javax.persistence.Column

@Entity
//@NamedQuery(name = Product.FindAllProducts, query = "SELECT p FROM Product p")
class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@BeanProperty
	@Column(name="product_id")
	var productId : Long = _
	
	@BeanProperty
	var name : String = _
	
	@BeanProperty
	var description : String = _
}
