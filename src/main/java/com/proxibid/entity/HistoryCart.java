package com.proxibid.entity;

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
public class HistoryCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String bidderId;

	@OneToMany(targetEntity = BidderHistoryItem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "history_item_id", referencedColumnName = "id")
	private List<BidderHistoryItem> historyItems;

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

	public List<BidderHistoryItem> getHistoryItems() {
		return historyItems;
	}

	public void setHistoryItems(List<BidderHistoryItem> historyItems) {
		this.historyItems = historyItems;
	}
}
