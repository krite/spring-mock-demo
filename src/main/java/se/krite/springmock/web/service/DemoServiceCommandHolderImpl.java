package se.krite.springmock.web.service;

import org.springframework.stereotype.Component;
import se.krite.springmock.web.domain.Command;

@Component("demoServiceCommandHolder")
public class DemoServiceCommandHolderImpl implements DemoServiceCommandHolder {

	private Command command;

	@Override
	public Command getCurrentCommand() {
		return this.command;
	}

	@Override
	public void setCurrentCommand(Command command) {
		this.command = command;
	}
}