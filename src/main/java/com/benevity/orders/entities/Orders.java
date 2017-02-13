package com.benevity.orders.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * @author Jasminka Mancevska
 *
 */
@XmlRootElement(name = "orders")
@XmlAccessorType (XmlAccessType.FIELD)
public class Orders implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "order")
    private List<Order> orders = null;

	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
}