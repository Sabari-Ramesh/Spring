package com.ec.Core.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ex.Interface.Ink;
import com.ex.Interface.Pen;

@Component
public class FountainPen implements Pen {

	private Ink ink;

	@Override
	public void write() {
		System.out.println("Hi dude I am Write" + ink.getColor() + " and brand " + ink.getBrand());
	}

	// Getter AND Setter
	
	@Autowired
	public void setInk(Ink ink) {
		this.ink = ink;
	}

	// This Method is Optional
	public Ink getInk() {
		return ink;
	}
}
