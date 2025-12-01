package com.contact.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties props = new Properties();


	static {
	try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
	if (is != null) props.load(is);
	} catch (IOException e) {
	e.printStackTrace();
	}
	}


	public static String get(String key) {
	return props.getProperty(key);
	}
	}

