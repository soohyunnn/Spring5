package com.apress.prospring5.ch10;

import org.springframework.core.convert.converter.Converter;

public class SingerToAnotherSingerConverter implements Converter<Singer, AnotherSinger>{

	@Override
	public AnotherSinger convert(Singer singer) {
		AnotherSinger anotherSinger = new AnotherSinger();
		anotherSinger.setFirstName(singer.getFirstName());
		anotherSinger.setLastName(singer.getLastName());
		anotherSinger.setBirthDate(singer.getBirthDate());
		anotherSinger.setPersonalSite(singer.getPersonalSite());
		
		return anotherSinger;
	}

}
