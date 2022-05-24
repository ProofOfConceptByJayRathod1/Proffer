package com.proxibid.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

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
import org.springframework.web.multipart.MultipartFile;

import com.proxibid.entity.Auction;
import com.proxibid.entity.Auctioneer;
import com.proxibid.entity.Catalog;
import com.proxibid.service.AuctionService;
import com.proxibid.service.CatalogService;
import com.proxibid.service.CategoryService;
import com.proxibid.service.LiveBidService;
import com.proxibid.service.AuctioneerService;
import com.proxibid.util.AuctionStatus;
import com.proxibid.util.CookieUtil;
import com.proxibid.util.DateFormatter;
import com.proxibid.util.JwtUtil;
import com.proxibid.util.ROLE;

@Controller
public class AuctioneerController {

	@Autowired
	private AuctioneerService auctioneerService;

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private LiveBidService liveBidService;

	private static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/auctionimage";
	private static String uploadDirectoryForCatalog = System.getProperty("user.dir") + "/src/main/webapp/catalogimage";

	@RequestMapping(value = "/auctionhouse/signup")
	public String auctioneerSignUp(@ModelAttribute Auctioneer auctioneer) {
		return "/auctioneer/signup";
	}

	@RequestMapping(value = "/auctionhouse/signup/save")
	public String signUpAsAuctioneer(@ModelAttribute Auctioneer auctioneer, HttpServletRequest request) {
		request.setAttribute("error", null);
		if (auctioneerService.existsByEmail(auctioneer.getEmail())) {
			request.setAttribute("error", "User with same email already exixst!");
			return "auctioneer-signup";
		} else {
			auctioneer.setPassword(new BCryptPasswordEncoder().encode(auctioneer.getPassword()));
			auctioneer.setRole(ROLE.AUCTIONEER.toString());
			auctioneerService.saveSeller(auctioneer);
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/auctionhouse/addauction", method = RequestMethod.GET)
	public String getauction() {
		return "Auction";
	}

	@RequestMapping(value = "/auctionhouse/addauction", method = RequestMethod.POST)
	@ResponseBody
	public String saveStudent(@ModelAttribute Auction auction, @RequestParam("imgName") MultipartFile file) {

		String filename = file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4);
		Path fileNameAndPath = Paths.get(uploadDirectory, filename);

		try {
			Files.write(fileNameAndPath, file.getBytes());

		} catch (IOException e) {

			e.printStackTrace();
		}

		String startDate = auction.getStartDate().toString();
		String startTime = auction.getStartTime().toString();

		// merge date and time
		LocalDateTime localDateTime = DateFormatter.getFormattedLocalDateTime(startDate, startTime);

		// set local date time in default format
		auction.setDate(localDateTime);

		// change auction date format
		auction.setStartDateTime(DateFormatter.formatToFullDateTime(startDate, startTime));

		// calculate end time and save
		auction.setEndDateTime(auction.getDate().plusMinutes(auction.getDuration()));
		auction.setStatus(AuctionStatus.CREATED.toString());
		auctionService.save(auction);
		return "Save Data Successfully ! ";
	}

	@RequestMapping(value = "/auctionhouse/catalog", method = RequestMethod.POST)
	@ResponseBody
	public String saveCatalogInfo(@RequestParam("itemName") ArrayList<String> itemName,
			@RequestParam("itemImage") ArrayList<MultipartFile> file,
			@RequestParam("itemStartBid") ArrayList<Integer> itemStartBid,
			@RequestParam("itemDesc") ArrayList<String> itemDesc) throws IOException{
		
			
		int n = itemName.size();
		
		for (int i = 0; i < n; i++) {
			Catalog c = new Catalog();
			c.setItemDesc(itemDesc.get(i));
			c.setItemName(itemName.get(i));
			c.setItemStartBid(itemStartBid.get(i));
			
			MultipartFile f = file.get(i);
			String filename = f.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDirectoryForCatalog, filename);
			Files.write(fileNameAndPath, f.getBytes());
			c.setItemImage(filename);

			catalogService.save(c);
		}
		return "Saved Data in catalog Successfully ! ";
	}

	@RequestMapping(value = "/auctionhouse/auction", method = RequestMethod.GET)
	public String getfullauction(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		return "/auctioneer/create-auction";
	}

	@RequestMapping(value = "/auctionhouse/auction", method = RequestMethod.POST)
	@ResponseBody
	public String saveauction(HttpServletRequest httpServletRequest, @ModelAttribute Auction auction,
			@RequestParam("imgName") MultipartFile file1, @RequestParam("itemName") ArrayList<String> itemName,
			@RequestParam("itemImage") ArrayList<MultipartFile> file,
			@RequestParam("itemStartBid") ArrayList<Integer> itemStartBid,
			@RequestParam("itemDesc") ArrayList<String> itemDesc) {

		try {
			String filename1 = file1.getOriginalFilename();
			Path fileNameAndPath1 = Paths.get(uploadDirectory, filename1);

			auction.setImageName(file1.getOriginalFilename());
			Files.write(fileNameAndPath1, file1.getBytes());

			String startDate = auction.getStartDate().toString();
			String startTime = auction.getStartTime().toString();

			// merge date and time
			LocalDateTime localDateTime = DateFormatter.getFormattedLocalDateTime(startDate, startTime);

			// set local date time in default format
			auction.setDate(localDateTime);

			// change auction date format
			auction.setStartDateTime(DateFormatter.formatToFullDateTime(startDate, startTime));

			// calculate end time and save
			auction.setEndDateTime(auction.getDate().plusMinutes(auction.getDuration()));
			auction.setStatus(AuctionStatus.CREATED.toString());

			auction.setImageName(filename1);
			String username = CookieUtil.getCookieByName(httpServletRequest, "username");
			auction.setSellerId(jwtUtil.extractUsername(username));

			ArrayList<Catalog> catlist = new ArrayList<>();
			int n = itemName.size();
			for (int i = 0; i < n; i++) {
				Catalog c = new Catalog();
				c.setItemDesc(itemDesc.get(i));
				c.setItemName(itemName.get(i));
				c.setItemStartBid(itemStartBid.get(i));
				MultipartFile f = file.get(i);
				String filename = f.getOriginalFilename();
				Path fileNameAndPath = Paths.get(uploadDirectoryForCatalog, filename);
				try {
					Files.write(fileNameAndPath, f.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
				c.setItemImage(filename);
				catlist.add(c);
			}
			auction.setItems(catlist);

			auctionService.save(auction);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";

		}
	}

	@RequestMapping(value = "/auctionhouse/dashboard", method = RequestMethod.GET)
	public String auctioneerDashboardGet(HttpServletRequest request, Model model) {

		String username = getCurrentUsernameFromCookie(request);

		// view todays upcoming events only
		model.addAttribute("todayUpcomingAuctions", auctionService.findTodaysUpcomingEventsByUsername(username));

		// live events
		model.addAttribute("auctions", auctionService.findLiveByUsername(username));
		return "/auctioneer/dashboard";
	}

	private String getCurrentUsernameFromCookie(HttpServletRequest request) {
		return Arrays.asList(request.getCookies()).stream().filter(c -> c.getName().equals("username")).findFirst()
				.get().getValue();
	}

	@RequestMapping(value = "/auctionhouse/event/{eventno}", method = RequestMethod.GET)
	public String auctioneerEventPageGet(@PathVariable("eventno") long eventNo, Model model) {

		model.addAttribute("liveItems", liveBidService.findAllByAuctionId(eventNo));

		model.addAttribute("items", auctionService.findByeventNo(eventNo));
		Auction a = (Auction) auctionService.findByeventNo(eventNo);
		model.addAttribute("catalog", a.getItems());
		model.addAttribute("eventNo", eventNo);
		return "/auctioneer/live-auction";
	}

	@RequestMapping(value = "/auctioneer/history", method = RequestMethod.GET)
	public String history(HttpServletRequest request, Model model) {

		String username = CookieUtil.getCookieByName(request, "username");
		model.addAttribute("myAuction", auctionService.findPastAuctionByAuctioneer(username));

		return "/auctioneer/history";
	}

}
