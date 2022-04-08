package com.proffer.endpoints.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.proffer.endpoints.filter.JwtFilter;
import com.proffer.endpoints.service.BidderService;
import com.proffer.endpoints.service.CustomUserDetailsService;
import com.proffer.endpoints.service.SellerService;
import com.proffer.endpoints.util.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private SellerService sellerService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable().csrf().disable();
		http.authorizeRequests()
				.antMatchers("/authenticate", "/auctionhouse/signup", "/bidder/signup", "/css/**", "/scripts/**", "/",
						"/category/**", "/proxibid.com", "/carousel/**", "/auctionimage/**", "/catalogimage/**",
						"/proxibid.com/**", "/login")
				.permitAll().anyRequest().authenticated();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.formLogin().usernameParameter("userName").loginPage("/login")
				.successHandler(new AuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {

						String token = new JwtUtil().generateToken(authentication.getName());
						Cookie cookie = new Cookie("token", token);
						cookie.setMaxAge(6 * 60); // expires in 10 minutes
						cookie.setSecure(true);
						cookie.setHttpOnly(true);
						response.addCookie(cookie);

						if (sellerService.existsByEmail(authentication.getName())) {
							new DefaultRedirectStrategy().sendRedirect(request, response, "/auctionhouse/dashboard");
						} else {
							new DefaultRedirectStrategy().sendRedirect(request, response, "/bidder/dashboard");
						}

					}
				});

		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("token", "JSESSIONID")
				.clearAuthentication(true).invalidateHttpSession(true);

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
