package com.benevity.orders;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import com.benevity.orders.entities.Company;
import com.benevity.orders.entities.Order;
import com.benevity.orders.entities.Orders;
import com.benevity.orders.entities.User;
import com.benevity.orders.services.CompanyService;
import com.benevity.orders.services.OrderService;
import com.benevity.orders.services.UserService;


/**
 * @author Jasminka Mancevska
 * 
 */
public class AsyncOrdersProcessor implements Runnable {
	// private AsyncContext asyncContext;
	private String company;
	private String user;
	private Date startDate;
	private Date endDate;
	private String country;
	
	private List<Order> orders;

//	public AsyncOrdersProcessor() {
//	}

	public AsyncOrdersProcessor(String company, String user, Date startDate,
			Date endDate, String country) {
		this.company = company;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.country = country;
	}

	// public AsyncOrdersProcessor(AsyncContext asyncCtx) {
	// this.asyncContext = asyncCtx;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		Company oCompany = null;
		User oUser = null;
		
		try {

			if (company != null && !company.equals("0")) {
				BigInteger companyId = new BigInteger(company);
				oCompany = CompanyService.listCompanyById(companyId);
				if (oCompany == null) {
					// Report an error
				}
			}
			if (user != null && !user.equals("0")) {
				BigInteger userId = new BigInteger(user);
				oUser = UserService.listUserById(userId);
				if (oUser == null) {
					// Report an error
				}
			}
			

			if (country != null && country.trim().length() == 0) {
				country = null;
			}
		}
		catch (NumberFormatException e) {
			// Report an error
			System.err.println(e.getLocalizedMessage());
		}

		// Get the data from the DB for the specified parameters
		orders = OrderService.listOrders(oCompany, oUser, startDate, endDate, country);
		
		if (orders != null ) {
			// Create the XML compatible list
			Orders ordersList = new Orders();
			ordersList.setOrders(orders);

			XmlConvertor.toXml(ordersList, System.out);
		}

	}

}
