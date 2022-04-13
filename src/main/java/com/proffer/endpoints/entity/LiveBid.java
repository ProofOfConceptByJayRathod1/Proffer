package com.proffer.endpoints.entity;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class LiveBid {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long auctionId;
	private LocalTime bidTime;
	private String bidderId;
	private int currentBidValue;
	private String bidStatus;

	@OneToOne(targetEntity = Catalog.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "itemId", referencedColumnName = "itemId")
	private Catalog catalog;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(long auctionId) {
		this.auctionId = auctionId;
	}

	public LocalTime getBidTime() {
		return bidTime;
	}

	public void setBidTime(LocalTime bidTime) {
		this.bidTime = bidTime;
	}

	public String getBidderId() {
		return bidderId;
	}

	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}

	public int getCurrentBidValue() {
		return currentBidValue;
	}

	public void setCurrentBidValue(int currentBidValue) {
		this.currentBidValue = currentBidValue;
	}

	public String getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

}
