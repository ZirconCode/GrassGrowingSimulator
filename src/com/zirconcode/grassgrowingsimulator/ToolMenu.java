package com.zirconcode.grassgrowingsimulator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class ToolMenu {

	// pick tools
	
	// Menu
	// pick tools
	// cutter - (-> sell)
	// waterer
	// planter - (pick grass)
	// Store?
	
	private int currentTool = 0;
		// 0 - plant current grass type
		// 1 - scissors
		// 2 - water the grass
	
	private Rect rectangle;
	
	private int[] colors = {Color.RED,Color.BLUE,Color.YELLOW};
	
	
	public ToolMenu(Rect r)
	{
		rectangle = r;
	}
	
	public void draw(Paint p, Canvas c)
	{
		p.setColor(colors[currentTool]);
		c.drawRect(rectangle, p);
	}
	
	public boolean respondToTouch(MotionEvent event)
	{
		// returns true if touched
		// closes menu if outside of range
		if(rectangle.contains((int)event.getX(), (int) event.getY()))
		{
			currentTool += 1;
			if(currentTool > 2) currentTool = 0;
			return true;
		}
		
		return false;
	}
	
	public int getTool()
	{
		return currentTool;
	}
	
}
