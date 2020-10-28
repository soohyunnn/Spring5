package com.apress.prospring5.ch14.javascript;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaScriptTest {
	
	private static Logger logger = LoggerFactory.getLogger(JavaScriptTest.class);
	
	public static void main(String... args) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
		try {
			jsEngine.eval("print('Hello JavaScript in Java')");	
		} catch (ScriptException ex) {
			logger.error("자바스크립트 표현식을 평가할 수 없습니다!", ex);
		}
	}

}
