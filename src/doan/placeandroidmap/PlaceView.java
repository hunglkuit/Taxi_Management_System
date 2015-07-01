package doan.placeandroidmap;

import com.example.test112.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlaceView extends LinearLayout {
	
	TextView tvPlaceAdd;
	TextView tvPlaceName;
	TextView tvPlaceRate;
	TextView tvPlacePosi;
	
	
	public PlaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.listplacelayout, this, true);
		
		tvPlaceAdd = (TextView) findViewById(R.id.tv_lvAdd);
		tvPlaceName = (TextView) findViewById(R.id.tv_lvName);
		tvPlacePosi = (TextView) findViewById(R.id.tv_lvPos);
		tvPlaceRate = (TextView) findViewById(R.id.tv_lvRate);
	}

}
