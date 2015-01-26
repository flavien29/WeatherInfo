package com.fmqw.weatherinfo;

import org.json.JSONException;

import android.os.AsyncTask;

public class JSONWeatherTask extends AsyncTask<String, Integer, Weather>{
	
	protected Weather doInBackground(String... params) {
	    Weather weather = new Weather();
	    String data = ( (new WeatherHTTPClient()).getWeatherData(params[0]));
	 
	    try {
	        weather = JSONWeatherParser.getWeather(data);
	         
	        // Let's retrieve the icon
	        weather.iconData = ( (new WeatherHTTPClient()).getImage(weather.currentCondition.getIcon()));
	         
	    } catch (JSONException e) {               
	        e.printStackTrace();
	    }
	    return weather;
	 
	    }

}
