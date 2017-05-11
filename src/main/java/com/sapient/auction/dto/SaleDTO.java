package com.sapient.auction.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Sale DTO for holding sale details
 * 
 * @author jyengk
 *
 */
// @ValidSaleDate
public class SaleDTO {

	private Integer saleId;

	@NotNull
	private Double basePrice;

	@NotNull
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date")
	private String startDate;

	@NotNull
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date")
	private String endDate;

	@NotNull
	private String productName;

	@NotNull
	private String productDescription;

	@NotNull
	private String category;

	private String productImage;

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the productImage
	 */
	public String getProductImage() {
		return productImage;
	}

	/**
	 * @param productImage
	 *            the productImage to set
	 */
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaleDTO [saleId=" + saleId + ", basePrice=" + basePrice + ", startDate=" + startDate + ", endDate=" + endDate + ", productName=" + productName
				+ ", productDescription=" + productDescription + ", category=" + category + ", productImage=" + productImage + "]";
	}

}
