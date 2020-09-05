package com.khubla.hsmqtt;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.*;

import com.khubla.hsclient.*;
import com.khubla.hsclient.poll.*;
import com.khubla.hsclient.util.*;

/**
 * @author Tom Everett
 *         <p>
 *         Copyright (C) 2020,tom@khubla.com
 *         </p>
 */
public class MQTTSender implements DataPointCallback {
	/**
	 * logger
	 */
	private static Logger logger = LoggerFactory.getLogger(MQTTSender.class);
	/**
	 * configuration for hsinflux
	 */
	private final Configuration configuration;
	/**
	 * poller
	 */
	private final Poller poller;
	/**
	 * points queue
	 */
	private Queue<DataPoint> points;

	public MQTTSender(Configuration configuration) {
		super();
		this.configuration = configuration;
		poller = new Poller(configuration.getHsConfiguration(), this.configuration.getPollinginterval(), this, this.configuration.getPollingthreads(), true);
	}

	@Override
	public void beginUpdate() {
		points = new ConcurrentLinkedQueue<DataPoint>();
	}

	/**
	 * create MqttMessage from DataPoint
	 *
	 * @param dataPoint
	 * @return
	 */
	private MqttMessage createMessage(DataPoint dataPoint) {
		MqttMessage mqttMessage = null;
		final String msg = DataPointJSONEncoder.messageJSON(dataPoint);
		mqttMessage = new MqttMessage(msg.getBytes());
		mqttMessage.setQos(0);
		mqttMessage.setRetained(true);
		return mqttMessage;
	}

	@Override
	public void endUpdate(long timems) {
		/*
		 * send to MQTT
		 */
		mqttSend();
	}

	/**
	 * send message to mqtt
	 */
	private void mqttSend() {
		IMqttClient mqttClient = null;
		try {
			/*
			 * connect
			 */
			mqttClient = new MqttClient(configuration.getMqttConfiguration().getMqtturl(), configuration.getMqttConfiguration().getMqttpublisherid());
			final MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			mqttClient.connect(options);
			for (final DataPoint dataPoint : points) {
				logger.info(DeviceName.getDeviceName(dataPoint.getName()) + " : " + dataPoint.getValue());
				/*
				 * send
				 */
				mqttClient.publish(configuration.getMqttConfiguration().getMqtttopic(), createMessage(dataPoint));
			}
		} catch (final Exception e) {
			logger.error("Error writing device data to MQTT", e);
		} finally {
			try {
				mqttClient.close();
			} catch (final Exception e) {
				logger.error("Error closing MqttClient", e);
			}
		}
	}

	public void run() throws HSClientException, InterruptedException, IOException {
		poller.run();
	}

	@Override
	public void update(DataPoint dataPoint) {
		points.add(dataPoint);
	}
}
