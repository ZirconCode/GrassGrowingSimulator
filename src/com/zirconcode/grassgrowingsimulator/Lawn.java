package com.zirconcode.grassgrowingsimulator;

import java.util.Iterator;
import java.util.Vector;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import android.graphics.Canvas;
import android.graphics.Paint;

@Root
public class Lawn {

	@ElementList
	private Vector<Grass> lawn;
	
	private double wind;
	private double windChange;
	private double maxWind;
	
	private static final int cuttingRadius = 50; // TODO
	private static final int wateringRadius = 100;
	private static final int wateringMultiplier = 14;
	// water grid
	// wind grid
	
	public Lawn()
	{
		// no-arg for serialization
	}
	
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
		Grass g = new Grass(x,y);
		lawn.add(g);
	}
	
	public void cut(int x, int y)
	{
		// TODO remove reduncancy
		// TODO visual indicators...
		for (Iterator<Grass> it = lawn.iterator(); it.hasNext();) 
		{
		    Grass g = it.next();
		    int dist = (int) Math.sqrt(Math.pow(g.getX()-x,2)+Math.pow(g.getY()-y,2));
		    if(dist < cuttingRadius) it.remove(); 	
		}
	}
	
	public void water(int x, int y)
	{
		// TODO ditto ^
		for(int i = 0; i<lawn.size(); i++)
		{
			int dist = (int) Math.sqrt(Math.pow(lawn.get(i).getX()-x,2)+Math.pow(lawn.get(i).getY()-y,2));
			if(dist < wateringRadius)
			{
				for(int j = 0; j<wateringMultiplier; j++) 
					lawn.get(i).tick();
			}
			
		}
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
