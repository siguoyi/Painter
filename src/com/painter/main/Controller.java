package com.painter.main;

import java.io.File;
import java.util.ArrayList;

import com.example.painter.R;
import com.painter.pick.ImagePickActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Controller extends Activity implements OnClickListener{

	private static final String tag = "Controller";
	private static final int SELECT_IMAGES = 1;
	private DrawView dv;
	
	private ImageButton ib_color;
	private ImageButton ib_width;
	private ImageButton ib_shape;
	private ImageButton ib_eraser;
	private ImageButton ib_load;
	private ImageButton ib_undo;
	private ImageButton ib_redo;
	private ImageButton ib_delete;
	private ImageButton ib_save;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	Painter.setPaintFlag(0);
    	requestWindowFeature(Window.FEATURE_NO_TITLE); //设置无标题
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	setContentView(R.layout.canvas_new);
    	dv = (DrawView) findViewById(R.id.drawView1);
    	
    	Painter.colorPosition = 0;
    	Painter.eraserPosition = 0;
    	Painter.shapePosition = 0;
    	Painter.widthPosition = 0;
    	Painter.paintWidth = 5;
    	Painter.paintFlag = 0;
    	
    	ib_color = (ImageButton) findViewById(R.id.ib_color);
    	ib_width = (ImageButton) findViewById(R.id.ib_width);
    	ib_shape = (ImageButton) findViewById(R.id.ib_shape);
    	ib_eraser = (ImageButton) findViewById(R.id.ib_eraser);
    	ib_load = (ImageButton) findViewById(R.id.ib_load);
    	ib_undo = (ImageButton) findViewById(R.id.ib_undo);
    	ib_redo = (ImageButton) findViewById(R.id.ib_redo);
    	ib_delete = (ImageButton) findViewById(R.id.ib_delete);
    	ib_save = (ImageButton) findViewById(R.id.ib_save);
    	
    	ib_color.setOnClickListener(this);
    	ib_width.setOnClickListener(this);
    	ib_shape.setOnClickListener(this);
    	ib_eraser.setOnClickListener(this);
    	ib_load.setOnClickListener(this);
    	ib_undo.setOnClickListener(this);
    	ib_redo.setOnClickListener(this);
    	ib_delete.setOnClickListener(this);
    	ib_save.setOnClickListener(this);

    }

//	@Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//    	MenuInflater inflator = new MenuInflater(this);
//    	inflator.inflate(R.menu.toolsmenu, menu);
//    	
//        return super.onCreateOptionsMenu(menu);
//    }
    
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//    	
//    	
//    	dv.paint.setXfermode(null);
////    	dv.paint.setStrokeWidth(10);
//    	int id = item.getItemId();
//    	Log.v(tag , "" + id);
//    	switch (id) {
//		case R.id.red:
//			dv.paint.setStrokeWidth(Painter.getPaintWidth());
//			dv.paint.setColor(Color.RED);
//			item.setChecked(true);
//			break;
//			
//		case R.id.green:
//			dv.paint.setStrokeWidth(Painter.getPaintWidth());
//			dv.paint.setColor(Color.GREEN);
//			item.setChecked(true);
//			break;
//			
//		case R.id.blue:
//			dv.paint.setStrokeWidth(Painter.getPaintWidth());
//			dv.paint.setColor(Color.BLUE);
//			item.setChecked(true);
//			break;
//			
//		case R.id.yellow:
//			dv.paint.setStrokeWidth(Painter.getPaintWidth());
//			dv.paint.setColor(Color.YELLOW);
//			item.setChecked(true);
//			break;
//			
//		case R.id.black:
//			dv.paint.setStrokeWidth(Painter.getPaintWidth());
//			dv.paint.setColor(Color.BLACK);
//			item.setChecked(true);
//			break;
//			
//		case R.id.width_1:
//			dv.paint.setStrokeWidth(5);
//			Painter.setPaintWidth(5);
//			item.setChecked(true);
//			break;
//			
//		case R.id.width_2:
//			dv.paint.setStrokeWidth(10);
//			Painter.setPaintWidth(10);
//			item.setChecked(true);
//			break;
//			
//		case R.id.width_3:
//			dv.paint.setStrokeWidth(20);
//			Painter.setPaintWidth(20);
//			item.setChecked(true);
//			break;
//			
//		case R.id.line:
//			dv.paint.setStrokeWidth(5);
//			Painter.setPaintWidth(5);
//			Painter.setPaintFlag(0);
//			item.setChecked(true);
//			break;
//		case R.id.rectangle:
//			dv.paint.setStrokeWidth(5);
//			Painter.setPaintWidth(5);
//			Painter.setPaintFlag(1);
//			item.setChecked(true);
//			break;
//			
//		case R.id.straightline:
//			dv.paint.setStrokeWidth(5);
//			Painter.setPaintWidth(5);
//			Painter.setPaintFlag(2);
//			item.setChecked(true);
//			break;
//			
//		case R.id.circle:
//			dv.paint.setStrokeWidth(5);
//			Painter.setPaintWidth(5);
//			Painter.setPaintFlag(3);
//			item.setChecked(true);
//			break;
//			
//		case R.id.oval:
//			dv.paint.setStrokeWidth(5);
//			Painter.setPaintWidth(5);
//			Painter.setPaintFlag(4);
//			item.setChecked(true);
//			break;
//		
//		case R.id.eraser_width_1:
//			Painter.setPaintFlag(0);
//			dv.paint.setStrokeWidth(20);
//			item.setChecked(true);
//			dv.clear();
//			break;
//			
//		case R.id.eraser_width_2:
//			Painter.setPaintFlag(0);
//			dv.paint.setStrokeWidth(30);
//			item.setChecked(true);
//			dv.clear();
//			break;
//		
//		case R.id.eraser_width_3:
//			Painter.setPaintFlag(0);
//			dv.paint.setStrokeWidth(40);
//			item.setChecked(true);
//			dv.clear();
//			break;
//		
//		case R.id.loadBitmap:
//			picImage();
////			dv.loadBitmap();
//			break;
//			
//		case R.id.revoke:
//			dv.revoke();
//			break;
//			
//		case R.id.recovery:
//			dv.recovery();
//			break;
//			
//		case R.id.clear:
//			dv.savePath.clear();
//			dv.deletePath.clear();
//			dv.clearAll();
//			break;
//			
//		case R.id.save:
//			dv.save();
//			break;
//		}
//    	return true;
//    }

	private void picImage() {
		File mediaStorageDir = new File(Painter.getLoadPath(),"Camera");
		Log.d("load path", "" + Painter.getLoadPath());
		if(!mediaStorageDir.exists()){
			Toast.makeText(this, "无SD卡", Toast.LENGTH_SHORT).show();
			return;
		}
		Intent intent = new Intent(Controller.this, ImagePickActivity.class);
		startActivityForResult(intent, SELECT_IMAGES);
	}
	
	@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			switch(requestCode){
			case SELECT_IMAGES:
				if(resultCode == RESULT_OK){
					ArrayList<String> paths = data.getStringArrayListExtra("IMAGE_PATHS");
					if(!paths.isEmpty()) {
						String loadPath = paths.get(0);
						dv.loadBitmap(loadPath);
					} else {
						Toast.makeText(getApplicationContext(), "No such image!", Toast.LENGTH_LONG).show();
					}
				}
				break;			}
		}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_color:
			colorSetting();
			break;
		case R.id.ib_width:
			widthSetting();
			break;
		case R.id.ib_shape:
			shapeSetting();
			break;
		case R.id.ib_eraser:
			eraserSetting();
			break;
		case R.id.ib_load:
			picImage();
//			dv.loadBitmap();
			break;
		case R.id.ib_undo:
			dv.revoke();
			break;
			
		case R.id.ib_redo:
			dv.recovery();
			break;
			
		case R.id.ib_delete:
			dv.savePath.clear();
			dv.deletePath.clear();
			dv.clearAll();
			break;
			
		case R.id.ib_save:
			dv.save();
			break;
		default:
			break;
		}
		
	}

	private void eraserSetting() {
		AlertDialog dialog = null;
		AlertDialog.Builder builder = null;
		final View view = LayoutInflater.from(Controller.this).inflate(R.layout.eraser_dialog, null);
		final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_eraser);
		((RadioButton)radioGroup.getChildAt(Painter.eraserPosition)).setChecked(true);
		Log.d("eraser position", "" + Painter.eraserPosition);
		builder = new AlertDialog.Builder(Controller.this);
		builder.setTitle("请选择橡皮宽度：");
		builder.setView(view);
		builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				RadioButton radioButton = (RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId());
				switch (radioButton.getId()) {
				case R.id.eraser_width_1:
					Painter.setPaintFlag(0);
					dv.paint.setStrokeWidth(20);
					Painter.eraserPosition = 0;
					dv.clear();
					break;
					
				case R.id.eraser_width_2:
					Painter.setPaintFlag(0);
					dv.paint.setStrokeWidth(30);
					Painter.eraserPosition = 1;
					dv.clear();
					break;
				
				case R.id.eraser_width_3:
					Painter.setPaintFlag(0);
					dv.paint.setStrokeWidth(40);
					Painter.eraserPosition = 2;
					dv.clear();
					break;
				}
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});

		dialog = builder.create();
		dialog.show();		
		
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		params.width = 600;
		params.height = 1500 ;
		dialog.getWindow().setAttributes(params);
		
	}

	private void shapeSetting() {
		AlertDialog dialog = null;
		AlertDialog.Builder builder = null;
		final View view = LayoutInflater.from(Controller.this).inflate(R.layout.shape_dialog, null);
		final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_shape);
		((RadioButton)radioGroup.getChildAt(Painter.shapePosition)).setChecked(true);
		Log.d("shape position", "" + Painter.shapePosition);
		builder = new AlertDialog.Builder(Controller.this);
		builder.setTitle("请选择图形：");
		builder.setView(view);
		builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				RadioButton radioButton = (RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId());
				switch (radioButton.getId()) {
				case R.id.line:
					dv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					Painter.setPaintFlag(0);
					Painter.shapePosition = 0;
					break;
				case R.id.rectangle:
					dv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					Painter.setPaintFlag(1);
					Painter.shapePosition = 1;
					break;
					
				case R.id.straightline:
					dv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					Painter.setPaintFlag(2);
					Painter.shapePosition = 2;
					break;
					
				case R.id.circle:
					dv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					Painter.setPaintFlag(3);
					Painter.shapePosition = 3;
					break;
					
				case R.id.oval:
					dv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					Painter.setPaintFlag(4);
					Painter.shapePosition = 4;
					break;
				}
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});

		dialog = builder.create();
		dialog.show();		
		
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		params.width = 600;
		params.height = 1500 ;
		dialog.getWindow().setAttributes(params);
		
	}

	private void widthSetting() {
		AlertDialog dialog = null;
		AlertDialog.Builder builder = null;
		final View view = LayoutInflater.from(Controller.this).inflate(R.layout.width_dialog, null);
		final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_width);
		((RadioButton)radioGroup.getChildAt(Painter.widthPosition)).setChecked(true);
		Log.d("width position", "" + Painter.widthPosition);
		builder = new AlertDialog.Builder(Controller.this);
		builder.setTitle("请选择线宽：");
		builder.setView(view);
		builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				RadioButton radioButton = (RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId());
				switch (radioButton.getId()) {
				case R.id.width_1:
					dv.paint.setStrokeWidth(5);
					Painter.setPaintWidth(5);
					Painter.widthPosition = 0;
					break;
					
				case R.id.width_2:
					dv.paint.setStrokeWidth(10);
					Painter.setPaintWidth(10);
					Painter.widthPosition = 1;
					break;
					
				case R.id.width_3:
					dv.paint.setStrokeWidth(20);
					Painter.setPaintWidth(20);
					Painter.widthPosition = 2;
					break;
				}
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});

		dialog = builder.create();
		dialog.show();		
		
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		params.width = 600;
		params.height = 1500 ;
		dialog.getWindow().setAttributes(params);
		
	}

	private void colorSetting() {
		AlertDialog dialog = null;
		AlertDialog.Builder builder = null;
		final View view = LayoutInflater.from(Controller.this).inflate(R.layout.color_dialog, null);
		final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_color);
		((RadioButton)radioGroup.getChildAt(Painter.colorPosition)).setChecked(true);
		Log.d("color position", "" + Painter.colorPosition);
		builder = new AlertDialog.Builder(Controller.this);
		builder.setTitle("请选择颜色：");
		builder.setView(view);
		builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				RadioButton radioButton = (RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId());
				switch (radioButton.getId()) {
				case R.id.color_red:
					dv.paint.setStrokeWidth(Painter.getPaintWidth());
					dv.paint.setColor(Color.RED);
					Painter.colorPosition = 0;
					break;
				case R.id.color_green:
					dv.paint.setStrokeWidth(Painter.getPaintWidth());
					dv.paint.setColor(Color.GREEN);
					Painter.colorPosition = 1;
					break;
				case R.id.color_blue:
					dv.paint.setStrokeWidth(Painter.getPaintWidth());
					dv.paint.setColor(Color.BLUE);
					Painter.colorPosition = 2;
					break;
				case R.id.color_yellow:
					dv.paint.setStrokeWidth(Painter.getPaintWidth());
					dv.paint.setColor(Color.YELLOW);
					Painter.colorPosition = 3;
					break;
				case R.id.color_black:
					dv.paint.setStrokeWidth(Painter.getPaintWidth());
					dv.paint.setColor(Color.BLACK);
					Painter.colorPosition = 4;
					break;
				}
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});

		dialog = builder.create();
		dialog.show();		
		
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		params.width = 600;
		params.height = 1500 ;
		dialog.getWindow().setAttributes(params);
	}
}
