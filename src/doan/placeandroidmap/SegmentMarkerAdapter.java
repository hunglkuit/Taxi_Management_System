package doan.placeandroidmap;

import java.util.ArrayList;

import com.example.test112.Segment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SegmentMarkerAdapter extends ArrayAdapter<Segment> {
	Context mConText;
	int resource;
	ArrayList<Segment> segmentArray;
	Segment segment;
	public SegmentMarkerAdapter(Context context, int textViewResourceId,
			ArrayList<Segment> objects) {
		super(context, textViewResourceId, objects);
		this.mConText = context;
		this.resource = textViewResourceId;
		this.segmentArray = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null)
		{
			view = new SegmentMarkerView(getContext());
		}
		segment = segmentArray.get(position);
		if(segment != null)
		{
			TextView tv_Distance = ((SegmentMarkerView) view).tvSegmentDistance;
			TextView tv_Instruction = ((SegmentMarkerView) view).tvSegmentInstruction;
			TextView tv_Length = ((SegmentMarkerView) view).tvSegmentLength;
			TextView tv_Start = ((SegmentMarkerView) view).tvSegmentStart;
			
			if (segment.getStrDistance() != null) {
				tv_Distance.setText(segment.getStrDistance());
			}
			if (segment.getInstruction() != null) {
				tv_Instruction.setText(segment.getInstruction());
			}
			if (segment.getStart() != null) {
				tv_Start.setText(String.valueOf(segment.getStart().latitude)
						+ " : " + String.valueOf(segment.getStart().longitude));
			}
			if (segment.getLength() != 0) {
				tv_Length.setText(String.valueOf(segment.getLength()));
			}
		}
		return view;
	}
	
}
