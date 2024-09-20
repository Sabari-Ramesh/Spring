package com.ex.core.Beans;

import com.ex.Interface.Ink;
import com.ex.Interface.Pen;

public class FountainPen implements Pen{

	private Ink ink;
	
	public FountainPen(Ink ink) {
		this.ink=ink;
	}

	@Override
	public void write() {
		System.out.println("Hi I am Writing With "+ink.getColor()+" And brand "+ink.getBrandName());
	}
}
