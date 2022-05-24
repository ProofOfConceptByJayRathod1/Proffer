package com.proxibid.entity;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Bidder")
public class Bidder implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4151240444664451767L;
	@Id
	private String bidderEmail;
	private String bidderFirstName;
	private String bidderLastName;
	private int bidderContact;
	private String bidderPassword;
	private String role;
	private boolean isAccountVerified;
	private int otpPassword;

	public String getBidderEmail() {
		return bidderEmail;
	}

	public void setBidderEmail(String bidderEmail) {
		this.bidderEmail = bidderEmail;
	}

	public String getBidderFirstName() {
		return bidderFirstName;
	}

	public void setBidderFirstName(String bidderFirstName) {
		this.bidderFirstName = bidderFirstName;
	}

	public String getBidderLastName() {
		return bidderLastName;
	}

	public void setBidderLastName(String bidderLastName) {
		this.bidderLastName = bidderLastName;
	}

	public long getBidderContact() {
		return bidderContact;
	}

	public void setBidderContact(int bidderContact) {
		this.bidderContact = bidderContact;
	}

	public String getBidderPassword() {
		return bidderPassword;
	}

	public void setBidderPassword(String bidderPassword) {
		this.bidderPassword = bidderPassword;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		return bidderPassword;
	}

	@Override
	public String getUsername() {
		return bidderEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isAccountVerified() {
		return isAccountVerified;
	}

	public void setAccountVerified(boolean isAccountVerified) {
		this.isAccountVerified = isAccountVerified;
	}

	public int getOtpPassword() {
		return otpPassword;
	}

	public void setOtpPassword(int otpPassword) {
		this.otpPassword = otpPassword;
	}

}
