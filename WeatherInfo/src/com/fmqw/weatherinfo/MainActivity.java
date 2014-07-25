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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author flavien
 *
 */
public class MainActivity extends Activity {
  
  private final String defaut = "No result"; 
	
  Button findResult = null;	
  EditText city = null;
  TextView result = null;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    	
    findResult = (Button)findViewById(R.id.button1);	
    city = (EditText)findViewById(R.id.editText1);
    result = (TextView)findViewById(R.id.result);
    
    findResult.setOnClickListener(sendListener);
    city.addTextChangedListener(textWatcher);

  }

  private TextWatcher textWatcher = new TextWatcher() {

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    	result.setText(defaut);
    }
		
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
      int after) {
  
    }
  
    @Override
    public void afterTextChanged(Editable s) {
  
    }
  };
	
  // Find button
  private OnClickListener sendListener = new OnClickListener() {
    @Override
    public void onClick(View v) {
    	result.setText("City found !");
    }
  };
  
}