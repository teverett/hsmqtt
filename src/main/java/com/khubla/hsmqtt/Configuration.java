package com.khubla.hsmqtt;

import java.io.*;
import java.util.*;

import com.khubla.hsclient.*;

/**
 * @author Tom Everett
 *         <p>
 *         Copyright (C) 2020,tom@khubla.com
 *         </p>
 */
public class Configuration {
	private static final String FILENAME = "hsmqtt.properties";
	private static Configuration instance = null;

	public static Configuration getInstance() {
		if (null == instance) {
			instance = new Configuration();
		}
		return instance;
	}

	private HSConfiguration hsConfiguration;
	private MQTTConfiguration mqttConfiguration;
	private int pollingthreads;
	private int pollinginterval;

	private Configuration() {
		load();
	}

	public HSConfiguration getHsConfiguration() {
		return hsConfiguration;
	}

	public int getPollinginterval() {
		return pollinginterval;
	}

	public int getPollingthreads() {
		return pollingthreads;
	}

	private void load() {
		try {
			final Properties properties = new Properties();
			properties.load(new FileInputStream(FILENAME));
			hsConfiguration = new HSConfiguration(properties.getProperty("hsurl"), properties.getProperty("hsuser"), properties.getProperty("hspassword"));
			mqttConfiguration = new MQTTConfiguration(properties.getProperty("mqtturl"), properties.getProperty("mqtttopic"), properties.getProperty("mqttpublisherid"));
			pollingthreads = Integer.parseInt(properties.getProperty("pollingthreads"));
			pollinginterval = Integer.parseInt(properties.getProperty("pollinginterval"));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public MQTTConfiguration getMqttConfiguration() {
		return mqttConfiguration;
	}
}
