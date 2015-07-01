package doan.placeandroidmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test112.R;

public class SegmentMarkerView extends LinearLayout {
	TextView tvSegmentInstruction;
	TextView tvSegmentStart;
	TextView tvSegmentDistance;
	TextView tvSegmentLength;
	
	
	public SegmentMarkerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.listsegmentlayout, this, true);
		
		tvSegmentInstruction = (TextView) findViewById(R.id.tv_lvInstruction);
		tvSegmentDistance = (TextView) findViewById(R.id.tv_lvDistance);
		tvSegmentLength = (TextView) findViewById(R.id.tv_lvLength);
		tvSegmentStart = (TextView) findViewById(R.id.tv_lvStar);
	}

}
