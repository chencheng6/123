package com.example.tuxing;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

//import com.yangyueyue.demo.luyin.LuYinActivity;

public class DrawChart extends View{
	
	private int CHARTH = 600;
	//private int CHARTW = 400;
	private int CHARTW = 1000;
	private int OFFSET_LEFT = 50;
	private int OFFSET_TOP = 180;
	private int TEXT_OFFSET = 20;
	private int X_INTERVAL = 20;
	
	private List<Point> plist;

	public DrawChart(Context context ,double divider2,int[] shuzhu) {
		super(context);
		plist = new ArrayList<Point>();
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawTable(canvas);
		prepareLine();
		drawCurve(canvas);
	}
	
	private void drawTable(Canvas canvas){
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		
		//画外框
		paint.setStyle(Paint.Style.STROKE);
		Rect chartRec = new Rect(OFFSET_LEFT,OFFSET_TOP,CHARTW+OFFSET_LEFT,CHARTH+OFFSET_TOP);
		canvas.drawRect(chartRec, paint);
		
		//画左边的文字
		Path textPath = new Path();
		paint.setStyle(Paint.Style.FILL);
		textPath.moveTo(30, 420);
		textPath.lineTo(30, 300); 
		paint.setTextSize(20);
		paint.setAntiAlias(true);
		canvas.drawTextOnPath("电压(mV)", textPath, 0, 0, paint);
		Paint titlepaint = new Paint();
		titlepaint.setTextSize(35);//设置字体大小//设置字体类型
		titlepaint.setStyle(Paint.Style.FILL);
		titlepaint.setColor(Color.BLUE);
		canvas.drawText("肌电电压折线图", OFFSET_LEFT + 14*TEXT_OFFSET, OFFSET_TOP-75, titlepaint);
		
		//画左侧数字
        canvas.drawText("4000", OFFSET_LEFT - TEXT_OFFSET -5, OFFSET_TOP+5, paint);
        for(int i = 1 ; i<10 ; i++){
        	canvas.drawText(""+(4000-400*i), OFFSET_LEFT - TEXT_OFFSET-5, OFFSET_TOP + CHARTH/10*i, paint);
        }
        canvas.drawText("0", OFFSET_LEFT - TEXT_OFFSET , OFFSET_TOP + CHARTH, paint);
        
        //画表格中的虚线
        Path path = new Path();     
        PathEffect effects = new DashPathEffect(new float[]{2,2,2,2},1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(false);
        paint.setPathEffect(effects); 		
		for(int i = 1 ; i<10 ; i++){
			path.moveTo(OFFSET_LEFT, OFFSET_TOP + CHARTH/10*i);  
	        path.lineTo(OFFSET_LEFT+CHARTW,OFFSET_TOP + CHARTH/10*i); 
	        canvas.drawPath(path, paint);
		}
	}
	public static int[] prtBuf = new int[1000];
	private final double divider = 6.6;
	private void drawCurve(Canvas canvas){
		//int xtx = LuYinActivity.prtBuf[0];
//		for(int i = 0;i<1000;i++){
//			prtBuf[i]=i*2;
//		}
		int getIndex = 1000;
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(3);
		paint.setAntiAlias(true);
		//canvas.drawLines(line, paint);
		for(int i = 0; i <getIndex-1; i++){
			canvas.drawLine(OFFSET_LEFT + i*1,CHARTH+OFFSET_TOP - (int)((prtBuf[i])/divider),OFFSET_LEFT + (i+1)*1,CHARTH+OFFSET_TOP - (int)((prtBuf[i+1])/divider),paint);
		}
	}
	
	private void prepareLine(){
		int py = OFFSET_TOP + (int)(Math.random()*(CHARTH - OFFSET_TOP));
		Point p = new Point(OFFSET_LEFT + CHARTW , py );
		if(plist.size() > 21){
			plist.remove(0);
			for(int i = 0; i<20; i++){
				if(i == 0) plist.get(i).x -= (X_INTERVAL - 2);
				else plist.get(i).x -= X_INTERVAL;
			}
			plist.add(p);
		}
		else{
			for(int i = 0; i<plist.size()-1; i++){
				plist.get(i).x -= X_INTERVAL;
			}
			plist.add(p);
		}

	}
}
