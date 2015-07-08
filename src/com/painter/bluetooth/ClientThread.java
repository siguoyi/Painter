package com.painter.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;

public class ClientThread extends Thread {
	
	private InputStream mInputStream;
	private OutputStream mOutputStream;
	private BluetoothSocket mSocket;
	
	@Override
	public void run() {
		byte[] buffer = new byte[1024];
		int bytes;
		try {
			mInputStream = mSocket.getInputStream();
			mOutputStream = mSocket.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(true){
			try {
				bytes = mInputStream.read(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void write(byte[] bytes){
		try {
			mOutputStream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cancel(){
		try {
			mSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
