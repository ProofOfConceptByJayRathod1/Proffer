package com.proffer.endpoints.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BidderHistoryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String bidderId;
	private String name;
	private String description;
	private String image;
	private String category;
	private String auctionTitle;
	private LocalDateTime timestamp;
	private Integer bidValue;
}
