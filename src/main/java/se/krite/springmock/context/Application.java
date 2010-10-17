package se.krite.springmock.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Application implements ServletContextListener {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private ServletContext context = null;

	public void contextInitialized(ServletContextEvent event) {
		try {
			this.log.info("Context initialized");
			this.context = event.getServletContext();

		} catch (Throwable t) {
			t.printStackTrace();
			throw new Error(t.getMessage(), t);
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		this.log.info("Context destroying...");
		this.log.info("Context destroyed");
	}

	private String getInitParameter(String name) {
		return this.context.getInitParameter(name);
	}
}
