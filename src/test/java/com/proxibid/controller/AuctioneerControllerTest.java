package com.proxibid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxibid.entity.Auctioneer;
import com.proxibid.service.*;
import com.proxibid.util.JwtUtil;
import com.proxibid.util.ROLE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@AutoConfigureMockMvc
@SpringBootTest
public class AuctioneerControllerTest{

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;
    
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testAuctioneerSignUp() throws Exception {
        // Arrange
        Auctioneer auctioneer = new Auctioneer();
        auctioneer.setAddress("address");
        auctioneer.setContact("1234567879");
        auctioneer.setEmail("auctioneer@gmail.com");
        auctioneer.setPassword("1234567889");
        auctioneer.setRole(ROLE.AUCTIONEER.toString());
        auctioneer.setHouseName("housename");

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auctionhouse/signup")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(auctioneer));


		//Act and Assert
        mockMvc.perform(requestBuilder)
				.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("/auctioneer/signup"));


    }

}
