package com.proxibid.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxibid.entity.Notification;
import com.proxibid.repository.NotificationRepository;

@Service
public class NotificationService {

	private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

	@Autowired
	private NotificationRepository notificationRepository;

	public Notification create(Notification notification) {
		log.info("Notification created for " + notification.getUserId());
		return notificationRepository.save(notification);
	}

	public List<Notification> findTodaysAlerts() {
		return notificationRepository.findTodaysAlerts();
	}

	public List<Notification> findAllAlerts() {
		return notificationRepository.findAll();
	}

	public boolean existByEventIdAndUserId(String userId, Long eventId) {
		return notificationRepository.existsByUserIdAndEventId(userId, eventId);
	}

	public List<Notification> findTodaysByUserId(String userId) {
		return notificationRepository.findTodaysByUserId(userId, LocalTime.now());
	}

	public List<Notification> findPastByUserId(String userId) {
		return notificationRepository.findPastNotificationsByUserId(userId);
	}

}
