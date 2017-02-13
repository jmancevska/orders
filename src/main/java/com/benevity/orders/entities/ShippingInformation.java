package com.benevity.orders.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Jasminka Mancevska
 *
 */
@Entity
@Table (name = "shipping_information")
@XmlRootElement(name = "shippingInformation")
public class ShippingInformation implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

	@Column(name="shipping_contact", nullable = false)
	private String shippingContact;

	@Column(name="street_address", nullable = false)
	private String streetAddress;

	@Column(name="city", nullable = false)
	private String city;

	@Column(name="province", nullable = false)
	private String province;

	@Column(name="country", nullable = false)
	private String country;
    
	@Column(name="postcode", nullable = false)
	private String postcode;
    

	@Column(name="created_timestamp", nullable = false)
	private long createdTimestamp = System.currentTimeMillis();
	
	@Column(name="updated_timestamp", nullable = false)
	private long updatedTimestamp = System.currentTimeMillis();

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
	 * @return the order
	 */
	@XmlTransient
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the shippingContact
	 */
	public String getShippingContact() {
		return shippingContact;
	}

	/**
	 * @param shippingContact the shippingContact to set
	 */
	public void setShippingContact(String shippingContact) {
		this.shippingContact = shippingContact;
	}

	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
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
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	

}
