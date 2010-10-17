package se.krite.springmock.web.service;

import se.krite.springmock.web.domain.Command;

public interface DemoServiceCommandHolder {
	Command getCurrentCommand();

	void setCurrentCommand(Command command);
}
