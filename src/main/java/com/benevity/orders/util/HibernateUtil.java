/**
 * 
 */
package com.benevity.orders.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @author Jasminka Mancevska
 *
 * A helper class that takes care of startup and makes accessing 
 * the org.hibernate.SessionFactory more convenient. 
 *
 */
public class HibernateUtil
{ 
	private static final SessionFactory sessionFactory;
	
	// Static initializer
	static
	{
		try
		{
			// Create the SessionFactory from hibernate.cfg.xml
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		}
		catch (Throwable ex)
		{
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
