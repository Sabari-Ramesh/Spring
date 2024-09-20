package com.ex.core.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ex.Interface.Pen;

@Component
public class Writer {
    
	
	private Pen pen;
	
	@Autowired
	public void write() {
		pen.write();
	}

	public Pen getPen() {
		return pen;
	}

	public Writer(Pen pen) {
		this.pen=pen;
	}
}
