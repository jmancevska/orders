package com.benevity.orders.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Jasminka Mancevska
 * 
 *         A filter that allows access to the XML file for that session, and if
 *         the file name doesn't match the session ID, it forwards to no access
 *         page.
 * 
 */
public class FileFilter implements Filter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
			String sessionId = ((HttpServletRequest) request)
					.getRequestedSessionId();
			String requestURI = httpServletRequest.getRequestURI();
			try {
				String fileName = requestURI
						.substring(((HttpServletRequest) request)
								.getRequestURI().lastIndexOf('/') + 1);

				if (!(sessionId + ".xml").equals(fileName)) {
					httpServletRequest.getServletContext()
							.getRequestDispatcher("/noaccess.jsp")
							.forward(request, response);
					return;

				}
			} 
			catch (IndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
				request.setAttribute("error", "Error");
				httpServletRequest.getServletContext()
				.getRequestDispatcher("/status.jsp")
				.forward(request, response);
				return;

			}

		}
		chain.doFilter(request, response);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
