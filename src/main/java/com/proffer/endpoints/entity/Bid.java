package com.proffer.endpoints.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bidno;
	private long eventNo;
	private long itemId;
	private LocalTime bidTime;
	private String bidderEmail;
	private int bidValue;
	
	
	
	public long getItemId() {
		return itemId;
	}


	public void setItemId(long itemId) {
		this.itemId = itemId;
	}


	public Bid() {
		super();
	}


	public Bid(int bidValue,long itemId,String bidderEmail) {
		super();
		this.itemId = itemId;
		this.bidderEmail = bidderEmail;
		this.bidValue = bidValue;
	}


	public Bid(int bidno, long eventNo, long itemId, LocalTime bidTime, String bidderEmail, int bidValue) {
		super();
		this.bidno = bidno;
		this.eventNo = eventNo;
		this.itemId = itemId;
		this.bidTime = bidTime;
		this.bidderEmail = bidderEmail;
		this.bidValue = bidValue;
	}


	public long getBidno() {
		return bidno;
	}


	public void setBidno(int bidno) {
		this.bidno = bidno;
	}


	public long getEventNo() {
		return eventNo;
	}


	public void setEventNo(long eventNo) {
		this.eventNo = eventNo;
	}




	public LocalTime getBidTime() {
		return bidTime;
	}


	public void setBidTime(LocalTime bidTime) {
		this.bidTime = bidTime;
	}


	public String getBidderEmail() {
		return bidderEmail;
	}


	public void setBidderEmail(String bidderEmail) {
		this.bidderEmail = bidderEmail;
	}


	public int getBidValue() {
		return bidValue;
	}


	public void setBidValue(int bidValue) {
		this.bidValue = bidValue;
	}

	
	

	

}
