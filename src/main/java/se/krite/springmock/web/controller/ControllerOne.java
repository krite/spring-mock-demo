package se.krite.springmock.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import se.krite.springmock.web.domain.Command;
import se.krite.springmock.web.service.DemoServiceCommandHolder;
import se.krite.springmock.web.service.DemoServiceOne;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SessionAttributes("command")
@Controller("controllerOne")
public class ControllerOne {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@PostConstruct
	private void postConstruct() {
		log.info("Created controllerone");
	}

	@Resource
	private DemoServiceOne demoServiceOne;

	@Resource
	private DemoServiceCommandHolder demoServiceCommandHolder;

	@RequestMapping(value = "/controllerone", method = RequestMethod.GET)
	public String list(Model model) {
		Command command = this.demoServiceCommandHolder.getCurrentCommand();
		if (command == null) {
			command = new Command("");
		}
		model.addAttribute("command", command);
		return "one";
	}

	@RequestMapping(value = "/controllerone", method = RequestMethod.POST)
	public String update(@ModelAttribute("command") Command command) {
		command.setText(this.demoServiceOne.encrypt(command.getText(), "salt"));
		this.demoServiceCommandHolder.setCurrentCommand(command);
		return "redirect:/controllerone";
	}
}
