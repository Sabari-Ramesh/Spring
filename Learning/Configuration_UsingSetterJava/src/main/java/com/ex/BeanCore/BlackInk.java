package com.ex.BeanCore;

import com.ex.Interface.Ink;

public class BlackInk implements Ink{

	@Override
	public String getColor() {
		return "Black Color";
	}

	@Override
	public String getBrandName() {
		return "Parker";
	}
}
