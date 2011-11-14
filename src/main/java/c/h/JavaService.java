package c.h;

import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
public class JavaService {
	private long factor = 10L;
	
	/**
	 * @return a random String
	 */
	public String myEjbAction() {
	    System.out.println ("*********** JavaService.myEjbAction called");
	    double random  = Math.random();
	    long random2 = (long)(factor*random);
	    String res = ""+random2;
	    return "{J"+res+"}";
	  }
	  
	  
	@Schedule(persistent=false, second="0", minute="*", hour="*", dayOfMonth="*", month="*", year="*")
	public void oncePerMinute() {
		Date now = new Date();
		System.out.println ("******************************* JavaService timer called just now  : "+(now.toString()));
		
		factor = 10*factor;
	}
}
