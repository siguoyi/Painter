package com.painter.bluetooth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class ServerThread implements Runnable {

	private BluetoothAdapter mAdapter;
	private BluetoothServerSocket sSocket;
	private BluetoothSocket mSocket;
	
	private InputStream mInputStream;
	private OutputStream mOutputStream;
	private BufferedReader br;
	private BufferedWriter bw;
	PrintStream ps;
	private byte[] mPath;
	
	public ServerThread() throws IOException {
		super();
	}

	@Override
	public void run() {

		mAdapter = BluetoothAdapter.getDefaultAdapter();
		try {
			sSocket = mAdapter.listenUsingRfcommWithServiceRecord("myBluetoothServerSocket", 
					UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			try {
				mSocket = sSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(mSocket != null){
				try {
					mInputStream = mSocket.getInputStream();
					mOutputStream = mSocket.getOutputStream();
					br = new BufferedReader(new InputStreamReader(mInputStream));
					br.readLine();
					bw = new BufferedWriter(new OutputStreamWriter(mOutputStream));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

}
