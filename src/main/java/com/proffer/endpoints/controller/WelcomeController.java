package com.proffer.endpoints.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.proffer.endpoints.entity.Auction;
import com.proffer.endpoints.entity.AuthRequest;
import com.proffer.endpoints.entity.Catalog;
import com.proffer.endpoints.repository.AuctionRepository;
import com.proffer.endpoints.service.CatalogService;
import com.proffer.endpoints.service.CategoryService;
import com.proffer.endpoints.util.DateFormatter;
import com.proffer.endpoints.util.JwtUtil;
import com.proffer.endpoints.util.ListUtils;

@Controller
@CrossOrigin(origins = "*")
public class WelcomeController {

	@Autowired
	private CategoryService categoryservice;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	AuctionRepository auctionRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

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

		List<List<Catalog>> listOfListOfCatalog = ListUtils.chunkList(auctionRepository.findAll().get(0).getItems(), 4);

		model.addAttribute("auctionFourItems", listOfListOfCatalog.get(0));
		model.addAttribute("auctionItems", listOfListOfCatalog);
		model.addAttribute("catalogFiveItems", catalogService.getRandomFive());
		model.addAttribute("upcomingAuctions", auctionRepository.findFirstThree());

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

		auctionRepository.findAllByCategoryContaining(category.trim().toLowerCase()).stream().forEach((s) -> {
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

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String generateToken(@ModelAttribute AuthRequest authRequest, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println(authRequest.getUserName());
		System.out.println(authRequest.getPassword());
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");

		}

		System.out.println(jwtUtil.generateToken(authRequest.getUserName()));

		Cookie cookie = new Cookie("token", jwtUtil.generateToken(authRequest.getUserName()));
		cookie.setMaxAge(60 * 60 * 10);
		response.addCookie(cookie);
		// HttpSession session = request.getSession();
		// session.setAttribute("token",
		// jwtUtil.generateToken(authRequest.getUserName()));
		// response.sendRedirect("/welcome");
		return "auctioneer-welcome";

	}

	@RequestMapping(value = "/logout")
	public String bidderLogout(HttpServletRequest request, HttpServletResponse response) {

		Cookie cookie = new Cookie("token", "");
		cookie.setMaxAge(0); // expires in 10 minutes
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		return "redirect:/proxibid.com";
	}

	@RequestMapping(value = "/public/updateEventStaus/{eventId}")
	@ResponseBody
	public String updateEventStatus(@PathVariable int eventId) {
		System.out.println("Event updated successfuly");
		return "Success";
	}

}
