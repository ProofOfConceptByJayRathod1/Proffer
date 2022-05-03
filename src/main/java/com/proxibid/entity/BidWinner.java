package com.proxibid.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BidWinner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String bidderId;
	private long eventNo;
	private long itemId;
	private long amount;
	private LocalDateTime timestamp;

	public BidWinner(String bidderId, long eventNo, long itemId, long amount) {
		super();
		this.bidderId = bidderId;
		this.eventNo = eventNo;
		this.itemId = itemId;
		this.amount = amount;
	}

	public BidWinner() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBidderId() {
		return bidderId;
	}

	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}

	public long getEventNo() {
		return eventNo;
	}

	public void setEventNo(long eventNo) {
		this.eventNo = eventNo;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
