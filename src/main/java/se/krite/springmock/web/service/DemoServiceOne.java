package se.krite.springmock.web.service;

public interface DemoServiceOne {
	String encrypt(String plainText, String salt);

	String getCurrentCommandTextEncrypted();
}
