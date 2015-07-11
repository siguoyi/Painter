package com.painter.main;

import com.example.painter.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Controller extends Activity{

private static final String tag = "Controller";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.canvas);
    	Painter.setPaintFlag(0);
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflator = new MenuInflater(this);
    	inflator.inflate(R.menu.toolsmenu, menu);
    	
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	DrawView dv = (DrawView) findViewById(R.id.drawView1);
    	
    	dv.paint.setXfermode(null);
//    	dv.paint.setStrokeWidth(10);
    	int id = item.getItemId();
    	Log.v(tag , "" + id);
    	switch (id) {
		case R.id.red:
			dv.paint.setStrokeWidth(Painter.getPaintWidth());
			dv.paint.setColor(Color.RED);
			item.setChecked(true);
			break;
			
		case R.id.green:
			dv.paint.setStrokeWidth(Painter.getPaintWidth());
			dv.paint.setColor(Color.GREEN);
			item.setChecked(true);
			break;
			
		case R.id.blue:
			dv.paint.setStrokeWidth(Painter.getPaintWidth());
			dv.paint.setColor(Color.BLUE);
			item.setChecked(true);
			break;
			
		case R.id.yellow:
			dv.paint.setStrokeWidth(Painter.getPaintWidth());
			dv.paint.setColor(Color.YELLOW);
			item.setChecked(true);
			break;
			
		case R.id.black:
			dv.paint.setStrokeWidth(Painter.getPaintWidth());
			dv.paint.setColor(Color.BLACK);
			item.setChecked(true);
			break;
			
		case R.id.width_1:
			dv.paint.setStrokeWidth(5);
			Painter.setPaintWidth(5);
			item.setChecked(true);
			break;
			
		case R.id.width_2:
			dv.paint.setStrokeWidth(10);
			Painter.setPaintWidth(10);
			item.setChecked(true);
			break;
			
		case R.id.width_3:
			dv.paint.setStrokeWidth(20);
			Painter.setPaintWidth(20);
			item.setChecked(true);
			break;
			
		case R.id.line:
			dv.paint.setStrokeWidth(5);
			Painter.setPaintWidth(5);
			Painter.setPaintFlag(0);
			item.setChecked(true);
			break;
		case R.id.rectangle:
			dv.paint.setStrokeWidth(5);
			Painter.setPaintWidth(5);
			Painter.setPaintFlag(1);
			item.setChecked(true);
			break;
			
//		case R.id.triangle:
//			dv.paint.setStrokeWidth(10);
//			Painter.setPaintFlag(2);
//			item.setChecked(true);
//			break;
			
		case R.id.circle:
			dv.paint.setStrokeWidth(5);
			Painter.setPaintWidth(5);
			Painter.setPaintFlag(3);
			item.setChecked(true);
			break;
			
		case R.id.oval:
			dv.paint.setStrokeWidth(5);
			Painter.setPaintWidth(5);
			Painter.setPaintFlag(4);
			item.setChecked(true);
			break;
		
		case R.id.eraser_width_1:
			Painter.setPaintFlag(0);
			dv.paint.setStrokeWidth(20);
			item.setChecked(true);
			dv.clear();
			break;
			
		case R.id.eraser_width_2:
			Painter.setPaintFlag(0);
			dv.paint.setStrokeWidth(30);
			item.setChecked(true);
			dv.clear();
			break;
		
		case R.id.eraser_width_3:
			Painter.setPaintFlag(0);
			dv.paint.setStrokeWidth(40);
			item.setChecked(true);
			dv.clear();
			break;
		
		case R.id.loadBitmap:
			dv.loadBitmap();
			break;
			
		case R.id.revoke:
			dv.revoke();
			break;
			
		case R.id.recovery:
			dv.recovery();
			break;
			
		case R.id.clear:
			dv.savePath.clear();
			dv.deletePath.clear();
			dv.clearAll();
			break;
			
		case R.id.save:
			dv.save();
			break;
		}
    	return true;
    }
}
