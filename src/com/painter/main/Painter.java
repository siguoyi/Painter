package com.painter.main;

import android.bluetooth.BluetoothDevice;
import android.os.Environment;

public class Painter {

	public static volatile int paintFlag = 0;
	public static int getColorPosition() {
		return colorPosition;
	}

	public static void setColorPosition(int colorPosition) {
		Painter.colorPosition = colorPosition;
	}

	public static volatile int paintWidth = 5;
	public static volatile int colorPosition = 0;
	public static BluetoothDevice paintDevice = null;
	public static volatile boolean paintSocketConnected = false;

	public static boolean isPaintSocketConnected() {
		return paintSocketConnected;
	}

	public static void setPaintSocketConnected(boolean paintSocketConnected) {
		Painter.paintSocketConnected = paintSocketConnected;
	}

	public BluetoothDevice getPaintDevice() {
		return paintDevice;
	}

	public static void setPaintDevice(BluetoothDevice paintDevice) {
		Painter.paintDevice = paintDevice;
	}

	public static int getPaintWidth() {
		return paintWidth;
	}

	public static void setPaintWidth(int paintWidth) {
		Painter.paintWidth = paintWidth;
	}

	public static String savePath = "/mnt/sdcard/painter";
	public static String loadPath = Environment.getExternalStoragePublicDirectory(
			Environment.DIRECTORY_DCIM).getAbsolutePath();

	public static String getLoadPath() {
		return loadPath;
	}

	public static void setLoadPath(String loadPath) {
		Painter.loadPath = loadPath;
	}

	public static String getSavePath() {
		return savePath;
	}

	public static void setSavePath(String savePath) {
		Painter.savePath = savePath;
	}

	public static int getPaintFlag() {
		return paintFlag;
	}

	public static void setPaintFlag(int paintFlag) {
		Painter.paintFlag = paintFlag;
	}
	
	
}
