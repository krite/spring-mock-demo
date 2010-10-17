package se.krite.springmock.web.domain;

public class Command {
	private String text;

	public Command(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
