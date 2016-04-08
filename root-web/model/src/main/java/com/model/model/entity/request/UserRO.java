package com.model.model.entity.request;

import com.model.common.Key;
import com.model.common.Props;
import com.model.model.entity.User;
import com.model.model.entity.embedded.Address;
import com.model.model.entity.enums.Role;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * User Request Object
 *
 * @author: Y Kamesh Rao
 * @created: 3/11/12 12:54 AM
 * @company: &copy; 2012, Kaleidosoft Labs
 */
@XStreamAlias(Key.user)
public class UserRO {
	public String username;
	public String password;
	public String confirm;
	public String email;
	public String mobile;
	public @XStreamAlias(Key.role) Role role;
	public String companyName;
	public String streetAddress;
	public String city;
	public String state;
	public String country;
	public String postalCode;

	public User user(Props props) {
		User user = new User();
		user.setUserName(username);
		user.setPassWord(password);
		user.setEmail(email);
		user.setMobile(mobile);

		if (role != null)
			user.setRole(role);
		else
			user.setRole(Role.USER);

		Address address = new Address();
		address.setStreetAddress(streetAddress);
		address.setCity(city);
		address.setState(state);
		address.setPostalCode(postalCode);
		if (country == null || country.trim().equalsIgnoreCase("")) {
			address.setCountry(props.fUserCountry);
		} else {
			address.setCountry(country);
		}

		user.setAddress(address);
		return user;
	}

	@Override
	public String toString() {
		return "UserRO{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", confirm='" + confirm
				+ '\'' + ", email='" + email + '\'' + ", mobile='" + mobile + '\'' + ", role=" + role
				+ ", companyName='" + companyName + '\'' + ", streetAddress='" + streetAddress + '\'' + ", city='"
				+ city + '\'' + ", state='" + state + '\'' + ", country='" + country + '\'' + ", postalCode='"
				+ postalCode + '\'' + '}';
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
}
