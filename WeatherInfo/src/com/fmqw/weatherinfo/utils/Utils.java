package com.fmqw.weatherinfo.utils;

import java.util.Date;

import com.fmqw.weatherinfo.R;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {

	public static final String WEATHER_FONT_PATH = "font/weather.ttf";
	public static Typeface weatherfont;


	public static Typeface getWeatherFont(Context context) {
		if (weatherfont == null) {
			weatherfont = Typeface.createFromAsset(context.getAssets(), WEATHER_FONT_PATH);
		}
		return weatherfont;
	}
	
	public static String getWeatherIcon(Context context, int actualId, long sunrise, long sunset){
	    int id = actualId / 100;
	    int icon = 0;
	    if(actualId == 800){
	        long currentTime = new Date().getTime();
	        if(currentTime>=sunrise && currentTime<sunset) {
	            icon = R.string.weather_sunny;
	        } else {
	            icon = R.string.weather_clear_night;
	        }
	    } else {
	        switch(id) {
	        case 2 : icon = R.string.weather_thunder;
	                 break;        
	        case 3 : icon = R.string.weather_drizzle;
	                 break;    
	        case 7 : icon = R.string.weather_foggy;
	                 break;
	        case 8 : icon = R.string.weather_cloudy;
	                 break;
	        case 6 : icon = R.string.weather_snowy;
	                 break;
	        case 5 : icon = R.string.weather_rainy;
	                 break;
	        }
	    }
	    
	    return context.getString(icon);
	}
	
	public static String capitalize(String original) {
		if (original.length() == 0)
			return original;
		
		return original.substring(0, 1).toUpperCase() + original.substring(1);
	}
	
}
