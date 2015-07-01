package com.example.test112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

import doan.placeandroidmap.Place;
import doan.placeandroidmap.PlaceList;

import android.text.TextUtils.StringSplitter;
import android.util.Log;


public class GoogleParser extends XMLParser implements Parser {
    /** Distance covered. **/
    private int distance;

    public GoogleParser(String feedUrl) {
            super(feedUrl);
    }

    /**
     * Parses a url pointing to a Google JSON object to a Route object.
     * @return a Route object based on the JSON object.
     */

    public Route parse() {
            // turn the stream into a string
            final String result = convertStreamToString(this.getInputStream());
            //Create an empty route
            final Route route = new Route();
            //Create an empty segment
            final Segment segment = new Segment();
            try {
                    //Tranform the string into a json object
                    final JSONObject json = new JSONObject(result);
                    //Get the route object
                    final JSONObject jsonRoute = json.getJSONArray("routes").getJSONObject(0);
                    //Get the leg, only one leg as we don't support waypoints
                    final JSONObject leg = jsonRoute.getJSONArray("legs").getJSONObject(0);
                    //Get the steps for this leg
                    final JSONArray steps = leg.getJSONArray("steps");
                    //Number of steps for use in for loop
                    final int numSteps = steps.length();
                    //Set the name of this route using the start & end addresses
                    route.setName(leg.getString("start_address") + " to " + leg.getString("end_address"));
                    //Get google's copyright notice (tos requirement)
                    route.setCopyright(jsonRoute.getString("copyrights"));
                    //Get the total length of the route.
                    route.setLength(leg.getJSONObject("distance").getInt("value"));
                    //Get any warnings provided (tos requirement)
                    if (!jsonRoute.getJSONArray("warnings").isNull(0)) {
                            route.setWarning(jsonRoute.getJSONArray("warnings").getString(0));
                    }
                    /* Loop through the steps, creating a segment for each one and
                     * decoding any polylines found as we go to add to the route object's
                     * map array. Using an explicit for loop because it is faster!
                     */
                    for (int i = 0; i < numSteps; i++) {
                            //Get the individual step
                            final JSONObject step = steps.getJSONObject(i);
                            //Get the start position for this step and set it on the segment
                            final JSONObject start = step.getJSONObject("start_location");
                            final LatLng position = new LatLng(start.getDouble("lat"), 
                                    start.getDouble("lng"));
                            segment.setPoint(position);
                            //Set the length of this segment in metres
                            final int length = step.getJSONObject("distance").getInt("value");
                            distance += length;
                            final String strDistance = step.getJSONObject("distance").getString("text");
                            segment.setLength(length);
                            segment.setStrDistance(strDistance);
                            segment.setDistance(distance/1000);
                            //Strip html from google directions and set as turn instruction
                            segment.setInstruction(step.getString("html_instructions").replaceAll("<(.*?)*>", ""));
                            //Retrieve & decode this segment's polyline and add it to the route.
                            route.addPoints(decodePolyLine(step.getJSONObject("polyline").getString("points")));
                            //Push a copy of the segment to the route
                            route.addSegment(segment.copy());
                    }
            } catch (JSONException e) {
                    Log.e(e.getMessage(), "Google JSON Parser - " + feedUrl);
            }
            return route;
    }

    
    public PlaceList parseToPlaces()
    {
    	 // turn the stream into a string
        final String result = convertStreamToString(this.getInputStream());
        //Create an empty route
        final PlaceList places = new PlaceList();
        //Create an empty segment
        final Place place = new Place();
        try {
                final JSONObject json = new JSONObject(result);
               
                final JSONArray jsonPlaceList = json.getJSONArray("results");
                
                if(json.isNull("next_page_token")==false){
                	 places.setStatus(json.getString("next_page_token"));
                }
                
                final int numPlace = jsonPlaceList.length();
                
               
                 
                for (int i = 0; i < numPlace; i++) {

                        final JSONObject jsonPlace = jsonPlaceList.getJSONObject(i);
                        
                        final JSONObject jsonGeometry = jsonPlace.getJSONObject("geometry");
                        final JSONObject jsonLocation = jsonGeometry.getJSONObject("location");
                        final LatLng position = new LatLng(jsonLocation.getDouble("lat"), 
                                jsonLocation.getDouble("lng"));
                        place.setLocation(position);
                        place.setName(jsonPlace.getString("name"));
                        if (jsonPlace.isNull("formatted_address") == false) {
							place.setFormatted_address(jsonPlace
									.getString("formatted_address"));
						}
						place.setIcon(jsonPlace.getString("icon"));
                        
                        if (jsonPlace.isNull("rating") == false) {
							place.setRate(jsonPlace.getString("rating"));
						}
                        place.setTypes(jsonPlace.getString("types"));
                        if (jsonPlace.isNull("vicinity") == false) {
							place.setVicinity(jsonPlace.getString("vicinity"));
						}
                        place.setReference(jsonPlace.getString("reference"));
						places.addPlace(place.copy());
                }
        } catch (JSONException e) {
                Log.e(e.getMessage(), "Google JSON Parser - " + feedUrl);
        }
        return places;
    }
    
    public Place parseDetailPlace(Place place)
    {
    	String result = convertStreamToString(this.getInputStream());
    	try {
			JSONObject json = new JSONObject(result);
			JSONObject jsonArray = json.getJSONObject("result");
			if (jsonArray.isNull("formatted_address") == false) {
				place.setFormatted_address(jsonArray
						.getString("formatted_address"));
			}
			if (jsonArray.isNull("formatted_phone_number") == false) {
				place.setFormatted_phone_number(jsonArray
						.getString("formatted_phone_number"));
			}
			if (jsonArray.isNull("international_phone_number")) {
				place.setInternational_phone_number(jsonArray
						.getString("international_phone_number"));
			}
			if (jsonArray.isNull("website") == false) {
				place.setWebsite(jsonArray.getString("website"));
			}
			if (jsonArray.isNull("url") == false) {
				place.setUrl(jsonArray.getString("url"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    	return place;
    }
    
    
    /**
     * Convert an inputstream to a string.
     * @param input inputstream to convert.
     * @return a String of the inputstream.
     */

    private static String convertStreamToString(final InputStream input) {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    final StringBuilder sBuf = new StringBuilder();

    String line = null;
    try {
        while ((line = reader.readLine()) != null) {
            sBuf.append(line);
        }
    } catch (IOException e) {
            Log.e(e.getMessage(), "Google parser, stream2string");
    } finally {
        try {
            input.close();
        } catch (IOException e) {
            Log.e(e.getMessage(), "Google parser, stream2string");
        }
    }
    return sBuf.toString();
}

    /**
     * Decode a polyline string into a list of LatLng.
     * @param poly polyline encoded string to decode.
     * @return the list of LatLng represented by this polystring.
     */

    private List<LatLng> decodePolyLine(final String poly) {
            int len = poly.length();
            int index = 0;
            List<LatLng> decoded = new ArrayList<LatLng>();
            double lat = 0;
            double lng = 0;

            while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                    b = poly.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                    b = poly.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
            } while (b >= 0x20);
                    int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                    lng += dlng;

                    decoded.add(new LatLng((lat / 1E5),(lng / 1E5)));
            }

            return decoded;
            }
}