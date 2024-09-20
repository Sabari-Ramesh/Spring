package com.ex.core.Beans;

import org.springframework.stereotype.Component;

import com.ex.Interface.Ink;

@Component
public class BlackInk implements Ink{

	@Override
	public String getColor() {
		return "Black";
	}

	@Override
	public String getBrandName() {
		return "Hero";
	}

}
