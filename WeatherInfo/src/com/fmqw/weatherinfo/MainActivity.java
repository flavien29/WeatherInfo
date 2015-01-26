package com.fmqw.weatherinfo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author flavien
 *
 */
public class MainActivity extends Activity {

	private final String defaut = "No result";
	TextView cityText;
	TextView condDescr;
	TextView temp;
	TextView hum;
	TextView press;
	TextView windSpeed;
	TextView windDeg;
	ImageView imgView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String city = "Rome,IT";

		cityText = (TextView) findViewById(R.id.cityText);
		condDescr = (TextView) findViewById(R.id.condDescr);
		temp = (TextView) findViewById(R.id.temp);
		hum = (TextView) findViewById(R.id.hum);
		press = (TextView) findViewById(R.id.press);
		windSpeed = (TextView) findViewById(R.id.windSpeed);
		windDeg = (TextView) findViewById(R.id.windDeg);
		imgView = (ImageView) findViewById(R.id.condIcon);

		// TODO
		/*JSONWeatherTask task = new JSONWeatherTask();
		task.execute(new String[]{city});*/
	}

}