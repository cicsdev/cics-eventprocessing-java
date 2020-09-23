package com.ibm.cicsdev.eventprocessing;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.cics.server.CicsException;
import com.ibm.cics.server.Event;
import com.ibm.cics.server.EventErrorException;

/**
 * Servlet which emits events to a CICS event processor
 */
@WebServlet("/emit")
public class EventEmitter extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * Emits an event to CICS.
	 * <p>
	 * <strong>Note:</strong> In a real REST API, this should be use POST or PUT
	 * method. But for simplicity of a sample, this is bound to the GET method
	 * instead.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		// Retrieve the event name from the request parameters
		String eventName = request.getParameter("event");

		try
		{
			emit(eventName);
			response.setStatus(200);
			response.getWriter().println("Emitted event successfully");
		}
		catch(EventErrorException e)
		{
			response.setStatus(400);
			response.getWriter().println(e.getLocalizedMessage());
		}
		catch(CicsException e)
		{
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().println(e.getLocalizedMessage());
		}
	}
		
	private void emit(String eventName) throws CicsException
	{
		// Construct the CICS Event object
		Event event = new Event(eventName);

		System.out.println("Emitting event to: '" + event.getName() + "'");

		// Send the event
		event.signal();
	}
}
