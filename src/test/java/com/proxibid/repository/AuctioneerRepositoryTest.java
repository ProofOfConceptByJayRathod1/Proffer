package com.proxibid.repository;

import com.proxibid.entity.Auctioneer;
import com.proxibid.util.ROLE;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.mock.mockito.MockBean;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ExtendWith(MockitoExtension.class)
class AuctioneerRepositoryTest {

    @MockBean
    private AuctioneerRepository auctioneerRepository;

    @Test
    void testSave(){
        //Arrange
        Auctioneer auctioneer=new Auctioneer();
        auctioneer.setAddress("address");
        auctioneer.setContact("1234567879");
        auctioneer.setEmail("auctioneer@gmail.com");
        auctioneer.setPassword("1234567889");
        auctioneer.setRole(ROLE.AUCTIONEER.toString());
        auctioneer.setHouseName("housename");
        when(auctioneerRepository.save(any())).thenReturn(auctioneer);
        
        
        //Act
        Auctioneer auctioneer1= auctioneerRepository.save(auctioneer);

        //Assert
        assertThat(auctioneer1).isEqualTo(auctioneer);
        verify(auctioneerRepository,times(1)).save(auctioneer1);
    }

    
    @Test
    void tesFindByEmail() {
    	//Arrange
    	Auctioneer auctioneer=new Auctioneer();
        auctioneer.setAddress("address");
        auctioneer.setContact("1234567879");
        auctioneer.setEmail("auctioneer@gmail.com");
        auctioneer.setPassword("1234567889");
        auctioneer.setRole(ROLE.AUCTIONEER.toString());
        auctioneer.setHouseName("housename");
        when(auctioneerRepository.findByEmail(any())).thenReturn(Optional.of(auctioneer));
        
        //Act
        Optional<Auctioneer> auctioneer2=auctioneerRepository.findByEmail(auctioneer.getEmail());
        
        
        //Assert
        assertEquals(auctioneer, auctioneer2.get());
        verify(auctioneerRepository,times(1)).findByEmail(any());
    }
    
    @Test
    void tesFindByEmailFail() {
    	//Arrange
        when(auctioneerRepository.findByEmail(any())).thenReturn(Optional.empty());
        
        //Act
        Optional<Auctioneer> auctioneer2=auctioneerRepository.findByEmail("auctioneer@gmail.com");
        
        
        //Assert
        assertEquals(true, auctioneer2.isEmpty());
        verify(auctioneerRepository,times(1)).findByEmail(any());
    }

    
    @Test
    void testExistsByEmail() {
    	//Arrange
    	boolean expected = true;
    	String email="acutioneer@gmai.com";
    	when(auctioneerRepository.existsByEmail(any())).thenReturn(true);
    	
    	//Act
    	boolean actual = auctioneerRepository.existsByEmail(email);
    	
    	//Assert
    	assertEquals(expected, actual);
    	verify(auctioneerRepository,times(1)).existsByEmail(email);
    	
    }
    
    
    @Test
    void testExistsByEmailFail() {
    	//Arrange
    	boolean expected = false;
    	String email="acutioneer@gmai.com";
    	when(auctioneerRepository.existsByEmail(any())).thenReturn(false);
    	
    	//Act
    	boolean actual = auctioneerRepository.existsByEmail(email);
    	
    	//Assert
    	assertEquals(expected, actual);
    	verify(auctioneerRepository,times(1)).existsByEmail(email);
    	
    }
}
