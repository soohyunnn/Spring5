package com.example.demo.activemqApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.apress.prospring5.ch9.Application;
import com.apress.prospring5.ch9.entities.Singer;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Run implements ApplicationRunner {
	
	private static Logger logger = LoggerFactory.getLogger(Run.class);
	
    @Autowired
    private OrderSender orderSender;
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Run.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	logger.info("Spring Boot Embedded ActiveMQ Configuration Example");

        for (int i = 0; i < 5; i++){
            Order myMessage = new Order(i + " - 메시지", new Date());
            
            Singer singer = new Singer();
    		singer.setId(1L);
    		singer.setFirstName("John");
    		singer.setLastName("Mayer");
    		singer.setBirthDate(new Date(
    				(new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
    		
            orderSender.send(singer);
            TimeUnit.SECONDS.sleep(3);
        }

        logger.info("Waiting for all ActiveMQ JMS Messages to be consumed");
        TimeUnit.SECONDS.sleep(3 );
        System.exit(-1);
    }
}