package com.ec.Core.Beans;

import org.springframework.stereotype.Component;

import com.ex.Interface.Ink;

@Component
public class BlackInk implements Ink{

	@Override
	public String getColor() {
		return "Brown";
	}

	@Override
	public String getBrand() {
		return "Parker";
	}

}
