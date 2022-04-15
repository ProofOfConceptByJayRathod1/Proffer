package com.proffer.endpoints.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table
@Entity
public class BidderCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String bidderId;
	private double totalAmount;

	@OneToMany(targetEntity = BidderCartItem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_item_id", referencedColumnName = "id")
	private List<BidderCartItem> cartItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBidderId() {
		return bidderId;
	}

	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}

	public List<BidderCartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<BidderCartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "BidderCart [id=" + id + ", bidderId=" + bidderId + ", totalAmount=" + totalAmount + ", cartItems="
				+ cartItems + "]";
	}

}
