package com.proxibid.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxibid.entity.Auctioneer;
import com.proxibid.repository.AuctioneerRepository;
import com.proxibid.service.AuctioneerService;
import com.proxibid.util.ROLE;
import org.junit.jupiter.api.BeforeEach;
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

    @MockBean
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
        when(auctioneerService.existsByEmail(auctioneer.getEmail())).thenReturn(true);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auctionhouse/signup/save")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .param("password", auctioneer.getPassword())
                .param("email", auctioneer.getEmail());

        // Act
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/auctioneer-signup.jsp"))
                .andExpect(MockMvcResultMatchers.request().attribute("error", "User with same email already exixst!"));

    }

    @Test
    void testSignUpAsAuctioneerPOST2() throws Exception {
        // Arrange
        when(auctioneerRepository.existsByEmail(any())).thenReturn(false);
        when(auctioneerRepository.save(any())).thenReturn(auctioneer);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auctionhouse/signup/save")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .param("password", auctioneer.getPassword())
                .param("email", auctioneer.getEmail());

        // Act and Assert
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

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
