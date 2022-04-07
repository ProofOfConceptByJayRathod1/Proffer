package com.proffer.endpoints.controller;

import java.time.LocalTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proffer.endpoints.entity.Auction;
import com.proffer.endpoints.entity.BidWinner;
import com.proffer.endpoints.entity.ReturnBid;
import com.proffer.endpoints.entity.bid;
import com.proffer.endpoints.repository.AuctionRepository;
import com.proffer.endpoints.repository.BidRepository;
import com.proffer.endpoints.repository.BidWinnerRepository;
import com.proffer.endpoints.util.JwtUtil;

@Controller
public class BidController {
    
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	AuctionRepository auctionRepository;
	@Autowired
	BidRepository bidRepository;
	@Autowired
	BidWinnerRepository bidWinnerRepository;
	
	
	@MessageMapping("/hello")
	  @SendTo("/bid/returnbid")      
	  public ReturnBid greeting(bid message) throws Exception {
//	    Thread.sleep(1000); // simulated delay
//		message.setBidTime(LocalTime.now());
//		bidRepository.save(message);
	    return new ReturnBid(message.getBidValue(), message.getItemId());
	  }  
	
	@MessageMapping("/hello1")
	  @SendTo("/bid/placebid")      
	  public bid set(bid message) throws Exception {
		System.out.println("in hello1"); 
//	    Thread.sleep(1000); // simulated delay
		message.setBidTime(LocalTime.now());
		
		bidRepository.save(message);
	    return new bid(message.getBidValue(), message.getItemId(),message.getBidderEmail());
	  }  
	
	
	@MessageMapping("/hello2")  
	@SendTo("/bid/win")     
	  public BidWinner win(BidWinner bidWinner) throws Exception {

			System.out.println("in hello3"); 
			bidWinnerRepository.save(bidWinner);
			return new BidWinner(bidWinner.getBidderId(), bidWinner.getEventNo(),bidWinner.getItemId(),bidWinner.getAmount());

	  }  
	@RequestMapping(value="/auctionhouse/bid" , method=RequestMethod.GET)
	public String getbid()
	{
		return "Auctioneer_bid";
	}
	
	@RequestMapping(value="/auctionhouse/bidtest" , method=RequestMethod.GET)
	public String bidtest()
	{
		return "bidding-test";
	}
	
	 //------------------live auction ------------
    @RequestMapping(value="/bidder/live-auction/{eventNo}" , method=RequestMethod.GET)
    public String liveAuctionPost(@PathVariable("eventNo") long eventNo, Model model,HttpServletRequest httpServletRequest) {

    	
    	
    	model.addAttribute("items", auctionRepository.findByeventNo(eventNo));//items will have the list of items so will hagve to implement foreeach lopp in jsp page
    	Auction a = (Auction) auctionRepository.findByeventNo(eventNo);
    	model.addAttribute("catalog", a.getItems());
    	model.addAttribute("eventNo",eventNo);
    	String authorizationHeader = null;
		Cookie[] cookies=httpServletRequest.getCookies();
		
		for(Cookie c :cookies )
		{
			if(c.getName().equals("token"))
			{
				authorizationHeader=c.getValue();
			}
		}
		model.addAttribute("bidderEmail",jwtUtil.extractUsername(authorizationHeader));
    	//model.addAttribute("bidderId", a);
    	
    	
    	return "bidder-live-auction";
    }

    @RequestMapping(value="/bidder/live-auction" , method=RequestMethod.POST)
    public String liveAuctionGet(Model model) {
    	return "bidder-live-auction";
    }
	      
    @RequestMapping(value="/bidder/bidwinner" , method=RequestMethod.POST)
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

