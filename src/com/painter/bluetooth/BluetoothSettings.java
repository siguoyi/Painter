package com.painter.bluetooth;

import java.util.ArrayList;
import java.util.Set;

import com.example.painter.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class BluetoothSettings extends Activity {

	private static final String tag = "BluetoothSettings";
	
	private Button bt_enable;
	private Button bt_paired;
	private Button bt_scan;
	
	BluetoothAdapter mBluetoothAdapter;
	private ArrayList<BluetoothDevice> mDeviceList = new ArrayList<BluetoothDevice>();
	private ProgressDialog mProgressDlg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		
		bt_enable = (Button) findViewById(R.id.bt_enable);
		bt_paired = (Button) findViewById(R.id.bt_view_paired);
		bt_scan = (Button) findViewById(R.id.bt_scan);
		
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if(mBluetoothAdapter.isEnabled()){
			bt_enable.setText("关闭蓝牙");
			bt_paired.setEnabled(true);
			bt_scan.setEnabled(true);
		}
		mProgressDlg = new ProgressDialog(this);
		
		mProgressDlg.setMessage("搜索中...");
		mProgressDlg.setCancelable(false);
		mProgressDlg.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", 
				new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.dismiss();
		        mBluetoothAdapter.cancelDiscovery();
		    }
		});
		
		bt_enable.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mBluetoothAdapter.isEnabled()){
					mBluetoothAdapter.disable();
					bt_enable.setText("打开蓝牙");
					bt_paired.setEnabled(false);
					bt_scan.setEnabled(false);
					Toast.makeText(BluetoothSettings.this, "蓝牙关闭", Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				    startActivityForResult(intent, 1);
				    bt_enable.setText("关闭蓝牙");
				    bt_paired.setEnabled(true);
					bt_scan.setEnabled(true);
				    
				}
			}
		});
		
		bt_paired.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
				
				if (pairedDevices == null || pairedDevices.size() == 0) { 
					Toast.makeText(BluetoothSettings.this, "无配对设备", Toast.LENGTH_SHORT).show();
				} else {
					ArrayList<BluetoothDevice> list = new ArrayList<BluetoothDevice>();
					
					list.addAll(pairedDevices);
					
					Intent intent = new Intent(BluetoothSettings.this, DeviceListActivity.class);
					
					intent.putParcelableArrayListExtra("device.list", list);
					
					startActivity(intent);						
				}
			}
		});
		
		bt_scan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mBluetoothAdapter.startDiscovery();
			}
		});
		
		IntentFilter filter = new IntentFilter();
		
		filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
		filter.addAction(BluetoothDevice.ACTION_FOUND);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		
		registerReceiver(mReceiver, filter);
	}
	
	@Override
	public void onPause() {
		if (mBluetoothAdapter != null) {
			if (mBluetoothAdapter.isDiscovering()) {
				mBluetoothAdapter.cancelDiscovery();
			}
		}
		
		super.onPause();
	}
	
	@Override
	public void onDestroy() {
		unregisterReceiver(mReceiver);
		
		super.onDestroy();
	}
	
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {	    	
	        String action = intent.getAction();
	        
	        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
	        	final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
	        	 
	        	if (state == BluetoothAdapter.STATE_ON) {
	        		Toast.makeText(BluetoothSettings.this, "蓝牙开启", Toast.LENGTH_SHORT).show();
	        		
	        	 }
	        } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
	        	mDeviceList = new ArrayList<BluetoothDevice>();
				
				mProgressDlg.show();
	        } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
	        	mProgressDlg.dismiss();
	        	
	        	Intent newIntent = new Intent(BluetoothSettings.this, DeviceListActivity.class);
	        	
	        	newIntent.putParcelableArrayListExtra("device.list", mDeviceList);
				
				startActivity(newIntent);
	        } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	        	BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		        
	        	mDeviceList.add(device);
	        	Toast.makeText(BluetoothSettings.this, "发现设备：" + device.getName(), 
	        			Toast.LENGTH_SHORT).show();
	        }
	    }
	};
	
}
