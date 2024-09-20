package com.ex.cor.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ex.Interface.Pen;

@Component
public class Writer {

	@Autowired
	private Pen pen=null;
	
	public void write() {
		pen.write();
	}
}
