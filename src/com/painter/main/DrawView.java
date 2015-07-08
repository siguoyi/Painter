package com.painter.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Formatter.BigDecimalLayoutForm;

import com.example.painter.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DrawView extends View {
	
	private static final String tag = "DrawView";

	private int view_width = 0;
	private int view_height = 0;
	private Path path;
	public Paint paint;
	private float preX;
	private float preY;
	

	private Bitmap mBitmap;
	private Bitmap loadBitmap;
	private Bitmap tempBitmap;
	private Canvas loadCanvas;
	private Canvas mCanvas;
	private Canvas tempCanvas;
	
	public ArrayList<DrawPath> savePath = new ArrayList<DrawView.DrawPath>();
	public ArrayList<DrawPath> deletePath = new ArrayList<DrawView.DrawPath>();
	 
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		view_width = context.getResources().getDisplayMetrics().widthPixels;
		view_height = context.getResources().getDisplayMetrics().heightPixels;

		initCanvas();

	}
	
	
	private void initCanvas() {
		
		path = new Path();
		mBitmap = Bitmap.createBitmap(view_width, view_height, Bitmap.Config.ARGB_8888);
		tempBitmap = Bitmap.createBitmap(view_width, view_height, Bitmap.Config.ARGB_8888);
		loadBitmap = Bitmap.createBitmap(view_width, view_height, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		tempCanvas = new Canvas(tempBitmap);
		
		paint = new Paint(Paint.DITHER_FLAG);
		paint.setColor(Color.RED);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeWidth(10);
		paint.setAntiAlias(true);
		paint.setDither(true);
		
		
	}

	@SuppressLint("DrawAllocation") 
	@Override
	protected void onDraw(Canvas canvas) {
		Paint bmpPaint = new Paint();
		
		canvas.drawBitmap(mBitmap, 0, 0, bmpPaint);
		canvas.drawBitmap(tempBitmap, 0, 0, bmpPaint);
		Log.d(tag, "onDraw");

		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		
		float x = event.getX();
		float y = event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
//			Log.v(tag, "按下");
			path.reset();
			path.moveTo(x, y);
			preX = x;
			preY = y;
			
		
			mCanvas.drawPath(path, paint);
			invalidate();
			break;
		
		case MotionEvent.ACTION_MOVE:
//			Log.v(tag, "移动");
			/*
			 * Painter.paintFlag为不同的绘制模式
			 * Painter.paintFlag == 0 表示绘制自定义线条
			 * Painter.paintFlag == 1 表示绘制自定义矩形
			 */
			
			if(Painter.paintFlag == 0){
				path.quadTo(preX, preY, (x + preX)/2, (y + preY)/2);
				preX = x;
				preY = y;
				mCanvas.drawPath(path, paint);
			} else if(Painter.paintFlag == 1){
				tempBitmap.eraseColor(Color.TRANSPARENT);
				tempCanvas.drawRect(preX,preY,event.getX(),event.getY(),paint);
			} else if(Painter.paintFlag == 2){

			} else if(Painter.paintFlag == 3){
				tempBitmap.eraseColor(Color.TRANSPARENT);
				float radius = getRadius(preX,preY,event.getX(),event.getY());
				tempCanvas.drawCircle(preX, preY, radius, paint);
			} else {
				tempBitmap.eraseColor(Color.TRANSPARENT);
				RectF oval = new RectF(preX,preY,event.getX(),event.getY());
				tempCanvas.drawOval(oval, paint);
			}
			break;
			
		case MotionEvent.ACTION_UP:
//			Log.v(tag, "抬起");
			path.lineTo(preX, preY);
			if(Painter.paintFlag == 1){
				mCanvas.drawRect(preX,preY,event.getX(),event.getY(),paint);
				path.addRect(preX,preY,event.getX(),event.getY(), Path.Direction.CCW);
			} else if(Painter.paintFlag == 2){

			} else if(Painter.paintFlag == 3){
				float radius = getRadius(preX,preY,event.getX(),event.getY());
				mCanvas.drawCircle(preX, preY, radius, paint);
				path.addCircle(preX, preY, radius, Path.Direction.CCW);
			} else if(Painter.paintFlag == 4){
				RectF oval = new RectF(preX,preY,event.getX(),event.getY());
				mCanvas.drawOval(oval, paint);
				path.addOval(oval, Path.Direction.CCW);
			}
			DrawPath dp = new DrawPath();
			Path tPath = new Path();
			tPath.addPath(path);
			dp.mPath = tPath;
			Paint tPaint = new Paint();
			tPaint = paint;
			dp.mPaint = tPaint;
			
			savePath.add(dp);
			break;
		}

		invalidate();
		return true;
	}

	/*
	 * getRadius() 获取自定义圆形的半径
	 */
	
	private float getRadius(float preX2, float preY2, float x, float y) {
		float r = (float) Math.sqrt(Math.abs(preX - x)* Math.abs(preX - x) 
						+ Math.abs(preY - y) * Math.abs(preY - y));
		return r;
	}

	/*
	 * 橡皮擦
	 */
	
	public void clear(){
		paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		tempCanvas.drawPaint(paint);
	}
	
	/*
	 * 保存 
	 */
	
	public void save(){
		AlertDialog dialog = null;
		AlertDialog.Builder builder = null;
		View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog, null);
		final EditText et_save = (EditText) view.findViewById(R.id.et_dialog);
		builder = new AlertDialog.Builder(getContext());
		builder.setTitle("请输入保存文件名：");
		builder.setView(view);
		builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				String fileName = et_save.getText().toString();
				try {
					String status = saveBitmap(fileName);
					if(status.equals("error")){
						Toast.makeText(getContext(), "存储失败！",
								Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(getContext(), "存储成功！",
								Toast.LENGTH_SHORT).show();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});

		dialog = builder.create();
		dialog.show();
		
	}

	private String saveBitmap(String fileName) throws IOException {	
		String sPath = Painter.savePath;
		File dir = new File(sPath);
		if(!dir.exists()){
			dir.mkdir();
		}
		String savePath = fileName + ".png";
		File file = new File(sPath,savePath);
		if(file.exists()){
			Toast.makeText(getContext(), "此文件名已被占用，请重新输入！", Toast.LENGTH_SHORT).show();
			return "error";
		}else {
			file.createNewFile();
			FileOutputStream fileOS = new FileOutputStream(file);
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOS);
			fileOS.flush();
			fileOS.close();
			return "ok";
		}
		
	}

	public void clearAll() {
		paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		mCanvas.drawPaint(paint);
		tempCanvas.drawPaint(paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
		invalidate();
	}

	class DrawPath{
		Path mPath;
		Paint mPaint;
	}
	
	 /**
     * 撤销的核心思想就是将画布清空，
     * 将保存下来的Path路径最后一个移除掉，
     * 重新将路径画在画布上面。
     */
	
	public void revoke(){
//		initCanvas();
		if(savePath != null && savePath.size() > 0){
			Log.v(tag, "savePath.size(): " + savePath.size());
			DrawPath drawPath = savePath.get(savePath.size() - 1);
			deletePath.add(drawPath);
			Log.v(tag, "deletePath.size():" + deletePath.size());
			savePath.remove(savePath.size() - 1);
			clearAll();
			for(DrawPath dd:savePath){
				mCanvas.drawPath(dd.mPath, dd.mPaint);
				Log.v(tag, "canvas");
			}
			invalidate();
		}
	}
	
	 /**
     * 恢复的核心思想就是将撤销的路径保存到另外一个列表里面(栈)，
     * 然后从recovery的列表里面取出最顶端对象，
     * 画在画布上面即可
     */
	
	public void recovery(){
		if(deletePath.size() > 0){
			Log.v(tag, "deletePath.size():" + deletePath.size());
			DrawPath mdrawPath = deletePath.get(deletePath.size() - 1);
			savePath.add(mdrawPath);
			mCanvas.drawPath(mdrawPath.mPath, mdrawPath.mPaint);
			deletePath.remove(deletePath.size() - 1);
			invalidate();
		}
	}

	public void loadBitmap() {
		try {
			InputStream is = getResources().openRawResource(R.drawable.back);
			
			loadBitmap = BitmapFactory.decodeStream(is);
			loadCanvas = new Canvas(mBitmap);
			loadCanvas.drawBitmap(loadBitmap, 0, 0, null);
			invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
