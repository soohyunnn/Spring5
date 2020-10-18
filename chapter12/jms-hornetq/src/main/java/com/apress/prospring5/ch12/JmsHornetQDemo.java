package com.apress.prospring5.ch12;

import com.apress.prospring5.ch12.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class JmsHornetQDemo {
    public static void main(String... args) throws Exception{
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        MessageSender messageSender = ctx.getBean("messageSender", MessageSender.class);
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
        System.out.println("테스트1");
        for(int i=0; i < 10; ++i) {
        	
            messageSender.sendMessage("테스트 메시지: " + i);
        }
        System.out.println("테스트2");
        System.in.read();
        ctx.close();
    }
}
