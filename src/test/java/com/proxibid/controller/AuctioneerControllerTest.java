package com.proxibid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxibid.entity.Auctioneer;
import com.proxibid.repository.AuctioneerRepository;
import com.proxibid.service.AuctioneerService;
import com.proxibid.util.ROLE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AuctioneerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuctioneerRepository auctioneerRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private AuctioneerService auctioneerService;
    private ObjectMapper objectMapper;

    @Mock
    private Auctioneer auctioneer;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        auctioneer = new Auctioneer();
        auctioneer.setAddress("address");
        auctioneer.setContact("1234567879");
        auctioneer.setEmail("auctioneer@gmail.com");
        auctioneer.setPassword("1234567889");
        auctioneer.setRole(ROLE.AUCTIONEER.toString());
        auctioneer.setHouseName("housename");
    }

    @Test
    void testAuctioneerSignUpGET() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auctionhouse/signup")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(auctioneer));

        // Act and Assert
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.view().name("/auctioneer/signup"));

    }

    @Test
    void testSignUpAsAuctioneerPOST1() throws Exception {
        // Arrange
        Auctioneer auctioneer = new Auctioneer();
        auctioneer.setAddress("address");
        auctioneer.setContact("1234567879");
        auctioneer.setEmail("auctioneer@gmail.com");
        auctioneer.setPassword("1234567889");
        auctioneer.setRole(ROLE.AUCTIONEER.toString());
        auctioneer.setHouseName("housename");
        when(auctioneerRepository.findByEmail(any())).thenReturn(null);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auctionhouse/signup/save")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .param("password", "123456")
                .param("email", "asd123456@gmail.com")
                .flashAttr("auctioneer", objectMapper.writeValueAsBytes(auctioneer))
                .content(objectMapper.writeValueAsString(auctioneer));

        // Act
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));

    }

    @Test
    @Disabled
    void testSignUpAsAuctioneerPOST2() throws Exception {
        // Arrange

        when(auctioneerService.existsByEmail(auctioneer.getEmail())).thenReturn(true);
        when(auctioneerRepository.existsByEmail(auctioneer.getEmail())).thenReturn(true);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("auctionhouse/signup/save")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(auctioneer));

        // Act
        mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.request().attribute("error", "User with same email already exixst !"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("error"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("auctioneer-signup"));

    }

    @Test
    void testGetAuction() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auctionhouse/addauction");

        // Act
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        // Assert
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("Auction"));

    }

}
