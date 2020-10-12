package com.apress.prospring5.ch10.obj;

public enum Gender {
	MALE("M"), FRMALE("F");
	private String code;
	
	Gender(String code){
		this.code = code;
	}
	
	@Override
	public String toString() {
		return this.code;
	}
	
}