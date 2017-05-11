package com.sapient.auction.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BidDTO {

	private Long id;

	@NotNull
	@Min(value = 0)
	private Double amount;

	@Pattern(regexp = "[A-Za-z_.0-9-]+@{1}[a-z]+([.]{1}[a-z]{2,4})+", message = " Allowed alphabets (capital and small), 0 to 9, \".\", \"_\" and \"-\" before \"@\". Then exactly one \"@\" is allowed in whole email then small letters (1 or more) then multiple set of \".\" and alphabets (2 to 4)")
	private String buyerEmail;

	@NotNull
	private Integer saleId;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BidDTO [id=" + id + ", amount=" + amount + ", buyerEmail=" + buyerEmail + ", saleId=" + saleId + "]";
	}

}
