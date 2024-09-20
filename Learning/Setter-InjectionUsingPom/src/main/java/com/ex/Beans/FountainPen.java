package com.ex.Beans;

import com.ex.Interfaces.Ink;
import com.ex.Interfaces.Pen;

public class FountainPen implements Pen{
	
	private Ink ink;
	
	//GETTER AND SETTER
	
	public Ink getInk() {
		return ink;
	}
	public void setInk(Ink ink) {
		this.ink = ink;
	}
	
	@Override
	public void write() {
		System.out.println("Hi I write with "+ink.getColor()+" ink of "+ink.getbrandName()+" Brand");
	}
}
