package c.h.webservice.rest

import javax.faces.bean.ManagedBean
import javax.faces.bean.SessionScoped
import scala.reflect.BeanProperty
import javax.ejb.EJB
import javax.ejb.Stateless
import javax.ws.rs.Path
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.PathParam
import javax.ws.rs.PUT
import javax.ws.rs.Consumes
import javax.ws.rs.core.MediaType

@Path("/user/")
@Stateless
class UserRestService {
 
  @PUT
  @Consumes(Array(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON))
  /**
   * example requests with full data:
        curl -i -H "Content-Type: application/json" -H "Accept: application/json" -X PUT -d '{"firstName":"Grizz","lastName":"HaB","password":{"seed":666,"value":"my789pw"}}' "http://localhost:8080/scala2/services/user/"
        curl -i -H "Content-Type: application/json" -H "Accept: application/xml"  -X PUT -d '{"firstName":"Grizz","lastName":"HaB","password":{"seed":666,"value":"my789pw"}}' "http://localhost:8080/scala2/services/user/"
        curl -i -H "Content-Type: application/xml"  -H "Accept: application/xml"  -X PUT -d '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><remoteUser><firstName>Grizl</firstName><lastName>Mausi</lastName><password><seed>999</seed><value>meins</value></password></remoteUser>' "http://localhost:8080/scala2/services/user/" 
   * example request with subset of data:
        curl -i -H "Content-Type: application/json" -H "Accept: application/json" -X PUT -d '{"firstName":"Shorty"}' "http://localhost:8080/scala2/services/user/"
        curl -i -H "Content-Type: application/xml"  -H "Accept: application/xml"  -X PUT -d '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><remoteUser><firstName>Shorty</firstName></remoteUser>' "http://localhost:8080/scala2/services/user/" 
   */
  def putUser(user: RemoteUser): RemoteUser = {
    user.firstName = "new-"+user.firstName
    user.lastName =  "neu-"+user.lastName
    
    user
  }
  
  @GET
  @Path("{userId}/")
  @Produces(Array(/*MediaType.APPLICATION_XML,*/ MediaType.APPLICATION_JSON))
  /** example URL: http://localhost:8080/scala2/services/user/123/ */
  def getUser(@PathParam("userId") userId: String): RemoteUser = {
    val user = new RemoteUser()
    println("new user="+user);
    user.firstName = "Chris"
    user.lastName = "Hap"
    user.password.value = ""+userId;
    println("user="+user);
      
    user
  }
}
