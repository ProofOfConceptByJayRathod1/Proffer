package com.proxibid.service;

import com.proxibid.entity.Auctioneer;
import com.proxibid.util.ROLE;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.proxibid.repository.AuctioneerRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuctioneerServiceTest {

	@Mock
	private AuctioneerService auctioneerService;
	
	
	@Test
	void testSaveSeller() {
		//Arrange
		Auctioneer auctioneer=new Auctioneer();
		auctioneer.setAddress("address");
		auctioneer.setContact("1234567879");
		auctioneer.setEmail("auctioneer@gmail.com");
		auctioneer.setPassword("1234567889");
		auctioneer.setRole(ROLE.AUCTIONEER.toString());
		auctioneer.setHouseName("housename");
		
		doNothing().when(auctioneerService).saveSeller(any());

		//Act
		auctioneerService.saveSeller(auctioneer);

		//Assert
		verify(auctioneerService,times(1)).saveSeller(any());

	}

	@Test
	void testExistsByEmail(){
		//Arrange
		boolean expected=true;
		String email="abc@gmail.com";
		when(auctioneerService.existsByEmail(email)).thenReturn(expected);

		//Act
		boolean actual=auctioneerService.existsByEmail(email);

		//Assert
		org.junit.jupiter.api.Assertions.assertEquals(expected,actual);
	}
	
	

	@Test
	void testFindByEmail(){
		//Arrange
		Auctioneer auctioneer=new Auctioneer();
		auctioneer.setAddress("address");
		auctioneer.setContact("1234567879");
		auctioneer.setEmail("auctioneer@gmail.com");
		auctioneer.setPassword("1234567889");
		auctioneer.setRole(ROLE.AUCTIONEER.toString());
		auctioneer.setHouseName("housename");
		when(auctioneerService.findByEmail(auctioneer.getEmail())).thenReturn(auctioneer);

		//Act
		Auctioneer auctioneer1=auctioneerService.findByEmail(auctioneer.getEmail());

		//Assert
		assertThat(auctioneer1).isEqualTo(auctioneer);

	}

	@Test
	void testCheckIfSellerEmailIdAlreadyExistInTheDatabase(){
		//Arrange
		Auctioneer auctioneer=new Auctioneer();
		auctioneer.setAddress("address");
		auctioneer.setContact("1234567879");
		auctioneer.setEmail("auctioneer@gmail.com");
		auctioneer.setPassword("1234567889");
		auctioneer.setRole(ROLE.AUCTIONEER.toString());
		auctioneer.setHouseName("housename");
		boolean expected=true;
		when(auctioneerService.checkIfSellerEmailIdAlreadyExistInTheDatabase(auctioneer)).thenReturn(expected);

		//Act
		boolean actual=auctioneerService.checkIfSellerEmailIdAlreadyExistInTheDatabase(auctioneer);

		//Assert
		assertThat(expected).isEqualTo(actual);

	}
}
