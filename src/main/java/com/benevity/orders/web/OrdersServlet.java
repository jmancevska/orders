package com.benevity.orders.web;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.benevity.orders.AsyncOrdersProcessor;

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
		

		response.setContentType("text/html;charset=UTF-8");
		final AsyncContext asyncContext = request.startAsync();
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");
        //executor.execute(new AsyncOrdersProcessor(aCtx));
		
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
				e.printStackTrace();
				errMessage = "Start date is not valid. ";
			} 
		}
		if (endDate != null && endDate.trim().length() != 0) {
			java.util.Date date;
			try {
				date = dt.parse(endDate);
				dEndDate = new Date(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				errMessage = errMessage + " End date is not valid. ";
			} 
		}

		if (errMessage.length() == 0) {
			statusMessage = "Please wait for the result set.";
			executor.execute(new AsyncOrdersProcessor(company, user, dStartDate, dEndDate, country));
		}
		request.setAttribute("error", errMessage);
		request.setAttribute("status", statusMessage);
		
		
		asyncContext.dispatch("/status.jsp");
		//asyncContext.complete();
        
	}

}
