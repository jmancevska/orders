The following is a short summary regarding the exercise for the Orders.

GitHub location:
	https://github.com/jmancevska/orders
	The orders.war file is checked in.

Approach:

	- Java 7
	- Tomcat 7
	- Maven 
	- Java Servlet for triggering the query from the UI.
	- Hibernate for the DB interactions.
	- JAXB for marshaling the objects into XML.

DB:

	Changes from the original model:

	- Renamed items.product_id to items.product
	- Added
	  `user_id` bigint(18) unsigned NOT NULL,
	  to the `orders`.
	  It represents the user who made the order, which can be linked to the company.
	- Altered orders.status from int(7) to varchar(100), so it's more descriptive without adding a code table for statuses.

	The following two scripts should be run first to create and populate the DB:

	orders\src\main\sql\benevity_exercise_v2.sql
	orders\src\main\sql\insert.sql

	hibernate.cfg.xml – Specifies localhost as DB server, 
	username: guest
	password: guest  
	All of which should be changed to match the environment where the app is running.

	
Assumptions:

	- All the prices in the 'items' table are in one currency.
	- The database has some data.

	
What is done:

	- Web page that sends a request to the API for getting a list of orders.
	- The database retrieval is done asynchronously.
	- Filtering the list based on input parameters.
	- Date format validation.
	- Getting the list of orders from the DB, based on the input parameters.
	- Marshaling the orders list into XML file.
	- Only one XML file can be generated per session and the download/retrieval of the file,
	  is limited only for that session.  This can be modified to be based per user/company.
	- Notifying the original request that the file is ready for download, or there was an error.

What is not done:

	- Functionality for inserting/updating/deleting data in the database.
	- Separate billing information for the users.
	- Making pretty UI.
	- UI JavaScript validation.
	- Files cleanup.
	- Automated tests.
