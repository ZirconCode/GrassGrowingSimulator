package com.zirconcode.grassgrowingsimulator;

import java.util.Vector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Grass {

	// growth
	private int stage; // 0 = seed, 1 = growing, 2 = mature, 3 = dying
	private double stageTimer;
	private double size;
	
	private int thickness = 3;
	// location
	private int x,y;
	// grass blades
	//private double windOffset;
	Vector<Blade> blades;
	
	// special grass traits
	private int maxBlades;
	private double maxSize;
	private double growthChance;
	private double growthAmount; // TODO chance to growthRate & maxGrowth
	private int color;
	// color
	// etc...
	
	private Lawn lawn;
	
	public Grass(int x, int y, Lawn l)
	{
		this.x = x;
		this.y = y;
		lawn = l;
		stage = 0;
		stageTimer = 0;
		
		maxBlades = 4;
		size = 1;
		maxSize = 55;
		growthChance = 0.1;
		growthAmount = 1;
		randomizeColor();
		
		//windOffset = (Math.random()*5)-10;
		blades = new Vector<Blade>();
		int bladeNum = (int)(Math.random()*maxBlades);
		for(int i = 0; i<bladeNum; i++)
		{
			double variation = maxSize / 3;
			double bladeOffset = (Math.random()*variation*2)-(variation);
			blades.add(new Blade(bladeOffset));
		}
			
	}
	
	public void randomizeColor()
	{
		int r = (int) (Math.random()*50);
		int g = (int) (Math.random()*100)+155;
		int b = (int) (Math.random()*75);
		//int r = (int) (Math.random()*255);
		//int g = (int) (Math.random()*255);
		//int b = (int) (Math.random()*255);
		color = Color.rgb(r,g,b);
	}
	
	public void brownifyColor()
	{
		int r = Color.red(color);
		int g = Color.green(color);
		int b = Color.blue(color);
		
		r = r + 1;
		if(r > 150) r = 130;
		g = g - 1;
		if(g < 50) g = 70;
		b = b - 1;
		if(b < 0) b = 0;
		
		color = Color.rgb(r,g,b);
		
		//color = Color.GRAY;
	}
	
	public void tick()
	{
		if(stage == 0)
		{
			advanceStage(50*5);
		}
		else if(stage == 1)
		{
			if(Math.random()<growthChance) size += growthAmount;
			if(size > maxSize) size = maxSize;
			
			advanceStage(50*10);
		}
		else if(stage == 2)
		{
			advanceStage(50*10);
		}
		else if(stage == 3)
		{	
			// brownify
			brownifyColor();
			// eventually, die & re-seed, anything else?...
			advanceStage(50*10); 
		}
		
		
	}
	
	public void advanceStage(int ticksRequired)
	{
		stageTimer++;
		if(stageTimer > ticksRequired)
		{
			stage++;
			stageTimer = 0;
			if(stage >= 4) rebirth();
		}
	}
	
	public void rebirth()
	{
		stage = 0;
		size = 1;
		randomizeColor();
	}
	
	public void die()
	{
		//
	}
	
	public void draw(Paint p, Canvas c)
	{
		if(stage == 0)
		{
			p.setColor(Color.rgb(80, 40, 0));
			c.drawCircle(x, y, 3, p);
		}
		else
		{
			p.setColor(color);
			for(int i = 0; i<blades.size(); i++)
			{
				//c.draw
				//c.drawLine(x, y, x+((int)(lawn.getWind()+blades.get(i).bladeOffset)), (int) (y-size), p);
				drawTriangle(new Point(x-thickness,y),new Point(x+thickness,y), new Point(x+((int)(lawn.getWind()+blades.get(i).bladeOffset)), (int) (y-size)),color,c);
			}
		}
	}
	
	public void drawTriangle(Point p1, Point p2, Point p3, int clr, Canvas c)
	{
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

		paint.setStrokeWidth(1);
		paint.setColor(clr);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		paint.setAntiAlias(true);

		Path path = new Path();
		path.setFillType(Path.FillType.EVEN_ODD);
		path.moveTo(p1.x,p1.y);
		path.lineTo(p2.x,p2.y);
		path.lineTo(p3.x,p3.y);
		path.close();

		c.drawPath(path, paint);
	}
	
}
