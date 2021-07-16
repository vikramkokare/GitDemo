package alpha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo {
	
	private static Logger log = LogManager.getLogger(Demo.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		log.error("It's error message");
		log.fatal("It's fatal error");
		log.warn("It's warning message");
		log.debug("it's debug message");
		log.info("It's info");
		log.trace("Trace log it's");

	}

}
