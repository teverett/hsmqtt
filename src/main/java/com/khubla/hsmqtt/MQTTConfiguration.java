package com.khubla.hsmqtt;

/**
 * @author Tom Everett
 *         <p>
 *         Copyright (C) 2020,tom@khubla.com
 *         </p>
 */
public class MQTTConfiguration {
	private final String mqtturl;
	private final String mqtttopic;
	private final String mqttpublisherid;

	public MQTTConfiguration(String mqtturl, String mqtttopic, String mqttpublisherid) {
		super();
		this.mqtturl = mqtturl;
		this.mqtttopic = mqtttopic;
		this.mqttpublisherid = mqttpublisherid;
	}

	public String getMqtturl() {
		return mqtturl;
	}

	public String getMqtttopic() {
		return mqtttopic;
	}

	public String getMqttpublisherid() {
		return mqttpublisherid;
	}
}
