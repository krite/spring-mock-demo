package se.krite.springmock.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.krite.springmock.context.ContextXmlPropertyLookup;
import se.krite.springmock.context.TestContextLoader;

import java.io.File;

/**
 * This extended class decides how the project should load the spring context during test setup.
 * The demo loads it by a simple file based lookup strategy.
 *
 * @author kristoffer.teuber
 */
public class ProjectSpecificTestContextLoader extends TestContextLoader {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private String[] defaultLocations = new String[]{
			"applicationContextTest.xml"};

	private String findFile(String directory, String fileName) {
		return ContextXmlPropertyLookup.findFile(new File(directory), fileName).getAbsolutePath();
	}

	public ProjectSpecificTestContextLoader() {
		ContextXmlPropertyLookup cxpl = new ContextXmlPropertyLookup("testContext.xml", this.getClass().getName());
		String projectBasePath = cxpl.getProperties().getProperty("test_context_base_path");
		for (int i = 0; i < this.defaultLocations.length; i++) {
			this.defaultLocations[i] = this.findFile(projectBasePath, this.defaultLocations[i]);
		}
		log.info("Project base path set to: " + projectBasePath);
	}

	public String[] processLocations(Class<?> clazz, String... locations) {
		int i = 0;
		String[] finalLocations = new String[locations.length + this.defaultLocations.length];
		for (String location : locations)
			finalLocations[i++] = location;
		for (String location : this.defaultLocations)
			finalLocations[i++] = "file:" + location;
		return finalLocations;
	}
}
