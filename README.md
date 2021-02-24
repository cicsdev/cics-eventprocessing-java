# cics-eventprocessing-java

This Java web application demonstrates how to emit and consume CICS events in Java using the JCICS API.

## Overview

This sample shows how to consume and emit CICS events using Java code. Events are emitted from a Java EE servlet using the JCICS Event API, equivalent to an `EXEC CICS SIGNAL EVENT` call. This event is processed by CICS event processing and is handled by an event processing adapter. This adapter is configured to call the CICS program `EVNTCONS` when an event is received.

The CICS program `EVNTCONS` is a Java EE program designed to consume CICS events and, in this basic sample, log that an event was received to STDOUT.

## Requirements

* CICS TS V5.3 or later
* A configured Liberty JVM server
* Java SE 1.8 or later on the z/OS system
* Java SE 1.8 or later on the workstation
* Either Gradle or Apache Maven on the workstation (optional if using Wrappers)

## Structure

The project consists of two parts:

1. cics-eventprocessing-java-webapp - the Java source code for this example
2. cics-eventprocessing-java-bundle - the bundle definition for this example

## Code Structure

There are two Java classes:

1. [`EventEmitter.java`](./cics-eventprocessing-java/cics-eventprocessing-java-webapp/src/main/java/com/ibm/cicsdev/eventprocessing/EventEmitter.java) - a servlet that emits CICS events
2. [`EventConsumer.java`](./cics-eventprocessing-java/cics-eventprocessing-java-webapp/src/main/java/com/ibm/cicsdev/eventprocessing/EventConsumer.java) - the Java code to define the CICS program resource `EVNTCONS` and consume the CICS events

The code is built into a WAR file, packaged into a CICS bundle, and deployed to a Liberty JVM server as a web application.

## Bundle Structure

The CICS bundle contains the following elements:

1. [`EVNT.transaction`](./cics-eventprocessing-java/cics-eventprocessing-java-bundle/src/main/bundleParts/EVNT.transaction) - transaction which targets the `EVNTCONS` program
2. [`ACCOUNTADAPTER.epadapter`](./cics-eventprocessing-java/cics-eventprocessing-java-bundle/src/main/bundleParts/ACCOUNTADAPTER.epadapter) - event processing adapter which starts the `EVNT` transaction
3. [`ACCOUNT.evbind`](./cics-eventprocessing-java/cics-eventprocessing-java-bundle/src/main/bundleParts/ACCOUNTADAPTER.epadapter) - event binding which targets the `EVENTADAPTER` adpater
4. `WARBUNDLE` - Java WAR bundle is added when built

## Building

There are several ways to build this project. We suggest you build with a build toolkit such as Gradle or Maven.

### Building with Gradle

Run the following command from the `cics-eventprocessing-java` directory:

```sh
./gradlew assemble
```

This will compile the Java code, build a WAR file and package it and the EP adapter and Event binding into a CICS bundle as a ZIP file at `./cics-eventprocessing-java-bundle/build/distributions/cics-eventprocessing-java-bundle-0.0.1-SNAPSHOT.zip`

### Building with Maven

Run the following command from the `cics-eventprocessing-java` directory:

```sh
./mvnw verify
```

This will compile the Java code, build a WAR file and package it and the EP adapter and Event binding into a CICS bundle as a ZIP file at `./cics-eventprocessing-java-bundle/target/cics-eventprocessing-java-bundle-0.0.1-SNAPSHOT.zip`

## Configuration

You will need to define a CICS Liberty JVM server with the name `DFHWLP`. This name can be modified in the `pom.xml` of the cics-eventprocessing-java-bundle module.

The Liberty JVM server will need the following features installed at a minimum: `cicsts:core-1.0` and `cicsts:link-1.0` and `servlet-3.1`. For example:

```xml
<server name="cics-eventprocessing-java">
    <featureManager>
        <feature>cicsts:core-1.0</feature>
        <feature>cicsts:link-1.0</feature>
        <feature>servlet-3.1</feature>
    </featureManager>
  
  <!-- More configuration -->
</server>
```

## Deploying

1. Upload the built bundle ZIP file to zFS on your z/OS system in binary using FTP, or similar
2. Logon to zFS on your z/OS system using SSH, OMVS, or similar
3. Extract the ZIP file contents, for example using command `unzip /path/to/bundle.zip`
4. In CICS, define a new `BUNDLE` resource with the `BUNDLEDIR` set to the extracted ZIP file contents, then install the `BUNDLE` resource

You can confirm the application has started by inquiring the state of the `BUNDLE`, for example using transaction `CEMT INQUIRE BUNDLE`, or the CICS Explorer bundle view.

A message will be written to the Liberty `messages.log` file to confirm the application has started, and the base URL for this application.

## Running

1. Use a browser to target the address http://my.zos:9080/cics-eventprocessing-java-webapp/emit?name=EVENT replacing `my.zos` with the hostname of your z/OS system and `9080` with the port the Liberty JVM server is listening on.
2. View the JVM server CURRENT.STDOUT file, this should contain two log messages
   * Emitted event
   * Consumed event

You can also emit an event using `EXEC CICS SIGNAL EVENT(event)` either in another CICS program, or using transaction `CECI`.

## License

This project is licensed under [Eclipse Public License - v 2.0](LICENSE).
