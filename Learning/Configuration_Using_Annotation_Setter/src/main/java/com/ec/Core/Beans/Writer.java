package com.ec.Core.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ex.Interface.Pen;

@Component
public class Writer {
	private Pen pen;

	// Getter AND Setter

	// Setter Method is optional
	public Pen getPen() {
		return pen;
	}

	@Autowired
	public void setPen(Pen pen) {
		this.pen = pen;
	}

	public void write() {
		pen.write();
	}
}
