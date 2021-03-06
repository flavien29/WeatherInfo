package com.fmqw.weatherinfo.requests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.fmqw.weatherinfo.R;

import android.content.Context;
import android.location.Location;

public class HTTPRequest {

	private static final String CURRENT_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&mode=json&lang=%s";
	private static final String CURRENT_WEATHER_LOCATION_URL = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&mode=json&lang=%s";

	public static String loadCurrentWeather(Context context, String city) {
		return load(context, CURRENT_WEATHER_URL, city);
	}
	
	private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
     
    public static JSONObject getJSON(Context context, String city){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));          
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
             
            connection.addRequestProperty("x-api-key",
                    context.getString(R.string.open_weather_maps_app_id));
             
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
             
            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();
             
            JSONObject data = new JSONObject(json.toString());
             
            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }
             
            return data;
        }catch(Exception e){
            return null;
        }
    }   

	private static String load(Context context, String currentWeatherUrl,
			String city) {
		String data = null;
		BufferedReader reader = null;

		try {
			URL url = new URL(String.format(currentWeatherUrl, city, "FR"));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuffer json = new StringBuffer();
			String tmp = "";

			while ((tmp = reader.readLine()) != null) {
				json.append(tmp).append("\n");
			}

			data = json.toString();

		} catch (Exception e) {

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {

				}
			}
		}

		return data	;
	}

	public static String loadCurrentWeatherLocation(Context context, Location location) {
		return load(context, CURRENT_WEATHER_LOCATION_URL, location);
	}

	// Refactor and avoid duplicate code
	private static String load(Context context,
			String currentWeatherLocationUrl, Location location) {
		String data = null;
		BufferedReader reader = null;

		try {
			URL url = new URL(String.format(currentWeatherLocationUrl, location.getLatitude() + "",
					location.getLongitude() + "", context.getString(R.string.code_language)));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuffer json = new StringBuffer();
			String tmp = "";

			while ((tmp = reader.readLine()) != null) {
				json.append(tmp).append("\n");
			}

			data = json.toString();

		} catch (Exception e) {

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {

				}
			}
		}

		return data	;
	}
}
