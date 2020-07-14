package com.khubla.hsmqtt;

import com.google.gson.*;
import com.khubla.hsclient.poll.*;

/**
 * @author Tom Everett
 *         <p>
 *         Copyright (C) 2020,tom@khubla.com
 *         </p>
 */
public class DataPointJSONEncoder {
	private static final Gson gson = new Gson();

	public static String messageJSON(DataPoint dataPoint) {
		return gson.toJson(dataPoint);
	}
}
