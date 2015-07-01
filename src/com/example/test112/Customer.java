package com.example.test112;

import com.google.android.gms.maps.model.LatLng;



public class Customer {
	private String id;
	private String name;
	private String phoneNumber;
	private String time;
	private String sourceAddress;
	private LatLng sourceLocation;
	private String desAddress;
	private LatLng desLocation;
	public Customer(String id, String name, String phoneNumber, String time,
			String sourceAddress, LatLng sourceLocation, String desAddress,
			LatLng desLocation) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.time = time;
		this.sourceAddress = sourceAddress;
		this.sourceLocation = sourceLocation;
		this.desAddress = desAddress;
		this.desLocation = desLocation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSourceAddress() {
		return sourceAddress;
	}
	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}
	public LatLng getSourceLocation() {
		return sourceLocation;
	}
	public void setSourceLocation(LatLng sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	public String getDesAddress() {
		return desAddress;
	}
	public void setDesAddress(String desAddress) {
		this.desAddress = desAddress;
	}
	public LatLng getDesLocation() {
		return desLocation;
	}
	public void setDesLocation(LatLng desLocation) {
		this.desLocation = desLocation;
	}
	
	
}
