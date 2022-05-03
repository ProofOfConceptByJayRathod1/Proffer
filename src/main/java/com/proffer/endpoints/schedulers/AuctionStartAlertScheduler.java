package com.proffer.endpoints.schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proffer.endpoints.entity.Notification;
import com.proffer.endpoints.service.AuctionService;
import com.proffer.endpoints.service.NotificationService;

@Component
public class AuctionStartAlertScheduler {

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private NotificationService notificationService;

	private SimpMessagingTemplate messagingTemplate;

	@Scheduled(cron = "0 12 14 ? * *")
	public void scheduleAuctionAlerts() {

		notificationService.findTodaysAlerts().forEach(n -> {
			scheduler.scheduleTodaysAuctionAlert(() -> {
				messagingTemplate.convertAndSendToUser(n.getUserId(), "/user/alert", n.getMessage());
			}, n.getNotifyAt());

		});
	}
}
