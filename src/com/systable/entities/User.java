package com.systable.entities;

import com.systable.enumeration.Profile;

public class User {
	
	private int id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private int phoneCode;
	private int phoneNumber;
	private String email;
	private String address;
	private Profile profile;
	
	private static int idAssigners;
	
	public User() {
	}

	public User(String login, String password, String firstName, String lastName, int phoneCode, int phoneNumber,
			String email, String address, Profile profile) {
		this();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneCode = phoneCode;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.profile = profile;
	}
	
	public User(int id, String login, String password, String firstName, String lastName, int phoneCode, int phoneNumber,
			String email, String address, Profile profile) {
		
		this(login, password, firstName, lastName, phoneCode, phoneNumber, email, address, profile);
		this.id = id;		
	}

	public User(int id, String login, String password, String firstName, String lastName, int phoneCode, int phoneNumber,
			String email, String address, String profile) {
		
		this(login, password, firstName, lastName, phoneCode, phoneNumber, email, address, Profile.valueOf(profile));
		this.id = id;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(int phoneCode) {
		this.phoneCode = phoneCode;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public static int getIdAssigners() {
		return idAssigners;
	}

	public static void setIdAssigners(int idAssigners) {
		User.idAssigners = idAssigners;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + firstName + " " + lastName;
	}
	
}
