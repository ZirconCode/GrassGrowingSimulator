package com.zirconcode.grassgrowingsimulator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	SimulatorView simView;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	
		// load gamestate
		// .. TODO
		
		simView = new SimulatorView(this);
        setContentView(simView);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}
	
	
	@Override
	protected void onResume() {
		simView.setPaused(false);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		simView.setPaused(true);
		
		// save gamestate
		// .. TODO
		
		super.onPause();
	}
	
	
}
