package com.example.test112;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel.MapMode;
import java.text.Bidi;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.datatype.Duration;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import doan.placeandroidmap.Place;
import doan.placeandroidmap.PlaceList;
import doan.placeandroidmap.PlacesAdapter;
import doan.placeandroidmap.SegmentMarkerAdapter;

import com.example.test112.Customer;

import com.example.test112.GoogleParser;
import com.example.test112.MainActivity;
import com.example.test112.Parser;

import com.example.test112.R;

import com.example.test112.CreateURL;
import com.example.test112.IncomingCallReceiver;
import com.example.test112.Segment;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Interpolator;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RecoverySystem.ProgressListener;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.MediaStore.Audio.Media;
import android.provider.VoicemailContract;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.LineHeightSpan.WithDensity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class MainActivity extends FragmentActivity implements LocationListener {
	/**
	 * TODO Declare the variables
	 * 
	 */

	private LatLng srcPoint;

	private ArrayList<Segment> segment;
	private Route routeMain;

	private Button btn_place;
	private Button btn_cusdeny;
	private Button btn_finddrect;

	private EditText edt_place;

	private ImageView imPlaces;
	private ImageView imFind;
	private ImageView imGetCus;
	private ImageView imGetCusMn;
	private ImageView imBeGetCus;
	private ImageView imCallout;
	private ImageView imgPhone;
	private ImageView imgHomeSw;
	private ImageView imClear;
	private ImageView imPre;
	private ImageView imNext;

	private Dialog lvDialog;
	private Dialog dialog;
	private Dialog dialog_Drectail;
	private Dialog dialog_callout;
	private Dialog placeDialog;
	private Dialog dialog_Calldiff;
	private Dialog dialog_info;

	private ListView lv_place;
	private ListView lv_direcDetail;

	private ProgressDialog progressDialog;
	private ProgressDialog progressDialog1;
	private ProgressDialog progressDialog2;

	private TextView tv_placeInfor;
	private TextView tv_callout;
	private TextView tvCusInfo;
	private TextView tv_detailDirec;
	private EditText edt_InputPhoneNum;
	private Button btn_callNum;

	private double lat;
	private double lng;

	private float speed;

	private int flagToCheckThread;
	private int flagToGetCus;
	private int flagToCheckLocal;
	public int flagToDetailDirection;
	private int height;
	private int width;
	private int state;
	private int flagcurrentMarker;
	private int timetosleep;
	private int flagtobegetcus;
	private int flagtoregisvoip;
	private int flagtocheckcurrVoice;

	private LocationManager locationManager;

	private Location location;

	private PlaceList placeList;
	private PlaceList placeList1;

	private String term1;
	private String SdUserName;
	private String idDriver;
	private String placeQuery;
	private String token;
	private String idCustomer;
	private String sdpassWord;
	public String sipAddress = null;
	public String sipUser;
	public String sipPass;
	public String number;
	public String sipServer = "iptel.org";
	public String sipNumServer = "kimhung@iptel.org";

	private CreateURL url;

	private Thread checkLocation;

	private Handler handler;
	private Handler handler_callout;
	private Handler handler_1;

	private Runnable runnaDialog;
	private Runnable runa;

	private Drawable drawable;
	private Drawable dra;

	public SipManager manager = null;
	public SipProfile me = null;
	public SipAudioCall call = null;
	public IncomingCallReceiver callReceiver;
	private GoogleMap mMap;
	private Marker mTaxi;
	private Marker mPlace;
	private List<Marker> mManyPlace;
	private List<Marker> mManyPointMarker;

	private View mWindow;
	private ImageView findDirec;

	private Customer customer;

	private Polyline mPolyline;

	private View controlPlaceLayout;
	private View controlDirectLayout;
	private View controlLayout;

	private MediaPlayer playerCallOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Creat control layout.

		controlPlaceLayout = findViewById(R.id.controlPlaceLayout);
		controlDirectLayout = findViewById(R.id.controlDirecLayout);
		controlLayout = findViewById(R.id.controlLayout);

		// Receive Bundle infomation from login activity

		receiveBundleInfo();

		//
		setUpMapIfNeeded();

		// Create some value of variable.

		url = new CreateURL();
		state = 0;
		idCustomer = "unreceive";
		flagToCheckThread = 1;
		flagToGetCus = 0;
		flagToCheckLocal = 0;
		flagToDetailDirection = 0;
		flagcurrentMarker = 0;
		flagtobegetcus = 0;
		flagtoregisvoip = 0;
		flagtocheckcurrVoice = 0;
		timetosleep = 10000;
		playerCallOut = MediaPlayer.create(getApplicationContext(),
				R.raw.nhaccho1);
		playerCallOut.setLooping(true);
		// Create some views
		imPre = (ImageView) findViewById(R.id.imv_pre);
		imNext = (ImageView) findViewById(R.id.imv_next);
		imClear = (ImageView) findViewById(R.id.imv_clear);
		imBeGetCus = (ImageView) findViewById(R.id.imv_getcusfail);
		edt_place = (EditText) findViewById(R.id.edt_places);
		imPlaces = (ImageView) findViewById(R.id.imv_places);
		imFind = (ImageView) findViewById(R.id.imv_find);
		imgPhone = (ImageView) findViewById(R.id.imv_haveproblem);
		imgHomeSw = (ImageView) findViewById(R.id.imv_homesw);
		imGetCus = (ImageView) findViewById(R.id.imv_getcusok);
		imGetCus.setImageResource(R.drawable.close);
		imGetCus.setClickable(false);
		imGetCusMn = (ImageView) findViewById(R.id.imv_getcusmanu);
		findDirec = (ImageView) mWindow.findViewById(R.id.badge);

		dialog_Calldiff = new Dialog(this);
		dialog_Calldiff.setContentView(R.layout.numphonedialoglayout);
		edt_InputPhoneNum = (EditText) dialog_Calldiff
				.findViewById(R.id.edt_inputphone);
		btn_callNum = (Button) dialog_Calldiff
				.findViewById(R.id.btn_calldiffnum);

		// Create a dialog consist of listview to display places.

		lvDialog = new Dialog(this);
		lvDialog.setContentView(R.layout.listviewdialoglayout);
		lv_place = (ListView) lvDialog.findViewById(R.id.lv_place);
		btn_place = (Button) lvDialog.findViewById(R.id.btn_lvDismis);

		// Create a dialog to display customer infomation that taxi recieved
		// from server.

		dialog = new Dialog(this);
		dialog.setContentView(R.layout.customdialoglayout);
		dialog.setTitle("Customer Information");
		tvCusInfo = (TextView) dialog.findViewById(R.id.tv_cusInfo);
		Button btn_acc = (Button) dialog.findViewById(R.id.btn_cusRecAc);
		btn_cusdeny = (Button) dialog.findViewById(R.id.btn_cusRecDn);

		// Create a dialog to display the totural of direction.

		dialog_Drectail = new Dialog(this);
		dialog_Drectail.setContentView(R.layout.directiondetaillayout);
		dialog_Drectail.setTitle("Direction Detail");
		lv_direcDetail = (ListView) dialog_Drectail
				.findViewById(R.id.lv_direcDetail);
		tv_detailDirec = (TextView) dialog_Drectail
				.findViewById(R.id.tv_dorecDetail);

		// Create a dialog to display the Place information

		placeDialog = new Dialog(this);
		placeDialog.setContentView(R.layout.placedialoglayout);
		placeDialog.setTitle("Place Information");
		tv_placeInfor = (TextView) placeDialog.findViewById(R.id.tv_placeinfo);
		btn_finddrect = (Button) placeDialog.findViewById(R.id.btn_finddrec);

		// Create a dialog to display the Call out information

		dialog_callout = new Dialog(this);
		dialog_callout.setContentView(R.layout.calloutlayout);
		tv_callout = (TextView) dialog_callout
				.findViewById(R.id.tv_timeCallout);
		imCallout = (ImageView) dialog_callout.findViewById(R.id.img_callout);
		
		// Create a dialog to display the infomation's app
		
		dialog_info = new Dialog(this);
		dialog_info.setContentView(R.layout.inforlayout);

		// Check voip support and write to log file.

		Boolean voipSupported = SipManager.isVoipSupported(this);
		Boolean apiSupported = SipManager.isApiSupported(this);
		Log.i("voipSupport", voipSupported.toString());
		Log.i("aipSupport", apiSupported.toString());

		// Create a intent to listen call receive

		IntentFilter filter = new IntentFilter();
		filter.addAction("android.SipDemo.INCOMING_CALL");
		callReceiver = new IncomingCallReceiver();
		this.registerReceiver(callReceiver, filter);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// Set up account if os support voip

		if (voipSupported != null && apiSupported != null) {
			thietlapcuocgoi();
		}

		// Click Phone icon to call to server.

		imgPhone.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (flagtoregisvoip == 1) {
					// TODO Auto-generated method stub
					goi(sipNumServer);
				} else {
					Toast.makeText(getApplicationContext(),
							"Bạn chưa đăng ký thành công Voip",
							Toast.LENGTH_LONG).show();
				}

			}
		});

		// Get Location from GPS or internet.

		srcPoint = GetLocation();

		// Add taxi as a Marker.

		addTaxiMarker(srcPoint);
		changCamera(srcPoint);

		btn_callNum.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String phoneNum = edt_InputPhoneNum.getText().toString();
				if (flagtoregisvoip == 1) {
					// TODO Auto-generated method stub
					goi(phoneNum + "@" + sipServer);
				} else {
					Toast.makeText(getApplicationContext(),
							"Bạn chưa đăng ký thành công Voip",
							Toast.LENGTH_LONG).show();
				}

				dialog_Calldiff.dismiss();
			}
		});

		// Click to end the call

		imCallout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ketthuccuocgoi();
				dialog_callout.dismiss();
				playerCallOut.stop();
				try {
					playerCallOut.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (flagtocheckcurrVoice == 1) {
					ChecktoServer(url.createManageVoice(2, number, idDriver));
					flagtocheckcurrVoice = 0;
				}
			}
		});

		// Click this button to switch home control layout and find control
		// layout

		imgHomeSw.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {

				controlPlaceLayout.animate().setDuration(1000);
				controlPlaceLayout.animate().alpha(0);
				controlPlaceLayout.animate().setListener(
						new AnimatorListener() {

							@Override
							public void onAnimationEnd(Animator animation) {
								controlPlaceLayout.setVisibility(8);
								controlLayout.setVisibility(0);
								controlLayout.setAlpha(1);

							}

							@Override
							public void onAnimationCancel(Animator animation) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationRepeat(Animator animation) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onAnimationStart(Animator animation) {
								// TODO Auto-generated method stub

							}
						});

			}
		});

		// Click to item of listview to choice the places.

		lv_place.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> data, View arg1,
					int position, long arg3) {
				Place place = (Place) data.getItemAtPosition(position);
				addPlace(place);
				lvDialog.dismiss();
			}
		});

		// Click the button when taxi has taken the customer

		imGetCus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				if (flagToGetCus == 0) {
					state = 2;
					flagToGetCus = 1;
					imGetCus.animate().setDuration(1000);
					imGetCus.animate().rotationYBy(720);
					imGetCus.setImageResource(R.drawable.navigation3);

					// TODO : delete the polyline

					flagToDetailDirection = 0;
					mMap.clear();
					addTaxiMarker(srcPoint);
				} else {
					state = 1;
					ChecktoServer(url.createUpdateURL(SdUserName, srcPoint,
							speed, state, idCustomer, token));
					state = 0;
					imGetCus.animate().setDuration(1000);
					imGetCus.animate().rotationYBy(720);
					imGetCus.setImageResource(R.drawable.myway);
					idCustomer = null;
					imGetCus.setClickable(false);
					flagToGetCus = 0;
				}

			}
		});

		/*
		 * (non-Javadoc) TODO Click item in Listview to show the location
		 */

		lv_direcDetail.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> data, View arg1, int pos,
					long arg3) {
				Segment a = (Segment) data.getItemAtPosition(pos);
				mMap.moveCamera(CameraUpdateFactory.newLatLng(a.getStart()));
				dialog_Drectail.dismiss();
			}
		});

		/*
		 * (non-Javadoc) TODO Click to Find the Places
		 */

		imFind.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				flagToDetailDirection = 0;
				placeQuery = edt_place.getText().toString();
				if (placeQuery.contains("-") == true) {
					String[] infor = placeQuery.split("-");
					if (infor.length != 0) {
						findNearPlace find = new findNearPlace();
						find.execute();
					}
				} else {
					placeFindThread find1 = new placeFindThread();
					find1.execute(placeQuery);
				}
			}
		});

		/*
		 * (non-Javadoc) TODO Open Places control layout
		 */

		imPlaces.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				openFindPlaces();
			}
		});

		/*
		 * (non-Javadoc) TODO Click to accept the customer's information from
		 * server.
		 */

		btn_acc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				synchronized (runnaDialog) {
					imGetCus.setClickable(true);
					addItemAndDrawRoute(srcPoint, customer.getSourceLocation());
					showDirectLayoutFromCL();
					mMap.addMarker(new MarkerOptions()
							.position(customer.getSourceLocation())
							.title("Customer")
							.snippet(
									customer.getName() + "\n"
											+ customer.getPhoneNumber() + "\n"
											+ customer.getDesAddress())
							.icon(BitmapDescriptorFactory.defaultMarker()));
					flagToCheckLocal = 1;
					state = 3;
					idCustomer = customer.getId();
					dialog.dismiss();
				}
				synchronized (runa) {
					runa.notifyAll();
				}
			}
		});

		mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(final Marker marker) {
				if (marker.equals(mPlace)) {
					Toast.makeText(getApplicationContext(),
							marker.getSnippet(), Toast.LENGTH_LONG).show();

					addItemAndDrawRoute(srcPoint, marker.getPosition());
					showDirectLayout();
				}
				if (mManyPlace != null) {
					if (mManyPlace.contains(marker)) {
						addItemAndDrawRoute(srcPoint, marker.getPosition());
						showDirectLayout();
					}
				}
				marker.hideInfoWindow();
			}
		});

		/*
		 * (non-Javadoc) TODO Click to deny customer from server .
		 */

		btn_cusdeny.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				idCustomer = term1;
				state = 4;
				ChecktoServer(url.createUpdateURL(SdUserName, srcPoint, speed,
						state, idCustomer, token));
				idCustomer = "null";
				state = 0;
				dialog.dismiss();
				synchronized (runa) {
					runa.notifyAll();
				}
			}
		});

		/*
		 * (non-Javadoc) TODO Click when taxi take customer on the street. .
		 */

		imGetCusMn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if (state == 0) {
					state = 2;
					idCustomer = "unknow";
					imGetCusMn.animate().setDuration(1000);
					imGetCusMn.animate().rotationYBy(720);
					imGetCusMn.setImageResource(R.drawable.navigation3);
					Toast.makeText(getApplicationContext(),
							"Bạn đã bắt khách thành công", Toast.LENGTH_LONG)
							.show();
					imGetCus.animate().setDuration(1000);
					imGetCus.animate().rotationYBy(720);
					imGetCus.setImageResource(R.drawable.close);
					imGetCus.setClickable(false);
					imBeGetCus.animate().setDuration(1000);
					imBeGetCus.animate().rotationYBy(720);
					imBeGetCus.setImageResource(R.drawable.close);
					imBeGetCus.setClickable(false);
				} else {
					state = 1;
					ChecktoServer(url.createUpdateURL(SdUserName, srcPoint,
							speed, state, idCustomer, token));
					state = 0;
					idCustomer = "null";
					imGetCusMn.animate().setDuration(1000);
					imGetCusMn.animate().rotationYBy(720);
					imGetCusMn.setImageResource(R.drawable.navigation);
					Toast.makeText(getApplicationContext(),
							"Bạn đã trả khách thành công", Toast.LENGTH_LONG)
							.show();
					imGetCus.animate().setDuration(1000);
					imGetCus.animate().rotationYBy(720);
					if (flagtobegetcus == 1) {
						imGetCus.setImageResource(R.drawable.myway);
					} else {
						imGetCus.setImageResource(R.drawable.close);
					}
					imGetCus.setClickable(true);
					imBeGetCus.animate().setDuration(1000);
					imBeGetCus.animate().rotationYBy(720);
					imBeGetCus.setImageResource(R.drawable.myphoneexplorer);
					imBeGetCus.setClickable(true);
				}
			}
		});

		/*
		 * (non-Javadoc) TODO Click to begin get customer from server
		 */
		imBeGetCus.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				if (flagtobegetcus == 0) {
					idCustomer = "null";
					state = 0;
					imGetCus.animate().setDuration(1000);
					imGetCus.animate().rotationYBy(720);
					imGetCus.setImageResource(R.drawable.myway);
					imGetCus.setClickable(true);
					flagtobegetcus = 1;
				} else {
					state = 0;
					idCustomer = "unreceive";
					imGetCus.animate().setDuration(1000);
					imGetCus.animate().rotationYBy(720);
					imGetCus.setImageResource(R.drawable.close);
					imGetCus.setClickable(false);
					flagtobegetcus = 0;
				}
			}
		});

		/*
		 * (non-Javadoc) TODO Clear the direction and change layout
		 */

		imClear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				flagToDetailDirection = 0;
				mMap.clear();
				addTaxiMarker(srcPoint);
				showControlLayout();
			}
		});

		/*
		 * (non-Javadoc) TODO Next marker when you want to find the location.
		 */

		imNext.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (flagcurrentMarker < mManyPointMarker.size() - 1) {
					if (mManyPointMarker.get(flagcurrentMarker)
							.isInfoWindowShown() == true) {
						mManyPointMarker.get(flagcurrentMarker)
								.hideInfoWindow();
					}
					flagcurrentMarker++;
					if (mManyPointMarker != null) {
						mManyPointMarker.get(flagcurrentMarker)
								.showInfoWindow();
						mMap.animateCamera(CameraUpdateFactory
								.newLatLng(mManyPointMarker.get(
										flagcurrentMarker).getPosition()));
					}

				} else {
					Toast.makeText(getApplicationContext(),
							"Can't not show next marker", Toast.LENGTH_LONG)
							.show();
				}

			}
		});

		/*
		 * (non-Javadoc) TODO Pre marker when you want to find the location.
		 */

		imPre.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (flagcurrentMarker > 0) {
					if (mManyPointMarker.get(flagcurrentMarker)
							.isInfoWindowShown() == true) {
						mManyPointMarker.get(flagcurrentMarker)
								.hideInfoWindow();
					}
					flagcurrentMarker--;
					mManyPointMarker.get(flagcurrentMarker).showInfoWindow();
					mMap.animateCamera(CameraUpdateFactory
							.newLatLng(mManyPointMarker.get(flagcurrentMarker)
									.getPosition()));
				} else {
					Toast.makeText(getApplicationContext(),
							"Can't not show previous marker", Toast.LENGTH_LONG)
							.show();
				}

			}
		});
		handler_1 = new Handler();
		handler = new Handler();
		handler_callout = new Handler();
		threadForCheckLocation();
	}

	/**
	 * Thread use to check location to server.
	 */

	public void threadForCheckLocation() {

		runa = new Runnable() {
			public void run() {
				// TODO Thead for send location to server
				while (flagToCheckThread != 0) {
					String result = ChecktoServer(url.createUpdateURL(
							SdUserName, srcPoint, speed, state, idCustomer,
							token));

					if (result != null) {
						try {
							JSONArray jsonRes = new JSONArray(result);
							result = jsonRes.getJSONObject(0).getString(
									"Result");
							if (jsonRes.getJSONObject(2).isNull(
									"ConferenceInfo") != true) {
								JSONObject jsonConfe = jsonRes.getJSONObject(2)
										.getJSONObject("ConferenceInfo");
								Log.i("Conferrence info", jsonConfe.toString());
								if (jsonConfe != null) {
									number = jsonConfe.getString("number");
									if (flagtoregisvoip == 1) {
										// TODO Auto-generated method stub
										handler_1.post(new Runnable() {

											@Override
											public void run() {
												flagtocheckcurrVoice = 1;
												goi(number + "@" + sipServer);
											}
										});
										ChecktoServer(url.createManageVoice(1,
												number, idDriver));
									} else {
										handler_1.post(new Runnable() {

											@Override
											public void run() {
												Toast.makeText(
														getApplicationContext(),
														"Bạn chưa đăng ký thành công Voip",
														Toast.LENGTH_LONG)
														.show();
											}
										});
									}
									Log.i("Call reference",
											"You have to call to the Server by number:"
													+ number);

								}
							}
							if (jsonRes.getJSONObject(1).isNull("RequestTime") != true) {
								JSONObject jsonInfor = jsonRes.getJSONObject(1)
										.getJSONObject("RequestTime");
								timetosleep = jsonInfor.getInt("requestTime");
								Log.i("Time to way in thread",
										String.valueOf(timetosleep));
							}

						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (state == 3) {
							Log.i("Check Location:", "State = 3");
						}
						if (result.equals("ok") && state == 0) {
							Log.i("Check Location:", "Send location ok");
						}
						if (result.equals("ok") && state == 5) {
							Log.i("Check Location:", "Send location ok");
						}
						if (state == 2 && (result.equals("ok") == false)) {
							customer = takeCustomer(result);
							idCustomer = customer.getId();
						}
						if (state == 0 && (result.equals("ok") == false)) {
							customer = takeCustomer(result);
							term1 = customer.getId();
							runnaDialog = new Runnable() {
								public void run() {
									tvCusInfo.setText(customer
											.getSourceAddress()
											+ "-"
											+ customer.getName()
											+ "-"
											+ customer.getPhoneNumber());
									dialog.show();

									Log.i("Check Location:",
											"Receive customer's information");

								}
							};
							handler.post(runnaDialog);
							synchronized (this) {
								try {
									wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						try {
							Thread.sleep(timetosleep * 1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						Log.i("Error to check server",
								" Can not to check server. ");
					}

				}

			}
		};

		checkLocation = new Thread(runa);
		checkLocation.start();
	}

	/**
	 * Use to receive information customer from server.
	 */

	public Customer takeCustomer(String server) {
		Customer customer = null;
		String result = server;
		try {
			JSONObject json = new JSONObject(result);
			String name = json.getString("name");
			String id = json.getString("idCustomer");
			String phone = json.getString("phonenumber");
			String time = json.getString("recieveTime");
			String soAdd = json.getString("sourceAddress");
			JSONObject jsonLoca = json.getJSONObject("sourceLocation");
			LatLng soLoca = new LatLng(
					Double.valueOf(jsonLoca.getString("Lat")),
					Double.valueOf(jsonLoca.getString("Long")));
			String desAdd = json.getString("destinationAddress");
			JSONObject jsonDesLoca = json.getJSONObject("destinationLocation");
			LatLng desLoca = new LatLng(Double.valueOf(jsonDesLoca
					.getString("Lat")), Double.valueOf(jsonDesLoca
					.getString("Long")));
			customer = new Customer(id, name, phone, time, soAdd, soLoca,
					desAdd, desLoca);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return customer;
	}

	/**
	 * Use to draw a direction between two point in the map
	 */

	public void addItemAndDrawRoute(LatLng src, LatLng des) {

		Route route = directions(src, des);
		segment = route.getSegments();
		routeMain = route;
		String aa = "";
		for (int i = 0; i < segment.size(); i++) {
			aa = aa + "++--++" + segment.get(i).getInstruction() + "(-)"
					+ segment.get(i).getStrDistance();
		}
		Log.i("instruction", aa);

		if (mPolyline == null) {
			PolylineOptions plo = new PolylineOptions();
			plo.geodesic(true);
			plo.color(Color.BLUE);
			plo.width(10);
			plo.addAll(route.getPoints());
			mPolyline = mMap.addPolyline(plo);
			if (mManyPointMarker != null) {
				mManyPointMarker.clear();
			} else {
				mManyPointMarker = new ArrayList<Marker>();
			}
			for (int i = 0; i < segment.size(); i++) {
				MarkerOptions mMarkerOption = new MarkerOptions();
				mMarkerOption.position(segment.get(i).getStart());
				mMarkerOption.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.pointmarker));
				mMarkerOption.title("");
				mMarkerOption.snippet(segment.get(i).getInstruction());
				mManyPointMarker.add(mMap.addMarker(mMarkerOption));
			}
			mMap.moveCamera(CameraUpdateFactory.newLatLng(src));
		} else {
			mPolyline.getPoints().clear();
			mPolyline.setPoints(route.getPoints());
			mMap.moveCamera(CameraUpdateFactory.newLatLng(src));
		}
		// showDirectLayout();
	}

	/**
	 * Show direct layout from control layout
	 */
	public void showDirectLayoutFromCL() {

		controlLayout.animate().setDuration(1000);
		controlLayout.animate().alpha(0);
		controlLayout.animate().setListener(new AnimatorListener() {

			@Override
			public void onAnimationEnd(Animator animation) {
				controlLayout.setVisibility(8);
				controlDirectLayout.setVisibility(0);
				controlDirectLayout.setAlpha(1);
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}
		});

		flagToDetailDirection = 1;
	}

	/**
	 * Show direct layout
	 */
	public void showDirectLayout() {

		controlPlaceLayout.animate().setDuration(1000);
		controlPlaceLayout.animate().alpha(0);
		controlPlaceLayout.animate().setListener(new AnimatorListener() {

			@Override
			public void onAnimationEnd(Animator animation) {
				controlPlaceLayout.setVisibility(8);
				controlDirectLayout.setVisibility(0);
				controlDirectLayout.setAlpha(1);
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}
		});

		flagToDetailDirection = 1;
	}

	/**
	 * Show control layout
	 */

	public void showControlLayout() {

		controlDirectLayout.animate().setDuration(1000);
		controlDirectLayout.animate().alpha(0);
		controlDirectLayout.animate().setListener(new AnimatorListener() {

			@Override
			public void onAnimationEnd(Animator animation) {
				controlDirectLayout.setVisibility(8);
				controlLayout.setVisibility(0);
				controlLayout.setAlpha(1);

			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}
		});

	}

	class CustomInfoWindowAdapter implements InfoWindowAdapter {

		CustomInfoWindowAdapter() {
			mWindow = getLayoutInflater().inflate(R.layout.custom_info_window,
					null);
		}

		@Override
		public View getInfoWindow(Marker marker) {
			render(marker, mWindow);
			return mWindow;
		}

		private void render(Marker marker, View view) {

			findDirec.setImageResource(R.drawable.directions);
			String title = marker.getTitle();
			TextView titleUi = ((TextView) view.findViewById(R.id.title));
			if (title != null) {
				if (title.equals("") == false) {
					// Spannable string allows us to edit the formatting of the
					// text.
					SpannableString titleText = new SpannableString(title);
					titleText.setSpan(new ForegroundColorSpan(Color.RED), 0,
							titleText.length(), 0);
					titleUi.setText(titleText);
				} else {
					findDirec.setVisibility(8);
					titleUi.setVisibility(8);
				}
			} else {
				titleUi.setText("");
			}

			String snippet = marker.getSnippet();
			TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
			if (snippet != null) {
				SpannableString snippetText = new SpannableString(snippet);
				snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0,
						snippet.length(), 0);
				snippetUi.setText(snippetText);
			} else {
				snippetUi.setText("");
			}
		}

		@Override
		public View getInfoContents(Marker arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	/*
	 * (non-Javadoc) TODO Get route from google direction
	 */

	private Route directions(final LatLng start, final LatLng dest) {
		Parser parser;
		String jsonURL = "http://maps.google.com/maps/api/directions/json?";
		final StringBuffer sBuf = new StringBuffer(jsonURL);
		sBuf.append("origin=");
		sBuf.append(start.latitude);
		sBuf.append(',');
		sBuf.append(start.longitude);
		sBuf.append("&destination=");
		sBuf.append(dest.latitude);
		sBuf.append(',');
		sBuf.append(dest.longitude);
		sBuf.append("&language=vi&sensor=true&mode=driving");
		parser = new GoogleParser(sBuf.toString());
		Log.i("URL", sBuf.toString());
		Route r = parser.parse();
		return r;
	}

	/**
	 * Use to open the Find control layout.
	 */

	private void openFindPlaces() {

		controlLayout.animate().setDuration(1000);
		controlLayout.animate().alpha(0);
		controlLayout.animate().setListener(new AnimatorListener() {
			@Override
			public void onAnimationEnd(Animator animation) {
				controlLayout.setVisibility(8);
				controlPlaceLayout.setVisibility(0);
				controlPlaceLayout.setAlpha(1);
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}
		});

	}

	/**
	 * The main method use to find the place.
	 */

	private PlaceList placeFind(String query) {
		Parser parser;
		String jsonURL = "https://maps.googleapis.com/maps/api/place/textsearch/json?";
		final StringBuffer sBuf = new StringBuffer(jsonURL);
		sBuf.append("query=");
		sBuf.append(query.replaceAll(" ", "%20"));
		sBuf.append("&sensor=true&key=AIzaSyDP4KHPd4YXFspp9b77usqjN9ygBUnKppE");
		parser = new GoogleParser(sBuf.toString());
		Log.i("URL", sBuf.toString());
		PlaceList p = parser.parseToPlaces();

		/*for (int i = 0; i < p.getListPlace().size(); i++) {
			placeDetail(p.getListPlace().get(i).getReference(), p
					.getListPlace().get(i));
		}*/
		return p;
	}

	/**
	 * Find the exact place.
	 */

	private class placeFindThread extends AsyncTask<String, Void, PlaceList> {

		@Override
		protected PlaceList doInBackground(String... params) {
			return placeFind(params[0]);
		}

		@Override
		protected void onPostExecute(PlaceList result) {
			// TODO Auto-generated method stub
			ArrayList<Place> places = result.getListPlace();
			PlacesAdapter placeAdapter = new PlacesAdapter(
					getApplicationContext(), R.layout.listplacelayout, places);
			lv_place.setAdapter(placeAdapter);
			progressDialog.dismiss();
			lvDialog.show();

		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog
					.show(MainActivity.this, "Loading...",
							"Loading place, please wait...", false, false);
		}

	}

	/**
	 * Find the place near place.
	 */

	private class findNearPlace extends
			AsyncTask<Void, List<Place>, List<Place>> {
		@Override
		protected void onPreExecute() {
			progressDialog1 = ProgressDialog
					.show(MainActivity.this, "Loading...",
							"Loading place, please wait...", false, false);
		}

		@Override
		protected List<Place> doInBackground(Void... params) {
			placeQuery = edt_place.getText().toString();
			String[] infor;
			if (placeQuery.contains("-") == true) {
				infor = placeQuery.split("-");
				if (infor.length != 0) {
					placeList = placeNearMe(srcPoint,
							Integer.valueOf(infor[1]), infor[0]);
				}
			}
			if (placeList != null) {
				List<Place> places = placeList.getListPlace();
				publishProgress(places);
				return places;
			} else {
				publishProgress(null);
				return null;
			}
		}

		@Override
		protected void onProgressUpdate(List<Place>... values) {

		}

		@Override
		protected void onPostExecute(List<Place> result) {
			progressDialog1.dismiss();
			if (mManyPlace != null) {
				mManyPlace.clear();

			} else {
				mManyPlace = new ArrayList<Marker>();
			}
			placeQuery = edt_place.getText().toString();
			String[] infor = null;
			if (placeQuery.contains("-") == true) {
				infor = placeQuery.split("-");
				int count = 0;
				if (result != null) {
					LatLng p;
					for (int i = 0; i < result.size(); i++) {
						p = result.get(i).getLocation();

						Log.i("Place type", result.get(i).getTypes());
						Log.i("Name", infor[0]);
						Log.i("Boolean place type",
								String.valueOf(result.get(i).getTypes()
										.contains(infor[0])));

						if (result.get(i).getTypes().contains(infor[0]) == true) {
							mManyPlace.add(addManyPlace(result.get(i)));
							count++;
						}
					}
					if (count == 0) {
						Toast.makeText(getApplicationContext(),
								"Không tìm thấy địa điểm", Toast.LENGTH_LONG)
								.show();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Không tìm thấy địa điểm", Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	/**
	 * Find the place near me.
	 */

	private PlaceList placeNearMe(final LatLng position, int radius, String type) {
		Parser parser;
		String pagetoken = null;
		String jsonURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
		// location=10.796243,106.674612&radius=500&type=food&sensor=false&key=AIzaSyDP4KHPd4YXFspp9b77usqjN9ygBUnKppE";
		final StringBuffer sBuf = new StringBuffer(jsonURL);
		sBuf.append("location=");
		sBuf.append(position.latitude);
		sBuf.append(',');
		sBuf.append(position.longitude);
		sBuf.append("&radius=");
		sBuf.append(String.valueOf(radius));
		sBuf.append("&type=");
		sBuf.append(type);
		sBuf.append("&sensor=false&key=AIzaSyDP4KHPd4YXFspp9b77usqjN9ygBUnKppE");
		parser = new GoogleParser(sBuf.toString());
		Log.i("URL", sBuf.toString());
		PlaceList p = parser.parseToPlaces();
		if(p.getStatus() != null)
		{
			pagetoken = p.getStatus();
		}
		while(pagetoken != null)
		{
			PlaceList q = (new GoogleParser(sBuf.toString()+"&pagetoken="+pagetoken).parseToPlaces());
			p.getListPlace().addAll(q.getListPlace());
			if(q.getStatus() != null)
			{
				pagetoken = q.getStatus();
			}else
			{
				pagetoken = null;
			}
		}

		/*for (int i = 0; i < p.getListPlace().size(); i++) {
			placeDetail(p.getListPlace().get(i).getReference(), p
					.getListPlace().get(i));
		}*/
		return p;
	}

	/**
	 * Get place detail from Google
	 */

	public void placeDetail(String reference, Place place) {
		Parser parser;
		String jsonURL = "https://maps.googleapis.com/maps/api/place/details/json?reference=";
		StringBuilder sBuf = new StringBuilder(jsonURL);
		sBuf.append(reference);
		sBuf.append("&sensor=true&key=AIzaSyDP4KHPd4YXFspp9b77usqjN9ygBUnKppE");
		parser = new GoogleParser(sBuf.toString());
		Log.i("URL", sBuf.toString());
		parser.parseDetailPlace(place);
	}

	/**
	 * Check server to receive infomation.
	 */

	public String ChecktoServer(String a) {
		String _respone = null;
		HttpRequestBase get;
		HttpClient client = new DefaultHttpClient();
		InputStream instream = null;
		StringBuffer stringBuffer = new StringBuffer();
		int len = 0;
		get = new HttpGet(a);
		try {
			HttpResponse response = client.execute(get);
			if (response != null) {
				byte[] data = new byte[1024];
				instream = response.getEntity().getContent();
				while (-1 != (len = instream.read(data))) {
					stringBuffer.append(new String(data, 0, len));
				}
				_respone = stringBuffer.toString();
				instream.close();
			}
		} catch (Exception e) {
			Log.i("Error when check infomation from server", e.toString());
		}
		return _respone;
	}

	/**
	 * Add a many place into the Map.
	 */

	public Marker addManyPlace(Place place) {
		LatLng p = place.getLocation();
		String infoPlace = "Tên:";
		StringBuilder inPlace = new StringBuilder(infoPlace);
		MarkerOptions mko = new MarkerOptions();
		if (place != null) {
			if (place.getName() != null) {
				inPlace.append(place.getName());
			}
			inPlace.append("\n");
			inPlace.append("Địa chỉ:");
			if (place.getFormatted_address() != null) {
				inPlace.append(place.getFormatted_address());
			}
			if (place.getVicinity() != null) {
				inPlace.append("hay"+place.getVicinity());
			}
			inPlace.append("\n");
			inPlace.append("Điện thoại: ");
			if (place.getFormatted_phone_number() != null) {
				inPlace.append(place.getFormatted_phone_number());
			}
			inPlace.append("\n");
			inPlace.append("Website:");
			if (place.getWebsite() != null) {
				inPlace.append(place.getWebsite());
			}
			String info = inPlace.toString();
			mko.title("Thông tin địa điểm");
			mko.snippet(info);
			mko.icon(BitmapDescriptorFactory.defaultMarker());
			mko.position(p);
		}
		return mMap.addMarker(mko);
	}

	/**
	 * Add a place into the Map.
	 */

	public void addPlace(Place place) {
		final LatLng p = place.getLocation();
		String infoPlace = "Tên:";
		StringBuilder inPlace = new StringBuilder(infoPlace);
		MarkerOptions mko = new MarkerOptions();
		if (place != null) {
			if (place.getName() != null) {
				inPlace.append(place.getName());
			}
			inPlace.append("\n");
			inPlace.append("Địa chỉ:");
			if (place.getFormatted_address() != null) {
				inPlace.append(place.getFormatted_address());
			}
			inPlace.append("\n");
			inPlace.append("Điện thoại: ");
			if (place.getFormatted_phone_number() != null) {
				inPlace.append(place.getFormatted_phone_number());
			}
			inPlace.append("\n");
			inPlace.append("Website:");
			if (place.getWebsite() != null) {
				inPlace.append(place.getWebsite());
			}
			String info = inPlace.toString();
			mko.title("Thông tin địa điểm");
			mko.snippet(info);
			mko.icon(BitmapDescriptorFactory.defaultMarker());
			mko.position(p);
			// if (mPlace == null) {
			mPlace = mMap.addMarker(mko);
			/*
			 * } else { mPlace.setPosition(p);
			 * mPlace.setTitle("Place Infomation"); mPlace.setSnippet(info); }
			 */

			mMap.animateCamera(CameraUpdateFactory.newLatLng(p));

			/*final Handler handlerMarker = new Handler();
			final long start = SystemClock.uptimeMillis();
			Projection proj = mMap.getProjection();
			Point startPoint = proj.toScreenLocation(p);
			startPoint.offset(0, -100);
			final LatLng startLatLng = proj.fromScreenLocation(startPoint);
			final long duration = 2000;

			final android.view.animation.Interpolator interpolator = new BounceInterpolator();

			handlerMarker.post(new Runnable() {
				@Override
				public void run() {
					long elapsed = SystemClock.uptimeMillis() - start;
					float t = interpolator.getInterpolation((float) elapsed
							/ duration);
					double lng = t * p.longitude + (1 - t)
							* startLatLng.longitude;
					double lat = t * p.latitude + (1 - t)
							* startLatLng.latitude;
					mPlace.setPosition(new LatLng(lat, lng));
					if (t < 1.0) {
						// Post again 16ms later.
						handlerMarker.postDelayed(this, 16);
					}
				}
			});*/
		}
	}

	/**
	 * Add taxi into the Map
	 */

	private void addTaxiMarker(LatLng p) {
		if (p != null) {
			MarkerOptions mko = new MarkerOptions();
			mko.icon(BitmapDescriptorFactory.fromResource(R.drawable.taxi));
			mko.position(p);
			mko.title("Taxi");
			mko.snippet("This is your Taxi");
			mTaxi = mMap.addMarker(mko);
			Log.i("Marker", "has been added a taxi marker");
			// changCamera(p);

		} else {
			Toast.makeText(getBaseContext(), "Please turn on GPS",
					Toast.LENGTH_LONG).show();
		}

	}

	/**
	 * Get Location from GPS or internet.
	 */

	private LatLng GetLocation() {
		LatLng result = null;
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (locationManager == null) {
			Toast.makeText(this, "Location Manager Not Available",
					Toast.LENGTH_SHORT).show();
			return null;
		}
		location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 10f, this);
		if (location == null) {
			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 1000, 10f, this);
		}
		if (location == null) {
			Criteria criteria = new Criteria();
			criteria.setAccuracy(Criteria.ACCURACY_COARSE);
			String provider = locationManager.getBestProvider(criteria, false);
			location = locationManager.getLastKnownLocation(provider);
			locationManager.requestLocationUpdates(provider, 400, 1, this);
		}
		if (location == null) {
			Toast.makeText(getApplicationContext(), "Can not get Location",
					Toast.LENGTH_LONG).show();
		}
		if (location != null) {
			lat = location.getLatitude();
			lng = location.getLongitude();
			speed = location.getSpeed();
			Log.i("Location", String.valueOf(lat) + "+" + String.valueOf(lng)
					+ "speed: " + String.valueOf(speed));
			result = new LatLng(lat, lng);
		}
		return result;
	}

	/**
	 * Set up voip call
	 */

	public void thietlapcuocgoi() {
		if (manager == null) {
			manager = SipManager.newInstance(this);
		}
		thietlapprofile();
	}

	/**
	 * Set up voip profile
	 */

	public void thietlapprofile() {
		if (manager == null) {
			return;
		}
		if (me != null) {
			dongcuocgoi();
		}
		SipProfile.Builder builder = null;
		try {
			builder = new SipProfile.Builder(sipUser, sipServer);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		builder.setPassword(sipPass);
		me = builder.build();

		Intent i = new Intent();
		i.setAction("android.SipDemo.INCOMING_CALL");
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i,
				Intent.FILL_IN_DATA);
		try {
			manager.open(me, pi, null);
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			manager.setRegistrationListener(me.getUriString(),
					new SipRegistrationListener() {

						public void onRegistrationFailed(
								String localProfileUri, int errorCode,
								String errorMessage) {
							Log.i("regis", "fail -- " + "Local profileUri:"
									+ localProfileUri + " --- Error Message:"
									+ errorMessage + "--" + "Error code:"
									+ errorCode);
						}

						public void onRegistrationDone(String localProfileUri,
								long expiryTime) {
							flagtoregisvoip = 1;
							Log.i("regis", "Done");
						}

						public void onRegistering(String localProfileUri) {
							Log.i("regis", "registing");

						}
					});
		} catch (SipException e) {
			Log.i("Sip erro when registering", e.toString());
		}
	}

	/**
	 * End a voip call
	 */
	public void ketthuccuocgoi() {
		try {
			call.endCall();
		} catch (SipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dongcuocgoi() {
		if (manager == null) {
			return;
		}
		if (me != null) {
			try {
				manager.close(me.getUriString());
			} catch (SipException e) {
				// TODO Auto-generated catch block
				Log.d("Sip", "Failed to close local profile.", e);
			}
		}
	}

	/**
	 * This method use to receive bundle information
	 */

	void goi(String sodt) {
		dialog_callout.show();
		tv_callout.setText("Bạn đang gọi tới" + sodt);
		SipAudioCall.Listener listener = new SipAudioCall.Listener() {

			@Override
			public void onCallBusy(SipAudioCall call) {
				playerCallOut.stop();
				try {
					playerCallOut.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dialog_callout.dismiss();
			}

			@Override
			public void onError(SipAudioCall call, int errorCode,
					String errorMessage) {
				playerCallOut.stop();
				try {
					playerCallOut.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dialog_callout.dismiss();
				Toast.makeText(getApplicationContext(), errorMessage,
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onCalling(SipAudioCall call) {

				playerCallOut.start();
			}

			@Override
			public void onCallEstablished(SipAudioCall call) {
				playerCallOut.stop();
				try {
					playerCallOut.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				call.startAudio();
				call.setSpeakerMode(true);
				if (call.isMuted()) {
					call.toggleMute();
					Log.i("Sip Out Call", "has Muted");
				}
				handler_callout.post(new Runnable() {

					public void run() {
						// TODO Auto-generated method stub
						tv_callout.setText("Đã thiết lập cuộc gọi");
					}
				});
				Log.i("Phone Status", "Dang thiet lap cuoc goi");
			}

			@Override
			public void onCallEnded(SipAudioCall call) {
				if (dialog_callout.isShowing() == true) {
					dialog_callout.dismiss();
				}
				Log.i("Phone Status", "Ket thuc cuoc goi");
			}
		};
		try {
			call = manager.makeAudioCall(me.getUriString(), sodt, listener, 30);
			Log.i("Sip State of call", String.valueOf(call.getState()));
		} catch (SipException e) {
			// TODO Auto-generated catch block
			Log.i("Sip call", "Error when trying to close manager.", e);
			if (me != null) {
				try {
					manager.close(me.getUriString());
				} catch (Exception ee) {
					Log.i("SipCall", "Error when trying to close manager.", ee);
					ee.printStackTrace();
				}
			}
			if (call != null) {
				call.close();
			}
		}
	}

	/**
	 * This method use to receive bundle information
	 */
	public void receiveBundleInfo() {
		Bundle bUser = this.getIntent().getExtras();
		SdUserName = bUser.getString("user");
		// SdUserName = "anhlt";
		// sipUser = bUser.getString("sipNum");
		// sipUser = SdUserName;
		sipUser = "kimhung";
		Log.i("Sip User", sipUser);
		// sipPass = bUser.getString("sipPass");
		sipPass = "123456";
		Log.i("Sip Pass", sipPass);
		token = bUser.getString("token");
		// token = "353ea755-eef2-434b-ba99-51279414cb8b";
		sdpassWord = bUser.getString("pass");
		idDriver = bUser.getString("idDriver");
		Log.i("idDriver", idDriver);
	}

	public void changCamera(LatLng newlatln) {
		if (newlatln != null) {
			CameraPosition newCamera = new CameraPosition.Builder()
					.target(newlatln).zoom(15.5f).bearing(300).tilt(50).build();
			mMap.animateCamera(CameraUpdateFactory.newCameraPosition(newCamera));
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
				mMap.setMyLocationEnabled(true);
			}
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if (mTaxi != null) {
			mTaxi.setPosition(new LatLng(location.getLatitude(), location
					.getLongitude()));
		}
		srcPoint = new LatLng(location.getLatitude(), location.getLongitude());
		speed = location.getSpeed();
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/**
	 * Create Menu
	 */

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (item.getItemId() == R.id.menu_settings) {
			ChecktoServer(url.createLogoutURL(SdUserName, sdpassWord, token));
			dongcuocgoi();
			System.exit(0);
		}
		if (item.getItemId() == R.id.menu_detail && flagToDetailDirection == 1) {
			SegmentMarkerAdapter valueAdapter = new SegmentMarkerAdapter(
					getApplicationContext(), R.layout.listsegmentlayout,
					segment);
			lv_direcDetail.setAdapter(valueAdapter);
			tv_detailDirec.setText("Từ: " + routeMain.getName()
					+ "   Khoảng Cách: " + routeMain.getLength() + "m");
			dialog_Drectail.show();
		}
		if (item.getItemId() == R.id.menu_refresh) {
			flagToDetailDirection = 0;
			mMap.clear();
			changCamera(srcPoint);
			addTaxiMarker(srcPoint);
		}
		if (item.getItemId() == R.id.menu_haveproblem) {
			idCustomer = null;
			state = 5;
			imGetCus.animate().setDuration(1000);
			imGetCus.animate().rotationYBy(720);
			imGetCus.setImageResource(R.drawable.close);
			imGetCus.setClickable(false);
			flagtobegetcus = 0;
		}
		if (item.getItemId() == R.id.menu_callst) {
			dialog_Calldiff.show();
		}
		if(item.getItemId() == R.id.menu_about){
			dialog_info.show();
		}
		return false;
	}

	@Override
	protected void onPause() {
		flagToCheckThread = 0;
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		ChecktoServer(url.createLogoutURL(SdUserName, sdpassWord, token));
		Log.i("On detroy", "On detroy has been call");
		flagToCheckThread = 0;
		super.onDestroy();
		if (call != null) {
			call.close();
		}
		dongcuocgoi();
		if (callReceiver != null) {
			this.unregisterReceiver(callReceiver);
		}
	}
}