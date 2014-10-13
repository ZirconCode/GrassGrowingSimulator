package com.example.grassgrowingsimulator;

import java.util.Vector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Grass {

	// grass blades
	private int x,y;
	private double size;
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
		
		maxBlades = 4;
		size = 5;
		maxSize = 45;
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
	
	public void tick()
	{
		if(Math.random()<growthChance) size += growthAmount;
		
		if(size > maxSize) size = maxSize;
	}
	
	public void draw(Paint p, Canvas c)
	{
		p.setColor(color);
		for(int i = 0; i<blades.size(); i++)
		{
			c.drawLine(x, y, x+((int)(lawn.getWind()+blades.get(i).bladeOffset)), (int) (y-size), p);
		}
		
	}
	
}
