package com.apress.prospring5.ch12;

import org.springframework.stereotype.Component;

@Component
public class WeatherServiceImpl implements WeatherService{

	@Override
	public String getForcecast(String stateCode) {
		if("FL".equals(stateCode)) {
			return "더움";
		}else if("MA".equals(stateCode)) {
			return "추움";
		}
		return "지금은 이용할 수 없음";
	}

}