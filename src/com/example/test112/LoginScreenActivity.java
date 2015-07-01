package com.example.test112;

import java.io.InputStream;
import java.util.UUID;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreenActivity extends Activity {
	public static String staticUrl = "http://testtaxi11.jelastic.dogado.eu";
	public static final String myURI = staticUrl
			+ "/TaxiManagementSystem/resources/login?";
	public static final String myURIChange = staticUrl
			+ "/TaxiManagementSystem/resources/query/changePassword?";
	private EditText edtUserName;
	private EditText edtPassWord;
	private EditText edtUserName1;
	private EditText edtPassWord1;
	private EditText edtPassWordnew1;
	private EditText edtPassWordnew2;
	private ImageView btnLogin;
	private ImageView btnChangePass;
	private ProgressDialog progressDialog;
	private TextView tv_changpass;
	private TextView tv_changpassre;
	private View layoutchangpass;
	private View layoutlogin;
	private int flagtocheckGPS = 0;
	private int flagtocheckNetwork = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginlayout);
		CheckGPS();
		btnChangePass = (ImageView) findViewById(R.id.btnchangepass);
		edtUserName1 = (EditText) findViewById(R.id.edtusername1);
		edtPassWord1 = (EditText) findViewById(R.id.edtpassold);
		edtPassWordnew1 = (EditText) findViewById(R.id.edtPassworknew1);
		edtPassWordnew2 = (EditText) findViewById(R.id.edtPassworknew2);
		layoutchangpass = findViewById(R.id.layoutchangpass);
		layoutlogin = findViewById(R.id.layoutlogin);
		edtUserName = (EditText) findViewById(R.id.edtUserName);
		edtPassWord = (EditText) findViewById(R.id.edtPasswork);
		tv_changpassre = (TextView) findViewById(R.id.tv_changepassreturn);
		tv_changpass = (TextView) findViewById(R.id.tv_changepass);
		btnLogin = (ImageView) findViewById(R.id.btnLogin);
		btnChangePass.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (edtPassWordnew1.getText().toString() == edtPassWordnew2.getText().toString()) {
						String result11 = CheckChange(edtUserName1.getText().toString(), edtPassWord1.getText().toString(), edtPassWordnew1.getText().toString());
						if(result11 == "true")
						{
							Toast.makeText(getApplicationContext(), "Bạn đã đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
						}else{
							Toast.makeText(getApplicationContext(), "Bạn đã đổi mật khẩu thất bại, vui lòng thử lại", Toast.LENGTH_LONG).show();
						}
				}else
				{
					Toast.makeText(getApplicationContext(), "Xác nhận mật khẩu không chính xác", Toast.LENGTH_LONG).show();
				}
			}
		});
		tv_changpassre.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				layoutchangpass.animate().setDuration(1000);
				layoutchangpass.animate().alpha(0);
				layoutchangpass.animate().setListener(new AnimatorListener() {

					@Override
					public void onAnimationStart(Animator arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animator arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animator arg0) {
						// TODO Auto-generated method stub
						layoutchangpass.setVisibility(8);
						layoutlogin.setVisibility(0);
						layoutlogin.setAlpha(1);
					}

					@Override
					public void onAnimationCancel(Animator arg0) {
						// TODO Auto-generated method stub

					}
				});
			}
		});
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				CheckGPS();
				if (flagtocheckGPS == 1) {
					new LoadViewTask().execute();
				} else {
					Toast.makeText(getApplicationContext(),
							"Bạn vẫn chưa bật GPS hay GPRS", Toast.LENGTH_LONG)
							.show();
				}
			}
		});
		tv_changpass.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				layoutlogin.animate().setDuration(1000);
				layoutlogin.animate().alpha(0);
				layoutlogin.animate().setListener(new AnimatorListener() {

					@Override
					public void onAnimationStart(Animator arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animator arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animator arg0) {
						// TODO Auto-generated method stub
						layoutlogin.setVisibility(8);
						layoutchangpass.setVisibility(0);
						layoutchangpass.setAlpha(1);
					}

					@Override
					public void onAnimationCancel(Animator arg0) {
						// TODO Auto-generated method stub

					}
				});
			}
		});
	}

	private void CheckGPS() {
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (locationManager == null) {
			Toast.makeText(this, "Location Manager Not Available",
					Toast.LENGTH_SHORT).show();
		} else {
			if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == false) {
				Toast.makeText(getApplicationContext(), "Bạn vẫn chưa bật GPS",
						Toast.LENGTH_LONG).show();
			} else {
				flagtocheckGPS = 1;
			}
		}

	}

	// /////////////////////////
	// Check login by Server //
	// /////////////////////////

	public String CheckLogin(String user, String pass) {
		String _respone = null;
		String uri = myURI + "username=" + user + "&password=" + pass;
		Log.i("URI Login", uri);
		HttpRequestBase get;
		HttpClient client = new DefaultHttpClient();
		InputStream instream = null;
		StringBuffer stringBuffer = new StringBuffer();
		int len = 0;
		get = new HttpGet(uri);
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
			Log.e("Login", e.toString());
		}
		return _respone;
	}

	public String CheckChange(String user, String passold, String passnew) {
		String _respone = null;
		String uri = myURI + "username=" + user + "&oldPassword=" + passold
				+ "&newPassword=";
		Log.i("URI Change", uri);
		HttpRequestBase get;
		HttpClient client = new DefaultHttpClient();
		InputStream instream = null;
		StringBuffer stringBuffer = new StringBuffer();
		int len = 0;
		get = new HttpGet(uri);
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
			Log.e("Login", e.toString());
		}
		return _respone;
	}

	private class LoadViewTask extends AsyncTask<Void, String, String> {

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(LoginScreenActivity.this,
					"Loading...", "Loading application View, please wait...",
					false, false);
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
				// Log.i("Login result", result);
				if (result != "false") {
					String info[] = result.split("@");
					Intent inten = new Intent(LoginScreenActivity.this,
							MainActivity.class);
					Bundle bdUser = new Bundle();
					bdUser.putString("user", edtUserName.getText().toString());
					bdUser.putString("token", info[0]);
					bdUser.putString("pass", edtPassWord.getText().toString());
					bdUser.putString("sipNum", info[4]);
					bdUser.putString("sipPass", info[3]);
					bdUser.putString("phoneNum", info[1]);
					bdUser.putString("idDriver", info[5]);
					inten.putExtras(bdUser);
					startActivity(inten);
					LoginScreenActivity.this.onDestroy();
				} else {
					edtPassWord.setText("");
					edtUserName.setText("");
				}
			}
			progressDialog.dismiss();
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String _result = CheckLogin(edtUserName.getText().toString(),
					edtPassWord.getText().toString());
			String finalResult = null;
			if (_result != null) {
				if (_result != "false") {
					try {
						JSONArray jsonObj = new JSONArray(_result);
						JSONObject jsonDriInfo = jsonObj.getJSONObject(0)
								.getJSONObject("DriverInfo");
						String sesToken = jsonDriInfo.getString("token");
						String phoneNum = jsonDriInfo.getString("phonenumber");
						String idDriver = jsonDriInfo.getString("idDriver");

						JSONObject jsonSip = jsonObj.getJSONObject(1)
								.getJSONObject("SipInfo");
						String sipUserName = jsonSip.getString("username");
						String sipPassword = jsonSip.getString("password");
						String sipNumber = jsonSip.getString("number");

						finalResult = sesToken + "@" + phoneNum + "@"
								+ sipUserName + "@" + sipPassword + "@"
								+ sipNumber + "@" + idDriver;
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					finalResult = "false";
				}
			}
			return finalResult;
		}
	}

}