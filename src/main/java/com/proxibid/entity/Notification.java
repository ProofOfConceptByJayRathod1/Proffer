package com.proxibid.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String eventTitle;
	private String imageName;
	private String message;
	private LocalDateTime createdAt;
	private boolean isRead;
	private LocalDateTime notifyAt;
	private LocalTime notifyTime;
	private LocalDate notifyDate;
	private String userId;
	private Long eventId;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime getNotifyAt() {
		return notifyAt;
	}

	public void setNotifyAt(LocalDateTime notifyAt) {
		this.notifyAt = notifyAt;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public LocalTime getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(LocalTime notifyTime) {
		this.notifyTime = notifyTime;
	}

	public LocalDate getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(LocalDate notifyDate) {
		this.notifyDate = notifyDate;
	}
}
