package com.benevity.orders.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


/**
 * @author Jasminka Mancevska
 *
 */
@Entity
@Table (name = "orders")
@XmlRootElement(name = "order")
@XmlSeeAlso({OrderDetail.class, ShippingInformation.class})
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name="order_number", nullable = false, unique = true)
	private String orderNumber;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
	private User user;

	@Column(name="country", nullable = false)
	private String country;
	
	@Column(name="status", nullable = false)
	private String status;
	
	@Column(name="created_timestamp", nullable = false)
	private long createdTimestamp = System.currentTimeMillis();
	
	@Column(name="updated_timestamp", nullable = false)
	private long updatedTimestamp = System.currentTimeMillis();
	
	
    @OneToMany(mappedBy = "order", cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
	private List<OrderDetail> orderDetails;
    
    
    @OneToOne(mappedBy = "order", cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    private ShippingInformation shippingInformation;
    

	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	@XmlAttribute
	public void setId(BigInteger id) {
		this.id = id;
	}


	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}


	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the shippingInformation
	 */
	public ShippingInformation getShippingInformation() {
		return shippingInformation;
	}


	/**
	 * @param shippingInformation the shippingInformation to set
	 */
	public void setShippingInformation(ShippingInformation shippingInformation) {
		this.shippingInformation = shippingInformation;
	}


	/**
	 * @return the createdTimestamp
	 */
	public long getCreatedTimestamp() {
		return createdTimestamp;
	}


	/**
	 * @param createdTimestamp the createdTimestamp to set
	 */
	public void setCreatedTimestamp(long createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}


	/**
	 * @return the updatedTimestamp
	 */
	public long getUpdatedTimestamp() {
		return updatedTimestamp;
	}


	/**
	 * @param updatedTimestamp the updatedTimestamp to set
	 */
	public void setUpdatedTimestamp(long updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}


	/**
	 * @return the orderDetails
	 */
	@XmlElementWrapper(name = "orderDetailList")
	@XmlElement(name = "orderDetail")
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}


	/**
	 * @param orderDetails the orderDetails to set
	 */
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}


	/**
	 * Method that calculates the total cost of the order,
	 * based on the quantity and the price of the individual items included in the order.
	 * 
	 * @return
	 */
	@XmlElement(name = "totalCost")  
	public long getTotalCost() {
		
		long total = 0;
		
		for (int i = 0; i < getOrderDetails().size(); i++) {
			total = total + getOrderDetails().get(i).getQuantity() * getOrderDetails().get(i).getItem().getPrice();
		}
		
		return total;
	}

}
