package com.zirconcode.grassgrowingsimulator;

import java.io.File;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

public class SimulatorView extends SurfaceView implements SurfaceHolder.Callback{

	Paint paint = new Paint();
	
	private MainThread thread;
	private Lawn lawn;
	private ToolMenu tMenu;
	
	@SuppressLint("NewApi")
	public SimulatorView(Context context) {
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);
		
		//this.setBackgroundColor(Color.rgb(180,130,70));
		
		thread = new MainThread(getHolder(), this);
		
		int width = 0;
		int height = 0;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		if (android.os.Build.VERSION.SDK_INT >= 13)
		{
			Point size = new Point();
			display.getSize(size);
			width = size.x;
			height = size.y;
		}
		else
		{
			width = display.getWidth();  // deprecated
			height = display.getHeight();  // deprecated
		}
		
		final float scale = getResources().getDisplayMetrics().density;
		float top = 20*scale;
		float left = 20*scale;
		float size = 70*scale;
		RectF toolMenuRect = new RectF(top,left,top+size,left+size);
		
		tMenu = new ToolMenu(toolMenuRect);
		lawn = new Lawn(width,height);
	}
	
	public void setPaused(boolean paused) {
		thread.setPaused(paused);
	}
	
	public Lawn getGamestate()
	{
		return lawn;
	}
	
	public void setGamestate(Lawn l)
	{
		lawn = l;
	}
	
	public void update()
	{
		lawn.tick();
	}
	
    protected void render(Canvas canvas) {
    	//paint.setColor(Color.GREEN);
        //canvas.drawLine(0, 0, 200, 200, paint);
        //canvas.drawLine(200, 0, 0, 200, paint);
//    	Grass g = new Grass(50,50);
//    	g.draw(paint,canvas);
//    	
    	canvas.drawColor(Color.rgb(180,130,70));
    	lawn.draw(paint, canvas);
    	
    	tMenu.draw(paint, canvas);
    	
    	if(thread.getPaused()) 
    	{
    		canvas.drawText("Paused", 100, 100, paint); // TODO...
    	}
    	
    }
    
    @Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (thread.getState() == Thread.State.TERMINATED) {
			thread = new MainThread(getHolder(), this);
		}
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		thread.setRunning(false);
		
		boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
                //((Activity)getContext()).finish();
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(tMenu.respondToTouch(event))
		{
			
		}
		else if (event.getAction() == MotionEvent.ACTION_DOWN) {
			switch(tMenu.getTool()) {
				case 0: lawn.plant((int)event.getX(), (int)event.getY());
						break;
				case 1: lawn.cut((int)event.getX(), (int)event.getY());
						break;
				case 2: lawn.water((int)event.getX(), (int)event.getY());
						break;
			}
			
		}
		
		return super.onTouchEvent(event);
	}


}
