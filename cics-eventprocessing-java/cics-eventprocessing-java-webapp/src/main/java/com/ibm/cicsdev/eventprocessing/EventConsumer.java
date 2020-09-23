package com.ibm.cicsdev.eventprocessing;

import com.ibm.cics.server.CicsException;
import com.ibm.cics.server.invocation.CICSProgram;

/**
 * Consumes events from a CICS event processor
 */
public class EventConsumer
{
	/**
	 * CICS program entry point to allow this class to consume events
	 * @throws CicsException
	 */
	@CICSProgram("EVNTCONS")
	public void consumeEvent() throws CicsException
	{
		System.out.println("Consuming event");
	}
}
