package com.painter.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import com.painter.main.Painter;

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
            while(!mmSocket.isConnected()){}
            Painter.paintSocketConnected = true;
            Log.d(tag, "" + mmSocket.isConnected());
            mOutputStream = mmSocket.getOutputStream();  
            mInputStream = mmSocket.getInputStream();
			
        } catch (IOException connectException) {
                try {
					mmSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            return;
        }
     }
 
//    public static void write(BluetoothDrawPath mDrawPath) {
//        try {
//            mOutputStream.writeObject(mDrawPath);
//        } catch (IOException e) { }
//    }
    
    /** Will cancel an in-progress connection, and close the socket */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) { }
    }
}
