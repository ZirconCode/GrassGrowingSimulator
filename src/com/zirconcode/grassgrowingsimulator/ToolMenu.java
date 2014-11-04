package com.zirconcode.grassgrowingsimulator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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
	
	private RectF rectangle;
	private float textSize;
	
	//private int[] names = {R.string.tool_plant,R.string.tool_cut,R.string.tool_water};
	// TODO can't getString w/out context
	private String[] names = {"Plant","Cut","Water"};
	private int[] colors = {Color.RED,Color.BLUE,Color.YELLOW};
	
	
	public ToolMenu(RectF r)
	{
		rectangle = r;
		
	}
	
	// TODO put utility method(s) elsewhere...
	private float getTextSizeFor(Paint p, RectF r, String s, float padding)
	{
		// meh...
		p.setTextSize(20f);
		float currSize = p.measureText(s);
		float goalSize = r.width();
		float result = (20f*(goalSize/currSize))-(padding*2); // MEHH...!..
		
		return result;
	}
	
	public void draw(Paint p, Canvas c)
	{
		p.setColor(colors[currentTool]);
		//c.drawRect(rectangle, p);
		c.drawOval(rectangle, p);
		
		textSize = getTextSizeFor(p, rectangle,names[currentTool],5f);
		p.setTextSize(textSize);
		p.setColor(Color.WHITE);
		c.drawText(names[currentTool], rectangle.left+5f, rectangle.centerY(), p);
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
