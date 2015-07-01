package doan.placeandroidmap;

import java.util.ArrayList;
import java.util.List;

public class PlaceList {
	private String status;
	private ArrayList<Place> listPlace;
	
	public PlaceList()
	{
		listPlace = new ArrayList<Place>();
	}
	
	public void addPlace(Place a)
	{
		listPlace.add(a);
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the listPlace
	 */
	public ArrayList<Place> getListPlace() {
		return listPlace;
	}

	/**
	 * @param listPlace the listPlace to set
	 */
	public void setListPlace(ArrayList<Place> listPlace) {
		this.listPlace = listPlace;
	}
	
	
}
