package doan.placeandroidmap;

import com.google.android.gms.maps.model.LatLng;




public class Place {
	private String icon;
	private String name;
	private String rate;
	private String types;
	private String vicinity;
	private String formatted_address;
	private String reference;
	private String formatted_phone_number;
	private String international_phone_number;
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getFormatted_phone_number() {
		return formatted_phone_number;
	}

	public void setFormatted_phone_number(String formatted_phone_number) {
		this.formatted_phone_number = formatted_phone_number;
	}

	public String getInternational_phone_number() {
		return international_phone_number;
	}

	public void setInternational_phone_number(String international_phone_number) {
		this.international_phone_number = international_phone_number;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	private String website;
	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	private LatLng location;
	
	public Place()
	{
	}
	
	public Place copy()
	{
		final Place copy = new Place();
		copy.icon = icon;
		copy.location = location;
		copy.name = name;
		copy.rate = rate;
		copy.types = types;
		copy.formatted_address = formatted_address;
		copy.vicinity = vicinity;
		copy.formatted_phone_number = formatted_phone_number;
		copy.website = website;
		copy.international_phone_number = international_phone_number;
		copy.reference = reference;
		copy.url = url;
		return copy;
	}
	
	
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * @return the types
	 */
	public String getTypes() {
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(String types) {
		this.types = types;
	}

	/**
	 * @return the vicinity
	 */
	public String getVicinity() {
		return vicinity;
	}

	/**
	 * @param vicinity the vicinity to set
	 */
	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}

	/**
	 * @return the locatio
	 */
	public LatLng getLocation() {
		return location;
	}

	/**
	 * @param locatio the locatio to set
	 */
	public void setLocation(LatLng locatio) {
		this.location = locatio;
	}
	
	
	
}
