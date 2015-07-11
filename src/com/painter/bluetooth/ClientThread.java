package com.painter.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ClientThread extends Thread {
	
	private final String tag = "ClientThrtead";
	
	private final BluetoothSocket mmSocket;
    private BluetoothAdapter mBluetoothAdapter;
    private InputStream mInputStream;
    private OutputStream mOutputStream;
    private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
       
    private Handler mHandler = new Handler();
    
    public ClientThread(BluetoothDevice device) {
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
    	mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothSocket tmp = null;
        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) { }
        mmSocket = tmp;
    }
 
    public void run() {
        // Cancel discovery because it will slow down the connection
        mBluetoothAdapter.cancelDiscovery();
 
        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            mmSocket.connect();
            if(mmSocket.isConnected()){
            Log.d(tag, ""+mmSocket.isConnected());
            mInputStream = mmSocket.getInputStream();
            mOutputStream = mmSocket.getOutputStream();
            mOutputStream.write(new String("123").getBytes());
            
            byte[] buffer = new byte[10];
			while(true){
				int bytes = mInputStream.read(buffer);
				String data = new String(buffer);
        		Log.d("receive data from Server", "bytes: " + bytes + "data: " + data);
        	
        		Message msg = new Message();
        		msg.what = 0x345;
        		msg.obj = bytes;
        		mHandler.sendMessage(msg);
        		}
			}
        } catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            } catch (IOException closeException) { }
            return;
        }
 
        // Do work to manage the connection (in a separate thread)
    }
 
    /** Will cancel an in-progress connection, and close the socket */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}
