package com.example.tuxing;



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init_wave();
	}
	private DrawChart view;
	public static int[] prtBuf = new int[1000];
	private final double divider = 6.6;
	private void init_wave() {  
        LinearLayout layout=(LinearLayout) findViewById(R.id.wave);  
        view = new DrawChart(this ,divider,prtBuf);  
        view.invalidate();  //在初始化的过程中会调用一次！
        layout.addView(view);
    }
}
