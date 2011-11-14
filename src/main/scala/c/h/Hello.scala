package c.h

object Hello1 {
  var globalCounter:Int=1
  
  def main(argv : Array[String]) {
	  println ("hallxx")
	  globalCounter=2
	  globalCounter+=1
	  
	  println (counter())
	  println (counter())
  }
  
  def counter() : Int = {
     globalCounter+=1
     globalCounter
  }
  
  // @BeanProperty
}
