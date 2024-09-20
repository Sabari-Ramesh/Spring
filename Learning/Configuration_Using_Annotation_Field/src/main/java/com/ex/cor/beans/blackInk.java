package com.ex.cor.beans;

import org.springframework.stereotype.Component;

import com.ex.Interface.Ink;

@Component
public class blackInk implements Ink{

	@Override
	public String Color() {
		return "Red";
	}

	@Override
	public String GetBrand() {
		return "Fountain";
	}

}
