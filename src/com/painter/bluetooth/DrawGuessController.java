package com.painter.bluetooth;

import com.example.painter.R;
import com.painter.main.DrawView;
import com.painter.main.Painter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class DrawGuessController extends Activity {

	private static final String tag = "DrawGuessController";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.canvas);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = new MenuInflater(this);
		inflator.inflate(R.menu.mtoolsmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		DrawView dv = (DrawView) findViewById(R.id.drawView2);
		    	
		    	dv.paint.setXfermode(null);
		//    	dv.paint.setStrokeWidth(10);
		    	int id = item.getItemId();
		    	Log.v(tag , "" + id);
		    	switch (id) {
				case R.id.red:
					dv.paint.setColor(Color.RED);
					item.setChecked(true);
					break;
					
				case R.id.green:
					dv.paint.setColor(Color.GREEN);
					item.setChecked(true);
					break;
					
				case R.id.blue:
					dv.paint.setColor(Color.BLUE);
					item.setChecked(true);
					break;
					
				case R.id.yellow:
					dv.paint.setColor(Color.YELLOW);
					item.setChecked(true);
					break;
					
				case R.id.black:
					dv.paint.setColor(Color.BLACK);
					item.setChecked(true);
					break;
					
				case R.id.width_1:
					dv.paint.setStrokeWidth(10);
					item.setChecked(true);
					break;
					
				case R.id.width_2:
					dv.paint.setStrokeWidth(20);
					item.setChecked(true);
					break;
					
				case R.id.width_3:
					dv.paint.setStrokeWidth(30);
					item.setChecked(true);
					break;
					
				case R.id.line:
					dv.paint.setStrokeWidth(10);
					Painter.setPaintFlag(0);
					item.setChecked(true);
					break;
				case R.id.rectangle:
					dv.paint.setStrokeWidth(10);
					Painter.setPaintFlag(1);
					item.setChecked(true);
					break;
					
				case R.id.circle:
					Painter.setPaintFlag(3);
					item.setChecked(true);
					break;
					
				case R.id.oval:
					Painter.setPaintFlag(4);
					item.setChecked(true);
					break;
				
				case R.id.eraser_width_1:
					Painter.setPaintFlag(0);
					dv.paint.setStrokeWidth(30);
					item.setChecked(true);
					dv.clear();
					break;
					
				case R.id.eraser_width_2:
					Painter.setPaintFlag(0);
					dv.paint.setStrokeWidth(40);
					item.setChecked(true);
					dv.clear();
					break;
				
				case R.id.eraser_width_3:
					Painter.setPaintFlag(0);
					dv.paint.setStrokeWidth(50);
					item.setChecked(true);
					dv.clear();
					break;
				
				case R.id.revoke:
					dv.revoke();
					dv.invalidate();
					break;
					
				case R.id.recovery:
					dv.recovery();
					break;
					
				case R.id.clear:
					dv.clearAll();
					break;
					
				case R.id.save:
					dv.save();
					break;
				}
		    	return true;
	}
	
	
}
