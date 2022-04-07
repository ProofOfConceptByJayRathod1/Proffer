package com.proffer.endpoints.entity;

public class ReturnBid {

	private int rbid;
	private long itemId;

	
	public ReturnBid(int rbid, long itemId) {
		super();
		this.rbid = rbid;
		this.itemId = itemId;
	}



	public long getItemId() {
		return itemId;
	}



	public void setItemId(long itemId) {
		this.itemId = itemId;
	}



	public int getRbid() {
		return rbid;
	} 
 


	public ReturnBid(int rbid) {
		super();
		this.rbid = rbid;
	}

	public ReturnBid() {
		super();
	}
	
	
}
