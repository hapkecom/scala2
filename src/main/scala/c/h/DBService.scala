package c.h
import javax.ejb.Stateless
import java.util.Date
import javax.ejb.Schedule
import javax.ejb.Local
import javax.ejb.LocalBean
import javax.persistence.PersistenceContext
import javax.persistence.EntityManager
import scala.collection.mutable.Buffer


@Stateless(name="DBService")
@LocalBean
class DBService {
  @PersistenceContext
  protected var em: EntityManager = _
  
  
  def foo() = {
    1
  }
  
  
  def getAllItemtypes() : Buffer[Itemtype] =  {
    import scala.collection.JavaConversions._
    // manager createNamedQuery ("findAll" + m.erasure.getSimpleName) getResultList
    em.createNamedQuery("Itemtype.findAll").getResultList.asInstanceOf[java.util.List[Itemtype]]
    //r1
    
    //val s = 
/*    
      def find(id: Int)(implicit em: EntityManager) : User4 =
    em.find(classOf[User4], id).asInstanceOf[User4]
*/
    
    /*
    val result1 : List[Itemtype] = em.createQuery[Itemtype]("SELECT it FROM Itemtype it").getResultList()
    
    val query = em.createQuery[Itemtype]("SELECT it FROM Itemtype it")
    //query.getR
    val result : List[Itemtype] = query.getResultList()
    result
    */
  }
  def getAllItemsJavaList(groupId : Long): java.util.List[Item] = {
    println("getAllItemsJavaList start")
    var r = em.createNamedQuery("Item.findAllByGroupId")
    println(r)
    var r2 = r.setParameter("groupId", 1L)
    println(r2)
    var r3 = r2.getResultList.asInstanceOf[java.util.List[Item]]
    println(r3)
    r3
  }

  def getAllItems(groupId : Long): Buffer[Item] = {
    import scala.collection.JavaConversions._
    println("getAllItems start")
    var r4 = getAllItemsJavaList(groupId)
    println(r4)
    r4
  }
  
  def getItem(groupId: Long, itemtypeId: Long): Buffer[Item] = {
    import scala.collection.JavaConversions._
    em.createNamedQuery("Item.findAllByGroupId").setParameter("groupId", 1).getResultList.asInstanceOf[java.util.List[Item]]
  }

  def getItem(itemId: Long): Item = {
    em.find(classOf[Item], itemId).asInstanceOf[Item]
  }
  
  def updateItem(item: Item) {
    em.merge(item)
  }
  
}
