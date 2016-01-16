package com.painter.main;

import java.util.ArrayList;
import com.example.painter.R;
import com.painter.bluetooth.BluetoothSettings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Settings extends Activity{

	private static final String tag = "Settings";
	
	private Button bt_savepath;
	private Button bt_update;
	private Button bt_openBlueTooth;
	private Button bt_loadpath;
	
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> data;
	
    private BluetoothAdapter bluetoothAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		bt_savepath = (Button) findViewById(R.id.bt_savepath);
		bt_update = (Button) findViewById(R.id.bt_update);
		bt_openBlueTooth = (Button) findViewById(R.id.bt_openblue);
		bt_loadpath = (Button) findViewById(R.id.bt_loadpath);
		data = new ArrayList<String>();
		data.add("内部存储");
		data.add("SD卡");
		mAdapter = new ArrayAdapter<String>(Settings.this, android.R.layout.simple_list_item_1, data);
		
		bt_savepath.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog();
			}
		});
		
		bt_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(Settings.this, "还没写完。。", Toast.LENGTH_SHORT).show();
			}
		});
		
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		bt_openBlueTooth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(Settings.this, "还没写完。。", Toast.LENGTH_SHORT).show();
//				Intent bluetoothIntent = new Intent(Settings.this, BluetoothSettings.class);
//				startActivity(bluetoothIntent);
			}
		});
		
		bt_loadpath.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pathSelect();
			}

			
		});
	}
	
	private void pathSelect() {
		LinearLayout linearLayoutMain;
		ListView listView;
		linearLayoutMain = new LinearLayout(this);
		linearLayoutMain.setLayoutParams(new LayoutParams(  
		       LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));  
		listView = new ListView(this);
		listView.setFadingEdgeLength(0); 
		listView.setAdapter(mAdapter);
		linearLayoutMain.addView(listView);
		final AlertDialog dialog = new AlertDialog.Builder(this)  
	       .setTitle("加载图片路径").setView(linearLayoutMain)
	        .create();        
	  		dialog.show();  
	  		listView.setOnItemClickListener(new OnItemClickListener() {
	  			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,  
	            long arg3) {  
	  				if(arg2 == 0){
	  					String s = Environment.getExternalStoragePublicDirectory(
	  							Environment.DIRECTORY_DCIM).getAbsolutePath();
	  					Painter.setLoadPath(s);
	  					Log.d(tag, "agr2: " + arg2);
	  				}else if(arg2 == 1){
	  					String s = "/storage/sdcard1/DCIM";
	  					Painter.setLoadPath(s);
	  					Log.d(tag, "agr2: " + arg2);
	  				}
	  				dialog.cancel();   
	  			}  
	  		}); 
	}
	
	
	public void showDialog() {
		AlertDialog dialog = null;
		AlertDialog.Builder builder = null;
		View view = LayoutInflater.from(Settings.this).inflate(R.layout.savepath, null);
		final EditText et_savepath = (EditText) view.findViewById(R.id.et_savepath);
		builder = new AlertDialog.Builder(Settings.this);
		builder.setTitle("请输入保存路径：");
		builder.setView(view);
		builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String fileDir = et_savepath.getText().toString();
				Painter.setSavePath(fileDir);
				Toast.makeText(Settings.this, "设置保存路径成功为：" + fileDir, 
						Toast.LENGTH_SHORT).show();
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});

		dialog = builder.create();
		dialog.show();
	}
	
}
