package se.krite.springmock.web.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.krite.springmock.base.ProjectSpecificIntegrationTestBase;
import se.krite.springmock.context.MockResource;
import se.krite.springmock.web.domain.Command;

import javax.annotation.Resource;

/**
 * A simple integration test that mocks an underlying service when calling one
 * of the test methods.
 * Without class-level annotations, mocked beans will restore themselves
 * to original implementations.
 *
 * @author kristoffer.teuber
 */
public class DemoServiceTestOne extends ProjectSpecificIntegrationTestBase {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private DemoServiceOne demoServiceOne;

	@Resource
	private DemoServiceCommandHolder demoServiceCommandHolder;

	@Test
	public void shouldEncryptTextNormally() {
		String text = "Hello world normal 1!";
		String result = this.demoServiceOne.encrypt(text, "salt");
		this.log.info(result);
	}

	@Test
	public void shouldEncryptTextWithNoMock() {
		String text = "Hello world nomock 1";
		this.demoServiceCommandHolder.setCurrentCommand(new Command(text));
		String result = this.demoServiceOne.getCurrentCommandTextEncrypted();
		this.log.info(result);
	}

	@Test
	@MockResource(beanName = "demoServiceOne", mockBeanName = "mockDemoServiceOne")
	public void shouldEncryptTextWithMock() {
		String text = "Hello world mock 1!";
		this.demoServiceCommandHolder.setCurrentCommand(new Command(text));
		String result = this.demoServiceOne.getCurrentCommandTextEncrypted();
		this.log.info(result);
	}

	@Test
	public void shouldEncryptTextWithNoMockAgain() {
		String text = "Hello world nomock again 1!";
		this.demoServiceCommandHolder.setCurrentCommand(new Command(text));
		String result = this.demoServiceOne.getCurrentCommandTextEncrypted();
		this.log.info(result);
	}

	@Component("mockDemoServiceOne")
	public static class MockDemoServiceOneImpl extends DemoServiceOneImpl {
		@Override
		public String getCurrentCommandTextEncrypted() {
			return "Ha ha, I mock you 1!";
		}
	}
}
