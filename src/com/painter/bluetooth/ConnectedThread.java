package com.painter.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.painter.main.MainActivity;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ConnectedThread extends Thread {
	
	 private final BluetoothSocket mmSocket;
	 private final InputStream mmInStream;
	 private final OutputStream mmOutStream;
	 
	 private Handler mHandler = new Handler();
	 
	    public ConnectedThread(BluetoothSocket socket) {
	        mmSocket = socket;
	        InputStream tmpIn = null;
	        OutputStream tmpOut = null;
	 
	        // Get the input and output streams, using temp objects because
	        // member streams are final
	        try {
	            tmpIn = socket.getInputStream();
	            tmpOut = socket.getOutputStream();
	        } catch (IOException e) { }
	 
	        mmInStream = tmpIn;
	        mmOutStream = tmpOut;
	    }
	 
	    public void run() {
	        byte[] buffer = new byte[1024];  // buffer store for the stream
	        // bytes returned from read()
	 
	        // Keep listening to the InputStream until an exception occurs
	        while (true) {
	            try {
	                // Read from the InputStream\
	            		int bytes = mmInStream.read(buffer);
	            		Log.d("receive data", "bytes: " + bytes);
	            	
	            		Message msg = new Message();
	            		msg.what = 0x123;
	            		msg.obj = bytes;
	            		mHandler.sendMessage(msg);
	                // Send the obtained bytes to the UI activity
//	                mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
//	                        .sendToTarget();
	            } catch (IOException e) {
	                break;
	            }
	        }
	    }
	 
	    /* Call this from the main activity to send data to the remote device */
	    public void write(byte[] bytes) {
	        try {
	            mmOutStream.write(bytes);
	        } catch (IOException e) { }
	    }
	 
	    /* Call this from the main activity to shutdown the connection */
	    public void cancel() {
	        try {
	            mmSocket.close();
	        } catch (IOException e) { }
	    }

}
