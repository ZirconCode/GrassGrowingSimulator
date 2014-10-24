package com.zirconcode.grassgrowingsimulator;

import java.util.Vector;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Lawn {

	private Vector<Grass> lawn;
	
	private double wind;
	private double windChange;
	private double maxWind;
	
	// water grid
	// wind grid
	
	
	public Lawn(int width, int height)
	{
		lawn = new Vector<Grass>();
		//for(int i = 0; i<5000; i++) 
		//{
			//Grass g = new Grass((int)(Math.random()*width),(int)(Math.random()*height),this);
			//lawn.add(g);
		//}
		
		maxWind = 5; // TODO ...
	}
	
	public void plant(int x, int y)
	{
		Grass g = new Grass(x,y,this);
		lawn.add(g);
	}
	
	public void tick()
	{
		for(int i = 0; i<lawn.size(); i++)
		{
			lawn.get(i).tick();
		}
		
		// wind
		//windChange += (Math.random()*maxWind*0.1)-(maxWind*0.2);
		//if(windChange > maxWind*1) windChange = maxWind *2;
		//if(windChange < -maxWind*1) windChange = -maxWind *2;
		windChange = (Math.random()*maxWind*0.05)-(maxWind*0.1);
		
		wind += windChange;
		if(wind > maxWind) wind = maxWind;
		if(wind < -maxWind) wind = -maxWind;
		//wind = Math.random()*maxWind*2-(maxWind*1);
	}
	
	public void draw(Paint p, Canvas canvas) {
		for(int i = 0; i<lawn.size(); i++)
		{
			lawn.get(i).draw(p, canvas);
		}
	}
	
	public double getWind()
	{
		return wind;
	}

}
