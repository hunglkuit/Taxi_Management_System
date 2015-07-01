package doan.placeandroidmap;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlacesAdapter extends ArrayAdapter<Place> {
	Context mConText;
	int resource;
	ArrayList<Place> placeArray;
	Place place;
	public PlacesAdapter(Context context, int textViewResourceId,
			ArrayList<Place> objects) {
		super(context, textViewResourceId, objects);
		this.mConText = context;
		this.resource = textViewResourceId;
		this.placeArray = objects;
	
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null)
		{
			view = new PlaceView(getContext());
		}
		place = placeArray.get(position);
		if(place != null)
		{
			TextView tv_PlaceName = ((PlaceView) view).tvPlaceName;
			TextView tv_PlaceAdd = ((PlaceView) view).tvPlaceAdd;
			TextView tv_PlaceRate = ((PlaceView) view).tvPlaceRate;
			TextView tv_PlacePos = ((PlaceView) view).tvPlacePosi;
			
			tv_PlaceAdd.setText(place.getFormatted_address());
			tv_PlaceName.setText(place.getName());
			tv_PlacePos.setText(String.valueOf(place.getLocation().latitude)+ " : "+String.valueOf(place.getLocation().longitude));
			tv_PlaceRate.setText(place.getRate());
		}
		
		return view;
	}
	
	
}
