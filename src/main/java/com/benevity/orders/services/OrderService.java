package com.benevity.orders.services;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.benevity.orders.entities.Company;
import com.benevity.orders.entities.Order;
import com.benevity.orders.entities.User;
import com.benevity.orders.util.HibernateUtil;

/**
 * @author Jasminka Mancevska
 *
 */
public class OrderService {
	
	@SuppressWarnings("unchecked")
	public static List<Order> listOrders(Company company, User user, Date startDate, Date endDate, String country) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String sqlStr = "from Order order ";
		Query query = null;
		HashMap<String, Integer> conditions = new HashMap<String, Integer>();
		String companyCond = " order.user.company.id = ? ";
		String userCond = " order.user.id = ? ";
		String startDateCond = " order.createdTimestamp >= ? ";
		String endDateCond = " order.createdTimestamp <= ? ";
		String countryCond = " order.country = ? ";
		
		try
		{
			// Do transaction
			session.beginTransaction();
			
			if (company != null || user != null || startDate != null || endDate != null || country != null) {
				sqlStr = sqlStr + " where ";
			}
			
			// Create Query object
			if (company != null) {
				conditions.put(companyCond, conditions.size());
			}
			
			if (user != null) {
				conditions.put(userCond, conditions.size());
			}
		
			if (startDate != null) {
				conditions.put(startDateCond, conditions.size());
			}
			
			if (endDate != null) {
				conditions.put(endDateCond, conditions.size());
			}

			if (country != null) {
				conditions.put(countryCond, conditions.size());
			}
			
			sqlStr = sqlStr + StringUtils.join(conditions.keySet(), " and ");
			
			query = session.createQuery(sqlStr);

			if (company != null) {
				query.setBigInteger(conditions.get(companyCond), company.getId());
			}
			if (user != null) {
				query.setBigInteger(conditions.get(userCond), user.getId());
			}
			if (startDate != null) {
				query.setDate(conditions.get(startDateCond), startDate);
			}
			if (endDate != null) {
				query.setDate(conditions.get(endDateCond), endDate);
			}
			if (country != null) {
				query.setString(conditions.get(countryCond), country);
			}

			
			List<Order> result = query.list();
			
	        
	        // Commit and close the transaction
	        session.getTransaction().commit();
	        
	        return result;
		}
		catch (RuntimeException e)
		{
			System.err.println("listOrders() failed. Rolling back transaction....");
			session.getTransaction().rollback();
		    throw e;
		}
		
	}

}
