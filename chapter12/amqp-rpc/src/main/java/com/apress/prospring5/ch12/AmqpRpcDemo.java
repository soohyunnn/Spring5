package com.apress.prospring5.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AmqpRpcDemo {
	private static Logger logger = LoggerFactory.getLogger(AmqpRpcDemo.class);
	
	public static void main(String... args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/amqp-rpc-app-context.xml");
		ctx.refresh();
		
		WeatherService weatherService = ctx.getBean(WeatherService.class);
		
		logger.info("FL의 기상 예측: " + weatherService.getForcecast("FL"));
		logger.info("MA의 기상 예측: " + weatherService.getForcecast("MA"));
		logger.info("CA의 기상 예측: " + weatherService.getForcecast("CA"));
		
		ctx.close();
	}
	
}