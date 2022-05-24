package com.proxibid.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.proxibid.entity.Bidder;
import com.proxibid.entity.Catalog;
import com.proxibid.service.AuctionService;
import com.proxibid.service.BidderService;
import com.proxibid.service.CatalogService;
import com.proxibid.service.CategoryService;
import com.proxibid.service.MailSenderService;
import com.proxibid.util.CookieUtil;
import com.proxibid.util.ListUtils;

@Controller
@CrossOrigin(origins = "*")
public class WelcomeController {

	@Autowired
	private CategoryService categoryservice;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private AuctionService auctionService;

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/auctionimage";

	public static String uploadDirectoryForCatalog = System.getProperty("user.dir") + "/src/main/webapp/catalogimage";

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {

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
		return "login";
	}

	@RequestMapping("/")
	public String landingPage() {
		return "redirect:/proxibid.com";
	}

	@RequestMapping("/proxibid.com")
	public String homePage(Model model) {

		model.addAttribute("categories", categoryservice.getAllCategories());

		model.addAttribute("catalogItems", catalogService.getFirstEight());

		List<List<Catalog>> listOfListOfCatalog = ListUtils.chunkList(catalogService.getFirstEight(), 4);
		model.addAttribute("auctionFourItems", listOfListOfCatalog.get(0));
		model.addAttribute("auctionItems", listOfListOfCatalog);
		model.addAttribute("catalogFiveItems", catalogService.getRandomFive());
		model.addAttribute("upcomingAuctions", auctionService.findUpcomingEvents());
		return "index";
	}

	@RequestMapping("/proxibid.com/ViewCategory")
	public String viewcategory(@RequestParam(required = false) String category,
			@RequestParam(required = false) String keyword, Model model) {

		if (keyword != null) {
			model.addAttribute("categories", categoryservice.getAllCategories());
			model.addAttribute("categorizedList", catalogService.findByKeyword(keyword));
			model.addAttribute("category", "All");
			model.addAttribute("keyword", keyword);
			return "view-category";
		}

		if (category.equals("all")) {
			model.addAttribute("categorizedList", catalogService.getAll());
			model.addAttribute("category", "All");
			model.addAttribute("categories", categoryservice.getAllCategories());
			return "view-category";
		}

		List<Catalog> categorizedList = new ArrayList<>();

		auctionService.findAllUpcomingByCategoryContaining(category.trim().toLowerCase()).stream().forEach((s) -> {
			categorizedList.addAll(s.getItems());
		});
		model.addAttribute("categories", categoryservice.getAllCategories());
		model.addAttribute("categorizedList", categorizedList);
		model.addAttribute("category", category);
		return "view-category";
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(2000000);
		return multipartResolver;
	}

	@RequestMapping(value = "/logout")
	public String bidderLogout(HttpServletRequest request, HttpServletResponse response) {

		Cookie cookie = new Cookie("token", "");
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		return "redirect:/proxibid.com";
	}
}
