package com.painter.main;

import com.example.painter.R;
import com.painter.bluetooth.DrawGuessController;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String tag = "MainActivity";
    
    private Button bt_paint;
    private Button bt_about;
    private Button bt_settings;
    private Button bt_doublepaint;

    
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket mSocket;    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	setContentView(R.layout.activity_main);

    	bt_paint = (Button) findViewById(R.id.bt_paint);
    	bt_doublepaint = (Button) findViewById(R.id.bt_doublepaint);
    	bt_settings = (Button) findViewById(R.id.bt_settings);
    	bt_about = (Button) findViewById(R.id.bt_about);
    	
    	bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    	
    	bt_paint.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent paintIntent = new Intent(MainActivity.this, Controller.class);
				startActivity(paintIntent);
			}
		});
    			
		
		
		bt_doublepaint.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "敬请期待！", Toast.LENGTH_SHORT).show();
//				if(!bluetoothAdapter.isEnabled()){
//					Toast.makeText(MainActivity.this, "请在设置中打开蓝牙！",
//								Toast.LENGTH_SHORT).show();
//				}else if(Painter.paintDevice == null){
//					Toast.makeText(MainActivity.this, "请在设置中进行蓝牙配对！",
//							Toast.LENGTH_SHORT).show();
//				}else{
//					Intent drawGuessIntent = new Intent(MainActivity.this, DrawGuessController.class);
//					startActivity(drawGuessIntent);
//				}
				
//				Intent drawGuessIntent = new Intent(MainActivity.this, DrawGuessController.class);
//				startActivity(drawGuessIntent);
			}
		});
		
		bt_settings.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				Intent settingsIntent = new Intent(MainActivity.this, Settings.class);
				startActivity(settingsIntent);
			}
		});
		
		bt_about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
	        	LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
	            final View view = inflater.inflate(R.layout.aboutpanel, null);
	            final PopupWindow pop = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,true);
	            view.setFocusable(true);
	            view.setFocusableInTouchMode(true);
	            
	            pop.setFocusable(true);
//	            pop.setTouchable(true);
//	            pop.setOutsideTouchable(true);
	            pop.showAsDropDown(view);
	            view.setOnClickListener(new View.OnClickListener() {
	                public void onClick(View v) {
	                    pop.dismiss();
	                }
	            });
	            
	            view.setOnKeyListener(new OnKeyListener() {
					
					@Override
					public boolean onKey(View v, int keyCode, KeyEvent event) {
						if (keyCode == KeyEvent.KEYCODE_BACK) {
							pop.dismiss();
							return true;
						}
						return false;
					}
				});
	        }
							
		});
	
    }
    
    private boolean ExistSDCard() {
		  if (android.os.Environment.getExternalStorageState().equals(
		    android.os.Environment.MEDIA_MOUNTED)) {
		   return true;
		  } else
		   return false;
		 }

}
