package com.fmqw.weatherinfo.fragments;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.fmqw.weatherinfo.R;
import com.fmqw.weatherinfo.requests.HTTPRequest;
import com.fmqw.weatherinfo.utils.CityPreference;
import com.fmqw.weatherinfo.utils.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class CurrentWeatherFragment extends Fragment {

	Activity activity;
	TextView cityField, updatedField, detailsField, currentTemperatureField, weatherIcon;
	Handler handler;
	
	
	public CurrentWeatherFragment() {
		handler = new Handler();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.current_weather_fragment, container, false);

		cityField = (TextView)rootView.findViewById(R.id.city_field);
        updatedField = (TextView)rootView.findViewById(R.id.updated_field);
        detailsField = (TextView)rootView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView)rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView)rootView.findViewById(R.id.weather_icon);
		weatherIcon.setTypeface(Utils.getWeatherFont(activity));
		
		return rootView;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		weatherfont = Typeface.createFromAsset(getActivity().getAssets(), "font/weather.ttf");
		updateWeatherData(new CityPreference(getActivity()).getCity());
	}
	
	private void updateWeatherData(final String city){
	    new Thread(){
	        public void run(){
	        	final String data = HTTPRequest.loadCurrentWeather(getActivity(), city);
	            if(data == null){
	                handler.post(new Runnable(){
	                    public void run(){
	                        Toast.makeText(getActivity(),
	                                getActivity().getString(R.string.place_not_found),
	                                Toast.LENGTH_LONG).show();
	                    }
	                });
	            } else {
		            final JSONObject json;
					try {
						json = new JSONObject(data);
		                handler.post(new Runnable(){
		                    public void run(){
		                        renderWeather(json);
		                    }
		                });
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }              
	        }
	    }.start();
	}
	
	private void renderWeather(JSONObject json){
	    try {
	        cityField.setText(json.getString("name").toUpperCase(Locale.US) +
	                ", " +
	                json.getJSONObject("sys").getString("country"));
	         
	        JSONObject details = json.getJSONArray("weather").getJSONObject(0);
	        JSONObject main = json.getJSONObject("main");
	        detailsField.setText(
	                details.getString("description").toUpperCase(Locale.US) +
	                "\n" + "Humidity: " + main.getString("humidity") + "%" +
	                "\n" + "Pressure: " + main.getString("pressure") + " hPa");
	         
	        currentTemperatureField.setText(
	                    String.format("%.2f", main.getDouble("temp"))+ " ℃");
	 
	        DateFormat df = DateFormat.getDateTimeInstance();
	        String updatedOn = df.format(new Date(json.getLong("dt")*1000));
	        updatedField.setText("Last update: " + updatedOn);
	        
	        weatherIcon.setText(Utils.getWeatherIcon(activity, details.getInt("id"),
	        		json.getJSONObject("sys").getLong("sunrise") * 1000,
	        		json.getJSONObject("sys").getLong("sunset") * 1000));
	         
	    }catch(Exception e){
	        Log.e("SimpleWeather", "One or more fields not found in the JSON data");
	    }
	}
	
	
	public void changeCity(String city){
	    updateWeatherData(city);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}
}
