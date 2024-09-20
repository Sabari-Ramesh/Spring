package com.ex.core.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ex.Interface.Ink;
import com.ex.Interface.Pen;

@Component
public class FountainPen implements Pen {
	
	private Ink ink;

	@Autowired
	public FountainPen(Ink ink) {
		this.ink = ink;
	}

	@Override
	public void write() {
		System.out.println("Hi I am Writing With " + ink.getColor() + " And brand " + ink.getBrandName());
	}
}
