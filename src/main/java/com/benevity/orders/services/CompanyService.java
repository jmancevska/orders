package com.benevity.orders.services;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.benevity.orders.entities.Company;
import com.benevity.orders.util.HibernateUtil;

/**
 * @author Jasminka Mancevska
 *
 */
public class CompanyService {
	
	/**
	 * Returns a list of all the companies in the system.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Company> listCompanies() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String sqlStr = "from Company company ";
		Query query = null;

		try
		{
			// Do transaction
			session.beginTransaction();
			
			// Create Query object
			
			query = session.createQuery(sqlStr);

			List<Company> result = query.list();
	        
	        // Commit and close the transaction
	        session.getTransaction().commit();
	        
	        return result;
		}
		catch (RuntimeException e)
		{
			System.err.println("listCompanies() failed. Rolling back transaction....");
			session.getTransaction().rollback();
		    throw e;
		}

	}

	/**
	 * Returns a Company based on an id
	 * 
	 * @param companyId
	 * @return
	 */
	public static Company listCompanyById(BigInteger companyId)
	{
		Company company = null;
		
		if (companyId != null)
		{
			// Get transaction session
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				
			try
			{
				// Do transaction
				session.beginTransaction();
				// Create Query object
				String sqlStr = "from Company company where company.id = ?";
				Query query = session.createQuery(sqlStr);
				query.setBigInteger(0, companyId);
				company = (Company) query.uniqueResult();
								
				// Commit and close the transaction
				session.getTransaction().commit();
			}
			catch (RuntimeException e)
			{
				System.err.println("listCompanyById() failed. Rolling back all transactions....");
				session.getTransaction().rollback();
			    throw e;
			}
		}
		
		return company;
	}

}
