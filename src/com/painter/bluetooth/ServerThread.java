package com.painter.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ServerThread extends Thread {
	
	private static final String tag = "ServerThread";

	private BluetoothAdapter mAdapter;
	private BluetoothServerSocket sSocket;
	private BluetoothSocket mSocket;
	
	private InputStream mInputStream;
	private OutputStream mOutputStream;
	
	private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	
	public ServerThread(){
		BluetoothServerSocket tmpSocket = null;
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		try {
			tmpSocket = mAdapter.listenUsingInsecureRfcommWithServiceRecord(getName(), MY_UUID);
		} catch (Exception e) {
		}
		sSocket = tmpSocket;
	}

	@Override
	public void run() {
		while(true){
			try {
				mSocket = sSocket.accept();
			} catch (IOException e) {
				break;
			}
			
			if(mSocket != null){
				try {
					mInputStream = mSocket.getInputStream();
					mOutputStream = mSocket.getOutputStream();
					while(true){
						Log.d(tag, "receive from client");
//	            		mOutputStream.write(new String("456").getBytes());
					}
				} catch (Exception e) {
				}
				try {
					sSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	
	 public void cancel() {
	        try {
	            sSocket.close();
	        } catch (IOException e) { }
	    }
}
