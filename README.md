# cics-eventprocessing-java
Example code of how to use CICS event processing in the Java programming language

## Structure
The project consists of two Maven modules:

1. cics-eventprocessing-java-webapp - The Java source code for this example
2. cics-eventprocessing-java-bundle - The bundle definition for this example

## Code Structure
The code is built into a WAR file, which can be deployed to a Liberty JVM server as a web application.

There are two Java classes:

1. EventEmitter.java - A servlet which emits CICS events
2. EventConsumer.java - Defines the program `EVNTCONS` which receives CICS events, see [Bundle Structure](#bundle_structure) for more details

## Bundle Structure
The CICS bundle contains several elements

1. `EVNT` - A transaction which targets the `EVNTCONS` program
2. `EVENTADAPTER` - An EP Adapter which starts the `EVNT` transaction
3. `EVENT` - An Event Binding which targets the `EVENTADAPTER` adpater
4. A WARBUNDLE containing the built Java WAR.

## Building
*TODO*

## Configuration
You'll need to define a Liberty JVM server with the name `DFHWLP`. This name can be modified in the `pom.xml` of the cics-eventprocessing-java-bundle module.

The Liberty JVM server will need the `cicsts:core-1.0`, `cicsts:link-1.0` and `servlet-3.1` features installed at minimum. A sample server.xml file is provided below

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
*TODO*

## Running
1. Use a browser to target the address http://my.zos:9080/cics-eventprocessing-java-webapp/emit?name=EVENT replacing `my.zos` with the hostname of your z/OS system and `9080` with the port the Liberty JVM server is listening on.
2. View the JVM server CURRENT.STDOUT file, this should contain two log messages
   * Emitted event
   * Consumed event

You can also emit an event using `EXEC CICS SIGNAL EVENT(EVENT)` either in another CICS program, or using the `CECI` transaction.


## License
This project is licensed under [Eclipse Public License - v 2.0](LICENSE). 

