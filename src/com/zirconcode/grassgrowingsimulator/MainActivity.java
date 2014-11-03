package com.zirconcode.grassgrowingsimulator;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

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
	
		simView = new SimulatorView(this);
		load();
		
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
		save();
		
		super.onPause();
	}
	
	public void load()
	{
		// check if lawn exists...
		File f = new File(getFilesDir(), getString(R.string.lawn_filename));
		if (f.exists())
        {
            try
            {
                Serializer serializer = new Persister();
                Lawn lawn = serializer.read(Lawn.class, f);
                simView.setGamestate(lawn);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                // TODO
            }
        }
		else
		{
			// new lawn is created in SimulatorView constructor
		}
	}
	
	public void save()
	{
		File f = new File(getFilesDir(), getString(R.string.lawn_filename));
		try
        {
            Serializer serializer = new Persister();
            serializer.write(simView.getGamestate(), f);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // TODO
        }
	}
	
}
