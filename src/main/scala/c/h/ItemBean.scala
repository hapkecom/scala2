package c.h

import scala.reflect.BeanProperty
import javax.ejb.EJB
import scala.collection.mutable.Buffer
import javax.annotation.PostConstruct
import javax.faces.model.ListDataModel
import javax.faces.model.DataModel
import javax.inject.Named
import javax.enterprise.context.SessionScoped
import javax.enterprise.context.ConversationScoped

@Named
@ConversationScoped
class ItemBean extends Serializable {
  var myText = "initial myText value"

  val listItemsPage = "list_items.xhtml";
  val EditItemPage = "edit_item.xhtml";
    
  val groupId = 1
  @EJB
  private var dbService: DBService = _
  
  @BeanProperty
  var items: ListDataModel[Item] = _
  
  @BeanProperty
  var currentItem: Item = _
  
  @PostConstruct
  def init() {
    reload();    
  }

  def getBooks: DataModel[Item] = {
    items
  }
  
  def reload() {
    items = new ListDataModel[Item]()
    items.setWrappedData(dbService.getAllItemsJavaList(groupId))
  }
  
  def create(): String = {
    currentItem = new Item
    EditItemPage
  }
 
  /*
  public String delete() {
    Book book = books.getRowData();
    bookService.delete(book);
    reload();
    return listItemsPage;
  }
  */
  
  def edit(): String = {
    currentItem = items.getRowData()
    reload()
    EditItemPage
  }
  
  def save(): String = {
    dbService.updateItem(currentItem)
    reload()
    listItemsPage
  }

  def cancel(): String = {
    reload()
    listItemsPage;
  }
}
