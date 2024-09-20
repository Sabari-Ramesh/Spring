package com.ex.Beans;

import com.ex.Interfaces.Ink;

public class BlackInk implements Ink{

	@Override
	public String getColor() {
		return "Black color";
	}

	@Override
	public String getbrandName() {
		return "Hero";
	}

}
