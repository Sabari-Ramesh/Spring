package com.ex.Configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ex.BeanCore.BlackInk;
import com.ex.BeanCore.FountainPen;
import com.ex.BeanCore.Writer;
import com.ex.Interface.Ink;
import com.ex.Interface.Pen;

@Configuration

public class SpringConfig {

	@Bean
	public Writer writer(Pen pen) {
		Writer writer=new Writer();
		writer.setPen(pen);
		return writer;
	}
	
	@Bean
	public Pen FountainPen(Ink ink) {
		FountainPen fountainPen=new FountainPen();
		fountainPen.setInk(ink);
		return fountainPen;
	}
	
	@Bean 
	public Ink blackInk() {
		return new BlackInk();
	}
	
}
