package com.khubla.hsmqtt;

/**
 * @author Tom Everett
 *         <p>
 *         Copyright (C) 2020,tom@khubla.com
 *         </p>
 */
public class Main {
	public static void main(String... args) {
		System.out.println("HomeSeer MQTT Client");
		try {
			/*
			 * go
			 */
			final Configuration configuration = Configuration.getInstance();
			final MQTTSender mqttSender = new MQTTSender(configuration);
			mqttSender.run();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}