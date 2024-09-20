package com.ex.BeanCore;

import com.ex.Interface.Pen;

public class Writer {
	private Pen pen;

	// Getter and Setter

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
