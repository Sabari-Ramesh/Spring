package com.example.core.beans;

import com.example.interfaces.*;

public class Writer {

	private Pen pen;
		
	public  Writer(Pen pen) {
		this.pen=pen;
	}
	
	public void write() {
		pen.write();
	}
	
}
