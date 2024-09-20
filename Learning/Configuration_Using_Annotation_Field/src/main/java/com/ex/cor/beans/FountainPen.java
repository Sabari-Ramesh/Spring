package com.ex.cor.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ex.Interface.Ink;
import com.ex.Interface.Pen;

@Component
public class FountainPen implements Pen{

	@Autowired
	private Ink ink=null;
	
	@Override
	public void write() {
		System.out.println("Hi I am Writing With " + ink.Color() + " And brand " + ink.GetBrand());
	}

}
