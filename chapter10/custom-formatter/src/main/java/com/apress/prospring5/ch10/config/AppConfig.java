package com.apress.prospring5.ch10.config;

import java.net.URL;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.apress.prospring5.ch10.ApplicationConversionServiceFactoryBean;
import com.apress.prospring5.ch10.Singer;

@Configuration
@ComponentScan(basePackages = "com.apress.prospring5.ch10")
public class AppConfig {
	
	@Autowired
	ApplicationConversionServiceFactoryBean conversionService;
	
	@Bean
	public Singer john() throws Exception{
		Singer singer = new Singer();
		singer.setFirstName("John");
		singer.setLastName("Mayer");
		singer.setPersonalSite(new URL("http://johnMayer.com/"));
		singer.setBirthDate(
				conversionService.getDateTimeFormatter().parse("1977-10-16", Locale.ENGLISH));
		return singer;
	}

}
