package com.ex.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ex.Interface.Ink;
import com.ex.Interface.Pen;
import com.ex.core.Beans.BlackInk;
import com.ex.core.Beans.FountainPen;
import com.ex.core.Beans.Writer;

@Configuration

public class SpringConfig {
	
	@Bean
	public Writer writer(Pen pen) {
		return new Writer(pen);
	}
	
	@Bean
	public Pen fountainPen(Ink ink) {
		return new FountainPen(ink);
	}
	@Bean
	public Ink BlackInk() {
		return new BlackInk();
	}
}
