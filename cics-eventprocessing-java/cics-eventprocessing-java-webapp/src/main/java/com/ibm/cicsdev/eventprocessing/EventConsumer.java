package com.ibm.cicsdev.eventprocessing;

/* Licensed Materials - Property of IBM                                   */
/*                                                                        */
/* SAMPLE                                                                 */
/*                                                                        */
/* (c) Copyright IBM Corp. 2020 All Rights Reserved                       */
/*                                                                        */
/* US Government Users Restricted Rights - Use, duplication or disclosure */
/* restricted by GSA ADP Schedule Contract with IBM Corp                  */
/*                                                                        */

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
