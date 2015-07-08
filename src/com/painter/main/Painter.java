package com.painter.main;

public class Painter {

	public static volatile int paintFlag = 0;
	public static volatile int paintWidth = 10;
	public static int getPaintWidth() {
		return paintWidth;
	}

	public static void setPaintWidth(int paintWidth) {
		Painter.paintWidth = paintWidth;
	}

	public static String savePath = "/mnt/sdcard/painter";

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
