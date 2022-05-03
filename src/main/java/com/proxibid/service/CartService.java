package com.proxibid.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxibid.entity.Auction;
import com.proxibid.entity.BidderCart;
import com.proxibid.entity.CartItem;
import com.proxibid.entity.LiveBid;
import com.proxibid.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private CartItemService cartItemService;

	public BidderCart findByBidderId(String bidderId) {
		return cartRepository.findByBidderId(bidderId).orElse(null);
	}

	public BidderCart save(BidderCart cart) {
		return cartRepository.save(cart);
	}

	public BidderCart prepareCart(LiveBid bid) {
		BidderCart cart = findByBidderId(bid.getBidderId());
		if (cart == null) {

			Auction auction = auctionService.findAuctionCategoryTitleAndSellerIdById(bid.getAuctionId());
			CartItem cartItem = cartItemService.prepareCartItem(bid, auction);
			// save cart item
			cartItemService.save(cartItem);

			// create new cart
			cart = new BidderCart();
			cart.setBidderId(bid.getBidderId());
			cart.setCartItems(Arrays.asList(cartItem));
			cart.setTotalAmount(bid.getCurrentBidValue());
		} else {

			Auction auction = auctionService.findAuctionCategoryTitleAndSellerIdById(bid.getAuctionId());
			CartItem cartItem = cartItemService.prepareCartItem(bid, auction);
			// update cart

			double totalPrice = cart.getCartItems().stream().map(x -> x.getPrice()).reduce(0, (i1, i2) -> i1 + i2);

			cart.setTotalAmount(totalPrice);
			List<CartItem> cartItems = cart.getCartItems();
			cartItems.add(cartItem);
			cart.setCartItems(cartItems);
		}

		return cart;
	}

	public BidderCart prepareAndSaveCart(LiveBid bid) {
		BidderCart cart = findByBidderId(bid.getBidderId());
		if (cart == null) {

			Auction auction = auctionService.findAuctionCategoryTitleAndSellerIdById(bid.getAuctionId());
			CartItem cartItem = cartItemService.prepareCartItem(bid, auction);
			// save cart item
			cartItemService.save(cartItem);

			// create new cart
			cart = new BidderCart();
			cart.setBidderId(bid.getBidderId());
			cart.setCartItems(Arrays.asList(cartItem));
			cart.setTotalAmount(bid.getCurrentBidValue());
		} else {

			Auction auction = auctionService.findAuctionCategoryTitleAndSellerIdById(bid.getAuctionId());
			CartItem cartItem = cartItemService.prepareCartItem(bid, auction);
			// update cart

			double totalPrice = cart.getCartItems().stream().map(x -> x.getPrice()).reduce(0, (i1, i2) -> i1 + i2);

			cart.setTotalAmount(totalPrice);
			List<CartItem> cartItems = cart.getCartItems();
			cartItems.add(cartItem);
			cart.setCartItems(cartItems);
		}

		cartRepository.save(cart);
		return cart;
	}
}
