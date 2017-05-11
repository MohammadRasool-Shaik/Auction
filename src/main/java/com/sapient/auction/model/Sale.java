/**
 * 
 */
package com.sapient.auction.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.sapient.auction.enumerator.SaleStatus;

/**
 * Sale table entity to store sale/auction details.
 * 
 * @author mshai9
 *
 */
@Entity
@Table(indexes = { @Index(name = "IDX_SALEX1", columnList = "productName, category") })
public class Sale extends BaseEntity {

	private static final long serialVersionUID = -6383848448656300979L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer saleId;

	@NotNull
	@Min(value = 0)
	@Column(name = "base_price")
	private Double basePrice;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1)
	private SaleStatus saleStatus;

	private String productName;

	@Lob
	private String productDescription;

	private String category;

	@Lob
	@Column(name = "product_image")
	private byte[] photo;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	/**
	 * @return the saleId
	 */
	public Integer getSaleId() {
		return saleId;
	}

	/**
	 * @param saleId
	 *            the saleId to set
	 */
	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @return the saleStatus
	 */
	public SaleStatus getSaleStatus() {
		return saleStatus;
	}

	/**
	 * @param saleStatus
	 *            the saleStatus to set
	 */
	public void setSaleStatus(SaleStatus saleStatus) {
		this.saleStatus = saleStatus;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 
	 * @return the basePrice
	 */
	public Double getBasePrice() {
		return basePrice;
	}

	/**
	 * 
	 * @param basePrice
	 *            set the base price
	 */
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription
	 *            the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
