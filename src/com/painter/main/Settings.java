package com.painter.main;

import com.example.painter.R;
import com.painter.bluetooth.BluetoothSettings;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends Activity{

	private Button bt_savepath;
	private Button bt_update;
	private Button bt_openBlueTooth;
	
    private BluetoothAdapter bluetoothAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		bt_savepath = (Button) findViewById(R.id.bt_savepath);
		bt_update = (Button) findViewById(R.id.bt_update);
		bt_openBlueTooth = (Button) findViewById(R.id.bt_openblue);
		
		bt_savepath.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog();
			}
		});
		
		bt_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		bt_openBlueTooth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent bluetoothIntent = new Intent(Settings.this, BluetoothSettings.class);
				startActivity(bluetoothIntent);
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
