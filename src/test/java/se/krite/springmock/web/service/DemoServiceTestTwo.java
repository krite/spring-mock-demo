package se.krite.springmock.web.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.krite.springmock.base.ProjectSpecificIntegrationTestBase;
import se.krite.springmock.context.MockResource;
import se.krite.springmock.context.MockResources;
import se.krite.springmock.web.domain.Command;

import javax.annotation.Resource;

/**
 * When using class level mocking configuration, the beans mocked will be set
 * to this mocking implementation before each test method is run.
 *
 * @author kristoffer.teuber
 */
@MockResources({
		@MockResource(beanName = "demoServiceOne", mockBeanName = "mockDemoServiceTwo"),
		@MockResource(beanName = "demoServiceCommandHolder", mockBeanName = "mockDemoServiceCommandHolder")
})
public class DemoServiceTestTwo extends ProjectSpecificIntegrationTestBase {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private DemoServiceOne demoServiceOne;

	@Resource
	private DemoServiceCommandHolder demoServiceCommandHolder;

	@Test
	public void shouldEncryptTextNormally() {
		String text = "Hello world normal 2!";
		String result = this.demoServiceOne.encrypt(text, "salt2");
		this.log.info(result);
	}

	@Test
	public void shouldEncryptTextWithNoMock() {
		String text = "Hello world nomock 2!";
		this.demoServiceCommandHolder.setCurrentCommand(new Command(text));
		String result = this.demoServiceOne.getCurrentCommandTextEncrypted();
		this.log.info(result);
	}

	@Test
	@MockResources({
			@MockResource(beanName = "demoServiceOne", restoreToOriginal = true),
			@MockResource(beanName = "demoServiceCommandHolder", restoreToOriginal = true)
	})
	public void shouldEncryptTextWithRestore() {
		String text = "Hello world restore 2!";
		this.demoServiceCommandHolder.setCurrentCommand(new Command(text));
		String result = this.demoServiceOne.getCurrentCommandTextEncrypted();
		this.log.info(result);
	}

	@Test
	public void shouldEncryptTextWithNoMockAgain() {
		String text = "Hello world classdefault 2!";
		this.demoServiceCommandHolder.setCurrentCommand(new Command(text));
		String result = this.demoServiceOne.getCurrentCommandTextEncrypted();
		this.log.info(result);
	}

	@Component("mockDemoServiceTwo")
	private static class MockDemoServiceTwoImpl extends DemoServiceOneImpl {
		@Override
		public String getCurrentCommandTextEncrypted() {
			return "Ha ha, I mock you again 2!";
		}
	}

	@Component("mockDemoServiceCommandHolder")
	private static class MockDemoServiceCommandHolderImpl extends DemoServiceCommandHolderImpl {
		@Override
		public Command getCurrentCommand() {
			return new Command("Mocked text 2!");
		}

		@Override
		public void setCurrentCommand(Command command) {
			// do nothing
		}
	}
}
