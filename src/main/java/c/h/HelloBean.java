package c.h;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
 
@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	private String name;
 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name+"la";
	}
	
	public static void main(String[] argv) {
		System.out.println("HelloBean als Standalone");
		System.out.println(Hello1.counter());
		System.out.println(Hello1.counter());
	}
}