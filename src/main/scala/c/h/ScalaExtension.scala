import util._
import scala.reflect.NameTransformer

class ScalaExtension {
	/**
	 * Repeatedly run `f` until it returns None, and assemble results in a Stream.
	 */
	def unfold[A](a: A, f: A => Option[A]): Stream[A] = {
	  Stream.cons(a, f(a).map(unfold(_, f)).getOrElse(Stream.empty))
	}
	
	def get[T](f: java.lang.reflect.Field, a: AnyRef): T = {
	  f.setAccessible(true)
	  f.get(a).asInstanceOf[T]
	}
	
	/**
	 * @return None if t is null, Some(t) otherwise.
	 */
	def optNull[T <: AnyRef](t: T): Option[T] = if (t eq null) None else Some(t)
	
	/**
	 * @return a Stream starting with the class c and continuing with its superclasses.
	 */
	def classAndSuperClasses(c: Class[_]): Stream[Class[_]] = unfold[Class[_]](c, (c) => optNull(c.getSuperclass))
	
	def showReflect(a: AnyRef): String = {
	  val fields = classAndSuperClasses(a.getClass).flatMap(_.getDeclaredFields).filter(!_.isSynthetic)
	  fields.map((f) => NameTransformer.decode(f.getName) + "=" + get(f, a)).mkString(",")
	}
	
	// TEST
	trait T {
	  val t1 = "t1"
	}
	
	class Base(val foo: String, val ?? : Int) {
	}
	
	class Derived(val d: Int) extends Base("foo", 1) with T
	
	assert(showReflect(new Derived(1)) == "t1=t1,d=1,??=1,foo=foo")
}
