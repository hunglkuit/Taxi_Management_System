package com.example.test112;

import com.google.android.gms.maps.model.LatLng;

import android.util.Log;


public class CreateURL {
	public String staticUrl = "http://testtaxi11.jelastic.dogado.eu";
	
	public CreateURL()
	{
	}
	
	public String createLoginURL(String userName, String passWord)
	{
		String result = staticUrl+"/TaxiManagementSystem/resources/";
		final StringBuilder sBuf = new StringBuilder(result);
		sBuf.append("login?username=");
		sBuf.append(userName);
		sBuf.append("&password=");
		sBuf.append(passWord);
		Log.i("createLoginURL", sBuf.toString());
		return sBuf.toString();
	}
	

	
	public String createLogoutURL(String userName, String passWord, String token)
	{
		String result = staticUrl+"/TaxiManagementSystem/resources/";
		final StringBuilder sBuf = new StringBuilder(result);
		sBuf.append("logout?username=");
		sBuf.append(userName);
		sBuf.append("&password=");
		sBuf.append(passWord);
		sBuf.append("&token=");
		sBuf.append(token);
		Log.i("createLogoutURL", sBuf.toString());
		return sBuf.toString();
	}
	
	public String createUpdateURL(String userName, LatLng point, float speed, int state, String idCustomer, String token)
	{
		String result = staticUrl+"/TaxiManagementSystem/resources/";
		final StringBuilder sBuf = new StringBuilder(result);
		sBuf.append("update?username=");
		sBuf.append(userName);
		sBuf.append("&lat=");
		sBuf.append(point.latitude );
		sBuf.append("&long=");
		sBuf.append(point.longitude );
		sBuf.append("&speed=");
		sBuf.append(speed);
		sBuf.append("&state=");
		sBuf.append(state);
		sBuf.append("&idCustomer=");
		sBuf.append(idCustomer);
		sBuf.append("&token=");
		sBuf.append(token);
		Log.i("createUpdateURL", sBuf.toString());
		return sBuf.toString();
	}
	public String createManageVoice(int state, String number, String idDriver)
	{
		String result = staticUrl+"/TaxiManagementSystem/resources/query/joinConference?";
		final StringBuilder sBuf = new StringBuilder(result);
		sBuf.append("idDriver=");
		sBuf.append(idDriver);
		sBuf.append("&number=");
		sBuf.append(number);
		sBuf.append("&state=");
		sBuf.append(state);
		Log.i("CreateManageVoice", sBuf.toString());
		return sBuf.toString();
	}
}
