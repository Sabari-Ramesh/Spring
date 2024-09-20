package com.ex.Beans;

import com.ex.Interfaces.Pen;

public class Writer {
	
	private Pen pen;

	//GETTER AND SETTER
	
	public Pen getPen() {
		return pen;
	}

	public void setPen(Pen pen) {
		this.pen = pen;
	}
	
	public void write() {
		pen.write();
	}

}
