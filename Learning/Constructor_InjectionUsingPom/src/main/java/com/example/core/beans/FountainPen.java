package com.example.core.beans;

import com.example.interfaces.*;

public class FountainPen implements Pen{
	
	private Ink ink;
	
	public FountainPen(Ink ink) {
		this.ink=ink;
	}

	@Override
	public void write() {
		System.out.println("Hi I write with "+ink.getColor()+" ink of "+ink.getbrandName()+" Brand");
		
	}

}
