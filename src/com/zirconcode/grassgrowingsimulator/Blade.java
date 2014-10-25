package com.zirconcode.grassgrowingsimulator;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Blade {
	// blade of grass
	
	//public int x,y;
	//public double size;
	@Element
	public double bladeOffset;
	
	public Blade()
	{
		// no-arg for serialization
	}
	
	public Blade(double bladeOffset)
	{
		this.bladeOffset = bladeOffset;
	}
}
