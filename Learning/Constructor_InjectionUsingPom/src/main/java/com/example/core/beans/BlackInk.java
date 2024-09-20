package com.example.core.beans;

import com.example.interfaces.Ink;

public class BlackInk implements Ink{

	@Override
	public String getColor() {
		return "Black";
	}
	@Override
	public String getbrandName() {
		return "Hero";
	}

}
