package com.benevity.orders.services;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.benevity.orders.entities.User;
import com.benevity.orders.util.HibernateUtil;

/**
 * @author Jasminka Mancevska
 *
 */
public class UserService {
	
	/**
	 * Returns a list of all the users in the system.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<User> listUsers() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String sqlStr = "from User user ";
		Query query = null;

		try
		{
			// Do transaction
			session.beginTransaction();
			
			// Create Query object
			
			query = session.createQuery(sqlStr);

			List<User> result = query.list();
	        
	        // Commit and close the transaction
	        session.getTransaction().commit();
	        
	        return result;
		}
		catch (RuntimeException e)
		{
			System.err.println("listUsers() failed. Rolling back transaction....");
			session.getTransaction().rollback();
		    throw e;
		}

	}

	/**
	 * Returns a User based on an id
	 * 
	 * @param userId
	 * @return
	 */
	public static User listUserById(BigInteger userId)
	{
		User user = null;
		
		if (userId != null)
		{
			// Get transaction session
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				
			try
			{
				// Do transaction
				session.beginTransaction();
				// Create Query object
				String sqlStr = "from User user where user.id = ?";
				Query query = session.createQuery(sqlStr);
				query.setBigInteger(0, userId);
				user = (User) query.uniqueResult();
								
				// Commit and close the transaction
				session.getTransaction().commit();
			}
			catch (RuntimeException e)
			{
				System.err.println("listUserById() failed. Rolling back all transactions....");
				session.getTransaction().rollback();
			    throw e;
			}
		}
		
		return user;
	}

}
