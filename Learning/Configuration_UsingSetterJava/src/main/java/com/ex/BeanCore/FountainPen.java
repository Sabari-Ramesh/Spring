package com.ex.BeanCore;

import com.ex.Interface.Ink;
import com.ex.Interface.Pen;

public class FountainPen implements Pen {

	private Ink ink;

	// Getter AND Setter

	public Ink getInk() {
		return ink;
	}

	public void setInk(Ink ink) {
		this.ink = ink;
	}

	@Override
	public void write() {
		System.out.println("Hi I am writring " + ink.getBrandName() + " color " + ink.getColor());
	}

}
