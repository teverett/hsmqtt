package com.khubla.hsmqtt;

/**
 * @author Tom Everett
 *         <p>
 *         Copyright (C) 2020,tom@khubla.com
 *         </p>
 */
public class Main {
	public static void main(String... args) {
		System.out.println("HomeSeer MQQT Client");
		try {
			/*
			 * go
			 */
			final Configuration configuration = Configuration.getInstance();
			final MQTTSender importer = new MQTTSender(configuration);
			importer.run();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}