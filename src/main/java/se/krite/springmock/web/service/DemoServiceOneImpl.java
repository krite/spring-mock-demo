package se.krite.springmock.web.service;

import org.springframework.stereotype.Component;
import se.krite.springmock.web.domain.Command;

import javax.annotation.Resource;

@Component("demoServiceOne")
public class DemoServiceOneImpl implements DemoServiceOne {

	@Resource
	private DemoServiceCommandHolder demoServiceCommandHolder;

	@Override
	public String getCurrentCommandTextEncrypted() {
		Command command = this.demoServiceCommandHolder.getCurrentCommand();
		if (command == null) {
			return "";
		}
		String text = command.getText();
		if (text == null) {
			return "";
		}
		return this.encrypt(text, "salt");
	}

	public String encrypt(String plainText, String salt) {
		return "This is encoded: " + plainText + "-" + salt;
	}
}