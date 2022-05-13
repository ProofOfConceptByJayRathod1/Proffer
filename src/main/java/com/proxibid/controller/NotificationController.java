package com.proxibid.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proxibid.entity.Auction;
import com.proxibid.entity.Notification;
import com.proxibid.schedulers.Scheduler;
import com.proxibid.service.AuctionService;
import com.proxibid.service.NotificationService;
import com.proxibid.util.CookieUtil;
import com.proxibid.util.NotificationMessage;

@Controller
@RequestMapping("/public")
public class NotificationController {

	private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@ResponseBody
	@PostMapping("/createNotification")
	public String createNotification(@RequestParam Long eventNo, HttpServletRequest request) {
		String userId = CookieUtil.getCookieByName(request, "username");

		if (notificationService.existByEventIdAndUserId(userId, eventNo)) {
			return "Notification already added. \nYou will be notified when this auction starts!";
		}

		Auction auction = auctionService.findByeventNo(eventNo);

		Notification notification = new Notification();
		notification.setCreatedAt(LocalDateTime.now());
		notification.setMessage(NotificationMessage.EVENT_STARTED);
		notification.setUserId(userId);
		notification.setNotifyAt(auction.getDate());
		notification.setEventId(eventNo);
		notification.setImageName(auction.getImageName());
		notification.setEventTitle(auction.getEventTitle());
		notification.setNotifyDate(auction.getDate().toLocalDate());
		notification.setNotifyTime(auction.getDate().toLocalTime());

		notificationService.create(notification);

		log.info("Auction alert for user " + notification.getUserId() + " scheduled at " + notification.getNotifyAt());
		scheduler.scheduleAlert(() -> {
			messagingTemplate.convertAndSend("/alert/" + notification.getUserId(),
					auction.getEventTitle() + " has started...");
		}, notification.getNotifyAt());

		return "Notification added succesfully!";

	}
}
