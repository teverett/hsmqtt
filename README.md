![CI](https://github.com/teverett/hsmqtt/workflows/CI/badge.svg)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/92734c5b095c44b48f95bf2d993cddb7)](https://app.codacy.com/manual/teverett/hsmqtt?utm_source=github.com&utm_medium=referral&utm_content=teverett/hsmqtt&utm_campaign=Badge_Grade_Dashboard)

# HomeSeer hsMQTT

A Java [MQTT](https://en.wikipedia.org/wiki/MQTT) bridge for HomeSeer.  msMQTT polls the HomeSeer API on a schedule and publishes device change messages to an MQTT Broker.  Messages are published in JSON format.

hsMQTT is one of numerous HomeSeer support applications created by khubla.com, including

* [hsClient](https://github.com/teverett/hsclient)
* [hsInflux](https://github.com/teverett/hsinflux)
* [hsMQTT](https://github.com/teverett/hsOpenAPI)
* [hsOpenAPI](https://github.com/teverett/hsOpenAPI)

msMQTT uses [hsClient](https://github.com/teverett/hsclient)

## License

msMQTT is distributed under the BSD 3-Clause License.

## Configuration

msMQTT is configured via the file "hsmqtt.properties".  A typical example is

```
# HomeSeer
hsurl=http://192.168.75.129/JSON
hsuser=HOMESEERUSERNAME
hspassword=HOMESEERPASSWORD

# MQTT
mqtturl=tcp://192.168.75.71:1883
mqtttopic=devices
mqttpublisherid=hsMQTT

# Number of threads to poll HomeSeer on
pollingthreads=5

# minutes
pollinginterval=1
```
## Example message

```json
{"deviceRef":107,"value":0.0,"lastChange":1594530001541,"location":"Living Room","location2":"Main Floor","name":"Living Room Dimmer","status":"Off","type":"Z-Wave Switch"}
```

## Usage


```
java -jar target/hsmqtt-1.0.0-SNAPSHOT.jar 
```
A log file is generated to hsmqtt.log and rotated every 30 days.

