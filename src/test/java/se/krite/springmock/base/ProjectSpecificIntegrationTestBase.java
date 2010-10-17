package se.krite.springmock.base;

import org.springframework.test.context.ContextConfiguration;
import se.krite.springmock.context.IntegrationTestBase;

/**
 * This is the actual injection point of the mocking framework.
 * By telling the spring context configuration what context loader to use, the
 * context gets loaded properly and with mocking support aswell.
 *
 * @author kristoffer.teuber
 */
@ContextConfiguration(loader = ProjectSpecificTestContextLoader.class)
public class ProjectSpecificIntegrationTestBase extends IntegrationTestBase {
}
