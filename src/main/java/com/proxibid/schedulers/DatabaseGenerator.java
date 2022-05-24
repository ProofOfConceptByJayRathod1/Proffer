package com.proxibid.schedulers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.proxibid.entity.Auction;
import com.proxibid.entity.Auctioneer;
import com.proxibid.entity.Bidder;
import com.proxibid.entity.Catalog;
import com.proxibid.entity.Category;
import com.proxibid.entity.LiveBid;
import com.proxibid.repository.AuctionRepository;
import com.proxibid.repository.AuctioneerRepository;
import com.proxibid.repository.BidderRepository;
import com.proxibid.repository.CatalogRepository;
import com.proxibid.repository.CategoryRepository;
import com.proxibid.repository.LiveBidRepository;
import com.proxibid.util.AuctionStatus;
import com.proxibid.util.DateFormatter;
import com.proxibid.util.LiveBidStatus;
import com.proxibid.util.ROLE;

@Component
public class DatabaseGenerator {

	@Autowired
	private BidderRepository bidderRepository;

	@Autowired
	private AuctioneerRepository auctioneerRepository;

	@Autowired
	private AuctionRepository auctionRepository;

	@Autowired
	private CatalogRepository catalogRepository;

	@Autowired
	private LiveBidRepository liveBidRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	private static final Logger log = LoggerFactory.getLogger(DatabaseGenerator.class);

	@Scheduled(cron = "00 29 14 ? * *")
	public void generateDatabase() {

		log.info("Database generator started...");
		createCategories();
		createBidders();
		createAuctioneers();
		createMikePastAuctions();
		createRagnorPastAuctions();
		// createLiveCoinCatalogItems();
		// createLiveVehicleCatalogItems();
		createRagnorLiveAuctions();
		createMikeLiveAuctions();
		createRagnorUpcomingAuctions();
		createMikeUpcomingAuctions();
		log.info("Database generator finished successfully...");
	}

	public void createCategories() {
		Arrays.asList("Art/ Antiques & Collectibles", "Benefit & Charity", "Coins & Currency", "Collector Cars",
				"Commercial Trucks", "Computers & Electronics", "Estate & Personal Property",
				"Jewelry/ Gemstones & Watches", "Vehicles/ Marine & Aviation").forEach(x -> {
					Category category = new Category();
					category.setCategoryName(x);
					categoryRepository.save(category);

				});
	}

//	Art/ Antiques & Collectibles
//	Benefit & Charity
//	Coins & Currency
//	Collector Cars
//	Commercial Trucks
//	Computers & Electronics
//	Estate & Personal Property
//	Jewelry/ Gemstones & Watches
//	Vehicles/ Marine & Aviation

	/* Todays upcoming actions */

	public void createMikeUpcomingAuctions() {

		LocalDateTime dateTime = LocalDateTime.now().plusHours(2);

		Auction mikeAuction = new Auction();
		mikeAuction.setCategory("Coins & Currency");
		mikeAuction.setDescription(
				"On June 19, Sotheby’s in New York will auction the D. Brent Pogue Collection, a set of rare coins expected to sell for $200 million.");
		mikeAuction.setEventTitle("Rare Coins Up for Auction Are Worth a Pretty Penny");
		mikeAuction.setImageName("coin_auction_banner.jpg");
		mikeAuction.setSellerId("mike@gmail.com");
		mikeAuction.setStatus(AuctionStatus.CREATED.toString());
		mikeAuction.setDuration(360);
		mikeAuction.setStartDate(dateTime.toLocalDate());
		mikeAuction.setStartTime(dateTime.toLocalTime());
		String startDate = mikeAuction.getStartDate().toString();
		String startTime = mikeAuction.getStartTime().toString();
		LocalDateTime localDateTime = DateFormatter.getFormattedLocalDateTime(startDate, startTime);
		mikeAuction.setDate(localDateTime);
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(startDate, startTime));
		mikeAuction.setEndDateTime(mikeAuction.getDate().plusMinutes(mikeAuction.getDuration()));
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction.getStartDate().toString(),
				mikeAuction.getStartTime().toString()));
		List<Catalog> items1 = createCoinCatalogItems();
		mikeAuction.setItems(items1);
		Auction auction1 = auctionRepository.save(mikeAuction);

		items1.forEach(item -> {
			LiveBid liveBid = new LiveBid();
			liveBid.setAuctionId(auction1.getEventNo());
			liveBid.setBidderId(LiveBidStatus.NONE.toString());
			liveBid.setCatalog(item);
			liveBid.setCurrentBidValue(item.getItemStartBid());
			liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
			liveBid.setSecondaryStatus(LiveBidStatus.NONE.toString());
			liveBid.setBidTime(LocalTime.now());
			liveBid.setBidDate(LocalDate.now());
			liveBidRepository.save(liveBid);
		});

		LocalDateTime dateTime2 = LocalDateTime.now().plusMinutes(5);

		Auction mikeAuction2 = new Auction();
		mikeAuction2.setCategory("Vehicles/ Marine & Aviation");
		mikeAuction2.setDescription(
				"The first dealership I owned was a Buick dealership in Irving, TX. It all happened very quickly, but that is a different True Story.");
		mikeAuction2.setEventTitle("True Stories From A Former Car Dealer");
		mikeAuction2.setImageName("vehicles_banner.png");
		mikeAuction2.setSellerId("mike@gmail.com");
		mikeAuction2.setStatus(AuctionStatus.CREATED.toString());
		mikeAuction2.setDuration(420);
		mikeAuction2.setStartDate(dateTime2.toLocalDate());
		mikeAuction2.setStartTime(dateTime2.toLocalTime());
		String startDate1 = mikeAuction2.getStartDate().toString();
		String startTime1 = mikeAuction2.getStartTime().toString();
		LocalDateTime localDateTime1 = DateFormatter.getFormattedLocalDateTime(startDate1, startTime1);
		mikeAuction2.setDate(localDateTime1);
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(startDate1, startTime1));
		mikeAuction2.setEndDateTime(mikeAuction2.getDate().plusMinutes(mikeAuction2.getDuration()));
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction2.getStartDate().toString(),
				mikeAuction2.getStartTime().toString()));
		List<Catalog> items2 = createVehicleCatalogItems();
		mikeAuction2.setItems(items2);
		Auction auction2 = auctionRepository.save(mikeAuction2);

		items2.forEach(item -> {
			LiveBid liveBid = new LiveBid();
			liveBid.setAuctionId(auction2.getEventNo());
			liveBid.setBidderId(LiveBidStatus.NONE.toString());
			liveBid.setCatalog(item);
			liveBid.setCurrentBidValue(item.getItemStartBid());
			liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
			liveBid.setSecondaryStatus(LiveBidStatus.NONE.toString());
			liveBid.setBidTime(LocalTime.now());
			liveBid.setBidDate(LocalDate.now());
			liveBidRepository.save(liveBid);
		});
	}

	public void createRagnorUpcomingAuctions() {

		LocalDateTime dateTime = LocalDateTime.now().plusHours(1);

		Auction mikeAuction = new Auction();
		mikeAuction.setCategory("Coins & Currency");
		mikeAuction.setDescription(
				"On June 19, Sotheby’s in New York will auction the D. Brent Pogue Collection, a set of rare coins expected to sell for $200 million. ");
		mikeAuction.setEventTitle("Rare Coins Up for Auction Are Worth a Pretty Penny");
		mikeAuction.setImageName("coin_auction_banner.jpg");
		mikeAuction.setSellerId("ragna@gmail.com");
		mikeAuction.setStatus(AuctionStatus.CREATED.toString());
		mikeAuction.setDuration(360);
		mikeAuction.setStartDate(dateTime.toLocalDate());
		mikeAuction.setStartTime(dateTime.toLocalTime());
		String startDate = mikeAuction.getStartDate().toString();
		String startTime = mikeAuction.getStartTime().toString();
		LocalDateTime localDateTime = DateFormatter.getFormattedLocalDateTime(startDate, startTime);
		mikeAuction.setDate(localDateTime);
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(startDate, startTime));
		mikeAuction.setEndDateTime(mikeAuction.getDate().plusMinutes(mikeAuction.getDuration()));
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction.getStartDate().toString(),
				mikeAuction.getStartTime().toString()));
		List<Catalog> items1 = createCoinCatalogItems();
		mikeAuction.setItems(items1);
		Auction auction1 = auctionRepository.save(mikeAuction);

		items1.forEach(item -> {
			LiveBid liveBid = new LiveBid();
			liveBid.setAuctionId(auction1.getEventNo());
			liveBid.setBidderId(LiveBidStatus.NONE.toString());
			liveBid.setCatalog(item);
			liveBid.setCurrentBidValue(item.getItemStartBid());
			liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
			liveBid.setSecondaryStatus(LiveBidStatus.NONE.toString());
			liveBid.setBidTime(LocalTime.now());
			liveBid.setBidDate(LocalDate.now());
			liveBidRepository.save(liveBid);
		});

		LocalDateTime dateTime2 = LocalDateTime.now().plusMinutes(2);

		Auction mikeAuction2 = new Auction();
		mikeAuction2.setCategory("Vehicles/ Marine & Aviation");
		mikeAuction2.setDescription(
				"The first dealership I owned was a Buick dealership in Irving, TX. It all happened very quickly, but that is a different True Story. ");
		mikeAuction2.setEventTitle("True Stories From A Former Car Dealer");
		mikeAuction2.setImageName("vehicles_banner.png");
		mikeAuction2.setSellerId("ragna@gmail.com");
		mikeAuction2.setStatus(AuctionStatus.CREATED.toString());
		mikeAuction2.setDuration(420);
		mikeAuction2.setStartDate(dateTime2.toLocalDate());
		mikeAuction2.setStartTime(dateTime2.toLocalTime());
		String startDate1 = mikeAuction2.getStartDate().toString();
		String startTime1 = mikeAuction2.getStartTime().toString();
		LocalDateTime localDateTime1 = DateFormatter.getFormattedLocalDateTime(startDate1, startTime1);
		mikeAuction2.setDate(localDateTime1);
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(startDate1, startTime1));
		mikeAuction2.setEndDateTime(mikeAuction2.getDate().plusMinutes(mikeAuction2.getDuration()));
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction2.getStartDate().toString(),
				mikeAuction2.getStartTime().toString()));

		List<Catalog> items2 = createVehicleCatalogItems();
		mikeAuction2.setItems(items2);
		Auction auction2 = auctionRepository.save(mikeAuction2);
		
		
		items2.forEach(item -> {
			LiveBid liveBid = new LiveBid();
			liveBid.setAuctionId(auction2.getEventNo());
			liveBid.setBidderId(LiveBidStatus.NONE.toString());
			liveBid.setCatalog(item);
			liveBid.setCurrentBidValue(item.getItemStartBid());
			liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
			liveBid.setSecondaryStatus(LiveBidStatus.NONE.toString());
			liveBid.setBidTime(LocalTime.now());
			liveBid.setBidDate(LocalDate.now());
			liveBidRepository.save(liveBid);
		});

	}

	/* Live Auction */

	public List<Catalog> createLiveCoinCatalogItems() {

		List<Catalog> list = new ArrayList<>();

		Catalog coin1 = new Catalog();
		coin1.setBidStatus(LiveBidStatus.INITIAL.toString());
		coin1.setItemDesc("The 1804 silver dollar; estimate $8 million to $10 million.");
		coin1.setItemImage("coin1.jpg");
		coin1.setItemName("The 1804 silver dollar");
		coin1.setWinner(null);
		coin1.setItemStartBid(10);
		list.add(catalogRepository.save(coin1));

		Catalog coin2 = new Catalog();
		coin2.setBidStatus(LiveBidStatus.INITIAL.toString());
		coin2.setItemDesc("The 1797 half dollar; estimate $1.2 million to $1.75 million.");
		coin2.setItemImage("coin2.jpg");
		coin2.setItemName("The 1797 half dollar");
		coin2.setWinner(null);
		coin2.setItemStartBid(12);
		list.add(catalogRepository.save(coin2));

		Catalog coin3 = new Catalog();
		coin3.setBidStatus(LiveBidStatus.INITIAL.toString());
		coin3.setItemDesc("The 1794 silver dollar; estimate $3 million to $5 million.");
		coin3.setItemImage("coin3.jpg");
		coin3.setItemName("The 1794 silver dollar");
		coin3.setWinner(null);
		coin3.setItemStartBid(21);
		list.add(catalogRepository.save(coin3));

		return list;
	}

	public List<Catalog> createLiveVehicleCatalogItems() {

		List<Catalog> list = new ArrayList<>();

		Catalog car1 = new Catalog();
		car1.setBidStatus(LiveBidStatus.LIVE.toString());
		car1.setItemDesc(
				"This used 2022 Toyota Tacoma 2WD is for sale and conveniently located at Southfork Chrysler Jeep Dodge Ram in Manvel, Texas. ");
		car1.setItemImage("car1.jpg");
		car1.setItemName("2022 Toyota Tacoma 2WD Magnetic Gray Metallic");
		car1.setWinner(null);
		car1.setItemStartBid(39079);
		list.add(catalogRepository.save(car1));

		Catalog car2 = new Catalog();
		car2.setBidStatus(LiveBidStatus.LIVE.toString());
		car2.setItemDesc(
				"This new 2022 Hyundai Palisade is for sale and conveniently located at Harbor Hyundai in Long Beach, California.");
		car2.setItemImage("car2.jpg");
		car2.setItemName("2022 Hyundai Palisade Black");
		car2.setWinner(null);
		car2.setItemStartBid(39945);
		list.add(catalogRepository.save(car2));

		return list;
	}

	public void createMikeLiveAuctions() {

		LocalDateTime dateTime = LocalDateTime.now().minusHours(2);

		Auction mikeAuction = new Auction();
		mikeAuction.setCategory("Coins & Currency");
		mikeAuction.setDescription(
				"On June 19, Sotheby’s in New York will auction the D. Brent Pogue Collection, a set of rare coins expected to sell for $200 million.");
		mikeAuction.setEventTitle("Rare Coins Up for Auction Are Worth a Pretty Penny");
		mikeAuction.setImageName("coin_auction_banner.jpg");
		mikeAuction.setSellerId("mike@gmail.com");
		mikeAuction.setStatus(AuctionStatus.LIVE.toString());
		mikeAuction.setDuration(360);
		mikeAuction.setStartDate(dateTime.toLocalDate());
		mikeAuction.setStartTime(dateTime.toLocalTime());
		String startDate = mikeAuction.getStartDate().toString();
		String startTime = mikeAuction.getStartTime().toString();
		LocalDateTime localDateTime = DateFormatter.getFormattedLocalDateTime(startDate, startTime);
		mikeAuction.setDate(localDateTime);
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(startDate, startTime));
		mikeAuction.setEndDateTime(mikeAuction.getDate().plusMinutes(mikeAuction.getDuration()));
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction.getStartDate().toString(),
				mikeAuction.getStartTime().toString()));

		List<Catalog> items1 = createCoinCatalogItems();
		mikeAuction.setItems(items1);

		Auction auction1 = auctionRepository.save(mikeAuction);

		auction1.getItems().forEach(item -> {

			LiveBid liveBid = new LiveBid();
			liveBid.setAuctionId(auction1.getEventNo());
			liveBid.setBidderId(LiveBidStatus.NONE.toString());
			liveBid.setCatalog(item);
			liveBid.setCurrentBidValue(item.getItemStartBid());
			liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
			liveBid.setSecondaryStatus(LiveBidStatus.NONE.toString());
			liveBid.setBidTime(LocalTime.now());
			liveBid.setBidDate(LocalDate.now());
			liveBidRepository.save(liveBid);
		});

		LocalDateTime dateTime2 = LocalDateTime.now().minusHours(3);

		Auction mikeAuction2 = new Auction();
		mikeAuction2.setCategory("Vehicles/ Marine & Aviation");
		mikeAuction2.setDescription(
				"The first dealership I owned was a Buick dealership in Irving, TX. It all happened very quickly, but that is a different True Story.");
		mikeAuction2.setEventTitle("True Stories From A Former Car Dealer");
		mikeAuction2.setImageName("vehicles_banner.png");
		mikeAuction2.setSellerId("mike@gmail.com");
		mikeAuction2.setStatus(AuctionStatus.LIVE.toString());
		mikeAuction2.setDuration(420);
		mikeAuction2.setStartDate(dateTime2.toLocalDate());
		mikeAuction2.setStartTime(dateTime2.toLocalTime());
		String startDate1 = mikeAuction2.getStartDate().toString();
		String startTime1 = mikeAuction2.getStartTime().toString();
		LocalDateTime localDateTime1 = DateFormatter.getFormattedLocalDateTime(startDate1, startTime1);
		mikeAuction2.setDate(localDateTime1);
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(startDate1, startTime1));
		mikeAuction2.setEndDateTime(mikeAuction2.getDate().plusMinutes(mikeAuction2.getDuration()));
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction2.getStartDate().toString(),
				mikeAuction2.getStartTime().toString()));

		List<Catalog> items2 = createVehicleCatalogItems();
		mikeAuction2.setItems(items2);
		Auction auction2 = auctionRepository.save(mikeAuction2);

		auction2.getItems().forEach(item -> {

			LiveBid liveBid = new LiveBid();
			liveBid.setAuctionId(auction2.getEventNo());
			liveBid.setBidderId(LiveBidStatus.NONE.toString());
			liveBid.setCatalog(item);
			liveBid.setCurrentBidValue(item.getItemStartBid());
			liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
			liveBid.setSecondaryStatus(LiveBidStatus.NONE.toString());
			liveBid.setBidTime(LocalTime.now());
			liveBid.setBidDate(LocalDate.now());
			liveBidRepository.save(liveBid);
		});

	}

	public void createRagnorLiveAuctions() {

		LocalDateTime dateTime = LocalDateTime.now().minusHours(1);

		Auction mikeAuction = new Auction();
		mikeAuction.setCategory("Coins & Currency");
		mikeAuction.setDescription(
				"On June 19, Sotheby’s in New York will auction the D. Brent Pogue Collection, a set of rare coins expected to sell for $200 million. ");
		mikeAuction.setEventTitle("Rare Coins Up for Auction Are Worth a Pretty Penny");
		mikeAuction.setImageName("coin_auction_banner.jpg");
		mikeAuction.setSellerId("ragna@gmail.com");
		mikeAuction.setStatus(AuctionStatus.LIVE.toString());
		mikeAuction.setDuration(360);
		mikeAuction.setStartDate(dateTime.toLocalDate());
		mikeAuction.setStartTime(dateTime.toLocalTime());
		String startDate = mikeAuction.getStartDate().toString();
		String startTime = mikeAuction.getStartTime().toString();
		LocalDateTime localDateTime = DateFormatter.getFormattedLocalDateTime(startDate, startTime);
		mikeAuction.setDate(localDateTime);
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(startDate, startTime));
		mikeAuction.setEndDateTime(mikeAuction.getDate().plusMinutes(mikeAuction.getDuration()));
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction.getStartDate().toString(),
				mikeAuction.getStartTime().toString()));
		List<Catalog> items1 = createCoinCatalogItems();
		mikeAuction.setItems(items1);
		Auction auction1 = auctionRepository.save(mikeAuction);

		auction1.getItems().forEach(item -> {
			// Catalog catalog = catalogRepository.save(item);

			LiveBid liveBid = new LiveBid();
			liveBid.setAuctionId(auction1.getEventNo());
			liveBid.setBidderId(LiveBidStatus.NONE.toString());
			liveBid.setCatalog(item);
			liveBid.setCurrentBidValue(item.getItemStartBid());
			liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
			liveBid.setSecondaryStatus(LiveBidStatus.NONE.toString());
			liveBid.setBidTime(LocalTime.now());
			liveBid.setBidDate(LocalDate.now());
			liveBidRepository.save(liveBid);
		});

		LocalDateTime dateTime2 = LocalDateTime.now().minusHours(4);

		Auction mikeAuction2 = new Auction();
		mikeAuction2.setCategory("Vehicles/ Marine & Aviation");
		mikeAuction2.setDescription(
				"The first dealership I owned was a Buick dealership in Irving, TX. It all happened very quickly, but that is a different True Story. ");
		mikeAuction2.setEventTitle("True Stories From A Former Car Dealer");
		mikeAuction2.setImageName("vehicles_banner.png");
		mikeAuction2.setSellerId("ragna@gmail.com");
		mikeAuction2.setStatus(AuctionStatus.LIVE.toString());
		mikeAuction2.setDuration(420);
		mikeAuction2.setStartDate(dateTime2.toLocalDate());
		mikeAuction2.setStartTime(dateTime2.toLocalTime());
		String startDate1 = mikeAuction2.getStartDate().toString();
		String startTime1 = mikeAuction2.getStartTime().toString();
		LocalDateTime localDateTime1 = DateFormatter.getFormattedLocalDateTime(startDate1, startTime1);
		mikeAuction2.setDate(localDateTime1);
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(startDate1, startTime1));
		mikeAuction2.setEndDateTime(mikeAuction2.getDate().plusMinutes(mikeAuction2.getDuration()));
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction2.getStartDate().toString(),
				mikeAuction2.getStartTime().toString()));

		List<Catalog> items2 = createVehicleCatalogItems();
		//items2.forEach(i->System.out.println(i.getItemId()));
		
		mikeAuction2.setItems(items2);
		
		
		Auction auction2 = auctionRepository.save(mikeAuction2);

		
		auction2.getItems().forEach(item -> {
			// Catalog catalog = catalogRepository.save(item);

			LiveBid liveBid = new LiveBid();
			liveBid.setAuctionId(auction2.getEventNo());
			liveBid.setBidderId(LiveBidStatus.NONE.toString());
			liveBid.setCatalog(item);
			liveBid.setCurrentBidValue(item.getItemStartBid());
			liveBid.setBidStatus(LiveBidStatus.INITIAL.toString());
			liveBid.setSecondaryStatus(LiveBidStatus.NONE.toString());
			liveBid.setBidTime(LocalTime.now());
			liveBid.setBidDate(LocalDate.now());
			liveBidRepository.save(liveBid);
		});

	}

	/* past auctions */

	public List<Catalog> createCoinCatalogItems() {

		List<Catalog> list = new ArrayList<>();

		Catalog coin1 = new Catalog();
		coin1.setBidStatus(LiveBidStatus.INITIAL.toString());
		coin1.setItemDesc("The 1804 silver dollar; estimate $8 million to $10 million.");
		coin1.setItemImage("coin1.jpg");
		coin1.setItemName("The 1804 silver dollar");
		coin1.setWinner(null);
		coin1.setItemStartBid(10);
		list.add(coin1);

		Catalog coin2 = new Catalog();
		coin2.setBidStatus(LiveBidStatus.INITIAL.toString());
		coin2.setItemDesc("The 1797 half dollar; estimate $1.2 million to $1.75 million.");
		coin2.setItemImage("coin2.jpg");
		coin2.setItemName("The 1797 half dollar");
		coin2.setWinner(null);
		coin2.setItemStartBid(12);
		list.add(coin2);

		Catalog coin3 = new Catalog();
		coin3.setBidStatus(LiveBidStatus.INITIAL.toString());
		coin3.setItemDesc("The 1794 silver dollar; estimate $3 million to $5 million.");
		coin3.setItemImage("coin3.jpg");
		coin3.setItemName("The 1794 silver dollar");
		coin3.setWinner(null);
		coin3.setItemStartBid(21);
		list.add(coin3);

		return list;
	}

	public List<Catalog> createVehicleCatalogItems() {

		List<Catalog> list = new ArrayList<>();

		Catalog car1 = new Catalog();
		car1.setBidStatus(LiveBidStatus.INITIAL.toString());
		car1.setItemDesc(
				"This used 2022 Toyota Tacoma 2WD is for sale and conveniently located at Southfork Chrysler Jeep Dodge Ram in Manvel, Texas. ");
		car1.setItemImage("car1.jpg");
		car1.setItemName("2022 Toyota Tacoma 2WD Magnetic Gray Metallic");
		car1.setWinner(null);
		car1.setItemStartBid(39079);
		list.add(car1);

		Catalog car2 = new Catalog();
		car2.setBidStatus(LiveBidStatus.INITIAL.toString());
		car2.setItemDesc(
				"This new 2022 Hyundai Palisade is for sale and conveniently located at Harbor Hyundai in Long Beach, California. ");
		car2.setItemImage("car2.jpg");
		car2.setItemName("2022 Hyundai Palisade Black");
		car2.setWinner(null);
		car2.setItemStartBid(39945);
		list.add(car2);

		return list;
	}

	public void createMikePastAuctions() {

		LocalDateTime dateTime = LocalDateTime.now().minusDays(2);

		Auction mikeAuction = new Auction();
		mikeAuction.setCategory("Coins & Currency");
		mikeAuction.setDescription(
				"On June 19, Sotheby’s in New York will auction the D. Brent Pogue Collection, a set of rare coins expected to sell for $200 million. ");
		mikeAuction.setEventTitle("Rare Coins Up for Auction Are Worth a Pretty Penny");
		mikeAuction.setImageName("coin_auction_banner.jpg");
		mikeAuction.setSellerId("mike@gmail.com");
		mikeAuction.setStatus(AuctionStatus.CREATED.toString());
		mikeAuction.setDuration(360);
		mikeAuction.setStartDate(dateTime.toLocalDate());
		mikeAuction.setStartTime(dateTime.toLocalTime());
		String startDate = mikeAuction.getStartDate().toString();
		String startTime = mikeAuction.getStartTime().toString();
		LocalDateTime localDateTime = DateFormatter.getFormattedLocalDateTime(startDate, startTime);
		mikeAuction.setDate(localDateTime);
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(startDate, startTime));
		mikeAuction.setEndDateTime(mikeAuction.getDate().plusMinutes(mikeAuction.getDuration()));
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction.getStartDate().toString(),
				mikeAuction.getStartTime().toString()));
		mikeAuction.setItems(createCoinCatalogItems());
		auctionRepository.save(mikeAuction);

		LocalDateTime dateTime2 = LocalDateTime.now().minusDays(1);

		Auction mikeAuction2 = new Auction();
		mikeAuction2.setCategory("Vehicles/ Marine & Aviation");
		mikeAuction2.setDescription(
				"The first dealership I owned was a Buick dealership in Irving, TX. It all happened very quickly, but that is a different True Story.");
		mikeAuction2.setEventTitle("True Stories From A Former Car Dealer");
		mikeAuction2.setImageName("vehicles_banner.jpg");
		mikeAuction2.setSellerId("mike@gmail.com");
		mikeAuction2.setStatus(AuctionStatus.CREATED.toString());
		mikeAuction2.setDuration(420);
		mikeAuction2.setStartDate(dateTime2.toLocalDate());
		mikeAuction2.setStartTime(dateTime2.toLocalTime());
		String startDate1 = mikeAuction2.getStartDate().toString();
		String startTime1 = mikeAuction2.getStartTime().toString();
		LocalDateTime localDateTime1 = DateFormatter.getFormattedLocalDateTime(startDate1, startTime1);
		mikeAuction2.setDate(localDateTime1);
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(startDate1, startTime1));
		mikeAuction2.setEndDateTime(mikeAuction2.getDate().plusMinutes(mikeAuction2.getDuration()));
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction2.getStartDate().toString(),
				mikeAuction2.getStartTime().toString()));
		mikeAuction.setItems(createVehicleCatalogItems());
		auctionRepository.save(mikeAuction2);

	}

	public void createRagnorPastAuctions() {

		LocalDateTime dateTime = LocalDateTime.now().minusDays(1);

		Auction mikeAuction = new Auction();
		mikeAuction.setCategory("Coins & Currency");
		mikeAuction.setDescription(
				"On June 19, Sotheby’s in New York will auction the D. Brent Pogue Collection, a set of rare coins expected to sell for $200 million. ");
		mikeAuction.setEventTitle("Rare Coins Up for Auction Are Worth a Pretty Penny");
		mikeAuction.setImageName("coin_auction_banner.jpg");
		mikeAuction.setSellerId("ragna@gmail.com");
		mikeAuction.setStatus(AuctionStatus.CREATED.toString());
		mikeAuction.setDuration(360);
		mikeAuction.setStartDate(dateTime.toLocalDate());
		mikeAuction.setStartTime(dateTime.toLocalTime());
		String startDate = mikeAuction.getStartDate().toString();
		String startTime = mikeAuction.getStartTime().toString();
		LocalDateTime localDateTime = DateFormatter.getFormattedLocalDateTime(startDate, startTime);
		mikeAuction.setDate(localDateTime);
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(startDate, startTime));
		mikeAuction.setEndDateTime(mikeAuction.getDate().plusMinutes(mikeAuction.getDuration()));
		mikeAuction.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction.getStartDate().toString(),
				mikeAuction.getStartTime().toString()));
		mikeAuction.setItems(createCoinCatalogItems());
		auctionRepository.save(mikeAuction);

		LocalDateTime dateTime2 = LocalDateTime.now().minusDays(3);

		Auction mikeAuction2 = new Auction();
		mikeAuction2.setCategory("Vehicles/ Marine & Aviation");
		mikeAuction2.setDescription(
				"The first dealership I owned was a Buick dealership in Irving, TX. It all happened very quickly, but that is a different True Story.");
		mikeAuction2.setEventTitle("True Stories From A Former Car Dealer");
		mikeAuction2.setImageName("vehicles_banner.jpg");
		mikeAuction2.setSellerId("ragna@gmail.com");
		mikeAuction2.setStatus(AuctionStatus.CREATED.toString());
		mikeAuction2.setDuration(420);
		mikeAuction2.setStartDate(dateTime2.toLocalDate());
		mikeAuction2.setStartTime(dateTime2.toLocalTime());
		String startDate1 = mikeAuction2.getStartDate().toString();
		String startTime1 = mikeAuction2.getStartTime().toString();
		LocalDateTime localDateTime1 = DateFormatter.getFormattedLocalDateTime(startDate1, startTime1);
		mikeAuction2.setDate(localDateTime1);
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(startDate1, startTime1));
		mikeAuction2.setEndDateTime(mikeAuction2.getDate().plusMinutes(mikeAuction2.getDuration()));
		mikeAuction2.setStartDateTime(DateFormatter.formatToFullDateTime(mikeAuction2.getStartDate().toString(),
				mikeAuction2.getStartTime().toString()));
		mikeAuction.setItems(createVehicleCatalogItems());
		auctionRepository.save(mikeAuction2);

	}

	public void createAuctioneers() {
		Auctioneer mike = new Auctioneer();
		mike.setAddress("86 Porter Alley");
		mike.setContact("2541364789");
		mike.setEmail("mike@gmail.com");
		mike.setPassword(new BCryptPasswordEncoder().encode("123456"));
		mike.setHouseName("MK House ");
		mike.setRole(ROLE.AUCTIONEER.toString());
		auctioneerRepository.save(mike);

		Auctioneer ragnar = new Auctioneer();
		ragnar.setAddress("6 Golf Course Hill");
		ragnar.setContact("7541364789");
		ragnar.setEmail("ragna@gmail.com");
		ragnar.setPassword(new BCryptPasswordEncoder().encode("123456"));
		ragnar.setHouseName("Ragnar House ");
		ragnar.setRole(ROLE.AUCTIONEER.toString());
		auctioneerRepository.save(ragnar);
	}

	public void createBidders() {
		Bidder john = new Bidder();
		john.setBidderFirstName("John");
		john.setBidderLastName("Doe");
		john.setBidderContact(425714785);
		john.setBidderEmail("john@gmail.com");
		john.setBidderPassword(new BCryptPasswordEncoder().encode("123456"));
		john.setRole(ROLE.BIDDER.toString());
		john.setAccountVerified(true);
		bidderRepository.save(john);

		Bidder tony = new Bidder();
		tony.setBidderFirstName("Tony");
		tony.setBidderLastName("Stark");
		tony.setBidderContact(78587785);
		tony.setBidderEmail("tony@gmail.com");
		tony.setBidderPassword(new BCryptPasswordEncoder().encode("123456"));
		tony.setRole(ROLE.BIDDER.toString());
		tony.setAccountVerified(true);
		bidderRepository.save(tony);

	}

}
