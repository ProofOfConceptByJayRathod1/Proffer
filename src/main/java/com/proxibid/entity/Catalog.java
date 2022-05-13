package com.proxibid.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Catalog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itemId;
	private String itemImage;
	private String itemName;
	private int itemStartBid;
	private String itemDesc;
	private String bidStatus;

	@OneToOne(targetEntity = BidWinner.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "winnerId", referencedColumnName = "id")
	private BidWinner winner;

	public Catalog(long itemId, String itemImage, String itemName, int itemStartBid, String itemDesc) {
		super();
		this.itemId = itemId;
		this.itemImage = itemImage;
		this.itemName = itemName;
		this.itemStartBid = itemStartBid;
		this.itemDesc = itemDesc;
	}

	public Catalog() {
		super();
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemStartBid() {
		return itemStartBid;
	}

	public void setItemStartBid(int itemStartBid) {
		this.itemStartBid = itemStartBid;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	public BidWinner getWinner() {
		return winner;
	}

	public void setWinner(BidWinner winner) {
		this.winner = winner;
	}
}