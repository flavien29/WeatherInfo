package com.fmqw.weatherinfo;

import java.util.Locale;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.fmqw.weatherinfo.fragments.CurrentWeatherFragment;
import com.fmqw.weatherinfo.utils.CityPreference;
import com.fmqw.weatherinfo.utils.Utils;

public class MainActivity extends FragmentActivity {
	/**
	 * The pager widget, which handles animation and allows swiping horizontally to access previous
	 * and next wizard steps.
	 */
	private ViewPager mPager;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	private ScreenSlidePagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.change_city) {
			showInputDialog();
		}
		return false;
	}
	
	private void showInputDialog(){
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle("Change city");
	    final EditText input = new EditText(this);
	    input.setInputType(InputType.TYPE_CLASS_TEXT);
	    builder.setView(input);
	    builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            changeCity(input.getText().toString());
	        }
	    });
	    builder.show();
	}
	

	public void changeCity(String city) {
		if (city != null && !"".equals(city.trim())) {
			city = Utils.capitalize(city.trim());

			CurrentWeatherFragment currentWeatherFragment = (CurrentWeatherFragment) mPagerAdapter.getItem(0);

			// change city for all fragments
			if (currentWeatherFragment != null) {
				currentWeatherFragment.changeCity(city);
			    new CityPreference(this).setCity(city);
			}

		} else {
			Toast.makeText(this, "You must enter a city !", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onBackPressed() {
		if (mPager.getCurrentItem() == 0) {
			// If the user is currently looking at the first step, allow the system to handle the
			// Back button. This calls finish() on this activity and pops the back stack.
			super.onBackPressed();
		} else {
			// Otherwise, select the previous step.
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
		}
	}

	/**
	 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
	 * sequence.
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment;
			switch(position) {
			case 0:
				fragment = new CurrentWeatherFragment();
				break;
			default:
				throw new IllegalArgumentException("Invalid section number");
			}

			return fragment;
		}

		@Override
		public int getCount() {
			return 1;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			}
			return null;
		}
		
		private String getFragmentTag(int pos) {
			return "android:switcher" + R.id.pager + ":" + pos;
		}

		public CurrentWeatherFragment getCurrentWeatherFragment() {
			return (CurrentWeatherFragment) getSupportFragmentManager().findFragmentByTag(getFragmentTag(0));
		}
	}
}