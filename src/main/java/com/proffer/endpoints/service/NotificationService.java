package com.proffer.endpoints.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proffer.endpoints.entity.Notification;
import com.proffer.endpoints.repository.NotificationRepository;

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

}
