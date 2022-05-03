package com.proffer.endpoints.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proffer.endpoints.entity.Auction;
import com.proffer.endpoints.entity.Notification;
import com.proffer.endpoints.service.AuctionService;
import com.proffer.endpoints.service.NotificationService;
import com.proffer.endpoints.util.CookieUtil;
import com.proffer.endpoints.util.NotificationMessage;

@Controller
@RequestMapping("/public")
public class NotificationController {

	private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private AuctionService auctionService;

	@ResponseBody
	@PostMapping("/createNotification")
	public String createNotification(@RequestParam Long eventNo, HttpServletRequest request) {
		String userId = CookieUtil.getCookieByName(request, "username");

		Auction auction = auctionService.findByeventNo(eventNo);

		Notification notification = new Notification();
		notification.setCreatedAt(LocalDateTime.now());
		notification.setMessage(NotificationMessage.EVENT_STARTED);
		notification.setUserId(userId);
		notification.setNotifyAt(auction.getDate());

		notificationService.create(notification);

		return "Notification added succesfully!";

	}

	@MessageMapping("/user/alert")
	public String publishNotification(Principal principal) throws Exception {
		log.warn("Alert sent");
		return "Feed  refreshed successfully!";
	}

}
