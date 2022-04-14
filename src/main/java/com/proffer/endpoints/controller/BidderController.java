package com.proffer.endpoints.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proffer.endpoints.entity.Auction;
import com.proffer.endpoints.entity.BidWinner;
import com.proffer.endpoints.entity.Bidder;
import com.proffer.endpoints.service.AuctionService;
import com.proffer.endpoints.service.BidderService;
import com.proffer.endpoints.service.CategoryService;
import com.proffer.endpoints.service.LiveBidService;
import com.proffer.endpoints.service.SellerService;
import com.proffer.endpoints.util.JwtUtil;

@Controller
public class BidderController {

	@Autowired
	private BidderService bidderService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private SellerService sellerService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private LiveBidService liveBidService;

	@RequestMapping(value = "/bidder/signup")
	public String bidderSignUp(@ModelAttribute Bidder bidder) {
		return "bidder-signup";
	}

	@RequestMapping(value = "/bidder/signup/save")
	public String bidderSignInAfterSignUp(@ModelAttribute Bidder bidder, HttpServletRequest request) {
		request.setAttribute("error", null);
		if (bidderService.bidderExistsByEmail(bidder.getBidderEmail())) {
			request.setAttribute("error", "User with same email already exixst!");
			return "bidder-signup";
		} else {
			bidder.setBidderPassword(new BCryptPasswordEncoder().encode(bidder.getBidderPassword()));
			bidderService.bidderSignUp(bidder);
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/bidder/dashboard", method = RequestMethod.GET)
	public String bidderdashboard(Model model) {

		model.addAttribute("upcomingTodaysAuctions", auctionService.findTodaysUpcomingEvents());
		model.addAttribute("auctions", auctionService.findAllLiveEvents());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "dashboard";
	}

	@RequestMapping(value = "/bidder/dashboard/")
	public String postitembycategories(@RequestParam("checkbox") ArrayList<String> selectedCategory, Model model) {
		model.addAttribute("upcomingTodaysAuctions", auctionService.findTodaysUpcomingEvents());
		model.addAttribute("auctions", auctionService.findAllLiveEvents());
		List<Auction> filteredAuctions = new ArrayList<>();
		for (int i = 0; i < selectedCategory.size(); i++) {
			filteredAuctions.addAll(auctionService.getAllLiveEventsByCategory(selectedCategory.get(i)));
		}
		model.addAttribute("auctions", filteredAuctions);
		model.addAttribute("categories", categoryService.getAllCategories());

		return "dashboard";
	}

	@RequestMapping(value = "/bidder/event/{eventno}", method = RequestMethod.GET)
	public String bidderEventPageGet(@PathVariable("eventno") long eventNo, Model model) {

		Auction current_auction = auctionService.findByeventNo(eventNo);
		model.addAttribute("items", current_auction);

		model.addAttribute("eventNumber", eventNo);

		model.addAttribute("auctionHouseName", sellerService.findByEmail(current_auction.getSellerId()).getHouseName());

		Auction auction = auctionService.findByeventNo(eventNo);
		model.addAttribute("catalog", auction.getItems());
		return "event";
	}

	@RequestMapping(value = "/bidder/event/", method = RequestMethod.POST)
	public String bidderEventPagePost() {
		return "event";
	}

	@RequestMapping(value = "/bidder/login", method = RequestMethod.GET)
	public String bidderLogin(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		String errorMessage = null;
		if (session != null) {
			AuthenticationException ex = (AuthenticationException) session
					.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (ex != null) {
				errorMessage = ex.getMessage();
			}
		}
		model.addAttribute("error", errorMessage);
		return "bidder-login";
	}

	@RequestMapping(value = "/bidder/live-auction/{eventNo}", method = RequestMethod.GET)
	public String liveAuctionPost(@PathVariable("eventNo") long eventNo, Model model,
			HttpServletRequest httpServletRequest) {

		model.addAttribute("liveItems", liveBidService.findAllByAuctionId(eventNo));

		model.addAttribute("items", auctionService.findByeventNo(eventNo));

		Auction a = (Auction) auctionService.findByeventNo(eventNo);
		model.addAttribute("catalog", a.getItems());
		model.addAttribute("eventNo", eventNo);
		String authorizationHeader = null;
		Cookie[] cookies = httpServletRequest.getCookies();
		String username = null;
		for (Cookie c : cookies) {
			if (c.getName().equals("token")) {
				authorizationHeader = c.getValue();
			}
			if (c.getName().equals("username")) {
				username = c.getValue();
			}
		}
		model.addAttribute("bidderEmail", jwtUtil.extractUsername(authorizationHeader));
		model.addAttribute("bidderId", username);
		return "bidder-live-auction";
	}

	@RequestMapping(value = "/bidder/live-auction", method = RequestMethod.POST)
	public String liveAuctionGet(Model model) {
		return "bidder-live-auction";
	}

	@RequestMapping(value = "/bidder/bidwinner", method = RequestMethod.POST)
	@ResponseBody
	public String winnerPost(BidWinner bidWinner) {
		System.out.println("ok winner");

		System.out.println(bidWinner.getAmount());
		System.out.println(bidWinner.getBidderId());
		System.out.println(bidWinner.getEventNo());
		System.out.println(bidWinner.getItemId());
		return "success";
	}

}
