package com.apress.prospring5.ch11;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.apress.prospring5.ch11.config.AppConfig;

public class ScheduleTaskAnnotationDemo {
	public static void main(String... args) throws IOException {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		System.in.read();
		ctx.close();
	}

}
