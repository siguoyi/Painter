package com.painter.bluetooth;

import com.example.painter.R;
import com.painter.main.DrawView;
import com.painter.main.Painter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DrawGuessController extends Activity {

	private static final String tag = "DrawGuessController";
	private Handler mHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.canvas_bl);
		Painter.setPaintFlag(0);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		new ServerThread().start();
		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(msg.what == 0x123){
					String text = (String) msg.obj;
					Toast.makeText(DrawGuessController.this,
							"From Client: " + text, Toast.LENGTH_SHORT).show();
				} else if(msg.what == 0x345){
					String text = (String) msg.obj;
					Toast.makeText(DrawGuessController.this,
							"From Server: " + text, Toast.LENGTH_SHORT).show(); 
				}
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = new MenuInflater(this);
		inflator.inflate(R.menu.mtoolsmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		BluetoothView bv = (BluetoothView) findViewById(R.id.drawView2);
		    	
		    	bv.paint.setXfermode(null);
		//    	bv.paint.setStrokeWidth(10);
		    	int id = item.getItemId();
		    	Log.v(tag , "" + id);
		    	switch (id) {
				case R.id.bred:
					bv.paint.setStrokeWidth(Painter.getPaintWidth());
					bv.paint.setColor(Color.RED);
					item.setChecked(true);
					break;
					
				case R.id.bgreen:
					bv.paint.setStrokeWidth(Painter.getPaintWidth());
					bv.paint.setColor(Color.GREEN);
					item.setChecked(true);
					break;
					
				case R.id.bblue:
					bv.paint.setColor(Color.BLUE);
					item.setChecked(true);
					break;
					
				case R.id.byellow:
					bv.paint.setStrokeWidth(Painter.getPaintWidth());
					bv.paint.setColor(Color.YELLOW);
					item.setChecked(true);
					break;
					
				case R.id.bblack:
					bv.paint.setStrokeWidth(Painter.getPaintWidth());
					bv.paint.setColor(Color.BLACK);
					item.setChecked(true);
					break;
					
				case R.id.bwidth_1:
					bv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					item.setChecked(true);
					break;
					
				case R.id.bwidth_2:
					bv.paint.setStrokeWidth(10);
					Painter.setPaintWidth(10);
					item.setChecked(true);
					break;
					
				case R.id.bwidth_3:
					bv.paint.setStrokeWidth(20);
					Painter.setPaintWidth(20);
					item.setChecked(true);
					break;
					
				case R.id.bline:
					bv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					Painter.setPaintFlag(0);
					item.setChecked(true);
					break;
				case R.id.brectangle:
					bv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					Painter.setPaintFlag(1);
					item.setChecked(true);
					break;
				case R.id.straightline:
					bv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					Painter.setPaintFlag(2);
					item.setChecked(true);
					break;
				case R.id.bcircle:
					bv.paint.setStrokeWidth(5);
					Painter.setPaintFlag(3);
					Painter.setPaintWidth(5);
					item.setChecked(true);
					break;
					
				case R.id.boval:
					bv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					Painter.setPaintFlag(4);
					item.setChecked(true);
					break;
				
				case R.id.beraser_width_1:
					Painter.setPaintFlag(0);
					bv.paint.setStrokeWidth(20);
					item.setChecked(true);
					bv.clear();
					break;
					
				case R.id.beraser_width_2:
					Painter.setPaintFlag(0);
					bv.paint.setStrokeWidth(30);
					item.setChecked(true);
					bv.clear();
					break;
				
				case R.id.beraser_width_3:
					Painter.setPaintFlag(0);
					bv.paint.setStrokeWidth(40);
					item.setChecked(true);
					bv.clear();
					break;
				
				case R.id.brevoke:
					bv.revoke();
					bv.invalidate();
					break;
					
				case R.id.brecovery:
					bv.recovery();
					break;
					
				case R.id.bclear:
					bv.savePath.clear();
					bv.deletePath.clear();
					bv.clearAll();
					break;
					
				case R.id.bsave:
					bv.save();
					break;
				}
		    	return true;
	}
	
	
}
