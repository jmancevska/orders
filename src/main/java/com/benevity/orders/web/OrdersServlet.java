package com.benevity.orders.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benevity.orders.OrdersProcessor;


/**
 * @author Jasminka Mancevska
 *
 */
@WebServlet(urlPatterns = {"/ordersservlet"}, asyncSupported = true)
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, 
	                  HttpServletResponse response) {
		
		final String sessionId = request.getSession().getId();
		final String relativeWebPath = "/files";
		OrdersProcessor ordersProcessor = null;
		String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
		String fileFullPath = absoluteDiskPath + File.separator + sessionId + ".xml";


		response.setContentType("text/html;charset=UTF-8");
		final AsyncContext asyncContext = request.startAsync();
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
 
	    asyncContext.setTimeout(10L * 1000);
	    asyncContext.addListener(new AsyncListener() {
			
			public void onTimeout(AsyncEvent event) throws IOException {
				writeToResponse(event, "The process timed out.");
			}
			
			public void onStartAsync(AsyncEvent event) throws IOException {
				// TODO Auto-generated method stub
			}
			
			public void onError(AsyncEvent event) throws IOException {
				writeToResponse(event, event.getThrowable().getMessage());
			}
			
			public void onComplete(AsyncEvent event) throws IOException {
				writeToResponse(event, "The process completed. " +
						"<a href=\"files/" + sessionId + ".xml\">download file</a>" 
						 );
			}
		});


		String company = asyncContext.getRequest().getParameter("company");
		String user = asyncContext.getRequest().getParameter("user");
		String startDate = asyncContext.getRequest().getParameter("startDate");
		String endDate = asyncContext.getRequest().getParameter("endDate");
		String country = asyncContext.getRequest().getParameter("country");
		
		// Validate the parameters, and if not valid return appropriate message
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/mm/dd"); 
		Date dStartDate = null;
		Date dEndDate = null;
		
		String errMessage = "";
		String statusMessage = ""; 

		if (startDate != null && startDate.trim().length() != 0) {
			java.util.Date date;
			try {
				date = dt.parse(startDate);
				dStartDate = new Date(date.getTime());
			} catch (ParseException e) {
				System.err.println(e.getMessage());
				errMessage = "Start date is not valid. ";
			} 
		}
		if (endDate != null && endDate.trim().length() != 0) {
			java.util.Date date;
			try {
				date = dt.parse(endDate);
				dEndDate = new Date(date.getTime());
			} catch (ParseException e) {
				System.err.println(e.getMessage());
				errMessage = errMessage + " End date is not valid. ";
			} 
		}

		if (errMessage.length() == 0) {
			statusMessage = "Please wait for the result set.";
			ordersProcessor = new OrdersProcessor(company, user, dStartDate, dEndDate, country, fileFullPath);
			executor.execute(ordersProcessor);
		}
		request.setAttribute("error", errMessage);
		request.setAttribute("status", statusMessage);
		
		
		asyncContext.dispatch("/status.jsp");
        
	}
	
	private void writeToResponse(AsyncEvent event, String message) throws IOException {
        ServletResponse response = event.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.write(message);
    }


}
